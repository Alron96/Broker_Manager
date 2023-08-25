package com.broker_manager.service.bankAccountTransaction;

import com.broker_manager.model.*;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Operation;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.repository.BankAccountTransactionRepository;
import com.broker_manager.repository.StockRepository;
import com.broker_manager.service.stockInBankAccount.StockInBankAccountService;
import com.broker_manager.util.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BrokerBankAccountTransactionService {
    private final BankAccountTransactionRepository bankAccountTransactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final StockInBankAccountService stockInBankAccountService;
    private final StockRepository stockRepository;

    public BrokerBankAccountTransactionService(BankAccountTransactionRepository bankAccountTransactionRepository,
                                               BankAccountRepository bankAccountRepository, StockInBankAccountService stockInBankAccountService, StockRepository stockRepository) {
        this.bankAccountTransactionRepository = bankAccountTransactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.stockInBankAccountService = stockInBankAccountService;
        this.stockRepository = stockRepository;
    }

    public List<BankAccountTransaction> getAllBankAccountTransactionsByUser(User user) {
        return bankAccountTransactionRepository.findAllBySenderBrokerIdOrderByWhenDoneDesc(user);
    }

    public BankAccountTransaction getBankAccountTransaction(Integer id) {
        return bankAccountTransactionRepository.findById(id).orElseThrow(() -> new NotFoundException("Bank account transaction does not exist"));
    }

    public BankAccountTransaction createBankAccountTransaction(Stock stock, Integer amountStock, Operation operation, User user) {
        BankAccountTransaction bankAccountTransaction = new BankAccountTransaction();
        BankAccount bankAccountSender = null;
        BankAccount bankAccountRecipient = null;
        double stockPrice = 0;
        double transferAmount = 0;
        boolean containStock;

        switch (operation) {
            case BUY -> {
                bankAccountSender = bankAccountRepository.findByDepartment(user.getDepartment())
                        .orElseThrow(() -> new UnsupportedOperationException("Bank account for transaction not found"));
                bankAccountRecipient = bankAccountRepository.findByDepartment(Department.EXCHANGE)
                        .orElseThrow(() -> new UnsupportedOperationException("Bank account for transaction not found"));

                stockPrice = stock.getPriceBuy();
                transferAmount = amountStock * stockPrice;
                containStock = bankAccountSender.getStockInBankAccounts().stream()
                        .anyMatch(s -> s.getStock().equals(stock));

                if (bankAccountSender.getBalance() < transferAmount) {
                    throw new UnsupportedOperationException("Not enough money in the bank account");
                }

                buyStocks(bankAccountSender, stock, amountStock, containStock);
                bankAccountRepository.save(bankAccountSender);
            }
            case SELL -> {
                bankAccountSender = bankAccountRepository.findByDepartment(Department.EXCHANGE)
                        .orElseThrow(() -> new UnsupportedOperationException("Bank account for transaction not found"));
                bankAccountRecipient = bankAccountRepository.findByDepartment(user.getDepartment())
                        .orElseThrow(() -> new UnsupportedOperationException("Bank account for transaction not found"));

                stockPrice = stock.getPriceSell();
                transferAmount = amountStock * stockPrice;
                containStock = bankAccountSender.getStockInBankAccounts().stream()
                        .anyMatch(s -> s.getStock().equals(stock));

                if (bankAccountSender.getBalance() < transferAmount || !containStock) {
                    throw new UnsupportedOperationException("Not enough money or stocks for trade");
                }

                sellStocks(bankAccountRecipient, stock, amountStock);
                bankAccountRepository.save(bankAccountRecipient);
            }
        }

        bankAccountTransaction.setSenderBrokerId(user);
        bankAccountTransaction.setStock(stock);
        bankAccountTransaction.setOperation(operation);
        bankAccountTransaction.setAmountStock(amountStock);
        bankAccountTransaction.setWhenDone(LocalDateTime.now());
        bankAccountTransaction.setSenderBankAccountId(bankAccountSender);
        bankAccountTransaction.setRecipientBankAccountId(bankAccountRecipient);
        bankAccountTransaction.setPriceStock(stockPrice);
        bankAccountTransaction.setTransferAmount(transferAmount);

        return bankAccountTransactionRepository.save(bankAccountTransaction);
    }

    private void buyStocks(BankAccount departmentBankAccount, Stock stock, Integer amountStock, boolean containStock) {
        if (containStock) {
            departmentBankAccount.getStockInBankAccounts().stream()
                    .filter(s -> s.getStock().equals(stock))
                    .forEach(s -> s.setAmount(s.getAmount() + amountStock));

            stockInBankAccountService.update(departmentBankAccount.getStockInBankAccounts().stream()
                    .filter(s -> s.getStock().equals(stock))
                    .findFirst().orElseThrow(() -> new UnsupportedOperationException("Can not save stock")));

        } else {
            StockInBankAccount newStockInBankAccount =
                    stockInBankAccountService.create(new StockInBankAccount(null, amountStock, stock, departmentBankAccount));
            departmentBankAccount.getStockInBankAccounts().add(newStockInBankAccount);
        }

        departmentBankAccount.setBalance(departmentBankAccount.getBalance() - amountStock * stock.getPriceBuy());
    }

    private void sellStocks(BankAccount departmentBankAccount, Stock stock, Integer amountStock) {
        StockInBankAccount updated = departmentBankAccount.getStockInBankAccounts().stream()
                .filter(s -> s.getStock().equals(stock))
                .findFirst().orElseThrow(() -> new NotFoundException("Stock in bank account not found"));

        if (updated.getAmount() > amountStock) {
            departmentBankAccount.getStockInBankAccounts().stream()
                    .filter(s -> s.getStock().equals(stock))
                    .forEach(s -> s.setAmount(s.getAmount() - amountStock));

            stockInBankAccountService.update(departmentBankAccount.getStockInBankAccounts().stream()
                    .filter(s -> s.getStock().equals(stock))
                    .findFirst().orElseThrow(() -> new UnsupportedOperationException("Can not save stock")));

        } else if (updated.getAmount() == amountStock) {
            departmentBankAccount.getStockInBankAccounts().removeIf(s -> s.getStock().equals(stock));

            stockInBankAccountService.delete(departmentBankAccount.getStockInBankAccounts().stream()
                    .filter(s -> s.getStock().equals(stock))
                    .findFirst().orElseThrow(() -> new UnsupportedOperationException("Can not save stock")));

        } else {
            throw new UnsupportedOperationException("Not enough stock in this bank account");
        }

        departmentBankAccount.setBalance(departmentBankAccount.getBalance() + amountStock * stock.getPriceSell());
    }
}
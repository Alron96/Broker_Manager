package com.broker_manager.service.bankAccountTransaction;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.Stock;
import com.broker_manager.model.StockInBankAccount;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Operation;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.repository.BankAccountTransactionRepository;
import com.broker_manager.repository.StockInBankAccountRepository;
import com.broker_manager.util.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChiefBrokerBankAccountTransactionService {

    private final BankAccountTransactionRepository transactionRepository;
    private final BankAccountRepository accountRepository;
    private final StockInBankAccountRepository stockInBankAccountRepository;

    public ChiefBrokerBankAccountTransactionService(BankAccountTransactionRepository transactionRepository,
                                                    BankAccountRepository accountRepository,
                                                    StockInBankAccountRepository stockInBankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.stockInBankAccountRepository = stockInBankAccountRepository;
    }

    public List<BankAccountTransaction> getAllBankAccountTransactionsByDepartment(Department department) {
        return transactionRepository.findByDepartmentOrderByExecutionDateDesc(department);
    }

    public BankAccountTransaction getBankAccountTransaction(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BankAccountTransaction not found with id: " + id));
    }

    public BankAccountTransaction createBankAccountTransaction(Stock stock, Integer amount, Operation operation, Department department) {
        BankAccount senderAccount = accountRepository.findByDepartment(department);
        BankAccount recipientAccount = accountRepository.findByName("Exchange"); // заглушка для банковского счета биржи

        BankAccountTransaction transaction = new BankAccountTransaction();
        transaction.setExecutionDate(LocalDateTime.now());
        transaction.setSenderBankAccount(senderAccount);
        transaction.setRecipientBankAccount(recipientAccount);
        transaction.setStock(stock);
        transaction.setAmount(Double.valueOf(amount));

        if (operation == Operation.BUY) {
            StockInBankAccount stockInAccount = new StockInBankAccount();
            stockInAccount.setStock(stock);
            stockInAccount.setBankAccount(senderAccount);
            stockInAccount.setAmount(amount);
            stockInBankAccountRepository.save(stockInAccount);

            transaction.setStockInBankAccount(stockInAccount);
        } else if (operation == Operation.SELL) {
            StockInBankAccount stockInAccount = stockInBankAccountRepository.findByStockAndBankAccount(stock, senderAccount);
            stockInBankAccountRepository.delete(stockInAccount);
        }

        return transactionRepository.save(transaction);
    }
}
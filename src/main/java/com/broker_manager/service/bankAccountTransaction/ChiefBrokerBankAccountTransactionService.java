//package com.broker_manager.service.bankAccountTransaction;
//
//import com.broker_manager.model.BankAccount;
//import com.broker_manager.model.BankAccountTransaction;
//import com.broker_manager.model.Stock;
//import com.broker_manager.model.StockInBankAccount;
//import com.broker_manager.model.enums.Department;
//import com.broker_manager.model.enums.Operation;
//import com.broker_manager.repository.BankAccountRepository;
//import com.broker_manager.repository.BankAccountTransactionRepository;
//import com.broker_manager.repository.StockInBankAccountRepository;
//import com.broker_manager.repository.StockRepository;
//import com.broker_manager.util.error.InsufficientStocksException;
//import com.broker_manager.util.error.NotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class ChiefBrokerBankAccountTransactionService {
//
//    private final BankAccountTransactionRepository transactionRepository;
//    private final BankAccountRepository accountRepository;
//    private final StockInBankAccountRepository stockInBankAccountRepository;
//    private final StockRepository stockRepository;
//
//    public ChiefBrokerBankAccountTransactionService(BankAccountTransactionRepository transactionRepository,
//                                                    BankAccountRepository accountRepository,
//                                                    StockInBankAccountRepository stockInBankAccountRepository, StockRepository stockRepository) {
//        this.transactionRepository = transactionRepository;
//        this.accountRepository = accountRepository;
//        this.stockInBankAccountRepository = stockInBankAccountRepository;
//        this.stockRepository = stockRepository;
//    }
//
//    public List<BankAccountTransaction> getAllBankAccountTransactionsByDepartment(Department department) {
//        return transactionRepository.findByDepartmentOrderByExecutionDateDesc(department);
//    }
//
//    public BankAccountTransaction getBankAccountTransaction(Integer id) {
//        return transactionRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("BankAccountTransaction not found with id: " + id));
//    }
//
//    public BankAccountTransaction createBankAccountTransaction(Stock stock, Integer amount, Operation operation, Department department) throws InsufficientStocksException {
//        BankAccount senderAccount = accountRepository.findByDepartment(department);
//        BankAccount recipientAccount = accountRepository.findByName("Exchange");
//
//        BankAccountTransaction transaction = new BankAccountTransaction();
//        transaction.setWhenDone(LocalDateTime.now());
//        transaction.setSenderBankAccountId(senderAccount);
//        transaction.setRecipientBankAccountId(recipientAccount);
//        transaction.setStock(stock);
//        transaction.setAmountStock(amount);
//
//        Stock stockFromDatabase = stockRepository.findById(stock.getId()).orElseThrow(() -> new NotFoundException(stock.getId()));
//        double purchasePrice = stockFromDatabase.getPriceBuy();
//
//        if (operation == Operation.BUY) {
//
//            StockInBankAccount stockInAccount = stockInBankAccountRepository.findByStockAndBankAccount(stockFromDatabase, senderAccount);
//            if (stockInAccount.getAmount() < amount) {
//                throw new InsufficientStocksException(stockFromDatabase.getId(), stockInAccount.getAmount(), amount);
//            }
//            stockInAccount.setAmount(stockInAccount.getAmount() - amount);
//            stockInBankAccountRepository.save(stockInAccount);
//
//            transaction.setStock(stockInAccount.getStock());
//        } else if (operation == Operation.SELL) {
//            StockInBankAccount stockInAccount = new StockInBankAccount();
//            stockInAccount.setStock(stockFromDatabase);
//            stockInAccount.setBankAccount(senderAccount);
//            stockInAccount.setAmount(amount);
//            stockInBankAccountRepository.save(stockInAccount);
//            transaction.setStock(stockInAccount.getStock());
//        }
//
//        double totalPurchasePrice = purchasePrice * amount;
//        recipientAccount.setBalance(totalPurchasePrice);
//
//        return transactionRepository.save(transaction);
//    }
//}
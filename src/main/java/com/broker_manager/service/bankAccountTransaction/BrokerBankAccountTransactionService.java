package com.broker_manager.service.bankAccountTransaction;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.Stock;
import com.broker_manager.model.enums.Operation;
import com.broker_manager.repository.BankAccountTransactionRepository;
import com.broker_manager.repository.StockRepository;
import com.broker_manager.web.AuthorizedUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerBankAccountTransactionService {
    private final BankAccountTransactionRepository bankAccountTransactionRepository;
    private final StockRepository stockRepository;

    public BrokerBankAccountTransactionService(BankAccountTransactionRepository bankAccountTransactionRepository,
                                               StockRepository stockRepository) {
        this.bankAccountTransactionRepository = bankAccountTransactionRepository;
        this.stockRepository = stockRepository;
    }

    public List<BankAccountTransaction> getAllBankAccountTransactionsByUser(AuthorizedUser authUser) {
        return bankAccountTransactionRepository.findAllByUserOrderByExecutionDateDesc(authUser.getUser());
    }

    public BankAccountTransaction getBankAccountTransaction(Integer id) {
        Optional<BankAccountTransaction> optionalBankAccountTransaction = bankAccountTransactionRepository.findById(id);
        return optionalBankAccountTransaction.orElse(null);
    }

    public BankAccountTransaction createBankAccountTransaction(Stock stock, Integer amount, Operation operation, AuthorizedUser authUser) {
        BankAccountTransaction bankAccountTransaction = new BankAccountTransaction();
        bankAccountTransaction.setWhenDone(LocalDateTime.now());
        bankAccountTransaction.setSenderBankAccountId(authUser.getBankAccount());

        if (operation == Operation.BUY) {

            BankAccount recipientBankAccount = determineRecipientBankAccountForBuy(stock);
            bankAccountTransaction.getRecipientBankAccountId();
        } else if (operation == Operation.SELL) {

            BankAccount recipientBankAccount = determineRecipientBankAccountForSell(stock);
            bankAccountTransaction.setRecipientBankAccountId(recipientBankAccount);
        }

        return bankAccountTransactionRepository.save(bankAccountTransaction);
    }

    private BankAccount determineRecipientBankAccountForBuy(Stock stock) {
        // Логика для определения получателя при покупке акций
        // Например, можно выбрать основной счет или другой счет пользователя
        // в зависимости от определенных правил бизнес-логики
        return bankAccountTransactionRepository.findAllByUserOrderByPriceBuyDesc(stock);
    }

    private BankAccount determineRecipientBankAccountForSell(Stock stock) {
        // Логика для определения получателя при продаже акций
        // Например, можно выбрать основной счет или другой счет пользователя
        // в зависимости от определенных правил бизнес-логики
        return bankAccountTransactionRepository.findAllByUserOrderByPriceSellDesc(stock);
    }



}

package com.broker_manager.service.bankAccountTransaction;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.repository.BankAccountTransactionRepository;
import com.broker_manager.web.AuthorizedUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DirectorBankAccountTransactionService {

    private final BankAccountTransactionRepository bankAccountTransactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public DirectorBankAccountTransactionService(BankAccountTransactionRepository bankAccountTransactionRepository, BankAccountRepository bankAccountRepository) {
        this.bankAccountTransactionRepository = bankAccountTransactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccountTransaction> getAllBankAccountTransactions() {
        return bankAccountTransactionRepository.findAll(Sort.by(Sort.Direction.DESC, "executionDate"));
    }

    public BankAccountTransaction getBankAccountTransaction(Integer id) {
        return bankAccountTransactionRepository.findById(id).orElse(null);
    }

    public BankAccountTransaction createBankAccountTransaction(BankAccount senderBankAccount, BankAccount recipientBankAccount, int amount, AuthorizedUser authUser) {
        BankAccountTransaction bankAccountTransaction = new BankAccountTransaction();
        bankAccountTransaction.setSenderBankAccountId(senderBankAccount);
        bankAccountTransaction.setRecipientBankAccountId(recipientBankAccount);
        bankAccountTransaction.setAmountStock(amount);
        bankAccountTransaction.setId(authUser.getUser().getId());
        bankAccountTransaction.setWhenDone(LocalDateTime.now());

        senderBankAccount.setBalance(senderBankAccount.getBalance() - amount);
        recipientBankAccount.setBalance(recipientBankAccount.getBalance() + amount);

        bankAccountRepository.save(senderBankAccount);
        bankAccountRepository.save(recipientBankAccount);

        return bankAccountTransactionRepository.save(bankAccountTransaction);
    }
}
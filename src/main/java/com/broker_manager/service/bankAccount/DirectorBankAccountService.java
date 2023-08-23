package com.broker_manager.service.bankAccount;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.StockInBankAccount;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.web.AuthorizedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorBankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public DirectorBankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> allBankAccounts = bankAccountRepository.findAll();
        List<BankAccount> bankAccountsWithoutStock = new ArrayList<>();

        for (BankAccount bankAccount : allBankAccounts) {
            if (bankAccount.getStockInBankAccount() == null) {
                bankAccountsWithoutStock.add(bankAccount);
            }
        }

        return bankAccountsWithoutStock;
    }

    public BankAccount getBankAccount(Integer id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount != null) {
            if (bankAccount instanceof StockInBankAccount) {
                StockInBankAccount stockInBankAccount = (StockInBankAccount) bankAccount;
                return stockInBankAccount;

            } else if (bankAccount instanceof BankAccountTransaction) {
                BankAccountTransaction bankAccountTransaction = (BankAccountTransaction) bankAccount;
                return bankAccountTransaction;

            }
        }

        return bankAccount;
    }

    public BankAccount createBankAccount(BankAccount bankAccount, AuthorizedUser authUser) {
        if (authUser.getBankAccount() != null) {
            throw new IllegalArgumentException("User already has a bank account");
        }

        authUser.setBankAccount(bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Integer id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent() && bankAccount.get().getBalance() == 0 && bankAccount.get().getStockInBankAccount() != null && !bankAccount.get().getStockInBankAccount().isHasStockInBankAccount()) {
            bankAccountRepository.deleteById(id);
        }
    }
}

package com.broker_manager.service.bankAccount;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.StockInBankAccount;
import com.broker_manager.model.enums.Type;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.repository.BankAccountTransactionRepository;
import com.broker_manager.repository.StockInBankAccountRepository;
import com.broker_manager.web.AuthorizedUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorBankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final StockInBankAccountRepository stockInBankAccountRepository;
    private final BankAccountTransactionRepository bankAccountTransactionRepository;

    public DirectorBankAccountService(BankAccountRepository bankAccountRepository, StockInBankAccountRepository stockInBankAccountRepository, BankAccountTransactionRepository bankAccountTransactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.stockInBankAccountRepository = stockInBankAccountRepository;
        this.bankAccountTransactionRepository = bankAccountTransactionRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> allBankAccounts = bankAccountRepository.findAll();
        List<BankAccount> bankAccountsWithoutStock = new ArrayList<>();

        for (BankAccount bankAccount : allBankAccounts) {
            if (bankAccount.getStockInBankAccounts() == null) {
                bankAccountsWithoutStock.add(bankAccount);
            }
        }

        return bankAccountsWithoutStock;
    }

    @Transactional
    public BankAccount getBankAccount(Integer id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount != null) {
            bankAccount.setStockInBankAccounts(stockInBankAccountRepository.findByBankAccount(bankAccount));
            bankAccount.setStockInBankAccounts((List<StockInBankAccount>) bankAccountTransactionRepository.findByBankAccount(bankAccount));
        }

        return bankAccount;
    }


    public BankAccount createBankAccount(BankAccount bankAccount, AuthorizedUser authUser) {
        if (bankAccount.getType() == Type.DEPARTMENT) {
            // Проверяем, есть ли в БД счет для данного отдела
            if (bankAccountRepository.findByDepartment(authUser.getUser().getDepartment()) != null) {
                throw new IllegalArgumentException("Department already has a bank account");
            }
            authUser.getUser().setBankAccounts((List<BankAccount>) bankAccount);
        }
        else if (bankAccount.getType() == Type.PERSONAL) {
            // Проверяем, есть ли у пользователя личный счет
            if (authUser.getUser().getBankAccounts() != null) {
                throw new IllegalArgumentException("User already has a personal bank account");
            }
            authUser.getUser().setBankAccounts((List<BankAccount>) bankAccount);
        }

        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Integer id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent() && bankAccount.get().getBalance() == 0 && bankAccount.get().getStockInBankAccounts() == null) {
            bankAccount.get();
        }
    }
}

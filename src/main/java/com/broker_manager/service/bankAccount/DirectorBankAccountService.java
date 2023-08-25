package com.broker_manager.service.bankAccount;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.repository.BankAccountTransactionRepository;
import com.broker_manager.repository.StockInBankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectorBankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final StockInBankAccountRepository stockInBankAccountRepository;

    public DirectorBankAccountService(BankAccountRepository bankAccountRepository,
                                      StockInBankAccountRepository stockInBankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.stockInBankAccountRepository = stockInBankAccountRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> allBankAccounts = bankAccountRepository.findAll();
        for (BankAccount b : allBankAccounts) {
            b.setStockInBankAccounts(null);
        }
        return allBankAccounts;
    }

    @Transactional
    public BankAccount getBankAccount(Integer id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if (bankAccount != null) {
            bankAccount.setStockInBankAccounts(stockInBankAccountRepository.findByBankAccount(bankAccount));
        }
        return bankAccount;
    }


    public BankAccount createBankAccount(BankAccount bankAccount) {
        BankAccount bankAccountFromDB = bankAccountRepository.findByDepartmentAndType(bankAccount.getDepartment(), bankAccount.getType()).orElse(null);

        if (bankAccountFromDB == null) {
            bankAccountRepository.save(bankAccount);
        }
        if (bankAccountFromDB.getDepartment().equals(Department.COMPANY)) {
            throw new UnsupportedOperationException("Main bank account for company already exists");
        }
        if (bankAccountFromDB.getDepartment().equals(Department.ANALYTICAL) || bankAccountFromDB.getDepartment().equals(Department.CONSULTING)) {
            if (bankAccountFromDB.getType().equals(Type.DEPARTMENT)) {
                throw new UnsupportedOperationException("Department bank account for department already exists");
            }
            if (bankAccountFromDB.getType().equals(Type.PERSONAL) && bankAccountFromDB.getUsers().contains(bankAccount.getUsers().get(0))) {
                throw new UnsupportedOperationException("Personal bank account for user already exists");
            }
        }
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Integer id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new UnsupportedOperationException("Bank account not exist"));
        if (bankAccount.getBalance() == 0 && bankAccount.getStockInBankAccounts() == null) {
            bankAccountRepository.deleteById(id);
        }
    }
}

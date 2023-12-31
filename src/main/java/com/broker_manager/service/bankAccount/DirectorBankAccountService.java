package com.broker_manager.service.bankAccount;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.util.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorBankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public DirectorBankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> allBankAccounts = bankAccountRepository.findAll();

        return allBankAccounts.stream().filter(ba -> !(ba.getDepartment().equals(Department.EXCHANGE) && ba.getType().equals(Type.EXCHANGE))).toList();
    }

    @Transactional
    public BankAccount getBankAccount(Integer id) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountWithStocksById(id)
                .orElseThrow(() -> new NotFoundException("Bank account not found"));

        List<User> users = userRepository.findByBankAccounts(id);
        bankAccount.setUsers(users);

        return bankAccount;
    }


    public BankAccount createBankAccount(BankAccount bankAccount) {
        BankAccount bankAccountFromDB = bankAccountRepository.findByDepartmentAndType(bankAccount.getDepartment(), bankAccount.getType()).orElse(null);

        List<User> users = new ArrayList<>();

        for (User u : bankAccount.getUsers()) {
            users.add(userRepository.getReferenceById(u.getId()));
        }
        bankAccount.setUsers(users);

        if (bankAccountFromDB == null) {
            return bankAccountRepository.save(bankAccount);
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
        if (bankAccount.getBalance() == 0.0 && (bankAccount.getStockInBankAccounts() == null || bankAccount.getStockInBankAccounts().isEmpty())) {
            bankAccountRepository.deleteById(id);
        }
    }
}

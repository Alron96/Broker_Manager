package com.broker_manager.repository;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.Stock;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Integer> {
    List<BankAccountTransaction> findAllByUserOrderByExecutionDateDesc(User user);

    List<BankAccountTransaction> findByDepartmentOrderByExecutionDateDesc(Department department);

    Object findByBankAccount(BankAccount bankAccount);

    BankAccount findByUserAndPriceBuyDesc(Stock stock);

    BankAccount findByUserAndPriceSellDesc(Stock stock);
}

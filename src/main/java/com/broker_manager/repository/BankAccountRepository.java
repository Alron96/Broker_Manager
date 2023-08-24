package com.broker_manager.repository;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByDepartment(Department department);

    BankAccount findByName(String exchange);
}

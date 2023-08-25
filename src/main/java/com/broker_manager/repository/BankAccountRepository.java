package com.broker_manager.repository;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByDepartment(Department department);

    Optional<BankAccount> findByDepartmentAndType(Department department, Type type);

    BankAccount findByName(String exchange);
}

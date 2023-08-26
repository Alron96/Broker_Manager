package com.broker_manager.repository;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    Optional<BankAccount> findByDepartmentAndType(Department department, Type type);

    @Query("SELECT b FROM BankAccount b LEFT JOIN FETCH b.stockInBankAccounts WHERE b.id=:id")
    Optional<BankAccount> findBankAccountWithStocksById(@Param("id") Integer id);
}

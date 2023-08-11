package com.broker_manager.repository;

import com.broker_manager.model.BankAccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Integer> {
}

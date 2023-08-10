package com.broker_manager.repositories;

import com.broker_manager.models.BankAccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Long> {
}

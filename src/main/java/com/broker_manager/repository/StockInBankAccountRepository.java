package com.broker_manager.repository;

import com.broker_manager.model.StockInBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInBankAccountRepository extends JpaRepository<StockInBankAccount, Integer> {
}

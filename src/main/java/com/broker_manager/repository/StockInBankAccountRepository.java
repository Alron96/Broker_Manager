package com.broker_manager.repository;

import com.broker_manager.model.BankAccount;
import com.broker_manager.model.Stock;
import com.broker_manager.model.StockInBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockInBankAccountRepository extends JpaRepository<StockInBankAccount, Integer> {
    StockInBankAccount findByStockAndBankAccount(Stock stock, BankAccount senderAccount);

    Optional<StockInBankAccount> findByBankAccount(BankAccount bankAccount);
}

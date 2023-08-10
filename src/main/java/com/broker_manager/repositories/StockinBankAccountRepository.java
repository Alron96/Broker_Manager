package com.broker_manager.repositories;

import com.broker_manager.models.StockInBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockinBankAccountRepository extends JpaRepository<StockInBankAccount, Long> {
}

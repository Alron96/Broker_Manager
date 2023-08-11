package com.broker_manager.repositorie;

import com.broker_manager.model.StockInBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockinBankAccountRepository extends JpaRepository<StockInBankAccount, Integer> {
}

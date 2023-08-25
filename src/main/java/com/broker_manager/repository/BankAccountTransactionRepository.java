package com.broker_manager.repository;

import com.broker_manager.model.BankAccountTransaction;
import com.broker_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Integer> {

    List<BankAccountTransaction> findAllBySenderBrokerIdOrderByWhenDoneDesc(User user);
}

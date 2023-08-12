package com.broker_manager.repository;

import com.broker_manager.model.StockInTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInTicketRepository extends JpaRepository<StockInTicket, Integer> {
}

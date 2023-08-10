package com.broker_manager.repositories;

import com.broker_manager.models.StockInTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInTicketRepository extends JpaRepository<StockInTicket, Long> {
}

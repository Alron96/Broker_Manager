package com.broker_manager.repository;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("select t from Ticket t join User u on t.broker.id = u.id where t.broker.id = :id and u.department = :department")
    List<Ticket> getAllTicketByUserExcludeStockInTicket(Department department, Integer id);

    @Query("select t from Ticket t join User u on t.broker.id = u.id join fetch t.ticketStocks where t.broker.id = :id and u.department = :department")
    Ticket getTicketByDepartment(Department department, Integer id);
}
//    join User u on t.broker.id = u.id join fetch t.ticketStocks where t.broker.id = :id and u.department = :department
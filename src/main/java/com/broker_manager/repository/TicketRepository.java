package com.broker_manager.repository;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.enums.Department;
import com.broker_manager.web.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {


    List<Ticket> getAllTicketByDepartmentExcludeStockInTicket(Department department);

     void getAllTicketByDepartment(Department departmentUpperCase, Integer id, AuthorizedUser authUser);
}

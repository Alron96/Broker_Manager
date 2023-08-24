package com.broker_manager.service.ticket;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.repository.TicketRepository;
import com.broker_manager.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerTicketServiceImpl implements BrokerTicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public BrokerTicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTicketByUser(String department, User user) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return ticketRepository.getAllTicketByUserExcludeStockInTicket(departmentUpperCase, user.getId());
    }

    @Override
    public Ticket getTicketByDepartment(String department, Integer id, AuthorizedUser authUser) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return ticketRepository.getTicketByDepartment(departmentUpperCase, id);
    }
}

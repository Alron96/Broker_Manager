package com.broker_manager.service.ticket;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.repository.TicketRepository;
import com.broker_manager.to.TicketTo;
import com.broker_manager.util.UserUtil;
import com.broker_manager.util.error.NotFoundException;
import com.broker_manager.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChiefBrokerTicketServiceImpl implements ChiefBrokerTicketService {
    private final TicketRepository ticketRepository;


    @Autowired
    public ChiefBrokerTicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTicketByDepartment(String department, User user) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return ticketRepository.getAllTicketByDepartmentExcludeStockInTicket(departmentUpperCase, user.getId());
    }

    @Override
    public Ticket getTicketByDepartment(String department, Integer id, User user) {
        Department departmentUpperCase = Department.valueOf(department.toUpperCase());
        return ticketRepository.getTicketByDepartmentStockInTicketAndBankAccountTransaction(departmentUpperCase, id);
    }

    @Transactional
    public Ticket createTicket(String department, TicketTo ticketTo, AuthorizedUser authUser) {
        return null;
    }


}






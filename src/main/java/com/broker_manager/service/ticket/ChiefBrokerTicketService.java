package com.broker_manager.service.ticket;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;

import java.util.List;

public interface ChiefBrokerTicketService {
    List<Ticket> getAllTicketByDepartment(String department, User user);

    Ticket getTicketByDepartment(String department, Integer id, User user);
}

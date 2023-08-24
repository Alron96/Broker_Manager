package com.broker_manager.service.ticket;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.web.AuthorizedUser;

import java.util.List;

public interface BrokerTicketService {
    List<Ticket> getAllTicketByUser(String department, User user);

    Ticket getTicketByDepartment(String department, Integer id, AuthorizedUser authUser);
}

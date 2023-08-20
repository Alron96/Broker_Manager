package com.broker_manager.web.ticket;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.web.AuthorizedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BrokerTicketController.REST_URL)
public class BrokerTicketController {
    static final String REST_URL = "/broker/tickets/{department}";

    @GetMapping
    public List<Ticket> getAllTicketByUser(@PathVariable String department) {
        // Вывести список Ticket без stockInTicket данного User
        return null;
    }

    @GetMapping("/{id}")
    public Ticket getTicketByDepartment(@PathVariable String department, @PathVariable Integer id, @AuthenticationPrincipal AuthorizedUser authUser) {
        // Вывести Ticket с stockInTicket
        return null;
    }
}

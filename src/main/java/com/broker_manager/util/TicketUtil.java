package com.broker_manager.util;

import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.to.TicketTo;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class TicketUtil {

    public static Ticket createNewFromTo(TicketTo ticketTo, User broker, User chiefBroker) {
        return new Ticket(null, ticketTo.getOperation(), false, LocalDateTime.now(), null, broker, chiefBroker, ticketTo.getTicketStocks());
    }

    public static TicketTo asTo(Ticket ticket) {
        return new TicketTo(ticket.getId(), ticket.getOperation(), ticket.getBroker().getId(), ticket.getTicketStocks());
    }
}

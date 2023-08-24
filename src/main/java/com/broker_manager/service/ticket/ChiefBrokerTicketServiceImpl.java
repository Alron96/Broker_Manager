package com.broker_manager.service.ticket;

import com.broker_manager.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiefBrokerTicketServiceImpl implements ChiefBrokerTicketService {
    private final TicketRepository ticketRepository;


    @Autowired
    public ChiefBrokerTicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


}






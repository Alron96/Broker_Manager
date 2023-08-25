package com.broker_manager.to;

import com.broker_manager.model.StockInTicket;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TicketTo {
    private Integer id;

    @Enumerated
    private Operation operation;

    private Integer brokerId;

    private List<StockInTicket> ticketStocks;
}

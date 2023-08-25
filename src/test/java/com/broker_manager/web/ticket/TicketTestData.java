package com.broker_manager.web.ticket;

import com.broker_manager.MatcherFactory;
import com.broker_manager.model.Ticket;
import com.broker_manager.model.enums.Operation;
import com.broker_manager.to.TicketTo;

import java.time.LocalDateTime;
import java.util.List;

import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCKS_IN_TICKET_ANALYTICAL_1;
import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCKS_IN_TICKET_ANALYTICAL_2;
import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCKS_IN_TICKET_ANALYTICAL_3;
import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCKS_IN_TICKET_ANALYTICAL_4;
import static com.broker_manager.web.user.UserTestData.BROKER_ANALYTICAL_1;
import static com.broker_manager.web.user.UserTestData.BROKER_ANALYTICAL_2;
import static com.broker_manager.web.user.UserTestData.BROKER_ANALYTICAL_ID_1;
import static com.broker_manager.web.user.UserTestData.CHIEF_BROKER_ANALYTICAL;

public class TicketTestData {
    public static final MatcherFactory.Matcher<Ticket> TICKET_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Ticket.class);
    public static final MatcherFactory.Matcher<Ticket> TICKET_MATCHER_WITHOUT_STOCKS = MatcherFactory.usingIgnoringFieldsComparator(Ticket.class, "ticketStocks");

    public static final int TICKET_ID_1 = 1;
    public static final int TICKET_ID_2 = 2;
    public static final int TICKET_ID_3 = 3;
    public static final int TICKET_ID_4 = 4;

    public static final Ticket TICKET_ANALYTICAL_1 = new Ticket(TICKET_ID_1, Operation.BUY, false,
            LocalDateTime.of(2023, 1, 20, 10, 0), null, BROKER_ANALYTICAL_1, CHIEF_BROKER_ANALYTICAL, STOCKS_IN_TICKET_ANALYTICAL_1);
    public static final Ticket TICKET_ANALYTICAL_2 = new Ticket(TICKET_ID_2, Operation.SELL, false,
            LocalDateTime.of(2023, 1, 10, 16, 30), null, BROKER_ANALYTICAL_1, CHIEF_BROKER_ANALYTICAL, STOCKS_IN_TICKET_ANALYTICAL_2);
    public static final Ticket TICKET_ANALYTICAL_3 = new Ticket(TICKET_ID_3, Operation.SELL, false,
            LocalDateTime.of(2023, 2, 25, 11, 30), null, BROKER_ANALYTICAL_2, CHIEF_BROKER_ANALYTICAL, STOCKS_IN_TICKET_ANALYTICAL_3);
    public static final Ticket TICKET_ANALYTICAL_4 = new Ticket(TICKET_ID_4, Operation.SELL, false,
            LocalDateTime.of(2023, 2, 25, 12, 30), null, BROKER_ANALYTICAL_2, CHIEF_BROKER_ANALYTICAL, STOCKS_IN_TICKET_ANALYTICAL_4);

    public static final List<Ticket> TICKETS_FOR_BROKER_ANALYTICAL = List.of(TICKET_ANALYTICAL_1, TICKET_ANALYTICAL_2);
    public static final List<Ticket> TICKETS_FOR_DEPARTMENT_ANALYTICAL = List.of(TICKET_ANALYTICAL_1, TICKET_ANALYTICAL_2, TICKET_ANALYTICAL_3, TICKET_ANALYTICAL_4);

    public static TicketTo getNewAnalyticalTicketTo() {
        return new TicketTo(null, Operation.BUY, BROKER_ANALYTICAL_ID_1, STOCKS_IN_TICKET_ANALYTICAL_4);
    }
}

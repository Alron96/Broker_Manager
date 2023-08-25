package com.broker_manager.web.stockInTicket;

import com.broker_manager.MatcherFactory;
import com.broker_manager.model.StockInTicket;

import java.util.List;

import static com.broker_manager.web.stok.StockTestData.*;
import static com.broker_manager.web.ticket.TicketTestData.TICKET_ANALYTICAL_1;
import static com.broker_manager.web.ticket.TicketTestData.TICKET_ANALYTICAL_2;
import static com.broker_manager.web.ticket.TicketTestData.TICKET_ANALYTICAL_3;
import static com.broker_manager.web.ticket.TicketTestData.TICKET_ANALYTICAL_4;

public class StockInTicketTestData {
    public static final MatcherFactory.Matcher<StockInTicket> STOCK_IN_TICKET_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(StockInTicket.class);

    public static final int STOCK_IN_TICKET_ID_1 = 1;
    public static final int STOCK_IN_TICKET_ID_2 = 2;
    public static final int STOCK_IN_TICKET_ID_3 = 3;
    public static final int STOCK_IN_TICKET_ID_4 = 4;
    public static final int STOCK_IN_TICKET_ID_5 = 5;
    public static final int STOCK_IN_TICKET_ID_6 = 6;
    public static final int STOCK_IN_TICKET_ID_7 = 7;
    public static final int STOCK_IN_TICKET_ID_8 = 8;

    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_1 =
            new StockInTicket(STOCK_IN_TICKET_ID_1, 10, false, TICKET_ANALYTICAL_1, STOCK_1, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_2 =
            new StockInTicket(STOCK_IN_TICKET_ID_2, 20, false, TICKET_ANALYTICAL_1, STOCK_2, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_3 =
            new StockInTicket(STOCK_IN_TICKET_ID_3, 30, false, TICKET_ANALYTICAL_2, STOCK_3, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_4 =
            new StockInTicket(STOCK_IN_TICKET_ID_4, 40, false, TICKET_ANALYTICAL_2, STOCK_4, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_5 =
            new StockInTicket(STOCK_IN_TICKET_ID_5, 15, false, TICKET_ANALYTICAL_3, STOCK_5, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_6 =
            new StockInTicket(STOCK_IN_TICKET_ID_6, 25, false, TICKET_ANALYTICAL_3, STOCK_6, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_7 =
            new StockInTicket(STOCK_IN_TICKET_ID_7, 35, false, TICKET_ANALYTICAL_4, STOCK_7, null);
    public static final StockInTicket STOCK_IN_TICKET_ANALYTICAL_8 =
            new StockInTicket(STOCK_IN_TICKET_ID_8, 45, false, TICKET_ANALYTICAL_4, STOCK_8, null);

    public static final List<StockInTicket> STOCKS_IN_TICKET_ANALYTICAL_1 = List.of(STOCK_IN_TICKET_ANALYTICAL_1, STOCK_IN_TICKET_ANALYTICAL_2);
    public static final List<StockInTicket> STOCKS_IN_TICKET_ANALYTICAL_2 = List.of(STOCK_IN_TICKET_ANALYTICAL_3, STOCK_IN_TICKET_ANALYTICAL_4);
    public static final List<StockInTicket> STOCKS_IN_TICKET_ANALYTICAL_3 = List.of(STOCK_IN_TICKET_ANALYTICAL_5, STOCK_IN_TICKET_ANALYTICAL_6);
    public static final List<StockInTicket> STOCKS_IN_TICKET_ANALYTICAL_4 = List.of(STOCK_IN_TICKET_ANALYTICAL_7, STOCK_IN_TICKET_ANALYTICAL_8);
}

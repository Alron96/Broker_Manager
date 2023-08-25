package com.broker_manager.web.stockInTicket;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.repository.StockInTicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCK_IN_TICKET_ANALYTICAL_1;
import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCK_IN_TICKET_ID_1;
import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCK_IN_TICKET_ID_2;
import static com.broker_manager.web.stockInTicket.StockInTicketTestData.STOCK_IN_TICKET_MATCHER;
import static com.broker_manager.web.ticket.TicketTestData.TICKET_ANALYTICAL_1;
import static com.broker_manager.web.user.UserTestData.BROKER_ANALYTICAL_1;
import static com.broker_manager.web.user.UserTestData.BROKER_ANALYTICAL_1_MAIL;
import static com.broker_manager.web.user.UserTestData.CHIEF_BROKER_ANALYTICAL_MAIL;
import static com.broker_manager.web.user.UserTestData.NOT_FOUND;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrokerStockInTicketControllerTest extends AbstractControllerTest {
    public static final String REST_URL = "/broker/tickets/"
            + BROKER_ANALYTICAL_1.getDepartment().toString().toLowerCase()
            + "/" + TICKET_ANALYTICAL_1.getId();
    public static final String REST_URL_SLASH = REST_URL + "/";

    @Autowired
    private StockInTicketRepository stockInTicketRepository;

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void getStockInTicket() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + STOCK_IN_TICKET_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(STOCK_IN_TICKET_MATCHER.contentJson(STOCK_IN_TICKET_ANALYTICAL_1));
    }

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void update() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + STOCK_IN_TICKET_ID_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        Assertions.assertTrue(stockInTicketRepository.findById(STOCK_IN_TICKET_ID_2).orElseThrow().isStatus());
    }
}
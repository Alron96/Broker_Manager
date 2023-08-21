package com.broker_manager.web.ticket;

import com.broker_manager.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.ticket.TicketTestData.*;
import static com.broker_manager.web.user.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrokerTicketControllerTest extends AbstractControllerTest {
    public static final String REST_URL = "/broker/tickets/"
            + BROKER_ANALYTICAL_1.getDepartment().toString().toLowerCase();
    public static final String REST_URL_SLASH = REST_URL + "/";

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void getAllTicketByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TICKET_MATCHER_WITHOUT_STOCKS.contentJson(TICKETS_FOR_BROKER_ANALYTICAL));
    }

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void getTicketByDepartment() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + TICKET_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TICKET_MATCHER.contentJson(TICKET_ANALYTICAL_1));
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
}

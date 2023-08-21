package com.broker_manager.web.ticket;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.model.Ticket;
import com.broker_manager.model.User;
import com.broker_manager.repository.TicketRepository;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.TicketTo;
import com.broker_manager.util.JsonUtil;
import com.broker_manager.util.TicketUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.ticket.TicketTestData.*;
import static com.broker_manager.web.user.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChiefBrokerTicketControllerTest extends AbstractControllerTest {
    public static final String REST_URL = "/chief_broker/tickets/"
            + CHIEF_BROKER_ANALYTICAL.getDepartment().toString().toLowerCase();
    public static final String REST_URL_SLASH = REST_URL + "/";

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void getAllTicketByDepartment() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TICKET_MATCHER_WITHOUT_STOCKS.contentJson(TICKETS_FOR_DEPARTMENT_ANALYTICAL));
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void getTicketByDepartment() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + TICKET_ID_3))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TICKET_MATCHER.contentJson(TICKET_ANALYTICAL_3));
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
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
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + TICKET_ID_4)
                .param("chiefId", String.valueOf(CHIEF_BROKER_ANALYTICAL_ID)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(ticketRepository.findById(TICKET_ID_4).isPresent());
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void create() throws Exception {
        TicketTo newTicketTo = getNewAnalyticalTicketTo();
        Ticket newTicket = TicketUtil.createNewFromTo(newTicketTo, userRepository.getReferenceById(newTicketTo.getBrokerId()), CHIEF_BROKER_ANALYTICAL);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTicketTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        Ticket created = TICKET_MATCHER.readFromJson(action);
        int newId = created.getId();
        newTicket.setId(newId);
        TICKET_MATCHER.assertMatch(created, newTicket);
        TICKET_MATCHER.assertMatch(ticketRepository.findById(newId).orElse(null), newTicket);
    }

    @Test
    @WithUserDetails(value = CHIEF_BROKER_ANALYTICAL_MAIL)
    void createInvalid() throws Exception {
        User invalid = getUpdatedConsultingBroker();
        invalid.setFullName("");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, invalid.getPassword())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

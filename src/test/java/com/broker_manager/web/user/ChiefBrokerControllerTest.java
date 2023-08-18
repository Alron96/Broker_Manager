package com.broker_manager.web.user;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.user.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ChiefBrokerControllerTest extends AbstractControllerTest {
    public static final String REST_URL = ChiefBrokerController.REST_URL;
    public static final String REST_URL_USERS = REST_URL + "/users";
    public static final String REST_URL_SLASH = REST_URL_USERS + "/";

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllBrokersByDepartment() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + CHIEF_BROKER_ANALYTICAL.getDepartment().toString().toLowerCase()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(ALL_BROKERS_BY_ANALYTICAL_DEPARTMENT));
    }

    @Test
    void getBrokerByDepartment() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + CHIEF_BROKER_ANALYTICAL.getDepartment().toString().toLowerCase() + "/" + BROKER_ANALYTICAL_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(BROKER_ANALYTICAL_1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + CHIEF_BROKER_ANALYTICAL.getDepartment().toString().toLowerCase() + "/" + BROKER_ANALYTICAL_ID_2)
                .param("chiefId", String.valueOf(CHIEF_BROKER_ANALYTICAL_ID)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(userRepository.findById(BROKER_ANALYTICAL_ID_2).isPresent());
    }

    @Test
    void update() throws Exception {
        User updated = getUpdatedAnalyticalBroker();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + CHIEF_BROKER_ANALYTICAL.getDepartment().toString().toLowerCase() + "/" + updated.getId())
                .param("chiefId", String.valueOf(CHIEF_BROKER_ANALYTICAL_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updated, updated.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.findById(BROKER_ANALYTICAL_ID_1).orElse(null), getUpdatedAnalyticalBroker());
    }

    @Test
    void create() throws Exception {
        User newUser = getNewAnalyticalBroker();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_USERS)
                .param("chiefId", String.valueOf(CHIEF_BROKER_ANALYTICAL_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(newUser, newUser.getPassword())))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = USER_MATCHER.readFromJson(action);
        int newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.findById(newId).orElse(null), newUser);
    }
}

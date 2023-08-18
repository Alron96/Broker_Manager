package com.broker_manager.web.user;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.UserTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.user.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BrokerControllerTest extends AbstractControllerTest {
    public static final String REST_URL = BrokerController.REST_URL;
    public static final String REST_URL_SLASH = REST_URL + "/";

    @Autowired
    private UserRepository userRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + BROKER_ANALYTICAL_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(BROKER_ANALYTICAL_1));
    }

    @Test
    void update() throws Exception {
        UserTo updated = getUpdatedAnalyticalBrokerTo();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updated, updated.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.findById(BROKER_ANALYTICAL_ID_1).orElse(null), getUpdatedAnalyticalBroker());
    }
}

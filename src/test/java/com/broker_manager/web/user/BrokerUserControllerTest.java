package com.broker_manager.web.user;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import com.broker_manager.to.UserTo;
import com.broker_manager.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.user.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrokerUserControllerTest extends AbstractControllerTest {
    public static final String REST_URL = BrokerUserController.REST_URL;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(BROKER_ANALYTICAL_1));
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
        User updated = getUpdatedAnalyticalBroker();
        UserTo updatedTo = UserUtil.asTo(updated);
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updatedTo, updatedTo.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.findById(BROKER_ANALYTICAL_ID_1).orElse(null), getUpdatedAnalyticalBroker());
    }

    @Test
    @WithUserDetails(value = BROKER_ANALYTICAL_1_MAIL)
    void updateInvalid() throws Exception {
        User updated = getUpdatedAnalyticalBroker();
        UserTo updatedTo = UserUtil.asTo(updated);
        updatedTo.setFullName("");
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updatedTo, updatedTo.getPassword())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

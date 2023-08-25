package com.broker_manager.web.user;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.model.User;
import com.broker_manager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.user.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DirectorUserControllerTest extends AbstractControllerTest {
    public static final String REST_URL = DirectorUserController.REST_URL;
    public static final String REST_URL_SLASH = REST_URL + "/";

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USERS));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DIRECTOR_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(DIRECTOR));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
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
    @WithUserDetails(value = DIRECTOR_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + BROKER_CONSULTING_ID_2))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(userRepository.findById(BROKER_CONSULTING_ID_2).isPresent());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void update() throws Exception {
        User updated = getUpdatedConsultingBroker();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updated, updated.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.findById(BROKER_CONSULTING_ID_1).orElse(null), getUpdatedConsultingBroker());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void updateInvalid() throws Exception {
        User invalid = getUpdatedConsultingBroker();
        invalid.setFullName("");
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + BROKER_CONSULTING_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, invalid.getPassword())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void updateHtmlUnsafe() throws Exception {
        User invalid = getUpdatedConsultingBroker();
        invalid.setFullName("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + BROKER_CONSULTING_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, invalid.getPassword())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void create() throws Exception {
        User newUser = getNewConsultingBroker();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
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

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createInvalid() throws Exception {
        User invalid = getUpdatedConsultingBroker();
        invalid.setFullName("");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, invalid.getPassword())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createValidButWithIdNotNull() throws Exception {
        User invalid = getUpdatedConsultingBroker();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, invalid.getPassword())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}

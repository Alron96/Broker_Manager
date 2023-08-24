package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.bankAccountTransaction.BankAccountTransactionTestData.*;
import static com.broker_manager.web.user.UserTestData.BROKER_ANALYTICAL_1_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrokerBankAccountTransactionControllerTest extends AbstractControllerTest {
    static final String REST_URL = "/broker/transactions";

    @Test
    @WithUserDetails(BROKER_ANALYTICAL_1_MAIL)
    void getAllBankAccountTransactionsByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_TRANSACTION_MATCHER.contentJson(TRANSACTIONS_FOR_BROKER_ANALYTICAL_1));
    }

    @Test
    @WithUserDetails(BROKER_ANALYTICAL_1_MAIL)
    void getBankAccountTransaction() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_TRANSACTION_SALARY_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_TRANSACTION_MATCHER.contentJson(BANK_ACCOUNT_TRANSACTION_SALARY));
    }

    @Test
    @WithUserDetails(BROKER_ANALYTICAL_1_MAIL)
    void createBankAccountTransaction() {
    }
}
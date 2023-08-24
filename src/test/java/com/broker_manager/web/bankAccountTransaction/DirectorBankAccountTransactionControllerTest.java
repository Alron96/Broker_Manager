package com.broker_manager.web.bankAccountTransaction;

import com.broker_manager.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.bankAccountTransaction.BankAccountTransactionTestData.*;
import static com.broker_manager.web.user.UserTestData.DIRECTOR_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DirectorBankAccountTransactionControllerTest extends AbstractControllerTest {
    static final String REST_URL = "/director/transactions";

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getAllBankAccountTransactions() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_TRANSACTION_MATCHER.contentJson(TRANSACTIONS_FOR_DIRECTOR));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getBankAccountTransaction() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_TRANSACTION_TRANSFER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_TRANSACTION_MATCHER.contentJson(BANK_ACCOUNT_TRANSACTION_TRANSFER));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createBankAccountTransaction() {
    }
}
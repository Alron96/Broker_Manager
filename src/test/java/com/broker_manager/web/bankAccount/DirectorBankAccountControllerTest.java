package com.broker_manager.web.bankAccount;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.model.BankAccount;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.broker_manager.web.bankAccount.BankAccountTestData.*;
import static com.broker_manager.web.user.UserTestData.DIRECTOR_MAIL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DirectorBankAccountControllerTest extends AbstractControllerTest {
    static final String REST_URL = "/director/bankAccounts";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getAllBankAccounts() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS.contentJson(BANK_ACCOUNTS_FOR_DIRECTOR));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getBankAccount() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_COMPANY_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_MATCHER.contentJson(BANK_ACCOUNT_COMPANY));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createBankAccount() throws Exception {
        BankAccount newBankAccount = getNewBankAccount();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newBankAccount)))
                .andDo(print())
                .andExpect(status().isCreated());

        BankAccount created = BANK_ACCOUNT_MATCHER.readFromJson(action);
        int newId = created.getId();
        newBankAccount.setId(newId);
        BANK_ACCOUNT_MATCHER.assertMatch(created, newBankAccount);
        BANK_ACCOUNT_MATCHER.assertMatch(bankAccountRepository.findById(newId).orElse(null), newBankAccount);
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteBankAccount() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + BANK_ACCOUNT_PERSONAL_EMPTY_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(bankAccountRepository.findById(BANK_ACCOUNT_PERSONAL_EMPTY_ID).isPresent());
    }
}
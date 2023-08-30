package com.broker_manager.web.bankAccount;

import com.broker_manager.AbstractControllerTest;
import com.broker_manager.model.BankAccount;
import com.broker_manager.model.StockInBankAccount;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import com.broker_manager.repository.BankAccountRepository;
import com.broker_manager.util.JsonUtil;
import com.broker_manager.util.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Objects;

import static com.broker_manager.web.bankAccount.BankAccountTestData.*;
import static com.broker_manager.web.user.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DirectorBankAccountControllerTest extends AbstractControllerTest {
    static final String REST_URL = DirectorBankAccountController.REST_URL;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getAllBankAccounts() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.contentJson(BANK_ACCOUNTS_FOR_DIRECTOR));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getCompanyBankAccount() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_COMPANY_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.contentJson(BANK_ACCOUNT_COMPANY));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getConsultingBankAccount() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_CONSULTING_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.contentJson(BANK_ACCOUNT_CONSULTING));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getPersonalBankAccount() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_PERSONAL_EMPTY_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.contentJson(BANK_ACCOUNT_PERSONAL_EMPTY));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void getExchange() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + BANK_ACCOUNT_EXCHANGE_ID))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals("Bank account not found",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
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

        BankAccount created = BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.readFromJson(action);
        int newId = created.getId();
        newBankAccount.setId(newId);

        BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.assertMatch(created, newBankAccount);
        BANK_ACCOUNT_MATCHER_WITHOUT_STOCKS_AND_USERS.assertMatch(bankAccountRepository.findById(newId).orElse(null), newBankAccount);
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createSecondBankAccountForCompany() throws Exception {
        BankAccount secondCompanyBankAccount = getNewBankAccount();
        secondCompanyBankAccount.setDepartment(Department.COMPANY);
        secondCompanyBankAccount.setType(Type.COMPANY);

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(secondCompanyBankAccount)))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Main bank account for company already exists",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createSecondBankAccountForDepartment() throws Exception {
        BankAccount secondConsultingBankAccount = getNewBankAccount();
        secondConsultingBankAccount.setDepartment(Department.CONSULTING);

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(secondConsultingBankAccount)))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Department bank account for department already exists",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void createSecondBankAccountForBroker() throws Exception {
        BankAccount secondPersonalBankAccount = getNewBankAccount();
        secondPersonalBankAccount.setType(Type.PERSONAL);
        secondPersonalBankAccount.setUsers(List.of(DIRECTOR, BROKER_ANALYTICAL_1));

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(secondPersonalBankAccount)))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Personal bank account for user already exists",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteBankAccount() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + BANK_ACCOUNT_PERSONAL_EMPTY_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(bankAccountRepository.findById(BANK_ACCOUNT_PERSONAL_EMPTY_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteBankAccountDoesNotExist() throws Exception {
        BankAccount bankAccountDoesNotExist = getNewBankAccount();
        bankAccountDoesNotExist.setId(Integer.MAX_VALUE);

        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + bankAccountDoesNotExist.getId()))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Bank account not exist",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteBankAccountWithNotNullBalanceAndStock() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + BANK_ACCOUNT_CONSULTING_ID))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Bank account not empty",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
        assertTrue(bankAccountRepository.findById(BANK_ACCOUNT_CONSULTING_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteBankAccountWithNotNullBalanceOnly() throws Exception {
        List<StockInBankAccount> tempStockInBankAccount = BANK_ACCOUNT_CONSULTING.getStockInBankAccounts();
        BANK_ACCOUNT_CONSULTING.setStockInBankAccounts(List.of());

        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + BANK_ACCOUNT_CONSULTING_ID))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Bank account not empty",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
        assertTrue(bankAccountRepository.findById(BANK_ACCOUNT_CONSULTING_ID).isPresent());

        BANK_ACCOUNT_CONSULTING.setStockInBankAccounts(tempStockInBankAccount);
    }

    @Test
    @WithUserDetails(value = DIRECTOR_MAIL)
    void deleteBankAccountWithNotNullStockOnly() throws Exception {
        double tempBalance = BANK_ACCOUNT_CONSULTING.getBalance();
        BANK_ACCOUNT_CONSULTING.setBalance(0.0);

        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + BANK_ACCOUNT_CONSULTING_ID))
                .andExpect(status().is5xxServerError())
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Bank account not empty",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
        assertTrue(bankAccountRepository.findById(BANK_ACCOUNT_CONSULTING_ID).isPresent());

        BANK_ACCOUNT_CONSULTING.setBalance(tempBalance);
    }
}
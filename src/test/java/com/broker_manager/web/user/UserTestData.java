package com.broker_manager.web.user;

import com.broker_manager.MatcherFactory;
import com.broker_manager.model.User;
import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import com.broker_manager.util.JsonUtil;

import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password", "bankAccounts");

    public static final int DIRECTOR_ID = 1;
    public static final int CHIEF_BROKER_ANALYTICAL_ID = 2;
    public static final int CHIEF_BROKER_CONSULTING_ID = 3;
    public static final int BROKER_ANALYTICAL_ID_1 = 4;
    public static final int BROKER_ANALYTICAL_ID_2 = 5;
    public static final int BROKER_CONSULTING_ID_1 = 6;
    public static final int BROKER_CONSULTING_ID_2 = 7;

    public static final User DIRECTOR = new User(DIRECTOR_ID, "Директор Брокеров Игнатьевич", "director@mail.ru", "8-800-000-00-00", "directorpassword", Department.COMPANY, Role.DIRECTOR, null);
    public static final User CHIEF_BROKER_ANALYTICAL = new User(CHIEF_BROKER_ANALYTICAL_ID, "Шеф Аналитики Игнатьевич", "cheif_broker_analitycal@mail.ru", "8-800-111-11-11", "chiefAnalyticalPassword", Department.ANALYTICAL, Role.CHEIFBROKER, null);
    public static final User CHIEF_BROKER_CONSULTING = new User(CHIEF_BROKER_CONSULTING_ID, "Шеф Консалтинга Игнатьевич", "cheif_broker_consulting@mail.ru", "8-800-111-11-22", "chiefConsultingPassword", Department.CONSULTING, Role.CHEIFBROKER, null);
    public static final User BROKER_ANALYTICAL_1 = new User(BROKER_ANALYTICAL_ID_1, "Брокер Аналитики Первый", "broker_analitycal_1@mail.ru", "8-800-222-22-11", "brokerAnalyticalFirstPassword", Department.ANALYTICAL, Role.BROKER, null);
    public static final User BROKER_ANALYTICAL_2 = new User(BROKER_ANALYTICAL_ID_2, "Брокер Аналитики Второй", "broker_analitycal_2@mail.ru", "8-800-222-22-22", "brokerAnalyticalSecondPassword", Department.ANALYTICAL, Role.BROKER, null);
    public static final User BROKER_CONSULTING_1 = new User(BROKER_CONSULTING_ID_1, "Брокер Консалтинга Первый", "broker_consulting_1@mail.ru", "8-800-222-22-33", "brokerConsultingFirstPassword", Department.CONSULTING, Role.BROKER, null);
    public static final User BROKER_CONSULTING_2 = new User(BROKER_CONSULTING_ID_2, "Брокер Консалтинга Второй", "broker_consulting_2@mail.ru", "8-800-222-22-44", "brokerConsultingSecondPassword", Department.CONSULTING, Role.BROKER, null);

    public static final List<User> USERS = List.of(DIRECTOR, CHIEF_BROKER_ANALYTICAL, CHIEF_BROKER_CONSULTING, BROKER_ANALYTICAL_1, BROKER_ANALYTICAL_2, BROKER_CONSULTING_1, BROKER_CONSULTING_2);

    public static User getNew() {
        return new User(null, "Новый Брокеровов Игнатьевич", "new@mail.ru", "8-800-777-77-77", "newpassword", Department.ANALYTICAL, Role.BROKER, null);
    }

    public static User getUpdated() {
        return new User(BROKER_ANALYTICAL_ID_1, "Улучшенный Брокеровов Игнатьевич", "update@mail.ru", "8-800-999-99-99", "updatepassword", Department.ANALYTICAL, Role.BROKER, null);
    }

    public static String jsonWithPassword(User user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}

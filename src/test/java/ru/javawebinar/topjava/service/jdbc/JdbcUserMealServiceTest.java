package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

/**
 * Created by Restrictor on 07.07.2016.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JDBC})
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}

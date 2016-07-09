package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Restrictor on 07.07.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void testGetWithMeals() {
        User user = service.get(USER_ID);
        MATCHER.assertEquals(USER, user);
        MealTestData.MATCHER.assertCollectionEquals(
                MealTestData.USER_MEALS,
                user.getMeals());
    }
}

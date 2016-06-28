package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Restrictor on 27.06.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {
    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(meal1.getId(), USER_ID);
        MATCHER.assertEquals(meal1, userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWrongUser() throws Exception {
        service.get(meal1.getId(), ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(meal1.getId(), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(
                meal6, meal5, meal4, meal3, meal2),
                service.getAll(USER_ID)
        );
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongUser() throws Exception {
        service.delete(meal1.getId(), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of(2015, Month.MAY, 30);
        LocalDate endDate = LocalDate.of(2015, Month.MAY, 30);
        MATCHER.assertCollectionEquals(
                Arrays.asList(meal3, meal2, meal1),
                service.getBetweenDates(startDate, endDate, USER_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2015, Month.MAY, 30, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2015, Month.MAY, 30, 15, 0);
        MATCHER.assertCollectionEquals(
                Arrays.asList(meal2, meal1),
                service.getBetweenDateTimes(startDateTime, endDateTime, USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(adminMeal2, adminMeal1), all);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = new UserMeal(
                meal1.getId(),
                meal1.getDateTime(),
                meal1.getDescription(),
                meal1.getCalories()
        );
        updated.setDescription("UpdatedDescription");
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(meal1.getId(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWrongUser() throws Exception {
        UserMeal updated = new UserMeal(
                meal1.getId(),
                meal1.getDateTime(),
                meal1.getDescription(),
                meal1.getCalories()
        );
        updated.setDescription("UpdatedDescription");
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        UserMeal created = service.save(new UserMeal(LocalDateTime.of(2015, Month.JUNE, 2, 20, 0), "Админ ужин", 1400), ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(
                created, adminMeal2, adminMeal1), service.getAll(ADMIN_ID)
        );
    }

}
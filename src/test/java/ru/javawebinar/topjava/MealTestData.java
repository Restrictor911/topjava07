package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    private static int id = START_SEQ;

    public static final UserMeal meal1 = new UserMeal(id++, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final UserMeal meal2 = new UserMeal(id++, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final UserMeal meal3 = new UserMeal(id++, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final UserMeal meal4 = new UserMeal(id++, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final UserMeal meal5 = new UserMeal(id++, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final UserMeal meal6 = new UserMeal(id++, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static final UserMeal adminMeal1 = new UserMeal(id++, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final UserMeal adminMeal2 = new UserMeal(id++, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}

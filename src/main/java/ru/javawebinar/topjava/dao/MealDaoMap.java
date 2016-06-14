package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

/**
 * Created by Restrictor on 11.06.2016.
 */
public class MealDaoMap implements MealDao {
    private static int counter = 1;
    private Map<Integer, UserMeal> mealMap;

    public MealDaoMap() {
        mealMap = new HashMap<>();
        mealMap.put(counter, new UserMeal(counter, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        counter++;
        mealMap.put(counter, new UserMeal(counter, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        counter++;
        mealMap.put(counter, new UserMeal(counter, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        counter++;
        mealMap.put(counter, new UserMeal(counter, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        counter++;
        mealMap.put(counter, new UserMeal(counter, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        counter++;
        mealMap.put(counter, new UserMeal(counter, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        counter++;
    }

    synchronized public List<UserMeal> getMeals() {
        return new ArrayList<>(mealMap.values());
    }

    synchronized public void add(String description, int calories, LocalDateTime dateTime) {
        mealMap.put(counter, new UserMeal(counter, dateTime, description, calories));
        counter++;
    }

    synchronized public void update(int id, String description, int calories, LocalDateTime dateTime) {
        mealMap.put(id, new UserMeal(id, dateTime, description, calories));
    }

    synchronized public void delete(int id) {
        mealMap.remove(id);
    }

    synchronized public UserMeal getMealById(int id) {
        return mealMap.get(id);
    }
}

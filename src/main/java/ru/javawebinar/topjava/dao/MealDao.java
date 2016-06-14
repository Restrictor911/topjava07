package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Restrictor on 12.06.2016.
 */
public interface MealDao {
    List<UserMeal> getMeals();
    void add(String description, int calories, LocalDateTime dateTime);
    void update(int id, String description, int calories, LocalDateTime dateTime);
    void delete(int id);
    UserMeal getMealById(int id);
}

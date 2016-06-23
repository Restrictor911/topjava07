package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    List<UserMealWithExceed> getAll(int userId);
    List<UserMealWithExceed> getFiltered(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    UserMeal save(UserMeal userMeal);
    void delete(int id, int userId);
    void update(UserMeal userMeal);
    UserMeal get(int id, int userId);


}

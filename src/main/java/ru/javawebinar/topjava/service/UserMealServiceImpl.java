package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    public List<UserMealWithExceed> getAll(int userId) {
        return UserMealsUtil.getWithExceeded(repository.getAll(userId),
                LoggedUser.getCaloriesPerDay());
    }

    public List<UserMealWithExceed> getFiltered(int userId, LocalTime startTime, LocalTime endTime) {
        return UserMealsUtil.getFilteredWithExceeded(repository.getAll(userId),
                startTime, endTime, LoggedUser.getCaloriesPerDay());
    }

    public UserMeal save(UserMeal userMeal) {
        return repository.save(userMeal);
    }

    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    public void update(UserMeal userMeal) {
        repository.save(userMeal);
    }

    public UserMeal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }
}

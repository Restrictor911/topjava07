package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public List<UserMealWithExceed> getAll() {
        LOG.info("getAllMeals");
        return service.getAll(LoggedUser.id());
    }

    public List<UserMealWithExceed> getFiltered(LocalTime startTime, LocalTime endTime) {
        LOG.info("getFilteredMeals");
        return service.getFiltered(LoggedUser.id(), startTime, endTime);
    }

    public UserMeal create(UserMeal userMeal) {
        userMeal.setId(null);
        userMeal.setUserId(LoggedUser.id());
        LOG.info("create meal " + userMeal);
        return service.save(userMeal);
    }

    public void delete(int id) {
        LOG.info("delete meal " + id);
        service.delete(id, LoggedUser.id());
    }

    public void update(UserMeal userMeal, int id) {
        if (!userMeal.getUserId().equals(LoggedUser.id())) {
            throw new NotFoundException("Meal not found.");
        }
        userMeal.setId(id);
        LOG.info("update meal " + userMeal);
        service.update(userMeal);
    }

    public UserMeal get(int id) {
        LOG.info("get meal " + id);
        return service.get(id, LoggedUser.id());
    }
}

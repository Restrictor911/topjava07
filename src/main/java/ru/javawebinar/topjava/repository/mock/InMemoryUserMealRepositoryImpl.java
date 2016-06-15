package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    public UserMeal save(UserMeal userMeal) {
        LOG.info("save meal " + userMeal);
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    public void delete(int id, int userId) {
        LOG.info("delete meal " + id);
        if (repository.get(id).getUserId() == userId) {
            repository.remove(id);
        } else {
            throw new NotFoundException("Meal not found.");
        }
    }

    public UserMeal get(int id) {
        LOG.info("get meal " + id);
        UserMeal userMeal = repository.get(id);
        if (userMeal == null) return null;
        return userMeal.getUserId().equals(LoggedUser.id()) ? userMeal : null;
    }

    public Collection<UserMeal> getAll(int userId) {
        LOG.info("getAllMeals");
        List<UserMeal> userMeals = new ArrayList<>(repository.values())
                .stream()
                .filter(um -> um.getUserId().equals(userId))
                .collect(Collectors.toList());
        userMeals.sort((um1, um2) -> um1.getDateTime().isAfter(um2.getDateTime()) ? 1 : -1);
        return userMeals;
    }
}


package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService service;

    @Autowired
    private UserMealService mealService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", service.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", UserMealsUtil
                .getWithExceeded(mealService.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "mealList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteMeal(HttpServletRequest request) {
        Integer id = Integer.valueOf(Objects.requireNonNull(request.getParameter("id")));
        mealService.delete(id, AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateMeals(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        if (id == null) {
            model.addAttribute("meal", new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000));
        } else {
            model.addAttribute("meal", mealService.get(Integer.valueOf(id), AuthorizedUser.id()));
        }
        return "mealEdit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateMeals(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            mealService.save(userMeal, AuthorizedUser.id());
        } else {
            mealService.update(userMeal, AuthorizedUser.id());
        }
        return "redirect:meals";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filterMeals(HttpServletRequest request) throws IOException {
        LocalDate startDate = TimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = TimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = TimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = TimeUtil.parseLocalTime(request.getParameter("endTime"));

        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);

        request.setAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(
                mealService.getBetweenDates(
                        startDate != null ? startDate : TimeUtil.MIN_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, AuthorizedUser.id()),
                startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, AuthorizedUser.getCaloriesPerDay()
        ));
        return "mealList";
    }
}

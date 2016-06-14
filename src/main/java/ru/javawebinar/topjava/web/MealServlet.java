package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMap;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: restrictor911
 * Date: 10.06.2016
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    public static MealDao mealDao = new MealDaoMap();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        String forward;

        if (action.equals("delete")) {
            mealDao.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meals", UserMealsUtil
                    .getAllWithExceeded(mealDao.getMeals(), 2000));
            forward = "/mealList.jsp";
        } else if (action.equals("add")) {
            request.setAttribute("meal", new UserMeal(0, LocalDateTime.now(),
                    "description", 2000));
            forward = "/edit.jsp";
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            UserMeal userMeal = mealDao.getMealById(id);
            request.setAttribute("meal", userMeal);
            forward = "/edit.jsp";
        } else {
            request.setAttribute("meals", UserMealsUtil
                    .getAllWithExceeded(mealDao.getMeals(), 2000));
            forward = "/mealList.jsp";
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(
                req.getParameter("datetime"),
                DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")
        );
        int id = Integer.parseInt(req.getParameter("id"));
        if (id == 0) {
            mealDao.add(description, calories, dateTime);
        } else {
            mealDao.update(id, description, calories, dateTime);
        }
        req.setAttribute("meals", UserMealsUtil
                .getAllWithExceeded(mealDao.getMeals(), 2000));
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}

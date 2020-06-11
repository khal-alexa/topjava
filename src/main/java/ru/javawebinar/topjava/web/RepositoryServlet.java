package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealRepository;
import ru.javawebinar.topjava.model.Repository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RepositoryServlet extends HttpServlet {
    private Repository repository = MealRepository.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealId = request.getParameter("Id");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("Date/Time").trim(), formatter);

        String description = request.getParameter("Description");
        String calories = request.getParameter("Calories");
        Meal meal = new Meal(dateTime, description, Integer.parseInt(calories));

        if (mealId == null || mealId.isEmpty()) {
            repository.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            repository.update(meal);
        }
        request.setAttribute("mealsTo", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            System.out.println("here");
            int id = Integer.parseInt(request.getParameter("mealId"));
            repository.remove(id);
            System.out.println("here");
            forward = "meals.jsp";
            request.setAttribute("mealsTo", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        } else if (action.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = (Meal) repository.getById(id).get();
            forward = "mealOperations.jsp";
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")) {
            forward = "meals.jsp";
            request.setAttribute("mealsTo", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        } else {
            forward = "mealOperations.jsp";
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}

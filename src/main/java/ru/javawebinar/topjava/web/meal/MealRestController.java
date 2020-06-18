package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        return MealsUtil.getTos(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(Integer id) {
        return service.get(id, SecurityUtil.getAuthUserID());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.getAuthUserID());
    }

    public Meal create(Meal meal) {
        System.out.println("here");
        checkNew(meal);
        meal.setUserId(SecurityUtil.getAuthUserID());
        System.out.println(meal);
        return service.create(meal);
    }

    public void update(Meal meal) {
        service.update(meal);
    }

    public List<MealTo> filterByDateAndTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getTos(service.filterByDateAndTime(startDate, startTime, endDate, endTime), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
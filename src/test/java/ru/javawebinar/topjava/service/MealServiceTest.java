package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Autowired
    @Qualifier("jdbcMealRepository")
    private MealRepository mealRepository;

    @Test
    public void get() {
        Meal meal = mealService.get(MEAL_ID, USER_ID_MEAL);
        assertMatchMeal(meal, MEAL);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> mealService.get(NOT_FOUND_MEAL, USER_ID_MEAL));
    }

    @Test
    public void delete() {
        mealService.delete(MEAL_ID, USER_ID_MEAL);
        assertNull(mealRepository.get(MEAL_ID, USER_ID_MEAL));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> mealService.delete(NOT_FOUND_MEAL, USER_ID_MEAL));
    }

    @Test
    public void getAll() {
        List<Meal> all = mealService.getAll(USER_ID_MEAL);
        assertMatchMeal(all, MEAL, MEAL2);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        mealService.update(updated, USER_ID_MEAL);
        assertMatchMeal(mealService.get(MEAL_ID, USER_ID_MEAL), updated);
    }

    @Test
    public void updateNotFound() {
        Meal updated = getUpdatedMeal();
        updated.setId(NOT_FOUND_MEAL);
        assertThrows(NotFoundException.class, () -> mealService.update(updated, USER_ID_MEAL));
    }

    @Test
    public void create() {
        Meal newMeal = getNewMeal();
        Meal created = mealService.create(newMeal, USER_ID_MEAL);
        Integer newId = created.getId();
        newMeal.setId(newId);
        MealTestData.assertMatchMeal(created, newMeal);
        MealTestData.assertMatchMeal(mealService.get(newId, USER_ID_MEAL), newMeal);
    }
}
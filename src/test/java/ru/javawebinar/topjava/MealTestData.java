package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestData {
    public static final int NOT_FOUND_MEAL = 50;
    public static final int MEAL_ID = 1;
    public static final int USER_ID_MEAL = START_SEQ;

    public static final Meal MEAL = new Meal(1, LocalDateTime.parse("31-01-2020 14:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), "dinner", 500);
    public static final Meal MEAL1 = new Meal(2, LocalDateTime.parse("31-01-2020 10:30", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), "breakfast", 750);
    public static final Meal MEAL2 = new Meal(3, LocalDateTime.parse("31-01-2020 19:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), "dinner2", 850);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.parse("30-01-2020 14:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), "new", 1555);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(MEAL);
        updated.setDescription("UpdatedDesc");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatchMeal(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Meal... expected) {
        assertMatchMeal(actual, Arrays.asList(expected));
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}

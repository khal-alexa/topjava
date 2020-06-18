package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
        List<Meal> meals = new ArrayList<>(repository.values());
        int userId = 1;
        for (int i = 0; i < meals.size(); i++) {
            if (i >= meals.size() / 2) {
                userId = 2;
            }
            Meal meal = meals.get(i);
            meal.setUserId(userId);
        }
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream()
                .filter(x -> x.getUserId() != null && x.getUserId() == SecurityUtil.getAuthUserID())
                .sorted((a1, a2) -> a2.getDateTime().compareTo(a1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> filterByDateAndTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        List<Meal> meals = new ArrayList<>(getAll());
        if (startDate == null) {
            startDate = DateTimeUtil.getMinDate(meals);
        }
        if (endDate == null) {
            endDate = DateTimeUtil.getMaxDate(meals);
        }
        if (startTime == null) {
            startTime = LocalTime.MIN;
        }
        if (endTime == null) {
            endTime = LocalTime.MAX;
        }

        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate = endDate;
        LocalTime finalStartTime = startTime;
        LocalTime finalEndTime = endTime;

        return meals.stream()
                .filter(a -> DateTimeUtil.isDateBetweenClosed(a.getDateTime().toLocalDate(), finalStartDate, finalEndDate))
                .filter(a -> DateTimeUtil.isTimeBetweenHalfOpen(a.getDateTime().toLocalTime(), finalStartTime, finalEndTime))
                .collect(Collectors.toList());
    }
}


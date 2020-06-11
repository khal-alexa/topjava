package ru.javawebinar.topjava.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepository implements Repository<Meal> {
    private static MealRepository mealRepository;
    private final Map<Integer, Meal> allMeals = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(allMeals.size());

    public static MealRepository getInstance() {
        if (mealRepository == null) {
            mealRepository = new MealRepository();
        }
        return mealRepository;
    }

    @Override
    public void add(Meal entity) {
        entity.setId(counter.incrementAndGet());
        allMeals.put(entity.getId(), entity);
    }

    @Override
    public void update(Meal entity) {
        int id = entity.getId();
        allMeals.put(id, entity);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(allMeals.values());
    }

    @Override
    public Optional<Meal> getById(int id) {
        if (allMeals.containsKey(id)) {
            return Optional.of(allMeals.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void remove(int id) {
        allMeals.remove(id);
    }
}

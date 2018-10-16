package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

     {
        MealsUtil.MEALS.forEach(meal -> save(1, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> ifAbsent = repository.computeIfAbsent(userId, id -> new HashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            ifAbsent.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return ifAbsent.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int userId, int mealId) {
         repository.get(userId).remove(mealId);
    }

    @Override
    public Meal get(int userId, int mealId) {
        return repository.get(userId).get(mealId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Collection<Meal> values = repository.get(userId).values();
        if(CollectionUtils.isEmpty(values)) {
            return Collections.emptyList();
        }
        return values.stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed()
                .thenComparing(Meal::getTime))
                .collect(Collectors.toList());
    }
}


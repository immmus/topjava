package ru.javawebinar.topjava.memory_storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStorage {
    private static MemoryStorage Instance = new MemoryStorage();
    private final Map<Long, Meal> meals = new ConcurrentHashMap<>();
    public static MemoryStorage getInstance() {
        return Instance;
    }

    private MemoryStorage() {
        meals.put(1L, new Meal(1L, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(2L,new Meal(2L, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(3L,new Meal(3L, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(4L,new Meal(4L, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(5L,new Meal(5L, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(6L,new Meal(6L, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public Map<Long, Meal> getMeals() {
        return meals;
    }
}

package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.memory_storage.MemoryStorage;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class MealRepositoryImpl implements MealRepository {
    private MemoryStorage memoryStorage = MemoryStorage.getInstance();
    private AtomicLong countId = new AtomicLong(memoryStorage.getMeals().size());


    @Override
    public Collection<Meal> findAll() {
        return memoryStorage.getMeals().values();
    }

    @Override
    public Optional<Meal> getById(Long id) {
        return Optional.of(memoryStorage.getMeals().get(id));
    }

    @Override
    public void delete(Long id) {
        Optional<Meal> meal = getById(id);
        meal.ifPresent(m -> memoryStorage.getMeals().remove(m.getId()));
    }

    @Override
    public boolean create(Meal meal) {
        if (meal == null){
            return false;
        }
        Long id = countId.incrementAndGet();
        meal.setId(id);
        memoryStorage.getMeals().put(id, meal);
        return true;
    }

    @Override
    public boolean update(Long id, LocalDateTime date, String description, int calories) {
        Optional<Meal> meal = getById(id);
        if (!meal.isPresent()){
            return false;
        }
        meal.get().setDateTime(date);
        meal.get().setDescription(description);
        meal.get().setCalories(calories);
        return true;
    }
}

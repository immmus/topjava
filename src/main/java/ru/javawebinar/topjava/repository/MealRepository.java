package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface MealRepository {
    Collection<Meal> findAll();
    Optional<Meal> getById(Long id);
    void delete(Long id);
    boolean create(Meal meal);
    boolean update(Long id, LocalDateTime date, String description, int calories);
}

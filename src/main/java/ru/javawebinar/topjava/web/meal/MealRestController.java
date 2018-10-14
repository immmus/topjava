package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal add(Meal meal) {
        int userId = SecurityUtil.authUserId();

        return service.create(userId, meal);
    }

    public void delete(int mealId) {
        int userId = SecurityUtil.authUserId();

        service.delete(userId, mealId);
    }

    public Meal get(int mealId) {
        int userId = SecurityUtil.authUserId();

        return service.get(userId, mealId);
    }

    public void update(Meal meal) {
        int userId = SecurityUtil.authUserId();
        service.update(userId, meal);
    }

    public List<MealWithExceed> getAll() {
        int userId = SecurityUtil.authUserId();
        return MealsUtil.getWithExceeded(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealWithExceed> getAllWithFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        if (StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)
                && StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)){
            return getAll();
        }
        return MealsUtil.getFilteredWithExceeded(
                service.getAll(userId),
                SecurityUtil.authUserCaloriesPerDay(),
                meal ->
                    DateTimeUtil.isBetween(meal.getDate(),
                            StringUtils.isEmpty(startDate) ? LocalDate.MIN : startDate,
                            StringUtils.isEmpty(endDate) ? LocalDate.MAX : endDate)
                &&
                            DateTimeUtil.isBetween(meal.getTime(),
                                    StringUtils.isEmpty(startTime) ? LocalTime.MIN : startTime,
                                    StringUtils.isEmpty(endTime) ? LocalTime.MAX : endTime)
                );
    }
}
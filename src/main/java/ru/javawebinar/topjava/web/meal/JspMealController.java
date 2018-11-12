package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.Util.orElse;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    private final MealService mealService;
    private int userId;

    @Autowired
    public JspMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public String mealsList(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) LocalTime startTime,
            @RequestParam(required = false) LocalTime endTime,
            Model model) {
        userId = SecurityUtil.authUserId();
        List<Meal> mealsDateFiltered = mealService.getBetweenDates(
                orElse(DateTimeUtil.parseLocalDate(startDate), DateTimeUtil.MIN_DATE),
                orElse(DateTimeUtil.parseLocalDate(endDate), DateTimeUtil.MAX_DATE), userId);

        List<MealTo> filteredWithExcess = MealsUtil.getFilteredWithExcess(mealsDateFiltered,
                SecurityUtil.authUserCaloriesPerDay(),
                orElse(startTime, LocalTime.MIN), orElse(endTime, LocalTime.MAX));
        model.addAttribute("meals", filteredWithExcess);
        return "meals";
    }

    @GetMapping(path = "/edit/{id}")
    public String update(Model model, @PathVariable Integer id) {
        userId = SecurityUtil.authUserId();
        Meal meal = mealService.get(id, userId);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping(path = "/edit/{id}")
    public String updateComplete(
            @PathVariable Integer id,
            @RequestParam String dateTime,
            @RequestParam String description,
            @RequestParam int calories) {
        userId = SecurityUtil.authUserId();
        Meal meal = mealService.get(id, userId);
        meal.setDateTime(LocalDateTime.parse(dateTime));
        meal.setDescription(description);
        meal.setCalories(calories);

        mealService.update(meal, userId);
        return "redirect:/meals";
    }

    @PostMapping(path = "/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userId = SecurityUtil.authUserId();
        mealService.delete(id, userId);

        return "redirect:/meals";
    }

    @GetMapping(path = "/edit")
    public String add(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping(path = "/edit")
    public String addComplete(@RequestParam String dateTime,
                              @RequestParam String description,
                              @RequestParam int calories) {
        userId = SecurityUtil.authUserId();
        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, calories);
        mealService.create(meal, userId);
        return "redirect:/meals";
    }
}

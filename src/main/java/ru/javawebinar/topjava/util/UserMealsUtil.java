package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 300),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 700),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 1000),
                new UserMeal(LocalDateTime.of(2017, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2017, Month.MAY, 30,13,0), "Обед", 1050),
                new UserMeal(LocalDateTime.of(2017, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 501));
        getFilteredWithExceeded(mealList, LocalTime.of(14, 0), LocalTime.of(21,59), 2000)
        .forEach(meal -> System.out.println(meal.getDateTime()+" : "+meal.getDescription()+" --- "+meal.isExceed()));
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded
            (List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        if (mealList.isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        Map<LocalDate, Integer> mapDayCalories = mealList.stream().collect(
                Collectors.groupingBy(userMeal ->
                                userMeal.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories))
        );
                return mealList.stream().filter(
                        meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                        .map(meal -> new UserMealWithExceed(
                                meal.getDateTime(),
                                meal.getDescription(),
                                meal.getCalories(),
                                mapDayCalories.get(meal.getDateTime().toLocalDate()) <= caloriesPerDay
                        )).collect(Collectors.toCollection(ArrayList::new));
            }
}

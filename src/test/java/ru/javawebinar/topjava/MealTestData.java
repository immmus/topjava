package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MealTestData {
    public static final Meal MEAL_USER_1 = new Meal
            (100000, LocalDateTime.of(2018, 10, 22, 10, 23, 34), "Завтрак", 500);
    public static final Meal MEAL_USER_2 = new Meal
            (100001, LocalDateTime.of(2018, 10, 22, 14, 24, 34), "Обед", 503);
    public static final Meal MEAL_USER_3 = new Meal
            (100002, LocalDateTime.of(2018, 10, 22, 19, 25, 34), "Ужин", 545);
public static final Meal MEAL_ADMIN_1 = new Meal
            (100003, LocalDateTime.of(2018, 10, 23, 10, 23, 34), "Завтрак", 500);
    public static final Meal MEAL_ADMIN_2 = new Meal
            (100004, LocalDateTime.of(2018, 10, 23, 14, 24, 34), "Обед", 1003);
    public static final Meal MEAL_ADMIN_3 = new Meal
            (100005, LocalDateTime.of(2018, 10, 23, 19, 25, 34), "Ужин", 545);
    public static final Meal MEAL_CREATE = new Meal(
            LocalDateTime.of(2018, 10, 28, 10, 23, 34),
            "New",
            123
    );

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}

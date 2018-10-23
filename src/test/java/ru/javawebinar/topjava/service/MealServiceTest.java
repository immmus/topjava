package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db-test.xml"
})
@RunWith(SpringRunner.class)
@Sql (value = {"classpath:dbTest/populateDB-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }
    @Autowired
    private MealService mealService;

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        mealService.get(100003, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        mealService.delete(100003, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal mealUpd = MEAL_USER_1;
        mealUpd.setDescription("Upd");
        mealUpd.setCalories(122);
        mealService.update(mealUpd, ADMIN_ID);
    }

    @Test
    public void update() {
        Meal mealUpd = MEAL_USER_1;
        mealUpd.setDescription("Upd");
        mealUpd.setCalories(1023);
        mealService.update(mealUpd, USER_ID);
        MealTestData.assertMatch(mealService.get(MEAL_USER_1.getId(), USER_ID), mealUpd);
    }

    @Test
    public void create() {
        mealService.create(MEAL_CREATE, USER_ID);
        MealTestData.assertMatch(mealService.get(100006, USER_ID), MEAL_CREATE);
    }

    @Test
    public void getAll() {
        List<Meal> all = mealService.getAll(ADMIN_ID);
        MealTestData.assertMatch(all, MEAL_ADMIN_3, MEAL_ADMIN_2, MEAL_ADMIN_1);
    }
}
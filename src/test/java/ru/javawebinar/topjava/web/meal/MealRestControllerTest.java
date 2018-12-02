package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.json.JsonUtil.writeValue;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

class MealRestControllerTest extends AbstractControllerTest {
    @Test
    void testGetAll() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));
    }
    @Test
    void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL + "/" +MEAL1_ID)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL1));
    }

    @Test
    void testDelete() throws Exception {
        TestUtil.print(
                mockMvc.perform(delete(REST_URL + "/" + MEAL1_ID))
                        .andExpect(status().isNoContent())
        );
        assertMatch(mealService.getAll(USER_ID),MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    void testCreate() throws Exception {
        LocalDateTime time =  LocalDateTime.now();
        Meal testMeal =  new Meal(time, "ТЕСТ", 15);
        TestUtil.print(
                mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(writeValue(testMeal))
        ).andDo(print())
        ).andExpect(status().isCreated());
    }
    @Test
    void testUpdate() throws Exception {
        LocalDateTime time =  LocalDateTime.now();
        Meal testMeal =  new Meal(MEAL1_ID, time, "Тест update", 35);
        TestUtil.print(
                mockMvc.perform(put(REST_URL + "/" + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(writeValue(testMeal))
        ).andDo(print())
        ).andExpect(status().isNoContent());

        assertMatch(mealService.get(MEAL1_ID, USER_ID), testMeal);
    }
    @Test
    void getBetween() throws Exception {
        mockMvc.perform(get(REST_URL+"/filter?" +"startDateTime=2015-05-30T10:00:00&endDateTime=2015-05-30T16:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL2, MEAL1));
    }
    @Test
    void getBetween2() throws Exception {
        mockMvc.perform(get(REST_URL+"/filter2?" +"startDate=2015-05-30&startTime=&endDate=2015-05-30&endTime="))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL3, MEAL2, MEAL1));
    }
}

package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final String LIST_MEALS = "/meals.jsp";
    private static final Logger log = getLogger(MealServlet.class);
    private MealRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new MealRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward mealsList");
        List<MealWithExceed> mealsList = MealsUtil
                .getFilteredWithExceeded(repository.findAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealsList", mealsList);
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    //    response.sendRedirect("users.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            repository.delete(id);
        }
        else if ("edit".equalsIgnoreCase(action)){
            Long id = Long.parseLong(request.getParameter("id"));
            LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            repository.update(id, date, description, calories);
        }
        else if ("create".equalsIgnoreCase(action)){
            LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            Meal meal =  new Meal(date, description, calories);
            repository.create(meal);
        }
     doGet(request, resp);
    }
}

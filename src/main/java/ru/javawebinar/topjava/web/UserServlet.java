package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (ConfigurableApplicationContext appCtx =
                     new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            userService = appCtx.getBean(UserService.class);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        if(SecurityUtil.authUserId() != -1){
            response.sendRedirect("meals");
            return;
        }
        List<User> users = userService.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        User user = userService.getByEmail(userEmail);
        if (user == null){
            request.setAttribute("userEmailError", "email is not correct");
            doGet(request, response);
        }
        boolean isCorrectPass = Objects.requireNonNull(user).getPassword().equals(password);
        if (!isCorrectPass){
            request.setAttribute("userCorrectEmail", userEmail);
            request.setAttribute("passwordError", "password is not correct");
            doGet(request, response);
        }
        SecurityUtil.setAuthUserId(user.getId());
        response.sendRedirect("meals");
    }
}

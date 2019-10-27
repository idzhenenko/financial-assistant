package ru.dzhenenko.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.dzhenenko.SpringContext;
import ru.dzhenenko.controller.AuthController;
import ru.dzhenenko.controller.Controller;
import ru.dzhenenko.controller.SecureController;
import ru.dzhenenko.json.AuthRequest;
import ru.dzhenenko.json.AuthResponse;
import ru.dzhenenko.json.ErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private ObjectMapper om = new ObjectMapper();
    private Map<String, Controller> controllers = new HashMap<>();
    private Map<String, SecureController> secureControllers = new HashMap<>();

    public MainServlet() {
        ApplicationContext context = SpringContext.getContext();

        for (String beanName : context.getBeanNamesForType(Controller.class)) {
            controllers.put(beanName, context.getBean(beanName, Controller.class));
        }

        for (String beanName : context.getBeanNamesForType(SecureController.class)) {
            secureControllers.put(beanName, context.getBean(beanName, SecureController.class));
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        res.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");

        try {
            Controller controller = controllers.get(uri);
            if (controller != null) {
                if (controller instanceof AuthController) {
                    AuthController authController = (AuthController) controller;

                    AuthRequest authRequest = om.readValue(req.getInputStream(), authController.getRequestClass());
                    AuthResponse authResponse = authController.handle(authRequest);

                    if (authResponse == null) {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("userId", authResponse.getId());

                        om.writeValue(res.getWriter(), authResponse);
                    }
                } else {
                    om.writeValue(
                            res.getWriter(),
                            controller.handle(om.readValue(req.getInputStream(), controller.getRequestClass()))
                    );
                }
            } else {
                SecureController secureController = secureControllers.get(uri);
                if (secureController != null) {
                    HttpSession session = req.getSession();
                    Long userId = (Long) session.getAttribute("userId");
                    if (userId == null) {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        om.writeValue(
                                res.getWriter(),
                                secureController.handle(
                                        om.readValue(req.getInputStream(), secureController.getRequestClass()),
                                        userId
                                )
                        );
                    }
                } else {
                    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            om.writeValue(res.getWriter(), new ErrorResponse(e.getMessage()));
        }
    }
}


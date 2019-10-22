package ru.dzhenenko.web;

import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.dzhenenko.SpringContext.getContext;

public class UserServlet extends HttpServlet {
    private final AuthService authService;

    public UserServlet() {
        this.authService = getContext().getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            writer.write("Access denied!!!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            UserDTO user = authService.getUserById(userId);
            writer.write(user.toString());
        }
    }
}

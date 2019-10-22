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

public class AuthServlet extends HttpServlet {
    private final AuthService authService;

    public AuthServlet() {
        this.authService = getContext().getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserDTO user = authService.auth(login, password);
        if (user == null) {
            writer.write("Access denied!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            writer.write(user.toString());
            HttpSession session = req.getSession();
            session.setAttribute("userId", UserDTO.getId());
        }
    }
}


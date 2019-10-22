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

public class RegistrationServlet extends HttpServlet {
    private final AuthService authService;

    public RegistrationServlet() {
        this.authService = getContext().getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();

        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDTO user = authService.registration(firstName, lastName, phone, email, password);

        if (user != null) {
            writer.write("Hello " + user.getFirstName() + " !");
            session.setAttribute("id", user.getId());
            writer.write(user.toString());
        }
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writer.write("Ошибка регистрации!");
        }
    }
}
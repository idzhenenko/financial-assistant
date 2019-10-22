package ru.dzhenenko.web;

import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static ru.dzhenenko.SpringContext.getContext;

public class ViewAccountServlet extends HttpServlet {
    private final AccountService accountService;

    public ViewAccountServlet() {
        this.accountService = getContext().getBean(AccountService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            writer.write("Your accounts is not found!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            writer.write("Accounts:");
            writer.write(" ");
            List<AccountDTO> accountDTO = null;
            try {
                accountDTO = accountService.viewAccount(userId);
            } catch (SQLException ignored) {
            }
            accountDTO.stream().map(dto -> " id = " + dto.getId()+ " " + dto.getName() + " " + "[ счет = " + dto.getBalance() + "]<br>").forEach(writer::write);
        }
    }
}


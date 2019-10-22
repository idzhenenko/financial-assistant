package ru.dzhenenko.web;

import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static ru.dzhenenko.SpringContext.getContext;

public class DeleteAccountServlet extends HttpServlet {
    private final AccountService accountService;

    public DeleteAccountServlet() {
        this.accountService = getContext().getBean(AccountService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String idAccount = req.getParameter("id");
        int id = Integer.parseInt(idAccount);

        if (userId == null) {
            writer.write("Your accounts is not found!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            AccountDTO accountDTO = null;
            try {
                accountDTO = accountService.removeAccount(id);
            } catch (SQLException ignored) {
            }
            writer.write("  Your account is successfully deleted!");
            writer.write(accountDTO.getName() + " = " + accountDTO.getBalance() + " id = " + accountDTO.getId());
        }
    }
}

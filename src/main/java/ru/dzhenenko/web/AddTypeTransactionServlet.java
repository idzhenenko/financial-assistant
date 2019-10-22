package ru.dzhenenko.web;

import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static ru.dzhenenko.SpringContext.getContext;

public class AddTypeTransactionServlet extends HttpServlet {
    private final AccountTypeService accountTypeService;

    public AddTypeTransactionServlet() {
        this.accountTypeService = getContext().getBean(AccountTypeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String name = req.getParameter("name");

        if (userId == null) {
            writer.write("Error!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            AccountTypeDTO accountTypeDTO = null;
            try {
                accountTypeDTO = accountTypeService.createTypeAccount(name);
            } catch (SQLException ignored) {
            }
            writer.write("  Your type account is successfully create!");
            writer.write(accountTypeDTO.getName());
            writer.write(accountTypeDTO.toString());
        }
    }
}

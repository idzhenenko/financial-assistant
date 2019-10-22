package ru.dzhenenko.web;

import ru.dzhenenko.service.TransactionDTO;
import ru.dzhenenko.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static ru.dzhenenko.SpringContext.getContext;

public class TransactionServlet extends HttpServlet {
    private final TransactionService transactionService;

    public TransactionServlet() {
        this.transactionService = getContext().getBean(TransactionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String sourceAcc = req.getParameter("source");
        int sourceAccount = Integer.parseInt(sourceAcc);
        String targetAcc = req.getParameter("target");
        int targetAccount = Integer.parseInt(targetAcc);
        String idTypeTran = req.getParameter("type");
        int idTypeTransaction = Integer.parseInt(idTypeTran);
        String amountTransaction = req.getParameter("balance");
        int amount = Integer.parseInt(amountTransaction);
        String idCat = req.getParameter("id");
        int idCategory = Integer.parseInt(idCat);

        if (userId == null) {
            writer.write("Error!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            TransactionDTO transactionDTO = null;
            try {
                transactionDTO = transactionService.insertTransaction(sourceAccount, targetAccount,
                        idTypeTransaction, amount, idCategory, userId);
            } catch (SQLException ignored) {
            }
            writer.write("Транзакция успешно создана!");
        }
    }
}

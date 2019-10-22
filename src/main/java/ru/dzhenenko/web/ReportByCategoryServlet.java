package ru.dzhenenko.web;

import ru.dzhenenko.service.ReportByCategoryDTO;
import ru.dzhenenko.service.ReportByCategoryService;

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

public class ReportByCategoryServlet extends HttpServlet {
    private final ReportByCategoryService reportByCategoryService;

    public ReportByCategoryServlet() {
        this.reportByCategoryService = getContext().getBean(ReportByCategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        String startDay = req.getParameter("startDay");
        String endDay = req.getParameter("endDay");

        if (userId == null) {
            writer.write("Error!");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            writer.write("Report:");
            List<ReportByCategoryDTO> reportByCategoryDTO = null;
            try {
                reportByCategoryDTO = reportByCategoryService.viewReportCategory(userId, startDay, endDay);
            } catch (SQLException ignored) {
            }
            for (ReportByCategoryDTO dto : reportByCategoryDTO) {
                writer.write(dto.getName() + "[ счет = " + dto.getAmount() + "]<br>");
            }
        }
    }
}

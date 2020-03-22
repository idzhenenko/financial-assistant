package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.TransactionDTO;
import ru.dzhenenko.service.TransactionService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.InsertNewTransactionForm;

import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class InsertNewTransactionController {
    private final TransactionService transactionService;
    private final AuthService authService;

    @GetMapping("/insert-new-transaction")
    public String getTransactionNew(Model model) {

        model.addAttribute("form", new InsertNewTransactionForm());

        return "addNewTransactionGet";
    }

    @PostMapping("/insert-new-transaction")
    public String postAccount(@ModelAttribute("form") @Valid InsertNewTransactionForm form, BindingResult result,
                              Model model) throws SQLException {
        if (!result.hasErrors()) {
            UserDTO userId = authService.currentUser();

            UserDTO userDTO = authService.getUserById(userId.getId());

            TransactionDTO transactionDTO = transactionService.insertTransaction(form.getSourceAccount(),
                    form.getTargetAccount(),form.getTypeTransaction(), form.getIdCategory(), form.getAmount(),
                    userDTO.getId());

            model.addAttribute("sourceAccount", transactionDTO.getSourceAccount());
            model.addAttribute("targetAccount", transactionDTO.getTargetAccount());
            model.addAttribute("amount", transactionDTO.getAmount());
            model.addAttribute("typeTransaction", transactionDTO.getTypeTransaction());
            model.addAttribute("idCategory", transactionDTO.getIdCategory());

            return "addNewTransaction";
        }
        model.addAttribute("form", form);

        return "addNewTransaction";
    }
}

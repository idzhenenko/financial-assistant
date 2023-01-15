package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.TransactionRequest;
import ru.dzhenenko.api.json.TransactionResponse;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.TransactionDTO;
import ru.dzhenenko.service.TransactionService;
import ru.dzhenenko.service.UserDTO;

import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthService authService;

    @PostMapping("/add-transaction")
    public @ResponseBody
    ResponseEntity<TransactionResponse> addTypeTransaction(@RequestBody @Valid TransactionRequest request) throws SQLException {
        UserDTO userDTO = authService.currentUser();
        TransactionDTO transaction = transactionService.insertTransaction(request.getSourceAccount(),
                request.getTargetAccount(), request.getAmount(), request.getTypeTransaction(), request.getIdCategory(),
                userDTO.getId());
        if (transaction != null) {
            return ok(new TransactionResponse(
                    transaction.getId(),
                    transaction.getSourceAccount(),
                    transaction.getTargetAccount(),
                    transaction.getCreateDate(),
                    transaction.getTypeTransaction(),
                    transaction.getAmount()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}

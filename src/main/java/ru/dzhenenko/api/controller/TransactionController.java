package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.TransactionRequest;
import ru.dzhenenko.api.json.TransactionResponse;
import ru.dzhenenko.service.TransactionDTO;
import ru.dzhenenko.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/add-transaction")
    public @ResponseBody
    ResponseEntity<TransactionResponse> viewListAccount(@RequestBody @Valid TransactionRequest request,
                                                        HttpServletRequest httpServletRequest) throws SQLException {

        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        TransactionDTO transactionDTO = transactionService.insertTransaction(request.getSourceAccount(),
                request.getTargetAccount(), request.getAmount(), request.getTypeTransaction(), request.getIdCategory(),
                request.getId());

        if (transactionDTO != null) {
            return ok(new TransactionResponse(transactionDTO.getId(), transactionDTO.getSourceAccount(),
                    transactionDTO.getTargetAccount(), transactionDTO.getCreateDate(),
                    transactionDTO.getTypeTransaction(), transactionDTO.getAmount()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}

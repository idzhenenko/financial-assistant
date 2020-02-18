package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.DeleteTypeTransactionRequest;
import ru.dzhenenko.api.json.DeleteTypeTransactionResponse;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DeleteTypeTransactionController {
    private final AccountTypeService accountTypeService;

    @PostMapping("/delete-type-account")
    public @ResponseBody
    ResponseEntity<DeleteTypeTransactionResponse> deleteAccount(@RequestBody @Valid DeleteTypeTransactionRequest request,
                                                        HttpServletRequest httpServletRequest) throws SQLException {

        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        AccountTypeDTO accountTypeDTO = accountTypeService.removeAccountType(request.getId());

        if (userId != null) {
            return ok(new DeleteTypeTransactionResponse(accountTypeDTO.getId(), accountTypeDTO.getName()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.EditTypeTransactionRequest;
import ru.dzhenenko.api.json.EditTypeTransactionResponse;
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
public class EditTypeTransactionController {
    private final AccountTypeService accountTypeService;

    @PostMapping("/edit-type-account")
    public @ResponseBody
    ResponseEntity<EditTypeTransactionResponse> deleteAccount(@RequestBody @Valid EditTypeTransactionRequest request,
                                                              HttpServletRequest httpServletRequest) throws SQLException {
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        AccountTypeDTO accountTypeDTO = accountTypeService.editingAccountType(request.getName(), request.getId());

        if (userId != null) {
            return ok(new EditTypeTransactionResponse(accountTypeDTO.getId(), accountTypeDTO.getName()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
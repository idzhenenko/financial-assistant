package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.EditTypeTransactionRequest;
import ru.dzhenenko.api.json.EditTypeTransactionResponse;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EditTypeTransactionController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @PostMapping("/edit-type-account")
    public @ResponseBody
    ResponseEntity<EditTypeTransactionResponse> editAccount(@RequestBody @Valid EditTypeTransactionRequest request) throws SQLException {
        UserDTO userId = authService.currentUser();
        AccountTypeDTO accountTypeDTO = accountTypeService.editingAccountType(request.getName(), request.getId());
        if (userId != null) {
            return ok(new EditTypeTransactionResponse(
                    accountTypeDTO.getId(),
                    accountTypeDTO.getName()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
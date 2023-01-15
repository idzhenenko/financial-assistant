package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.AddTypeTransactionRequest;
import ru.dzhenenko.api.json.AddTypeTransactionResponse;
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
public class AddTypeTransactionController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @PostMapping("/add-type-account")
    public @ResponseBody
    ResponseEntity<AddTypeTransactionResponse> addTypeAccount(@RequestBody @Valid AddTypeTransactionRequest request) throws SQLException {
        UserDTO userId = authService.currentUser();
        AccountTypeDTO accountType = accountTypeService.createTypeAccount(request.getName(), userId.getId());
        if (userId != null) {
            return ok(new AddTypeTransactionResponse(
                    accountType.getId(),
                    accountType.getName()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
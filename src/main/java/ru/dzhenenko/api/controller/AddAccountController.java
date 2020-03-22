package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.AddAccountRequest;
import ru.dzhenenko.api.json.AddAccountResponse;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AddAccountController {
    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping("/add-account")
    public @ResponseBody
    ResponseEntity<AddAccountResponse> addAccount(@RequestBody @Valid AddAccountRequest addAccountRequest) throws SQLException {

        UserDTO userId = authService.currentUser();

        AccountDTO accountDTO = accountService.createAccount(addAccountRequest.getName(), addAccountRequest.getBalance(),
                addAccountRequest.getId());

        if (userId != null) {
            return ok(new AddAccountResponse(accountDTO.getId(), accountDTO.getName(), accountDTO.getBalance(),
                    accountDTO.getUserId()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
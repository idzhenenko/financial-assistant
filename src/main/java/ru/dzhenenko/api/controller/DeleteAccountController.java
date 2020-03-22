package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.converter.ServiceAccountToResponseConverter;
import ru.dzhenenko.api.json.DeleteAccountRequest;
import ru.dzhenenko.api.json.DeleteAccountResponse;
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
public class DeleteAccountController {
    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping("/delete-account")
    public @ResponseBody
    ResponseEntity<DeleteAccountResponse> deleteAccount(@RequestBody @Valid DeleteAccountRequest deleteAccountRequest) throws SQLException {

        UserDTO userId = authService.currentUser();

        AccountDTO accountDTO = accountService.removeAccount(deleteAccountRequest.getId());

        if (userId != null) {
            return ok(new DeleteAccountResponse(accountDTO.getName(), deleteAccountRequest.getBalance(),
                    accountDTO.getId()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
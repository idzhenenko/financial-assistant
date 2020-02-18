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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DeleteAccountController {
    private final ServiceAccountToResponseConverter converter;
    private final AccountService accountService;

    @PostMapping("/delete-account")
    public @ResponseBody
    ResponseEntity<DeleteAccountResponse> deleteAccount(@RequestBody @Valid DeleteAccountRequest deleteAccountRequest,
                                                        HttpServletRequest httpServletRequest) throws SQLException {

        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        AccountDTO accountDTO = accountService.removeAccount(deleteAccountRequest.getId());

        if (userId != null) {
            return ok(new DeleteAccountResponse(accountDTO.getName(), deleteAccountRequest.getBalance(),
                    accountDTO.getId()));
        }
        return status(HttpStatus.BAD_REQUEST).build();
    }
}
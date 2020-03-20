package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.converter.ServiceAccountToResponseConverter;
import ru.dzhenenko.api.json.AccountRequest;
import ru.dzhenenko.api.json.AccountResponse;
import ru.dzhenenko.repository.ServiceAccountRepository;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountController {
    private final ServiceAccountRepository serviceAccountRepository;
    private final ServiceAccountToResponseConverter converter;
    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping("/view-account")
    public @ResponseBody
    ResponseEntity<List<AccountResponse>> viewListAccount(@RequestBody @Valid AccountRequest accountRequest,
                                                          HttpServletRequest httpServletRequest) throws SQLException {


        //Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        UserDTO userId = authService.currentUser();

        List<AccountDTO> accountDTO = accountService.viewAccount(userId.getId());

        return ok(accountDTO.stream()
                .map(accountDTO1 -> new AccountResponse(accountDTO1.getId(), accountDTO1.getName(),
                        accountDTO1.getBalance(), accountDTO1.getUserId()))
                .collect(Collectors.toList()));
    }
}


package ru.dzhenenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.AccountRequest;
import ru.dzhenenko.json.AccountResponse;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service("/account")
@RequiredArgsConstructor
public class AccountController implements SecureController<AccountRequest, List<AccountResponse>> {
    private final AccountService accountService;

    @Override
    public List<AccountResponse> handle(AccountRequest request, Long userId) throws Exception {
        List<AccountDTO> accountDTO = accountService.viewAccount(userId);
        return accountDTO.stream()
                .map(accountDTO1 -> new AccountResponse(accountDTO1.getId(), accountDTO1.getName(), accountDTO1.getBalance(), accountDTO1.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public Class<AccountRequest> getRequestClass() {
        return AccountRequest.class;
    }
}
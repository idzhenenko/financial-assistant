package ru.dzhenenko.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.AddAccountRequest;
import ru.dzhenenko.json.AddAccountResponse;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;

@Service("/addAccount")
@AllArgsConstructor
public class AddAccountController implements SecureController<AddAccountRequest, AddAccountResponse> {
    private final AccountService accountService;

    @Override
    public AddAccountResponse handle(AddAccountRequest request, Long userId) throws Exception {
        AccountDTO accountDTO = accountService.createAccount(request.getName(), request.getBalance(), request.getId());
        if (accountDTO != null) {
            return new AddAccountResponse(accountDTO.getId(), accountDTO.getName(), accountDTO.getBalance(), accountDTO.getUserId());
        }
        return null;
    }

    @Override
    public Class<AddAccountRequest> getRequestClass() {
        return AddAccountRequest.class;
    }
}

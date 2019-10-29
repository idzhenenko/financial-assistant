package ru.dzhenenko.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.AddTypeTransactionRequest;
import ru.dzhenenko.json.AddTypeTransactionResponse;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;

@Service("/addTypeAccount")
@AllArgsConstructor
public class AddTypeTransactionController implements SecureController<AddTypeTransactionRequest, AddTypeTransactionResponse> {
    private final AccountTypeService accountTypeService;

    @Override
    public AddTypeTransactionResponse handle(AddTypeTransactionRequest request, Long userId) throws Exception {
        AccountTypeDTO accountTypeDTO = accountTypeService.createTypeAccount(request.getName());
        if (accountTypeDTO != null) {
            return new AddTypeTransactionResponse(accountTypeDTO.getId(), accountTypeDTO.getName());
        }
        return null;
    }

    @Override
    public Class<AddTypeTransactionRequest> getRequestClass() {
        return AddTypeTransactionRequest.class;
    }
}


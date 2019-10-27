package ru.dzhenenko.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.DeleteTypeTransactionRequest;
import ru.dzhenenko.json.DeleteTypeTransactionResponse;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;

@Service("/deleteTypeAccount")
@AllArgsConstructor
public class DeleteTypeTransactionController implements SecureController<DeleteTypeTransactionRequest, DeleteTypeTransactionResponse>{
    private final AccountTypeService accountTypeService;

    @Override
    public DeleteTypeTransactionResponse handle(DeleteTypeTransactionRequest request, Long userId) throws Exception {
        AccountTypeDTO accountTypeDTO = accountTypeService.removeAccountType((int) request.getId());
        if (accountTypeDTO != null) {
            return new DeleteTypeTransactionResponse(accountTypeDTO.getId(), accountTypeDTO.getName());
        }
        return null;
    }

    @Override
    public Class<DeleteTypeTransactionRequest> getRequestClass() {
        return DeleteTypeTransactionRequest.class;
    }
}

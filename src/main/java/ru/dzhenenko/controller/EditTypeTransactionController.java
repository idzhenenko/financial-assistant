package ru.dzhenenko.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.EditTypeTransactionRequest;
import ru.dzhenenko.json.EditTypeTransactionResponse;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;

@Service("/editTypeAccount")
@AllArgsConstructor
public class EditTypeTransactionController implements SecureController<EditTypeTransactionRequest, EditTypeTransactionResponse> {
    private final AccountTypeService accountTypeService;

    @Override
    public EditTypeTransactionResponse handle(EditTypeTransactionRequest request, Long userId) throws Exception {
        AccountTypeDTO accountTypeDTO = accountTypeService.editingAccountType(request.getName(), (int) request.getId());
        if (accountTypeDTO != null) {
            return new EditTypeTransactionResponse(request.getId(), request.getName());
        }
        return null;
    }

    @Override
    public Class<EditTypeTransactionRequest> getRequestClass() {
        return EditTypeTransactionRequest.class;
    }
}

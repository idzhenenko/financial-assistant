package ru.dzhenenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.TransactionRequest;
import ru.dzhenenko.json.TransactionResponse;
import ru.dzhenenko.service.TransactionDTO;
import ru.dzhenenko.service.TransactionService;

@Service("/transaction")
@RequiredArgsConstructor
public class TransactionController implements SecureController<TransactionRequest, TransactionResponse> {
    private final TransactionService transactionService;

    @Override
    public TransactionResponse handle(TransactionRequest request, Long userId) throws Exception {
        TransactionDTO trDTO = transactionService.insertTransaction(request.getId(),
                request.getSourceAccount(), request.getTargetAccount(), request.getAmount(),
                request.getTypeTransaction(), request.getId());
        if (trDTO != null) {
            return new TransactionResponse(trDTO.getId(), trDTO.getSourceAccount(), trDTO.getTargetAccount(),
                    trDTO.getCreateDate(), trDTO.getAmount(), trDTO.getTypeTransaction());
        }
        return null;
    }

    @Override
    public Class<TransactionRequest> getRequestClass() {
        return TransactionRequest.class;
    }
}
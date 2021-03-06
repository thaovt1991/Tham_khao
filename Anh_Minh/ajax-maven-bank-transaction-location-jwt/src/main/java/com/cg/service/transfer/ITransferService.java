package com.cg.service.transfer;

import com.cg.model.Transfer;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ITransferService extends IGeneralService<Transfer> {
    List<ITransferDTO> findAllITransferDTO();

    List<TransactionDTO> findAllTransactionDTO();

    List<ITransactionDTO> findAllITransactionDTO();

    Optional<TransferDTO> findTransferDTOById(Long id);

    Optional<SumFeesAmountDTO> sumFeesAmount();
}

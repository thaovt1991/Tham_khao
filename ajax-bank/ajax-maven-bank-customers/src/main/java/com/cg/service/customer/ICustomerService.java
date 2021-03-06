package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    Iterable<CustomerDTO> findAllCustomerDTO();

    Customer getById(Long id);

    Boolean existsByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Iterable<Customer> findAllByDeletedIsFalse();

    CustomerDTO getByIdWithCustomerDTO(Long id);

    Optional<DepositDTO> findByIdWithDepositDTO(Long id);

    Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id);

    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSender(Long id);

    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(Long id);

    CustomerDTO doDeposit(DepositDTO depositDTO);

    CustomerDTO doWithdraw(WithdrawDTO withdrawDTO);

    void doTransfer(TransferDTO transferDTO, Customer sender, Customer recipient) ;

    void incrementBalance(BigDecimal balance, Long id);

    void reduceBalance(BigDecimal balance, Long id);

}

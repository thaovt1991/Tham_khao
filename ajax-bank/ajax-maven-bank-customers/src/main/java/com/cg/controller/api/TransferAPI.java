package com.cg.controller.api;

import com.cg.model.dto.ITransferDTO;
import com.cg.model.dto.SumFeesAmountDTO;
import com.cg.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfers")
public class TransferAPI {

    @Autowired
    private ITransferService transferService;

    @GetMapping
    public ResponseEntity<Iterable<ITransferDTO>> findAll() {
        try {
            List<ITransferDTO> iTransferDTOS = transferService.findAllByITransferDTO();
//            Optional<SumFeesAmountDTO> sumFeesAmount = transferService.sumFeesAmount();

            if (iTransferDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(iTransferDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/sum-fees-amount")
    public ResponseEntity<SumFeesAmountDTO> sumFeesAmount() {
        Optional<SumFeesAmountDTO> sumFeesAmount = transferService.sumFeesAmount();

        return sumFeesAmount.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

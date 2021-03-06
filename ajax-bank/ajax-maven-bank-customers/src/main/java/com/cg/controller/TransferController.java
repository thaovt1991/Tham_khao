package com.cg.controller;

import com.cg.model.dto.ITransferDTO;
import com.cg.model.dto.SumFeesAmountDTO;
import com.cg.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private ITransferService transferService;

    @GetMapping
    public ModelAndView listTransfers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/transfer/list");
        return modelAndView;
    }
}

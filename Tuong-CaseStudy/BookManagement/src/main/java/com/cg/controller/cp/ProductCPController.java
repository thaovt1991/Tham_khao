package com.cg.controller.cp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cp/products")
public class ProductCPController {

    @GetMapping
    public ModelAndView showCpProductIndex() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/product/list");

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCpProductCreate() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/product/create");

        return modelAndView;
    }
}

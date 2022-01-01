package com.cg.controller.cp;

import com.cg.model.Category;
import com.cg.model.CategoryGroup;
import com.cg.service.category.CategoryService;
import com.cg.service.categoryGroup.CategoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cp/categoryGroups")
public class CategoryGroupCPController {
    @Autowired
    private CategoryGroupService categoryGroupService;

    @GetMapping
    public ModelAndView showCpCategoryGroupIndex() {
        List<CategoryGroup> categoryGroups = categoryGroupService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/categoryGroup/list");
        modelAndView.addObject("categoryGroup", categoryGroups);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCpCategoryGroupCreate() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/categoryGroup/create");
        return modelAndView;
    }


}

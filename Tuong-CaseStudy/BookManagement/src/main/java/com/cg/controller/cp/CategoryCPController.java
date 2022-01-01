package com.cg.controller.cp;

import com.cg.model.Category;
import com.cg.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cp/categories")
public class CategoryCPController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView showCpCategoryIndex() {
        List<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCpCategoryCreate() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ModelAndView save(@Validated @ModelAttribute("category") Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/category/create");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("script", true);
        }
        else {
            try {

                categoryService.save(category);

                modelAndView.addObject("category", new Category());
                modelAndView.addObject("success", "Successfully added new category");
            } catch (Exception e) {
                e.printStackTrace();
                modelAndView.addObject("error", "Invalid data, please contact system administrator");
            }
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showCpCategoryEdit(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/cp/category/edit");
        modelAndView.addObject("category", category.get());
        return modelAndView;
    }


    @PostMapping("/edit/{id}")
    public ModelAndView updateCpCategory(@PathVariable Long id, @Validated @ModelAttribute("category") Category category,
                                   BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("cp/category/edit");

        if(bindingResult.hasFieldErrors()) {
            modelAndView.addObject("script", true);

        } else {
            categoryService.save(category);
            modelAndView.addObject("category", category);
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showCpCategoryDelete(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/cp/category/edit");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteCpCategory(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/cp/category/delete");
        Optional<Category> category = categoryService.findById(id);
        category.get().setDeleted(true);
        categoryService.save(category.get());
        return modelAndView;
    }
}

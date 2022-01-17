package com.cg.controller.cp;

import com.cg.model.Blog;
import com.cg.model.dto.BlogDTO;
import com.cg.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cp/blogs")
public class BlogCPController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ModelAndView showCpProductList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/blog/list");

        List<BlogDTO> blogDTOS = blogService.findAllBlogDTOOrderByTsDesc();
        modelAndView.addObject("blogDTOS", blogDTOS);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCpProductCreate() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/blog/create");

        List<Blog> blogs = blogService.findAll();

        modelAndView.addObject("categories", blogs);

        return modelAndView;
    }

    @GetMapping(value = "/edit/{slug}")
    public ModelAndView showCpProductEdit(@PathVariable String slug, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();

        Boolean existSlug = blogService.existsBySlugEquals(slug);

        if (!existSlug) {
            modelAndView.setViewName("error/404");
        }
        else {
            modelAndView.setViewName("cp/blog/edit");

            modelAndView.addObject("slug", slug);
        }

        return modelAndView;
    }
}

package com.cg.controller.cp.api;


import com.cg.exception.DataInputException;
import com.cg.exception.SlugExistsException;
import com.cg.model.Blog;
import com.cg.model.DescriptionMedia;
import com.cg.model.dto.BlogDTO;
import com.cg.model.dto.BlogDataForm;
import com.cg.model.dto.IBlogDTO;
import com.cg.service.BlogService;
import com.cg.service.DescriptionMediaService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cp/api/blogs")
public class BlogCPAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private BlogService blogService;

    @Autowired
    private DescriptionMediaService descriptionMediaService;

    @GetMapping
    public ResponseEntity<Iterable<?>> findAll() {
        try {
            List<Blog> blogs = blogService.findAll();

            if (blogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> findBySlug(@PathVariable String slug) {
        Optional<BlogDTO> blogDTO = blogService.findBlogDTOBySlug(slug);

        if (!blogDTO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(blogDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid BlogDataForm blogDataForm, BindingResult bindingResult) {

        MultipartFile multipartFile = blogDataForm.getFile();

        if (multipartFile == null)
            throw new DataInputException("The Avatar file is required");

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        String slug = AppUtils.removeNonAlphanumeric(blogDataForm.getTitle());

        Boolean existSlug = blogService.existsBySlugEquals(slug);

        if (existSlug) {
            throw new SlugExistsException("The Blog title already exists");
        }
        else {
            try {
                blogDataForm.setSlug(slug);

                blogService.create(blogDataForm);

                return new ResponseEntity<>(HttpStatus.CREATED);

            } catch (DataIntegrityViolationException e) {
                throw new DataInputException("Blog information is not valid, please check the information again");
            }
        }
    }

    @PostMapping(value = "/update-avatar/{slug}")
    public ResponseEntity<?> updateAvatar(@PathVariable String slug, @RequestParam MultipartFile file) {

        Optional<BlogDTO> optionalBlogDTO = blogService.findBlogDTOBySlug(slug);

        if (!optionalBlogDTO.isPresent())
            throw new DataInputException("Blog information is not valid");

        try {
            blogService.updateAvatar(optionalBlogDTO.get().toBlog(), file);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Blog avatar information is not valid, please check the information again");
        }
    }


    @PostMapping(value = "/{slug}")
    public ResponseEntity<?> updateInfo(@PathVariable String slug, @Valid @RequestBody BlogDTO blogDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        Optional<BlogDTO> optionalBlogDTO = blogService.findBlogDTOBySlug(slug);

        if (!optionalBlogDTO.isPresent())
            throw new DataInputException("Blog title is not valid");

        String newSlug = AppUtils.removeNonAlphanumeric(blogDTO.getTitle());

        Boolean existNewSlug = blogService.existsBySlugEqualsAndIdIsNot(newSlug, optionalBlogDTO.get().getId());

        if (existNewSlug)
            throw new SlugExistsException("The Blog title already exists");

        try {
            blogDTO.setId(optionalBlogDTO.get().getId());
            blogDTO.setSlug(newSlug);
            blogDTO.setBlogMedia(optionalBlogDTO.get().getBlogMedia());
            blogService.updateInfo(blogDTO);

            IBlogDTO iBlogDTO =  blogService.findIBlogDTOById(blogDTO.getId());

            return new ResponseEntity<>(iBlogDTO, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Blog update information is not valid, please check the information again");
        }
    }


    @PostMapping("/descriptions/upload-images")
    public ResponseEntity<?> uploadImageDescription(DescriptionMedia descriptionMedia) {

        try {
            DescriptionMedia createdDescriptionMedia = descriptionMediaService.create(descriptionMedia);

            return new ResponseEntity<>(createdDescriptionMedia.getFileUrl(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Description Media information is not valid, please check the information again");
        }
    }
}

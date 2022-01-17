package com.cg.service;

import com.cg.model.Blog;
import com.cg.model.BlogMedia;
import com.cg.model.dto.BlogDataForm;
import com.cg.model.dto.IBlogDTO;
import com.cg.model.dto.BlogDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BlogService {

    List<Blog> findAll();

    Optional<Blog> findById(String id);

    List<BlogDTO> findAllBlogDTO();

    List<BlogDTO> findAllBlogDTOOrderByTsDesc();

    IBlogDTO findIBlogDTOById(String id);

    Optional<BlogDTO> findBlogDTOBySlug(String slug);

    Boolean existsBySlugEquals(String slug);

    Boolean existsBySlugEqualsAndIdIsNot(String slug, String id);

    Blog create(BlogDataForm blogDataForm);

    void updateAvatar(Blog blog, MultipartFile file);

    void updateInfo(BlogDTO blogDTO);

    void delete(Blog blog) throws IOException;


}

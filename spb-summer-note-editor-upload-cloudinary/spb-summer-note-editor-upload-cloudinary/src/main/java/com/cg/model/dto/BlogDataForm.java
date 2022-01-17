package com.cg.model.dto;

import com.cg.model.Blog;
import com.cg.model.BlogMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDataForm {

    private String id;

    @Pattern(regexp = "^[\\pL .,0-9()_:-]{2,50}$", message = "The Blog title must contain between 2-50 characters and no special characters")
    @NotBlank(message = "The Blog title cannot be empty")
    private String title;

    private String slug;
    private String description;

    private BlogMedia blogMedia;

    private MultipartFile file;

    public Blog toBlog() {
        return new Blog()
                .setId(id)
                .setTitle(title)
                .setSlug(slug)
                .setDescription(description)
                .setBlogMedia(blogMedia);
    }

}

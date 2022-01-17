package com.cg.model.dto;

import com.cg.model.Blog;
import com.cg.model.BlogMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogDTO {

    private String id;

    @Pattern(regexp = "^[\\pL .,0-9()_:-]{2,50}$", message = "The Blog title must contain between 2-50 characters and no special characters")
    @NotBlank(message = "The Blog title cannot be empty")
    private String title;

    private String slug;
    private String description;

    private BlogMediaDTO blogMedia;

    private Long ts;

    public BlogDTO(String id, String title, String slug, String description, BlogMedia blogMedia, Long ts) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.blogMedia = blogMedia.toBlogMediaDTO();
        this.ts = ts;
    }


    @Override
    public String toString() {
        return "BlogDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Blog toBlog() {
        return new Blog()
                .setId(id)
                .setTitle(title)
                .setSlug(slug)
                .setDescription(description)
                .setBlogMedia(blogMedia.toBlogMedia());
    }

}

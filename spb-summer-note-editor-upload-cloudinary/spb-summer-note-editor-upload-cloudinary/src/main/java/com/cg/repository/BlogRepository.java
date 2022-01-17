package com.cg.repository;

import com.cg.model.Blog;
import com.cg.model.dto.BlogDTO;
import com.cg.model.dto.IBlogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    Boolean existsBySlugEquals(String slug);

    Boolean existsBySlugEqualsAndIdIsNot(String slug, String id);


    @Query("SELECT NEW com.cg.model.dto.BlogDTO (" +
                "b.id, " +
                "b.title, " +
                "b.slug, " +
                "b.description, " +
                "b.blogMedia, " +
                "b.ts " +
            ") " +
            "FROM Blog b " +
            "WHERE b.slug = :slug"
    )
    Optional<BlogDTO> findBlogDTOBySlug(@Param("slug") String slug);


    @Query("SELECT NEW com.cg.model.dto.BlogDTO (" +
                "b.id, " +
                "b.title, " +
                "b.slug, " +
                "b.description, " +
                "b.blogMedia, " +
                "b.ts " +
            ") " +
            "FROM Blog b "
    )
    List<BlogDTO> findAllBlogDTO();


    @Query("SELECT NEW com.cg.model.dto.BlogDTO (" +
                "b.id, " +
                "b.title, " +
                "b.slug, " +
                "b.description, " +
                "b.blogMedia, " +
                "b.ts " +
            ") " +
            "FROM Blog b " +
            "ORDER BY b.ts DESC "
    )
    List<BlogDTO> findAllBlogDTOOrderByTsDesc();


    @Query("SELECT " +
            "pm.blog.id AS id, " +
            "pm.blog.title AS title, " +
            "pm.blog.slug AS slug, " +
            "pm.blog.description AS description, " +
            "pm.id AS fileId, " +
            "pm.fileName AS fileName, " +
            "pm.fileFolder AS fileFolder, " +
            "pm.fileUrl AS fileUrl, " +
            "pm.fileType AS fileType, " +
            "pm.cloudId AS cloudId " +
        "FROM BlogMedia pm " +
        "WHERE pm.blog.id = :id"
    )
    IBlogDTO findIBlogDTOById(@Param("id") String id);

}

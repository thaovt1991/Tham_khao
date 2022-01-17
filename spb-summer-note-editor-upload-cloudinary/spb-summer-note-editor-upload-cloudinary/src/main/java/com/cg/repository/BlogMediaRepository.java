package com.cg.repository;

import com.cg.model.Blog;
import com.cg.model.BlogMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BlogMediaRepository extends JpaRepository<BlogMedia, String> {

    Optional<BlogMedia> findByBlog(Blog blog);
}

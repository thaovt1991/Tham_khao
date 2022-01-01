package com.cg.repository;


import com.cg.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Boolean existsBySlugEquals(String slug);

    Boolean existsBySlugEqualsAndIdIsNot(String slug, long id);
}

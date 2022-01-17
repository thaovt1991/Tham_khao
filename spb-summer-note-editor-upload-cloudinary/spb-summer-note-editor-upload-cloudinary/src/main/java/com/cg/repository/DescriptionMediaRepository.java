package com.cg.repository;

import com.cg.model.DescriptionMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DescriptionMediaRepository extends JpaRepository<DescriptionMedia, String> {
}

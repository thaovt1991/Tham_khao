package com.cg.service;

import com.cg.model.BlogMedia;

import java.util.List;

public interface BlogMediaService {

    List<BlogMedia> findAll();

    BlogMedia create(BlogMedia blogMedia);

    void delete(BlogMedia blogMedia);
}

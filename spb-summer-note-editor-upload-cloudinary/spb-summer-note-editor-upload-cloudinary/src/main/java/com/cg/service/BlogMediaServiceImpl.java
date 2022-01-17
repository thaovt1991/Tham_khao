package com.cg.service;


import com.cg.model.BlogMedia;
import com.cg.repository.BlogMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BlogMediaServiceImpl implements BlogMediaService {

    @Autowired
    private BlogMediaRepository blogMediaRepository;

    @Override
    public List<BlogMedia> findAll() {
        return blogMediaRepository.findAll();
    }

    @Override
    public BlogMedia create(BlogMedia blogMedia) {
        return blogMediaRepository.save(blogMedia);
    }

    @Override
    public void delete(BlogMedia blogMedia) {
        blogMediaRepository.delete(blogMedia);
    }
}

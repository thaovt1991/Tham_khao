package com.cg.service;

import com.cg.model.DescriptionMedia;

import java.util.List;

public interface DescriptionMediaService {

    List<DescriptionMedia> findAll();

    DescriptionMedia create(DescriptionMedia descriptionMedia);

    void delete(DescriptionMedia descriptionMedia);
}

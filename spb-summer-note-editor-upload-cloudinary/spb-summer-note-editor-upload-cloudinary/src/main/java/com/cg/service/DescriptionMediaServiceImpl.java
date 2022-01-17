package com.cg.service;

import com.cg.exception.DataInputException;
import com.cg.model.DescriptionMedia;
import com.cg.model.enums.FileType;
import com.cg.repository.DescriptionMediaRepository;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class DescriptionMediaServiceImpl implements DescriptionMediaService {

    @Autowired
    private DescriptionMediaRepository descriptionMediaRepository;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<DescriptionMedia> findAll() {
        return null;
    }

//    @Override
//    public DescriptionMedia create(DescriptionMedia descriptionMediaDTO) {
//        String fileType = descriptionMediaDTO.getFile().getContentType();
//
//        assert fileType != null;
//
//        fileType = fileType.substring(0, 5);
//
//        descriptionMediaDTO.setFileType(fileType);
//
//        DescriptionMedia descriptionMedia = descriptionMediaRepository.save(descriptionMediaDTO);
//
//        if (fileType.equals(FileType.IMAGE.getValue()) || fileType.equals(FileType.VIDEO.getValue())) {
//            uploadAndSaveDescriptionMedia(descriptionMedia);
//        }
//
//        return descriptionMedia;
//    }

    @Override
    public DescriptionMedia create(DescriptionMedia descriptionMedia) {
        String fileType = descriptionMedia.getFile().getContentType();

        assert fileType != null;

        fileType = fileType.substring(0, 5);

        descriptionMedia.setFileType(fileType);

        descriptionMediaRepository.save(descriptionMedia);

        if (fileType.equals(FileType.IMAGE.getValue()) || fileType.equals(FileType.VIDEO.getValue())) {
            uploadAndSaveDescriptionMedia(descriptionMedia);
        }

        return descriptionMedia;
    }

    private void uploadAndSaveDescriptionMedia(DescriptionMedia descriptionMedia) {
        try {
            Map uploadResult = uploadService.uploadImage(descriptionMedia.getFile(), uploadUtils.buildDescriptionUploadParams(descriptionMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            descriptionMedia.setFileName(descriptionMedia.getId() + "." + fileFormat);
            descriptionMedia.setFileUrl(fileUrl);
            descriptionMedia.setFileFolder(UploadUtils.DESCRIPTION_UPLOAD_FOLDER);
            descriptionMedia.setCloudId(descriptionMedia.getFileFolder() + "/" + descriptionMedia.getId());
            descriptionMediaRepository.save(descriptionMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload media thất bại");
        }
    }

    @Override
    public void delete(DescriptionMedia descriptionMedia) {

    }
}

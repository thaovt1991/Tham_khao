package com.cg.service;


import com.cg.exception.DataInputException;
import com.cg.model.Blog;
import com.cg.model.BlogMedia;
import com.cg.model.dto.BlogDataForm;
import com.cg.model.dto.IBlogDTO;
import com.cg.model.dto.BlogDTO;
import com.cg.model.enums.FileType;
import com.cg.repository.BlogMediaRepository;
import com.cg.repository.BlogRepository;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogMediaRepository blogMediaRepository;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;


    @Override
    public List<Blog> findAll() {
        Sort sortOrder = Sort.by("ts");
        return blogRepository.findAll(sortOrder);
    }

    @Override
    public Optional<BlogDTO> findBlogDTOBySlug(String slug) {
        return blogRepository.findBlogDTOBySlug(slug);
    }

    @Override
    public List<BlogDTO> findAllBlogDTO() {
        return blogRepository.findAllBlogDTO();
    }

    @Override
    public List<BlogDTO> findAllBlogDTOOrderByTsDesc() {
        return blogRepository.findAllBlogDTOOrderByTsDesc();
    }

    @Override
    public Optional<Blog> findById(String id) {
        return blogRepository.findById(id);
    }

    @Override
    public IBlogDTO findIBlogDTOById(String id) {
        return blogRepository.findIBlogDTOById(id);
    }

    @Override
    public Boolean existsBySlugEquals(String slug) {
        return blogRepository.existsBySlugEquals(slug);
    }

    @Override
    public Boolean existsBySlugEqualsAndIdIsNot(String slug, String id) {
        return blogRepository.existsBySlugEqualsAndIdIsNot(slug, id);
    }

    @Override
    public Blog create(BlogDataForm blogDataForm) {

        String fileType = blogDataForm.getFile().getContentType();

        assert fileType != null;

        fileType = fileType.substring(0, 5);

        BlogMedia blogMedia = new BlogMedia();

        blogMedia.setFileType(fileType);

        blogMedia = blogMediaRepository.save(blogMedia);

        blogDataForm.setBlogMedia(blogMedia);

        if (fileType.equals(FileType.IMAGE.getValue())) {
            uploadAndSaveBlogImage(blogDataForm.getFile(), blogMedia);
        }

        if (fileType.equals(FileType.VIDEO.getValue())) {
            uploadAndSaveBlogVideo(blogDataForm.getFile(), blogMedia);
        }

        Blog blog = blogRepository.save(blogDataForm.toBlog());

        return blog;
    }

    @Override
    public void updateAvatar(Blog blog, MultipartFile file) {

        String fileType = file.getContentType();

        assert fileType != null;

        fileType = fileType.substring(0, 5);

        BlogMedia blogMedia = new BlogMedia();

        blogMedia.setFileType(fileType);

        blogMedia = blogMediaRepository.save(blogMedia);

        if (fileType.equals(FileType.IMAGE.getValue())) {
            uploadAndSaveBlogImage(file, blogMedia);
        }

        if (fileType.equals(FileType.VIDEO.getValue())) {
            uploadAndSaveBlogVideo(file, blogMedia);
        }

        blog.setBlogMedia(blogMedia);

        blogRepository.save(blog);
    }

    @Override
    public void updateInfo(BlogDTO blogDTO) {
        blogRepository.save(blogDTO.toBlog());
    }


    private void uploadAndSaveBlogImage(MultipartFile fileUpload, BlogMedia blogMedia) {
        try {
            Map uploadResult = uploadService.uploadImage(fileUpload, uploadUtils.buildImageUploadParams(blogMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            blogMedia.setFileName(blogMedia.getId() + "." + fileFormat);
            blogMedia.setFileUrl(fileUrl);
            blogMedia.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            blogMedia.setCloudId(blogMedia.getFileFolder() + "/" + blogMedia.getId());
            blogMediaRepository.save(blogMedia);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    private void uploadAndSaveBlogVideo(MultipartFile fileUpload, BlogMedia blogMedia) {
        try {
            Map uploadResult = uploadService.uploadVideo(fileUpload, uploadUtils.buildVideoUploadParams(blogMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            blogMedia.setFileName(blogMedia.getId() + "." + fileFormat);
            blogMedia.setFileUrl(fileUrl);
            blogMedia.setFileFolder(UploadUtils.VIDEO_UPLOAD_FOLDER);
            blogMedia.setCloudId(blogMedia.getFileFolder() + "/" + blogMedia.getId());
            blogMediaRepository.save(blogMedia);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload video thất bại");
        }
    }

    @Override
    public void delete(Blog blog) throws IOException {

        Optional<BlogMedia> productImageVideo = blogMediaRepository.findByBlog(blog);

        if (productImageVideo.isPresent()) {
            String publicId = productImageVideo.get().getCloudId();

            if (productImageVideo.get().getFileType().equals(FileType.IMAGE.getValue())) {
                uploadService.destroyImage(publicId, uploadUtils.buildImageDestroyParams(blog, publicId));
            }

            if (productImageVideo.get().getFileType().equals(FileType.VIDEO.getValue())) {
                uploadService.destroyVideo(publicId, uploadUtils.buildVideoDestroyParams(blog, publicId));
            }

            blogMediaRepository.delete(productImageVideo.get());
        }

        blogRepository.delete(blog);

    }
}

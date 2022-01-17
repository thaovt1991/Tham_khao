package com.cg.utils;

import com.cg.exception.DataInputException;
import com.cg.model.Blog;
import com.cg.model.BlogMedia;
import com.cg.model.DescriptionMedia;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtils {
    public static final String IMAGE_UPLOAD_FOLDER = "blog_images";
    public static final String DESCRIPTION_UPLOAD_FOLDER = "blog_images/descriptions";
    public static final String VIDEO_UPLOAD_FOLDER = "blog_videos";

    public Map buildDescriptionUploadParams(DescriptionMedia descriptionMedia) {
        if (descriptionMedia == null || descriptionMedia.getId() == null)
            throw new DataInputException("Không thể upload media của Description chưa được lưu");

        String publicId = String.format("%s/%s", DESCRIPTION_UPLOAD_FOLDER, descriptionMedia.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", descriptionMedia.getFileType()
        );
    }

    public Map buildImageUploadParams(BlogMedia blogMedia) {
        if (blogMedia == null || blogMedia.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của blog chưa được lưu");

        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, blogMedia.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Blog blog, String publicId) {
        if (blog == null || blog.getId() == null)
            throw new DataInputException("Không thể destroy hình ảnh của Blog không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildVideoUploadParams(BlogMedia blogMedia) {
        if (blogMedia == null || blogMedia.getId() == null)
            throw new DataInputException("Không thể upload video của Blog chưa được lưu");

        String publicId = String.format("%s/%s", VIDEO_UPLOAD_FOLDER, blogMedia.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "video"
        );
    }

    public Map buildVideoDestroyParams(Blog blog, String publicId) {
        if (blog == null || blog.getId() == null)
            throw new DataInputException("Không thể destroy video của Blog không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "video"
        );
    }
}

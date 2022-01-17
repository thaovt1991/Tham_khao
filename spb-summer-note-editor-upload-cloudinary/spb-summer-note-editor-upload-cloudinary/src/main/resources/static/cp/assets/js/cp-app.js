class App {

    static DOMAIN = location.origin;

    static BASE_URL_BLOG = this.DOMAIN + "/cp/api/blogs";

    static BASE_URL_DESCRIPTION_MEDIA = this.BASE_URL_BLOG + "/descriptions/upload-images";

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/c0721k1/image/upload";
    static BASE_URL_CLOUD_VIDEO = "https://res.cloudinary.com/c0721k1/video/upload";
    static BASE_SCALE_IMAGE_150_100 = "c_limit,w_150,h_100,q_100";
    static BASE_SCALE_IMAGE_250_200 = "c_limit,w_250,h_200,q_100";
    static BASE_SCALE_IMAGE_250_250 = "c_limit,w_250,h_250,q_100";

    static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
    static ERROR_401 = "Access Denied! Invalid credentials.";
    static ERROR_403 = "Access Denied! You are not authorized to perform this function.";
    static ERROR_404 = "An error occurred. Please try again later!";
    static ERROR_500 = "Lưu dữ liệu không thành công, vui lòng liên hệ quản trị hệ thống.";
    static SUCCESS_CREATED = "Successful data generation !";
    static SUCCESS_UPDATED = "Info update successful !";
    static AVATAR_UPDATED_SUCCESS = "Avatar update successful !";
    static SUCCESS_DELETED = "Delete blog successfully !";

    static showSuspendedConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to delete the selected blog ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please delete this blog !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }

    static removeFormatNumberSpace(x) {
        return x.toString().replace(" ", "");
    }

    static formatTooltip() {
        $('[data-toggle="tooltip"]').tooltip();
    }
}


class Blog {
    constructor(id, title, slug, fileUrl, description, blogMedia) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.fileUrl = fileUrl;
        this.description = description;
        this.blogMedia = blogMedia;
    }
}

class BlogMedia {
    constructor(id, fileName, fileFolder, fileUrl) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
    }
}

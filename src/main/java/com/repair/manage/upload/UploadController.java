package com.repair.manage.upload;

import com.repair.manage.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 文件上传接口
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${file.upload-dir:C:\\uploads\\}")
    private String uploadDir;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${file.access-url}")
    private String fileAccessUrl;

    /**
     * 文件上传接口（返回完整链接）
     *
     * @param file 上传的文件
     * @return 上传结果信息
     */
    @PostMapping("/file/url")
    public Result uploadFileReturnUrl(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件为空，请选择文件上传");
        }
        try {
            // 创建上传目录(如果不存在)
            Path uploadPath = Paths.get(uploadDir + appName + "\\");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // 获取文件原始名称和扩展名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String oldName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            // 生成新的唯一文件名
            String newFilename = UUID.randomUUID() + "-" + oldName + fileExtension;
            // 保存文件
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath);
            String path = fileAccessUrl.endsWith("/") ? fileAccessUrl : fileAccessUrl + "/";
            return Result.success(path + appName + "/" + newFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 文件上传接口（只返回文件名称）
     *
     * @param file 上传的文件
     * @return 上传结果信息
     */
    @PostMapping("/file/name")
    public Result uploadFileReturnFileName(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件为空，请选择文件上传");
        }
        try {
            // 创建上传目录(如果不存在)
            Path uploadPath = Paths.get(uploadDir + appName + "\\");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // 获取文件原始名称和扩展名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String oldName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            // 生成新的唯一文件名
            String newFilename = UUID.randomUUID() + "-" + oldName + fileExtension;
            // 保存文件
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath);
            // 返回上传的文件名称
            return Result.success(appName + "/" + newFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }


    /**
     * 配置静态资源处理
     */
    @Configuration
    public static class WebConfig implements WebMvcConfigurer {

        @Value("${file.upload-dir:C:\\uploads\\}")
        private String uploadDir;

        @Value("${spring.application.name}")
        private String appName;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            String fileAccessUrl = "file:" + uploadDir + appName + "\\";
            registry.addResourceHandler("/uploads/" + appName + "/**").addResourceLocations(fileAccessUrl);
        }

    }

}

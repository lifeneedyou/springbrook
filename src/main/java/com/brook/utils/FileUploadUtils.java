package com.brook.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @Author: xuequan
 * @Date: 2019/5/27 11:22
 * @Description:
 */
public class FileUploadUtils {

    /**
     * 文件上传工具类服务方法
     *
     * @param multipartFile
     * @param filePath
     * @param myName
     * @return
     * @throws Exception
     */
    public static File uploadFile(MultipartFile multipartFile, String filePath, String myName) throws Exception {


        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        if (StringUtils.isBlank(myName)) {
            // 获取文件名
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String timeName = dateTimeFormatter.format(time);
            myName = timeName + MakeNumUtils.makeCode(5);
        }
        
        // 添加后缀
        myName = myName + prefix;

        if (StringUtils.isBlank(filePath)) {
            filePath = ResourceUtils.getURL("classpath:").getPath() + "upload/";
        }

        File file = new File(filePath + "/" + myName);
        if (!file.getParentFile().exists()) {
            //父目录不存在就创建一个
            file.getParentFile().mkdir();
        }

        //保存文件
        multipartFile.transferTo(file);

        return file;
    }

    /**
     * 创建临时文件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static File uploadTempFile(MultipartFile multipartFile) throws IOException {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File file = File.createTempFile(UUID.randomUUID().toString(), prefix);
        // MultipartFile to File
        multipartFile.transferTo(file);

        return file;
    }

    /**
     * 删除文件
     *
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}

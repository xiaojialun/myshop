package com.xjl.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {

    //图片后缀
    private static String[] TYPE = { ".jpg", ".jpeg", ".png", ".bmp", ".gif" };

    private String IMAGE_SERVER_URL = "http://192.168.25.133/";


    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map uploadImg(MultipartFile uploadFile){
        // 声明标志位
        boolean flag = false;
        //失败结果
        Map<String,Object> result = new HashMap<>();
        result.put("error",1);
        result.put("message","上传失败");
        // 校验后缀
        for (String type : TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),type)){
                flag = true;
                break;
            }
        }
        // 如果校验失败，直接返回
        if (!flag){
            return result;
        }
        // 重置标志位
        flag = false;

        // 图片内容校验
        try {
            BufferedImage image = ImageIO.read(uploadFile.getInputStream());
            if (image != null) {
                flag = true;
            }
        } catch (Exception e) {
            return result;
        }

//         校验成功，需要上传图片
        if (flag) {
            try {
                // 1. 加载tracker配置文件
                ClientGlobal.init(System.getProperty("user.dir") + "/src/main/resources/client.conf");
                // 2. 创建TrackerClient
                TrackerClient trackerClient = new TrackerClient();
                // 3. 获取TrackerServer
                TrackerServer trackerServer = trackerClient.getConnection();
                // 4. 声明StorageServer，为null
                StorageServer storageServer = null;
                // 5. 创建StorageClient
                StorageClient storageClient = new StorageClient(trackerServer, null);
                // 6. 使用StorageClient上传图片
                String ext = StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), ".");
                // 7. 获取上传文件的后缀名
                String[] str = storageClient.upload_file(uploadFile.getBytes(), ext, null);
                // 8. 进行返回的结果的拼接，上传图片的url
                String picUrl = this.IMAGE_SERVER_URL + str[0] + "/" + str[1];
                result.remove("message");
                result.replace("error",1,0);
                result.put("url",picUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
        }else {
            return result;
        }
        return result;
    }

}

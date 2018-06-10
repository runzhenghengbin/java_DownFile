package com.zhb.controller;


import com.zhb.utils.DownloadFileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: curry
 * @Date: 2018/6/10 17:56
 * @Description:
 */
@RestController
public class DownloadFileController {

    /**
     * 下载在线图片
     * 下载文件为图片格式
     */
    @RequestMapping("/downloadPic/{type}")
    public void downloadPic(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "type",required = true) String type) throws IOException {
        String fileName = "";
        byte[] data = new byte[]{};

        if (type.equals("img")) {
            fileName = "maven.png";
            data = DownloadFileUtil.downLoadImg("http://p7zk4x9pv.bkt.clouddn.com/maven/maven.png");

        }

        if(type.equals("zip")){
            fileName = "maven.zip";
            data = DownloadFileUtil.downLoadZip();

        }

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }





}

package com.zhb.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: curry
 * @Date: 2018/6/10 18:06
 * @Description:
 */
public class DownloadFileUtil {


    /**
     * 根据在线的url 生成输出byte[]
     * @param imgURL
     * @return
     */
    public static byte[] downLoadImg(String imgURL){
        // 构造HttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        if(StringUtils.isBlank(imgURL)){
            return null;
        }
        HttpGet httpPost = new HttpGet(imgURL);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                InputStream in =response.getEntity().getContent();

                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int count = -1;
                while((count = in.read(data,0,1024)) != -1){
                    outStream.write(data, 0, count);
                }

                data = null;
                return outStream.toByteArray();

            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] downLoadZip() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);


        //zip 放文件夹里面是图片
        String path =  "zhb" + File.separator + "down" + File.separator +"maven.png";
        StringWriter sw = new StringWriter();

        //添加到zip
        try {
            zip.putNextEntry(new ZipEntry(path));
            IOUtils.write(downLoadImg("http://p7zk4x9pv.bkt.clouddn.com/maven/maven.png"),zip);
            IOUtils.closeQuietly(sw);
            zip.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }







}

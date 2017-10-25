package com.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class DownFile {
    public static byte[] downLoad(String imgURL){
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
				        while((count = in.read(data,0,1024)) != -1)  
				            outStream.write(data, 0, count);  
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
    /**
     * 文件下载
     * @param filePath 文件路径
     * @param response 
     * @throws Exception
     */
    public void downLoad(String filePath, HttpServletResponse response,String fileName) throws Exception {
      byte[] buf = this.downLoad(filePath);
      response.reset(); // 非常重要
      response.setContentType("application/x-msdownload");
      response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
      OutputStream out = response.getOutputStream();
      out.write(buf);
      out.close();

      
  }
}

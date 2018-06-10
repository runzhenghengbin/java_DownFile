



## 功能概述

通过url下载java文件

增加通过url下载图片，将图片放到zip进行下载。使用springboot进行编写

## 特性

- 功能专一，简单好用

## 兼容性

- Chrome 23+ (Windows, Mac, Android, iOS, Linux, Chrome OS)
- Firefox 4+ (Windows, Mac, Android, Linux)
- Internet Explorer 6+ (Windows, Windows Phone)
- Opera 10+ (Windows, linux, Android)

## 下载代码展示
```
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

```

## 报告问题

- [Issues](https://github.com/runzhenghengbin/java_DownFile/issues "report question")

## 作者

**温暖阳光**



## 为什么会有这个项目

主要是做到项目,文件上传后存在mongodb,返回url,需要通过url下载该文件

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@ page import="com.test.*"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
response.setContentType("application/x-msdownload");
String file_url = request.getParameter("file_url");
new DownFile().downLoad("http://7xrftx.com1.z0.glb.clouddn.com/03.png", response,"1.png");
out.clear();
out = pageContext.pushBody();
%>
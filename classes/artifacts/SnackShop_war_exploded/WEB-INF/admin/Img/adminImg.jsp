<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/libs/sweetalert.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libs/bootstrap.min.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/angular.min.js"></script>
<title>图片管理</title>
</head>
<body ng-App="adminImgApp">
 <div ng-controller="adminImgCtrl">
 		<h2>首页图片替换</h2>
 		<form id="indexPic" method="post">
	        <table>
	            <tr>
	                <td>请选择图片:</td>
	                <td><input type="file" id="file" class="form-control"></td>
	            </tr>
	            <tr>
	              <td>请需要替换的图片:</td>
	              <td>
	                <select id="fileName" class="form-control">
	                   <option value="lb1.jpg">第一张</option>
	                   <option value="lb2.jpg">第二张</option>
	                   <option value="lb3.jpg">第三张</option>
	                </select>
	              </td>
	            </tr>
	            <tr>
	                <td><input type="button" id="uploadPic" value="替换" class="btn btn-primary"></td>
	            </tr>
	        </table>
        </form>
 </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/views/adminJs/adminImg.js"></script>
</html>
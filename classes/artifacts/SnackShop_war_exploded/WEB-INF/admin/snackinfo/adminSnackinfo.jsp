<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libs/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/libs/jquery.dataTables.css">
<link href="${pageContext.request.contextPath}/css/libs/sweetalert.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/libs/dataTimeCss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/libs/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/bootbox.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/laydate.js"></script>
<title>商品信息</title>
</head>
<body ng-App="adminSnackinfoApp">
 <div ng-controller="adminSnackinfoCtrl">
 		商品名:<input class="text-center" id="sName" style="width:150px;padding: 6px 12px;background-color: #fff;border: 1px solid #c2cad8;border-radius: 4px;">
 		上架日期范围:<input class="text-center" type="text" class="demo-input" id="startDate" style="width:150px;padding: 6px 12px;background-color: #fff;border: 1px solid #c2cad8;border-radius: 4px;">
                   -<input class="text-center" type="text" class="demo-input" id="endDate" style="width:150px;padding: 6px 12px;background-color: #fff;border: 1px solid #c2cad8;border-radius: 4px;">
                类别:<select id="sTypeS" style="width:150px;padding: 6px 12px;background-color: #fff;border: 1px solid #c2cad8;border-radius: 4px;">
                <option value="">全部</option>
                <c:forEach items="${typeList }" var="type">
                	<option value="${type.snackName}">${type.snackName}</option>
                </c:forEach>
            </select>
                   <button id="serchAdminSnackinfo" class="btn btn-primary">搜索</button>
                   <button id="newAdminSnackinfo" class="btn btn-primary">新建</button>
 		<table id="table_id_example" class="display" style="text-align: center">
			<thead>
				<tr>
				<th class="text-center">图片</th>
				<th class="text-center">批次号</th>
				<th class="text-center">商品名</th>
				<th class="text-center">价格</th>
				<th class="text-center">折扣</th>
				<th class="text-center">生产地</th>
				<th class="text-center">数量</th>
				<th class="text-center">上架日期</th>
				<th class="text-center">生产日期</th>
				<th class="text-center">保质期</th>
				<th class="text-center">类别</th>
				<th class="text-center">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		
		<div class="modal fade" id="newAdminSnackinfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog"  style="width: 800px; height: 500px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">商品上架</h4>
                    </div>
                    <div class="clearfix" style="margin:0px 15px 0px">
                        <div class="margin-top-15">
                        <form id="adminSnackinfForm">
	                        <table>
					            <tr>
					                <td>请选择图片:</td>
					                <td><input type="file" id="file" class="form-control"></td>
					           		 </tr>
					            <tr>
					              <td>商品名:</td>
					              <td>
					                <input type="text" id="sNameT" class="form-control">
					              </td>
					            </tr>
					            <tr>
						            <td>价格:</td>
						              <td>
					                	<input type="text" id="sPrice" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>折扣:</td>
						              <td>
					                	<select id="sDiscount" class="form-control">
					                	   <option>1</option>
					                	   <option>2</option>
					                	   <option>3</option>
					                	   <option>4</option>
					                	   <option>5</option>
					                	   <option>6</option>
					                	   <option>7</option>
					                	   <option>8</option>
					                	   <option>9</option>
					                	   <option>10</option>
					                	</select>
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>产地:</td>
						              <td>
					                	<input type="text" id="sPlace" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>品牌:</td>
						              <td>
					                	<input type="text" id="sBrand" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>是否进口:</td>
						              <td>
					                	<select id="sImported" class="form-control">
					                		<option value="0">否</option>
					                		<option value="1">是</option>
					                	</select>
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>生产日期:</td>
						              <td>
					                	<input type="text" id="sPdate" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>保质期:</td>
						              <td>
					                	<input type="text" id="sQdate" class="form-control">
						              </td>
						            </tr>
					            <tr>
					             <tr>
						            <td>数量:</td>
						              <td>
					                	<input type="text" id="sNumber" class="form-control">
						              </td>
						            </tr>
					            <tr>
					             <tr>
						            <td>详情:</td>
						              <td>
					                	<input type="text" id="sDese" class="form-control">
						              </td>
						            </tr>
						            <tr>
						            <td>类别:</td>
						              <td>
					                	<select id="sType" class="form-control">
					                		<c:forEach items="${typeList }" var="type">
							                	<option value="${type.snackName}">${type.snackName}</option>
							                </c:forEach>
					                	</select>
						              </td>
						            </tr>
					            <tr>
					                <td><button id='addAdminSnackinfo' class="btn btn-primary">添加</button></td>
					            </tr>
					        </table>
					        </form>
                        </div>
                    </div>
                 </div>
			 </div>
		</div>
		
		
		<div class="modal fade" id="updateAdminSnackinfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog"  style="width: 800px; height: 500px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">商品修改</h4>
                    </div>
                    <div class="clearfix" style="margin:0px 15px 0px">
                        <div class="margin-top-15">
	                        <table>
	                         <input type="hidden" id="sIdU" >
					            <tr>
					                <td>图片:</td>
					                <td>
					                <img height='60' width='60' id="picUrlU"/><br><input type="file" id="fileU" class="form-control"></td>
					           		 </tr>
					            <tr>
					              <td>商品名:</td>
					              <td>
					                <input type="text" id="sNameU" class="form-control">
					              </td>
					            </tr>
					            <tr>
						            <td>价格:</td>
						              <td>
					                	<input type="text" id="sPriceU" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>折扣:</td>
						              <td>
					                	<select id="sDiscountU" class="form-control">
					                	   <option>1</option>
					                	   <option>2</option>
					                	   <option>3</option>
					                	   <option>4</option>
					                	   <option>5</option>
					                	   <option>6</option>
					                	   <option>7</option>
					                	   <option>8</option>
					                	   <option>9</option>
					                	   <option>10</option>
					                	</select>
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>产地:</td>
						              <td>
					                	<input type="text" id="sPlaceU" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>品牌:</td>
						              <td>
					                	<input type="text" id="sBrandU" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>是否进口:</td>
						              <td>
					                	<select id="sImportedU" class="form-control">
					                		<option value="0">否</option>
					                		<option value="1">是</option>
					                	</select>
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>生产日期:</td>
						              <td>
					                	<input type="text" id="sPdateU" class="form-control">
						              </td>
						            </tr>
					            <tr>
					            <tr>
						            <td>保质期:</td>
						              <td>
					                	<input type="text" id="sQdateU" class="form-control">
						              </td>
						            </tr>
					            <tr>
					             <tr>
						            <td>数量:</td>
						              <td>
					                	<input type="text" id="sNumberU" class="form-control">
						              </td>
						            </tr>
					            <tr>
					             <tr>
						            <td>详情:</td>
						              <td>
					                	<input type="text" id="sDeseU" class="form-control">
						              </td>
						            </tr>
						            <tr>
						            <td>类别:</td>
						              <td>
					                	<select id="sTypeU" class="form-control">
					                		<c:forEach items="${typeList }" var="type">
							                	<option value="${type.snackName}">${type.snackName}</option>
							                </c:forEach>
					                	</select>
						              </td>
						            </tr>
					            <tr>
					                <td><button id='updateAdminSnackinfo' class="btn btn-primary">修改</button></td>
					            </tr>
					        </table>
                        </div>
                    </div>
                 </div>
			 </div>
		</div>
 </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/views/adminJs/adminSnackinfo.js"></script>
</html>
var integralModule = angular.module("adminOrderApp",[]);
			integralModule.controller("adminOrderCtrl",function($scope,$http,$timeout){
				createTable();
				initBind($http, $scope,$timeout);
				//初始化时间
				lay('#version').html('-v'+ laydate.v);
				//执行一个laydate实例
				laydate.render({
				  elem: '#oTimeStart'//指定元素
				});
				laydate.render({
				  elem: '#oTimeEnd'//指定元素
				});
			});
			
			function initBind($http, $scope,$timeout) {
				//删除
				$('#table_id_example').on('click', '.delete', function () {
					var params=$(this).attr('data-id');
					swal({
	   	                title: "确定删除？",
	   	                type: "warning",
	   	                showCancelButton: true, 
	   	                confirmButtonColor: "#DD6B55",
	   	                confirmButtonText: "确定", 
	   	                cancelButtonText: "取消",
	   	                closeOnConfirm: false,
	   	            },function(){
	   	            	$.ajax({
	   	 				url:'/SnackShop'+'/admin/order/deleteAdminOrder',
	   	 				data:{
	   	 			    	"oId":params
	   	 			    },
	   	 				type:'post',
	   	 				dataType:"json",
	   	 				success: function(result){
		   	 				result = $.parseJSON(result);
	   			        	if(result.errCode='000000'){
	   			        		swal("删除成功!","success");
	   			        		createTable();
	   			        	}else{
	   			        	    swal("删除失败!","error");
	   			        	    createTable();
	   			        	}
	   	 				},
		   	 			error:function(){
		   	 			    swal("没有权限!","error");
	   	 				}
	   	 			});
	   	            }) 
                });
				
				$("#serchAdminOrder").on('click', function () {
					createTable();
				})
				
				//查看
				$('#table_id_example').on('click', '.select', function () {
					var params=$(this).attr('data-id');
					var oType=$(this).attr('data-type');
					$("#oldoId").val(params);
					$("#oldoType").val(oType);
					createsnackTable(params,oType);
					showModal($('#orderSnackModal'));
				});
				
				//加载修改
				$('#table_id_example').on('click', '.update', function () {
					var params=$(this).attr('data-id');
					$.ajax({
	   	 				url:'/SnackShop'+'/admin/order/updateSelectAdminOrder',
	   	 				data:{
	   	 			    	"rId":params
	   	 			    },
	   	 				type:'post',
	   	 				dataType:"json",
	   	 				success: function (result) {
		   	 				result = $.parseJSON(result);
	   			        	$("#rIdT").val(result.data.rId);
	   			        	$("#oNameT").val(result.data.oName);
	   			        	$("#oPhoneT").val(result.data.oPhone);
	   			        	$("#oAddressT").val(result.data.oAddress);
	   			        	showModal($('#orderSnackUpdateModal'));
	   			        },
		   	 			error:function(){
		   	 				swal("没有权限!","error");
		 				}
	   	 			});
				});
				
				//更新rece
				$("#updateReceptinfo").on('click', function () {
					var params="rId="+$("#rIdT").val()+"&oName="+$("#oNameT").val()
					+"&oPhone="+$("#oPhoneT").val()+"&oAddress="+$("#oAddressT").val();
					$http({
   			            method: 'POST',
   			            url:'/SnackShop'+'/admin/order/updateAdminOrder',
   			            headers: {
   			                'Content-Type': 'application/x-www-form-urlencoded'
   			            },
   			            data: params
   			        }).success(function (result) {
   			        	if(result.errCode='000000'){
   			        		swal("修改成功!","success");
   			        		$('#orderSnackUpdateModal').modal('hide');
   			        		createTable();
   			        	}else{
   			        	    swal("修改失败!","error");
   			        	    createTable();
   			        	}
   			        })
				})
				
				$('#table_id_example').on('click', '.pushSnack', function () {
					var params=$(this).attr('data-id');
					swal({
	   	                title: "确认发货？",
	   	                type: "info",
	   	                showCancelButton: true, 
	   	                confirmButtonColor: "#DD6B55",
	   	                confirmButtonText: "确定", 
	   	                cancelButtonText: "取消",
	   	                closeOnConfirm: false,
	   	                showLoaderOnConfirm: true
	   	            },function(){
	   	            	$.ajax({
		   	 				url:'/SnackShop'+'/admin/order/userOrderConfirm',
		   	 				data:{
		   	 			    	"id":params,
		   	 			    	"typeNum":0
		   	 			    },
		   	 				type:'post',
		   	 				dataType:"json",
		   	 				success: function(result){
		   	 				result = $.parseJSON(result);
		   			        	if(result.errCode='000000'){
		   			        		if(result.data==-5){
		   			        			swal("商品数量不足!","error");
		   			        		}else{
		   			        			swal("发货成功!","success");
		   			        			createTable();
		   			        		}
		   			        	}else{
		   			        	    swal("发货失败!","error");
		   			        	    createTable();
		   			        	}
		   	 				},
			   	 			error:function(){
			   	 			    swal("没有权限!","error");
		   	 				}
		   	 			});
	   	            }) 
				});
				
				//导出报表
				$('#table_id_example').on('click', '.outOrderDetail', function () {
					var params=$(this).attr('data-id');
					window.open("/SnackShop/admin/order/outExcelOrder?oId=" +params);
				});
				
				
				//加载详情修改
				$('#table_snack_example').on('click', '.updateOrderDetial', function () {
					var params=$(this).attr('data-id');
					$.ajax({
	   	 				url:'/SnackShop'+'/admin/order/updateSelectAdminOrderDetial',
	   	 				data:{
	   	 			    	"dId":params
	   	 			    },
	   	 				type:'post',
	   	 				dataType:"json",
	   	 				success: function (result) {
		   	 				$('#orderSnackModal').modal('hide');
	   			        	result = $.parseJSON(result);
	   			        	$("#dId").val(result.data.dId);
	   			        	$("#picUrl").attr('src',"/SnackShop/image/"+result.data.picUrl); 
	   			        	$("#sName").val(result.data.sName);
	   			        	$("#oMoney").val(result.data.oMoney);
	   			        	$("#oNum").val(result.data.oNum);
	   			        	showModal($('#orderdetailUpdateModal'));
	   			        },
		   	 			error:function(){
		   	 				swal("没有权限!","error");
		 				}
	   	 			});
				});
				
				
				//更新orderdetail
				$("#updateOrderDetail").on('click', function () {
					var params="dId="+$("#dId").val()+"&oMoney="+$("#oMoney").val()
					+"&oNum="+$("#oNum").val();
					$http({
   			            method: 'POST',
   			            url:'/SnackShop'+'/admin/order/updateOrderdetail',
   			            headers: {
   			                'Content-Type': 'application/x-www-form-urlencoded'
   			            },
   			            data: params
   			        }).success(function (result) {
   			        	if(result.errCode='000000'){
   			        		$('#orderdetailUpdateModal').modal('hide');
   			        		createsnackTable($("#oldoId").val(),$("#oldoType").val());
   			        		showModal($('#orderSnackModal'));
   			        	}
   			        })
				})
				
				//删除orderdetail
				$('#table_snack_example').on('click', '.deleteOrderDetial', function () {
					var params=$(this).attr('data-id');
					var oId=$("#oldoId").val();
					swal({
	   	                title: "确定删除？",
	   	                type: "warning",
	   	                showCancelButton: true, 
	   	                confirmButtonColor: "#DD6B55",
	   	                confirmButtonText: "确定", 
	   	                cancelButtonText: "取消",
	   	                closeOnConfirm: false,
	   	            },function(){
	   	            	$.ajax({
	   	 				url:'/SnackShop'+'/admin/order/deleteOrderdetail',
	   	 				data:{
	   	 			    	"dId":params,
	   	 			    	"oId":oId
	   	 			    },
	   	 				type:'post',
	   	 				dataType:"json",
	   	 				success: function(result){
	   	 				result = $.parseJSON(result);
	   			        	if(result.data==1){
	   			        		swal("删除成功!","success");
	   			        		createsnackTable($("#oldoId").val(),$("#oldoType").val());
	   			        	}else if(result.data==2){
	   			        		swal("删除成功!","success");
	   			        		$('#orderSnackModal').modal('hide');
	   			        		createTable();
	   			        	}
	   	 				},
		   	 			error:function(){
		   	 			    swal("没有权限!","error");
	   	 				}
	   	 			});
	   	            }) 
                });
			}
			
			var snackDatatable=null;
			function createsnackTable(oId,oType){
				if(snackDatatable!=null){
					snackDatatable.destroy();
				}
				snackDatatable=$('#table_snack_example').DataTable({
						bLengthChange: false,
						searching: false,
					    ordering:  false,
					    language: {
					    	url: '/SnackShop'+'/js/china.json'
					    },
					    "aLengthMenu":[10],
					    serverSide: true,
					    ajax:{
					    url:"/SnackShop"+"/shop/selectOrderSnack",
					    dataSrc:"data",
					    data: {
					    	"oId":oId,
					    },
					    type:"post"
					    },
					    columns:[
							{ data:'picUrl',render: function ( data, type, row ) {
							    return "<img height='60' width='60' src='/SnackShop/image/"+data+"'/>";
							}},
					    	{ data:'sName'},
					    	{ data:'oMoney'},
					    	{ data:'oNum'},
					    	{ data:'dId',width:'20%',render: function ( data, type, row ) {
					    		if(oType==0 || oType==1 || oType==-2){
					    			return "无";
					    		}else{
						    		var html="<span style='cursor:pointer' class='updateOrderDetial' data-id='"+ row.dId+ "'>修改&nbsp;&nbsp;</span>"
						    			+"<span style='cursor:pointer' class='deleteOrderDetial' data-id='"+ row.dId+ "'>删除</span>"
					    		    return html;
					    		}
			    		    }}
					    ]
					});
			}
			
			var datatable=null;
			function createTable(){
				if(datatable!=null){
					datatable.destroy();
				}
					datatable=$('#table_id_example').DataTable({
						bLengthChange: false,
						searching: false,
					    ordering:  false,
					    language: {
					    	url: '/SnackShop'+'/js/china.json'
					    },
					    "aLengthMenu":[10],
					    serverSide: true,
					    ajax:{
					    url:"/SnackShop"+"/admin/order/adminOrderLimit",
					    dataSrc:"data",
					    data: {
					    	"oId":$("#oId").val(),
							"adUserName":$("#adUserName").val(),
							"oTimeStart":$("#oTimeStart").val(),
							"oTimeEnd":$("#oTimeEnd").val()
					    },
					    type:"post"
					    },
					    columns:[
					    	{ data:'oId',width:'10%'},
					    	{ data:'uUsername',width:'10%'},
					    	{ data:'oName',width:'10%'},
					    	{ data:'oPhone',width:'10%'},
					    	{ data:'oAddress',width:'15%'},
					    	{ data:'oTime',width:'10%'},
					    	{ data:'adUsername',width:'10%',render: function ( data, type, row ) {
					    		if(row.oType==-1 || row.oType==-2){
					    			return "未操作";
					    		}else{
					    			return row.adUsername;
					    		}
			    		    }},
					    	{ data:'oType',width:'10%',render: function ( data, type, row ) {
					    		if(data==-1){
					    			return "未付款,未发货";
					    		}else if(data==0){
					    			return "已发货";
					    		}else if(data==1){
					    			return "订单完成";
					    		}else if(data==-2){
					    			return "已付款，未发货";
					    		}
			    		    }},
					    	{ data:'oId',width:'20%',render: function ( data, type, row ) {
					    		var html="<span style='cursor:pointer' class='select' data-id='"+ row.oId+ "'  data-type='"+ row.oType+ "' >查看&nbsp;&nbsp;</span>";
					    		if(row.oType==-1){
					    			html=html+"<span style='cursor:pointer' class='update' data-id='"+ row.rId+ "'>修改&nbsp;&nbsp;</span>"
					    			+"<span style='cursor:pointer' class='delete' data-id='"+ row.oId+ "'>删除</span>";
				    		        return html;
					    		}else if(row.oType==-2){
					    			html=html+"<span style='cursor:pointer' class='update' data-id='"+ row.rId+ "'>修改&nbsp;&nbsp;</span>"
					    			+"<span style='cursor:pointer' class='pushSnack' data-id='"+ row.id+ "'>确认发货&nbsp;&nbsp;</span>"
					    			+"<span style='cursor:pointer' class='outOrderDetail' data-id='"+ row.oId+ "'>报表</span>";
					    			return html;
					    		}else{
					    			return html;
					    		}
					    		
			    		    }}
					    ]
					});
			}
			
		function showModal(modal, backdrop) {
		        modal.modal({
		            backdrop: backdrop || true,
		            show: true
		        })
		    };
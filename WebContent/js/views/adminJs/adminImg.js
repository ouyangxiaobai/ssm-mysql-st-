	var integralModule = angular.module("adminImgApp",[]);
			integralModule.controller("adminImgCtrl",function($http,$scope){
				initBind($http, $scope);
			});
			function initBind($http, $scope) {
				$("#uploadPic").on('click', function () {
					var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
					if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
						swal("请选择图片!","success");
						return;
					}
					var formFile = new FormData();
		                formFile.append("fileName",$("#fileName").val());  
		                formFile.append("file",fileObj); //加入文件对象
		            var data = formFile;
					$.ajax({
	   	 				url:'/SnackShop'+'/admin/img/upload',
	   	 				data:data,
	   	 				type:'post',
	   	 				dataType:"json",
	   	 				cache: false,//上传文件无需缓存
	                    processData: false,//用于对data参数进行序列化处理 这里必须false
	                    contentType: false, //必须
	   	 				success: function (result) {
	   	 					result = $.parseJSON(result);
	   			        	if(result.errCode='000000'){
	   			        		swal("替换成功!","success");
		   			        }else{
		   			        	swal("替换失败!","error");
		   			        }
		   			    },
		   	 			error:function(){
		   	 				swal("没有权限!","error");
		 				}
	   	 			});
				})
			}
		
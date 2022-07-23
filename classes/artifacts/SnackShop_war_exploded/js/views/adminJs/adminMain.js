	var integralModule = angular.module("adminMainApp",[]);
			integralModule.controller("adminMainCtrl",function($http,$scope){
				initBind($http, $scope);
			});
			function initBind($http, $scope) {
				$('#easyUILayoutBody').layout(); 
				
				$("#loginOut").on('click',function(){
					$.ajax({
	   	 				url:'/SnackShop'+'/admin/login/adminMainToLoginOut',
	   	 				type:'post',
	   	 				dataType:"json",
	   	 				success: function (result) {
	   			        	result = $.parseJSON(result);
	   			        	if(result.errCode='000000'){
	   			        		location.href="/SnackShop"+"/admin/login/adminLogin";
	   			        	}
	   			        }
	   	 			});
				})
				
			}
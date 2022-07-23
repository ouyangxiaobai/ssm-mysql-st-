
$(document).ready(function(){
	$("#typeSearch").on('click',function(){
		var type=$("#TypeNum").val();
		var snackName=$("#snackName").val();
		location.href='/SnackShop'+'/shop/puffingType?type=5&snackName='+snackName;
	})
	
})
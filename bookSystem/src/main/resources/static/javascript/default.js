/**
 * 	default.js
 */

$(function(){ // window.onload=function(){}
	
	$("#menuToggle").click(function(){ // document.getElementsByClassName("menuShow")[0].addEventListener("click",function(){})
		$(".menuShow").toggle(); // toggle은 알아서 왔다갔다 하는애
		$(".menuHide").toggle();
		var left = $("#userBox").css("left");
		left = Math.abs(parseInt(left)); 
		console.log(left);
		$("#userBox").css("left",(left-125)+"px");// -125 > 0 / 0 > -125 로 하려면 -125를 더한다
	});
	
});	
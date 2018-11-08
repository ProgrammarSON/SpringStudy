<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#goods>.good { display : inline-block;  width : 300px; height : 300px; border : 1px solid blue;}
#aside { position :fixed; right:0px; bottom:300px; border : 1px solid red;}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
var goodsList ;
$(function(){
	init();
	viewGoods();
});

function init(){
	goodsList = localStorage.getItem("lastViewGoodsList");
	if(goodsList == null) {
		goodsList = [];
	} else {
		goodsList = JSON.parse(goodsList);
	}
}
function viewGoods() {
	$(".good").click(function(){
		var name = $(this).find("span").text();
		if(goodsList.length >= 5) {
			goodsList.pop();
		}
		goodsList.unshift({id:1,name:name, src:'#'});
		localStorage.setItem("lastViewGoodsList",JSON.stringify(goodsList));
		console.log(goodsList);
		viewRender();
	});
}
function viewRender(){
	$("ul#viewGoods").empty();
	for(i in goodsList){  
			if(!isNull(goodsList[i])){
				$("ul#viewGoods").append(
					$("<li>").append(
						$("<a>").attr("href","/item/itemView.do?goodsId="+goodsList[i].id)
								.append(goodsList[i].name)
				        		.append($("<img>").attr("src",goodsList[i].src)
				               					  .attr("alt",goodsList[i].name)
						)
					)
				);
		   }
		  }
}

function isNull(obj){
	 if(obj == '' || obj == null || obj == undefined || obj == NaN){ 
	  return true;
	 }else{
	  return false;
	 }
}


</script>
</head>
<body>
<div id="goods">
	<div class="good">
		<span>상품1</span>
	</div>
	<div class="good">
		<span>상품2</span>
	</div>
	<div class="good">
		<span>상품3</span>
	</div>
	<div class="good">
		<span>상품4</span>
	</div>
	<div class="good">
		<span>상품5</span>
	</div>
	<div class="good">
		<span>상품6</span>
	</div>
	<div class="good">
		<span>상품7</span>
	</div>
	<div class="good">
		<span>상품8</span>
	</div>	
	<div class="good">
		<span>상품9</span>
	</div>
	<div class="good">
		<span>상품10</span>
	</div>
	<div class="good">
		<span>상품11</span>
	</div>
	<div class="good">
		<span>상품12</span>
	</div>
	<div class="good">
		<span>상품13</span>
	</div>
	<div class="good">
		<span>상품14</span>
	</div>
	<div class="good">
		<span>상품15</span>
	</div>
	<div class="good">
		<span>상품16</span>
	</div>		
</div>
<div id="aside">
<ul id="viewGoods"></ul>
</div>
</body>
</html>
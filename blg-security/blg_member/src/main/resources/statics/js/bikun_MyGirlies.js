/* chris jquery demo */

$(function(){
	//点击左侧导航展示的效果
	$( ".headerL_nav li" ).hover( function(){
		$(this).find("em").addClass("light");
		$(this).find("div").show();
	},function(){
		$(this).find("em").removeClass("light");
		$(this).find("div").hide();
	});


	//我的评价-点击导航，显示盒子的效果
	$(".evalLeft span").click(function(){
		var index = $(this).index();
		$(this).addClass("light").siblings().removeClass("light");
		$(".myEvaliation_box .myEval_tab,.myNews_cont .myNews_toggle,.coupons_listbox .coupons_toggle,.myOrder_cont .myOrder_toggle").eq(index).show().siblings().hide();
	});

})(jQuery);


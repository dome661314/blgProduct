


// 首页广告图
(function($) {
	$.fn.jCarouselLite = function(o) {
		o = $.extend({
			btnPrev: null,
			btnNext: null,
			btnGo: null,
			mouseWheel: false,
			auto: null,
			speed: 500,
			easing: null,
			vertical: false,
			circular: true,
			visible: 1,
			start: 0,
			scroll: 1,
			beforeStart: null,
			afterEnd: null
		},
		o || {});



	animCss = o.vertical ? "top" : "left",
	sizeCss = o.vertical ? "height" : "width";

	ul = $("ul", c),
	tLi = $("li", ul),
	tl = tLi.size(),
	v = o.visible;





	itemLength = f.size(),
	curr = o.start;


































	function (i, a) {
	    $(a).click(function () {
	        return go(o.circular ? o.visible + i : i)
	    })
	});






	o.auto + o.speed);
























			o.speed, o.easing,
			function () {
			    if (o.afterEnd) o.afterEnd.call(this, vis());
			    b = false
			});








};
function css(a, b) {
    return parseInt($.css(a[0], b)) || 0
};
function width(a) {
    return a[0].offsetWidth + css(a, 'marginLeft') + css(a, 'marginRight')
};
function height(a) {
    return a[0].offsetHeight + css(a, 'marginTop') + css(a, 'marginBottom')
}
})(jQuery);

$(function () {
    var div = $("#botton-scroll");
    var width = $(window).width() - 21 * 2 - 13 * 2 - 37 - 20;
    console.log(width)
       $('li', div).css({ width: width });

	$("#botton-scroll").jCarouselLite({
		btnNext: ".next",
		btnPrev: ".prev"
});  

});





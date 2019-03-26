
$(document).ready(function(){
	(function($){
	$.fn.teaPot = function( opts ){
		var def = {
			speed: 400,
			even: "click"	
		};
		var options = $.extend( def, opts );		
		this.append( $( "div.small" ) );		
		//////////////////////////////
		var len = $( ".small li" ).length,
		smUl = $( ".small ul" );
		smUl.width( 292 * len );		
		$( ".small b" ).click( function(){
			if( parseInt( smUl.css( "margin-left" ) ) < 0 ){
				smUl.not(":animated").animate( {"margin-left": "+=292" }, options.speed );
			}
		});
		$( ".small s" ).click( function(){
			if( parseInt( smUl.css( "margin-left" ) ) > (len-3) * -292 ){
				smUl.not(":animated").animate( {"margin-left": "-=292" }, options.speed );	
			}
		});
	}
})(jQuery);

$( document ).ready(function(e) {
	$("#div1").teaPot({
		speed: 200,
		even: "dblclick"	
	});
});  
})



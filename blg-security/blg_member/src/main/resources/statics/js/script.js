$(document).ready(function(){
	
    var h=$('.pics div dl').outerHeight(true);

    var dis=100;
	$('.btn').on('click',function(){
		$('.layer').stop().animate({'left':80},300,function(){
			$('.btn').fadeOut();
			$('.tit-x img').fadeOut();
			$('.tit').fadeIn();
			$('.right_btn').fadeIn();
			$('.bot-left').css({'margin-left':333});
			
			$('.waterbox').css({'width':345});
			
			var typeH=$(window).height();
	    	 // $('.layer,.waterbox,.type-nav').height(typeH);
			$('.layer,.waterbox,.type-nav').css({'height':$(window).height()});
			 $('.waterbox').height(typeH);
		});
	})

	$('.close,.right_btn').on('click',function(){
		$('.layer').stop().animate({'left':-345},500,function(){
			$('.btn').fadeIn();
			$('.tit-x img').fadeIn();
			$(this).width(345);
			$('.tit').fadeOut();
			$('.right_btn').fadeOut();
			$('.bot-left').css({'margin-left':0});			
			$('.waterbox').css({'width':1});
		});
		$('.sma-layer').stop().animate({'left':-100},500)
	})

	$('.type li').on('click',function(){
		$('.layer').css('width','500px');
		$('.sma-layer').stop().animate({'left':320},500)
		$('.smar').stop().animate({'left':13},500);
		$(this).addClass('hover').siblings().removeClass('hover');
		$('#toggle-btn').find('span').removeClass().addClass('allow lIcon lx');
		var index=$(this).index();
		$('#notice').stop().animate({'top':0},500);
		$('.pics div').eq(index).addClass('cur').siblings().removeClass('cur');
		$('.right_btn').fadeOut();
	})

	$('#toggle-btn').on('click',function(){
		var $icon=$(this).find('span');
		if($icon.hasClass('lx')){
          $('.smar').stop().animate({'left':-287},500,function(){
          	 $icon.removeClass().addClass('allow rIcon rx');
          	 $('.layer').width(320);
          	 $('.type').find('li').removeAttr('class','hover');
          	// $('.toggle-btn').hide();
          });

		}else{
		  $('.layer').width(500);
          $('.smar').stop().animate({'left':13},500);
          $icon.removeClass().addClass('allow lIcon lx');
          $('.type').find('li').Attr('class','hover')
         // $('.toggle-btn').show();
		}
	})

	$('.up-btn').on('click',function(){
       $('.cur>dl:first').stop().animate({'margin-top':-h},500,function(){
       	  $(this).appendTo($('.cur')).css('margin-top',0);
       })
       if($('#notice').position().top==0){
       	 $('#notice').stop().animate({'top':400},500);
       }else{
       	 $('#notice').stop().animate({'top':'-='+dis},500);
       }
	})
  
    $('.down-btn').on('click',function(){
       $('.cur>dl:last').prependTo($('.cur')).css('margin-top',-h);
       $('.cur>dl:first').stop().animate({'margin-top':0})
       
       if($('#notice').position().top>=400){
       	 $('#notice').stop().animate({'top':0},500);
       }else{
       	 $('#notice').stop().animate({'top':'+='+dis},500);
       }
	});

	
	// 焦点图		
	
$(window).resize(function(){
	scrollPic();
});

  function scrollPic(){

    if($(window).width()<=1024){   	
    	$(window).css({"width":1024}); 	
    };
	$('.pic-wrap').width(len*w);
	var rw=$(window).width()-$('.l').width();
	var focW=$(window).width()-$('.l').width()-7-25-80-22;
	var typeH=$('.header').height()+$('.focus').height()+$('.bot-info').height()+$('.footer').height();
	var lw=$('.r').css({"width":rw});
	$('.r,.focus').width(rw);
	$('.foc-box').width(focW);
	$('.pic-wrap div').width(focW);
	$('.pic-wrap div dl,.pic-wrap div dl dt,.pic-wrap div dl dd').width(focW/5);

	$('.pic-wrap div dl dt img').width(focW/5-20);
	$('.pic-wrap div dl dt img').height(focW/5-20);
	$('.pic-wrap div,.pic-wrap').height(focW/5-20+125);
	$('.foc-box').height(focW/5-20+180);
	$('.nav_slide').height(typeH);
	//$('.layer').height(typeH);
	$('.pic-wrap').css({width:100000});
    var len=$('.pic-wrap>div').length,
	w=$('.pic-wrap>div:first').outerWidth(),

	index=0;
	$('#next').on('click',function(){
       index++;
       if(index>len-1){
          index=0;
          $('.pic-wrap').stop().animate({'margin-left':0},500);
       }else{
          $('.pic-wrap').stop().animate({'margin-left':'-='+w},500);
       }
       $('.num-nav li').eq(index).addClass('on').siblings().removeClass('on');
	});
  
    $('#prev').on('click',function(){
       if(index==0){
         index=len-1;
         $('.pic-wrap').stop().animate({'margin-left':-(len-1)*w},500);
       }else{
       	 index--;
       	 $('.pic-wrap').stop().animate({'margin-left':'+='+w},500);
       }
       $('.num-nav li').eq(index).addClass('on').siblings().removeClass('on');
	});
	$('.num-nav li').on('click',function(){
	       index=$(this).index();
	       $('.num-nav li').eq(index).addClass('on').siblings().removeClass('on');
	       $('.pic-wrap').stop().animate({'margin-left':-index*w},500);
		});
	$('.closew').on('click',function(){
	       $('.nav_slide').stop().animate({'left':-200},500);
	       $('.wbtn').find('span').removeClass('rIcon').addClass('lIcon bef');
		});

		$('.wbtn').on('click',function(){
	         $('.nav_slide').stop().animate({'left':0},500);
	         $('.wbtn').find('span').removeClass('lIcon bef').addClass('rIcon');
		});
  };
    scrollPic();
	
});

  

  
  
  


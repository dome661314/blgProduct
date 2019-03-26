 // 瀑布流
                var dataName1 =new Array();
                comefrom.prototype.img = null;  
								comefrom.prototype.text = null;  
								comefrom.prototype.dtImg = null;  
								comefrom.prototype.name = null;  
								comefrom.prototype.time = null;  
                function comefrom(a1,a2,a3,a4,a5) { this.img = a1; this.text = a2; this.dtImg = a3;this.name=a4,this.time=a5} 
                dataName1[0]= new comefrom("0","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
                dataName1[1]= new comefrom("1","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
                dataName1[2]= new comefrom("2","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
                dataName1[3]= new comefrom("3","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
                dataName1[4]= new comefrom("4","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
                dataName1[5]= new comefrom("5","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
                dataName1[6]= new comefrom("6","华丽巴洛特风，女王范的气场","0dt","狗尾巴草","1小时前SHOW");
 
 var divAndjianjuWidth; //动态计算每个DIV+间距的总宽度
 var arrRow ; //当前分辨率下应该显示几列。如果浏览器大小改变，需要刷新jsp
 function initPubuLiu(){
 	            //console.log('瀑布流');
            if($(window).width()>=1782){
                arrRow = [0,0,0,0,0,0];
            }else if($(window).width()>=1502 ){
                arrRow = [0,0,0,0,0];
             }else if($(window).width()>=1222 ){
                arrRow = [0,0,0,0];
             }else{
             	 arrRow = [0,0,0];
            }
         	  divAndjianjuWidth = Math.round(($(window).width() - 82)/arrRow.length);
         	  //alert(divAndjianjuWidth);
 }
 //这个方法只能运行一次
 initPubuLiu();


 //这里是业务代码调用的方法,传入数据即可
 function showPuBuLiu(theData){
             for(var i=0;i<theData.length;i++){             	 
             	   //alert(theData[i]);
                 dataImg(theData[i]);
            }
        }
        
        //针对一个秀来处理
        function dataImg(data){
            var imgq=data.img,
                text=data.text,
                dtImg=data.dtImg,
                name=data.name,
                time=data.time;
            var str="";
            str+='<div class="pubu"><img src="images/'+imgq+'.jpg">';
            str+='<p>'+text+'</p></div>';
            str+='<dl><dt><img src="images/'+dtImg+'.jpg"></dt>';
            str+='<dd class="dd-left"><h1>'+name+'</h1>';
            str+='<p><a href="javascript:void(0)">'+time+'</a></p></dd>';
            str+='<dd class="dd-right"><img src="images/xin.jpg"/><em>26</em></dd></dl>';
                var max = Math.max.apply(Math,arrRow),
                    div = $('<div class="puBu0"></div>').css({
                            left:490,
                            top:max
                        }).append(str).appendTo($('.waterfall-r'));                
                div.children('div').children('img').imagesLoaded(function(){
                    var min = Math.min.apply( Math , arrRow),
                        minIndex = $.inArray( min ,arrRow );
                    div.animate({
                        left:parseInt(divAndjianjuWidth)*minIndex,
                        top:min
                    },500);
                    arrRow[minIndex]+= div.height()+10;
                })
        }
        
      //检测滚动条的加载数据  
      $( window).scroll( function () {
          var scrollH = $(document).height()-$(window).height();
          if( scrollH<=$(window).scrollTop()){
              //调用AJAX后台取数据
              //这里使用模拟数据
            //  showPuBuLiu(dataName1);
          }
      });
 //测试例子,页面加载后自动显示第一批数据
 //showPuBuLiu(dataName1);
         
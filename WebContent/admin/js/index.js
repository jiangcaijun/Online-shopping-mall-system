//分析用户权限
$(document).ready(function(){
	$.get("/sms",{action:"auth_url"},function(data){
      $.each(data,function(i,value){
    	  $('#sidebar a').each(function(){
    		  if($(this).attr("link")!=null){
	    		   if($(this).attr("link")==value){
	    			   $(this).parent().removeAttr("hidden");
		    		  }
		    	  }
	    	  }); 
        });
    },"json");
});

//初始化相关元素高度
function init(){
    $("body").height($(window).height()-80);
    $("#iframe-main").height($(window).height()-90);
    $("#sidebar").height($(window).height()-50);
}

$(function(){
    init();
    $(window).resize(function(){
        init();
    });
});

function goPage (newURL) {
    if (newURL != "") {
        if (newURL == "-" ) {
            resetMenu();            
        }          
        else {  
            document.location.href = newURL;
        }
    }
}

function resetMenu() {
    document.gomenu.selector.selectedIndex = 2;
}
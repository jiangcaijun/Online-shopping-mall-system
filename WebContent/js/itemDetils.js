$(document).ready(function(){
	/**
	 * @class Class for calculating pagination values
	 */
	$.PaginationCalculator = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
	}
	
	$.extend($.PaginationCalculator.prototype, {
		/**
		 * Calculate the maximum number of pages
		 * @method
		 * @returns {Number}
		 */
		numPages:function() {
			return Math.ceil(this.maxentries/this.opts.items_per_page);
		},
		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @returns {Array}
		 */
		getInterval:function(current_page)  {
			var ne_half = Math.floor(this.opts.num_display_entries/2);
			var np = this.numPages();
			var upper_limit = np - this.opts.num_display_entries;
			var start = current_page > ne_half ? Math.max( Math.min(current_page - ne_half, upper_limit), 0 ) : 0;
			var end = current_page > ne_half?Math.min(current_page+ne_half + (this.opts.num_display_entries % 2), np):Math.min(this.opts.num_display_entries, np);
			return {start:start, end:end};
		}
	});
	
	// Initialize jQuery object container for pagination renderers
	$.PaginationRenderers = {}
	
	/**
	 * @class Default renderer for rendering pagination links
	 */
	$.PaginationRenderers.defaultRenderer = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
		this.pc = new $.PaginationCalculator(maxentries, opts);
	}
	$.extend($.PaginationRenderers.defaultRenderer.prototype, {
		/**
		 * Helper function for generating a single link (or a span tag if it's the current page)
		 * @param {Number} page_id The page id for the new item
		 * @param {Number} current_page 
		 * @param {Object} appendopts Options for the new item: text and classes
		 * @returns {jQuery} jQuery object containing the link
		 */
		createLink:function(page_id, current_page, appendopts){
			var lnk, np = this.pc.numPages();
			page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
			appendopts = $.extend({text:page_id+1, classes:""}, appendopts||{});
			if(page_id == current_page){
				lnk = $("<a class='current'>" + appendopts.text + "</a>");
			}
			else
			{
				lnk = $("<a>" + appendopts.text + "</a>")
					.attr('href', this.opts.link_to.replace(/__id__/,page_id));
			}
			if(appendopts.classes){ lnk.addClass(appendopts.classes); }
			lnk.data('page_id', page_id);
			return lnk;
		},
		// Generate a range of numeric links 
		appendRange:function(container, current_page, start, end, opts) {
			var i;
			for(i=start; i<end; i++) {
				this.createLink(i, current_page, opts).appendTo(container);
			}
		},
		getLinks:function(current_page, eventHandler) {
			comment(current_page+1);
			var begin, end,
				interval = this.pc.getInterval(current_page),
				np = this.pc.numPages(),
				fragment = $("<div class='pagination'></div>");
			
			// Generate "Previous"-Link
			if(this.opts.prev_text && (current_page > 0 || this.opts.prev_show_always)){
				fragment.append(this.createLink(current_page-1, current_page, {text:this.opts.prev_text, classes:"prev"}));
			}
			// Generate starting points
			if (interval.start > 0 && this.opts.num_edge_entries > 0)
			{
				end = Math.min(this.opts.num_edge_entries, interval.start);
				this.appendRange(fragment, current_page, 0, end, {classes:'sp'});
				if(this.opts.num_edge_entries < interval.start && this.opts.ellipse_text)
				{
					$("<span class='pagination-break'>"+this.opts.ellipse_text+"</span>").appendTo(fragment);
				}
			}
			// Generate interval links
			this.appendRange(fragment, current_page, interval.start, interval.end);
			// Generate ending points
			if (interval.end < np && this.opts.num_edge_entries > 0)
			{
				if(np-this.opts.num_edge_entries > interval.end && this.opts.ellipse_text)
				{
					$("<span class='pagination-break'>"+this.opts.ellipse_text+"</span>").appendTo(fragment);
				}
				begin = Math.max(np-this.opts.num_edge_entries, interval.end);
				this.appendRange(fragment, current_page, begin, np, {classes:'ep'});
				
			}
			// Generate "Next"-Link
			if(this.opts.next_text && (current_page < np-1 || this.opts.next_show_always)){
				fragment.append(this.createLink(current_page+1, current_page, {text:this.opts.next_text, classes:"next"}));
			}
			$('a', fragment).click(eventHandler);
			return fragment;
		}
	});
	
	// Extend jQuery
	$.fn.pagination = function(maxentries, opts){
		
		// Initialize options with default values
		opts = $.extend({
			items_per_page:1,
			num_display_entries:4,
			current_page:0,
			num_edge_entries:1,
			link_to:"",
			prev_text:"<i></i>上一页",
			next_text:"下一页 <i></i>",
			ellipse_text:"...",
			prev_show_always:true,
			next_show_always:true,
			renderer:"defaultRenderer",
			show_if_single_page:false,
			load_first_page:false,
			callback:function(){return false;}
		},opts||{});
		
		var containers = this,
			renderer, links, current_page;

		//goto
    $(".page-btn").one("click",function(){
    	var allPage = $(".allPage").text();
    	//alert(allPage);
    	//console.log(allPage);
      var goPage = $(".page-go input").val() - 1; //跳转页数
      if(goPage > -1 && goPage < allPage){
				opts.current_page = goPage;
      	$("#Pagination").pagination(allPage,opts);
      }else {
      	$("#Pagination").pagination(allPage);
      }
      //清空用户跳转页数
      $(".page-go input").val("");
    });
		
		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_id The new page number
		 */
		function paginationClickHandler(evt){
			var links, 
				new_current_page = $(evt.target).data('page_id'),
				continuePropagation = selectPage(new_current_page);
			if (!continuePropagation) {
				evt.stopPropagation();
			}
			return continuePropagation;
		}
		
		/**
		 * This is a utility function for the internal event handlers. 
		 * It sets the new current page on the pagination container objects, 
		 * generates a new HTMl fragment for the pagination links and calls
		 * the callback function.
		 */
		function selectPage(new_current_page) {
			// update the link display of a all containers
			containers.data('current_page', new_current_page);
			links = renderer.getLinks(new_current_page, paginationClickHandler);
			containers.empty();
			links.appendTo(containers);
			// call the callback and propagate the event if it does not return false
			var continuePropagation = opts.callback(new_current_page, containers);
			return continuePropagation;
		}
		
		// -----------------------------------
		// Initialize containers
		// -----------------------------------
                current_page = parseInt(opts.current_page);
                
		containers.data('current_page', current_page);
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		
		if(!$.PaginationRenderers[opts.renderer])
		{
			throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
		}
		renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);
		
		// Attach control events to the DOM elements
		var pc = new $.PaginationCalculator(maxentries, opts);
		var np = pc.numPages();
		containers.bind('setPage', {numPages:np}, function(evt, page_id) { 
				if(page_id >= 0 && page_id < evt.data.numPages) {
					selectPage(page_id); return false;
				}
		});
		containers.bind('prevPage', function(evt){
				var current_page = $(this).data('current_page');
				if (current_page > 0) {
					selectPage(current_page - 1);
				}
				return false;
		});
		containers.bind('nextPage', {numPages:np}, function(evt){
				var current_page = $(this).data('current_page');
				if(current_page < evt.data.numPages - 1) {
					selectPage(current_page + 1);
				}
				return false;
		});
		
		// When all initialisation is done, draw the links
		links = renderer.getLinks(current_page, paginationClickHandler);
		containers.empty();
		if(np > 1 || opts.show_if_single_page) {
			links.appendTo(containers);
		}
		// call callback function
		if(opts.load_first_page) {
			opts.callback(current_page, containers);
		}
	} // End of $.fn.pagination block

/*$(document).ready(function() {
	 $.getJSON("/item?action=recommen",function(data){
		 var pic = "<li><a href=\"JavaScript:;\"><img src=\""+data[0].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"JavaScript:;\"><img src=\""+data[1].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"JavaScript:;\"><img src=\""+data[2].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"JavaScript:;\"><img src=\""+data[3].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"JavaScript:;\"><img src=\""+data[0].mainPic+"\" /></a></li>";
		 $("#recommen").html(pic);
	});
});*/
//获取url的id值

	var t = Math.random();
	var url="";
	//alert(8888);
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return "";
	}
	
	url = "/item?action=findOne&t="+t;
	$.getJSON(url,{id:GetQueryString("id"),date:new Date()},function(data){
	
		$.each(data,function(i,item){
					if (i == "title") {
						$("#title").html(data[i]);
					}
					if (i == "price") {
						$("#price").html(data[i]);
					}
					if (i == "num") {
						$("#J_SpanStock").html(data[i]);
					}
					if (i == "mainPic") {
						var pic = "<li><a href=\"JavaScript:;\"><img src=\""
								+ data[i] + "359x351.jpg\" /></a></li>";
						// alert(pic);
						$("#mainPic").html(pic);
					}
		});
	});

	function comment(p){
		$.getJSON("/item",{action:"getComment",idForComment:GetQueryString("id"),date:new Date(),pageForComment:p},function(data){
			var comments = "<div style=\"clear:both;\"></div>";
			$.each(data.rows,function(i,n){
				comments += "<dl><dt><a><img src=\""+n.url+"\"/></a></dt><dd><a>"+n.username+"</a><p class=\"b3-p1\">"+n.content+"</p><p class=\"b3-p2\">";
				if(n.curl!=""){
					comments +="<span><img id=\"comPic"+i+"\" src=\""+n.curl+"50×50.jpg\"/></span><span id = \"id\" style=\"background:#fff\"></span>";
					comments +="<script type=\"text/javascript\">$(document).ready(function(){xOffset=10,yOffset=30,$(\"#comPic"+i+"\").hover(function(s){$(\"<img id='imgshow' src='"+n.curl+"350×350.jpg' />\").appendTo(\"#id\"),$(\"#imgshow\").css(\"top\",s.pageY-xOffset+\"px\"),$(\"#imgshow\").css(\"left\",s.pageX+yOffset+\"px\"),$(\"#imgshow\").fadeIn(\"fast\")},function(){$(\"#imgshow\").remove()})});</script>"
				}
				comments += formatterDate(n.time)+"</p><div style=\"clear:both;\"></div></dd><div style=\"clear:both;\"></div></dl>";
			});
			comments = comments+"<div style=\"clear:both;\"></div>";
			$("#commmentAll").prepend(comments);
		});
	}
	
	$.getJSON("/item",{action:"itemInfo",itemid:GetQueryString("id"),date:new Date()},function(data){
		$("#info1").html(data[0].info);
	});
	
	$.getJSON("/item",{action:"getComment",idForComment:GetQueryString("id"),date:new Date(),pageForComment:1},function(data){
		var pag = Math.ceil(parseInt(data.total)/6);
		
		$(".allPage").text(pag);
		$("#Pagination").pagination(pag);
	});
	
	$.getJSON("/item",{action:"recommendId",recommendId:GetQueryString("id"),date:new Date()},function(data){
		var item = "";
		
		$.each( data, function(i, n){
			item = item+"<li><a href=\"/itemDetils_2.jsp?id="+n.id+"\"><img src=\""+n.mainPic+"95x100.jpg\"></a> <a class=\"if2-li-tit\" href=\"/itemDetils_2.jsp?id="+n.id+"\" >"+n.title+"</a><p>￥"+n.price+"</p></li>";
		});
		
		$("#recommend").html(item);
	});

	$("#search").click( function () {
		var itemSearch = $("#itemSearch").val();
		itemSearch = encodeURI(encodeURI(itemSearch));
		if(itemSearch!=""){
			location.href ="/searchDetails.jsp?search="+itemSearch;
		}
	});
	
	$(".btn1-r").mouseenter(function(){
		  $(".btn1-r").css("background-color","#C91623");
	});
	
	$(".btn1-r").mouseleave(function(){
		  $(".btn1-r").css("background-color","#fff");
		  $(".btn1-r").css("color","#AB5100");
	});
	
	$(".btn1-l").mouseenter(function(){
		  $(".btn1-l").css("background-color","#C91623");
	});
	
	$(".btn1-l").mouseleave(function(){
		  $(".btn1-l").css("background-color","#fff");
		  $(".btn1-l").css("color","#AB5100");
	});
	
	$(".btn1-r").click(function(){
		location.href ="/login.jsp";
	});
	$(".btn1-l").click(function(){
		location.href ="/reg.jsp";
	})
	
	$.post("/user", {action:"checkLogin"},function(data){
		  if(data=="true"){
			  $("#login").html("");
			  $("#login").css("background-color","#fff");
			  $("#login").css("border","none");
			  $("#logout").text("退出");
		  }
		  	
	});
	
	$.post("/user", {action:"checkAdmin"},function(data){
		  if(data=="true")
		  $("#login").after("<button class=\"btn2\">商家入口    ></button><script type=\"text/javascript\">$(\".btn2\").click(function(){location.href =\"/admin/index.jsp\";})</script>");
	});
	
	
});
		
function formatterDate(val) {
	//alert(val);//val是时间
    var date = new Date(val);
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    return date.getFullYear() + '-' + (m<10?('0'+m):m) + '-' + (d<10?('0'+d):d) + ' ' + (h<10?('0'+h):h) + ':' + (mi<10?('0'+mi):mi + ":" + (s<10?('0'+s):s));
}
function purchase(){
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return "";
	}
	var num = $("#num").val();
	$.getJSON("/item",{action:"exchange",num:num,id:GetQueryString("id"),date:new Date()},function(data){
		if(data.flag=="true"){
			location.href ="/customer/confirm.jsp?num="+num+"&itemId="+GetQueryString("id")+"";
		}else{
			alert("库存数量不足");
		}
	});
	
}


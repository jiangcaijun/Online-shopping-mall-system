$(document).ready(function() {
	var order = 1;
	var price=1;
	var low=0;
	var high=0;
	var orderBy="";
	var search = decodeURI(decodeURI(GetQueryString("search")));
	var type = decodeURI(decodeURI(GetQueryString("type")));
	
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
			//调用发送请求，把页面数据加载
			searchData(current_page+1);
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
			link_to:"#",
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
	
	
	
	
	
	
	
	$("#searchText").val(search);
	
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return "";
	}
	
	function searchData(p){
		$.getJSON("/item",{action : "search",page:p,type:type,value:search,order:order,low:low,high:high,orderBy:orderBy},function(data){
			$("#totle").html("找到共 "+data.total+"条");
			var items="";
			$.each( data.rows, function(i, n){
				if((i+1)%4==0){
					items = items+ "<li style=\"margin-right: 0;\"><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\""+n.mainPic+"95x100.jpg\"></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</span></p></div><p class=\"miaoshu\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\"></p><div class=\"md-l-r f-l\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
				}else{
					items = items+ "<li><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\""+n.mainPic+"95x100.jpg\"></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</span></p></div><p class=\"miaoshu\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\"></p><div class=\"md-l-r f-l\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
				}
			});
			$("#itemList").html(items);
		});
	}
	
	function cate(){
		$.getJSON("/item",{action : "CategoryRecommend",valRecommend:search,typeRecommend:type},function(data){
			var item = "";
			$.each( data, function(i, n){
				item = item+"<li><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\""+n.mainPic+"95x100.jpg\"></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</span></p></div><p class=\"miaoshu\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\"></p><div class=\"md-l-r f-l\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
			});
			$("#recommend").html(item);
		});
	}
	//初始化分页
	$.getJSON("/item",{action : "search",page:1,type:type,value:search,order:order,low:low,high:high,orderBy:orderBy},function(data){
		var items="";
		var pag = Math.ceil(parseInt(data.total)/12);
		$(".allPage").text(pag);
		$("#Pagination").pagination(pag);
	});
	
	cate();
	
	$("#sale").click(function(){
		if($("#sale").text()==="销量 ↓"){
			$("#sale").html("<a href=\"#\">销量 ↑</a>");
			order = 0;
		}else{
			$("#sale").html("<a href=\"#\">销量 ↓</a>");
			order = 1;
		}
		orderBy="sale";
		
	});
	
	$("#price").click(function(){
		if($("#price").text()==="价格 ↓"){
			$("#price").html("<a href=\"#\">价格 ↑</a>");
			order = 0;
		}else{
			$("#price").html("<a href=\"#\">价格 ↓</a>");
			order = 1;
		}
	});
	
	$("#sure").click(function(){
		low=$("#low").val();
		high=$("#high").val();
		if(parseInt(low)<0||low==""){
			low=0;
			$("#low").val("");
		}
		if(parseInt(high)<parseInt(low)||parseInt(high)<0||high==""){
			high=0;
			$("#high").val("");
		}
		$(".allPage").text(pag);
		$("#Pagination").pagination(pag);
	});	
	
	$(".f-r").click(function(){
		search = $("#searchText").val();
		$.getJSON("/item",{action : "search",page:1,value:search,order:order,low:low,high:high,orderBy:orderBy},function(data ,s){
			var items="";
			var pag = Math.ceil(parseInt(data.total)/12);
			$(".allPage").text(pag);
			$("#Pagination").pagination(pag);
		});
		var stateObject = {};
		var title = "Wow Title";
		var newUrl = "/searchDetails.jsp?search="+encodeURI(encodeURI(search));
		history.pushState(stateObject,title,newUrl);
		cate();
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
			  $(".btn1").html("");
			  $(".btn1").css("background-color","#fff");
			  $(".btn1").css("border","none");
			  $("#logout").text("退出");
		  }
		  	
	});
	
	$.post("/user", {action:"checkAdmin"},function(data){
		  if(data=="true")
		  $(".btn1").after("<button class=\"btn2\">商家入口    ></button><script type=\"text/javascript\">$(\".btn2\").click(function(){location.href =\"/admin/index.html\";})</script>");
	});
});

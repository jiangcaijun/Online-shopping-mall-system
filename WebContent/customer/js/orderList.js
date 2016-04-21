(function($){
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
	
})(jQuery);


$(document).ready(function(){
	url="/user?action=orderList";
	var exchange = "";
	$.getJSON(url,{date:new Date()},function(data){
	
		
		exchange += "<div class=\"pr-tit\"><P class=\"pr-p1\">宝贝图</P><P class=\"pr-p2\">宝贝名</P><P class=\"pr-p3\">单价(积分)</P><P class=\"pr-p3\">数量</P><P class=\"pr-p4\">交易时间</P><P class=\"pr-p5\">实付款(积分)</P><P class=\"pr-p5\">交易操作</P></div>";
		$.each(data.rows,function(i,n){
			if(n.state==1){
				exchange +="<div class=\"pr-info\" ><input type=\"hidden\" id=\""+i+"\" value=\""+n.id+"\"><div class=\"pr-con\"><div class=\"pr-con-tu f-l\"><a href=\"#\"><img height=\"80\" width=\"80\" src=\"/"+n.mainPic+"95x100.jpg\" /></a></div>";
				exchange +="<a class=\"pr-con-bt f-l\" href=\"#\">"+n.title+"</a>";
				exchange +="<div class=\"pr-con-sz1 f-l\"><p >"+n.price+"</p></div>";
				exchange +="<p class=\"pr-con-sl f-l\">"+n.num+"</p>";
				exchange +="<input type=\"hidden\" id=\""+i+"\" value=\""+n.id+"\">";
				exchange +="<div class=\"pr-con-yf f-l\"><span>"+formatterDate(n.time)+"</span></div>";
				exchange +="<input type=\"hidden\" id=\""+i+"\" value=\""+n.id+"\">";
				exchange +="<div class=\"pr-con-sz1 f-l\"><p>"+(n.num)*(n.price)+"</p></div>";
				exchange +="<div class=\"pr-con-pj f-l\"><a href=\"#\"><font  color=\"red\" >已评价</font></a><a class=\"pr-a2\" href=\"#\" id=\"delItem\">删除</a></div>";
				exchange +="<div style=\"clear: both;\"></div>";
				exchange +="</div>";
				exchange +="</div>";
			}else{
				exchange +="<div class=\"pr-info\" ><div class=\"pr-con\"><div class=\"pr-con-tu f-l\"><a href=\"#\"><img height=\"80\" width=\"80\" src=\"/"+n.mainPic+"95x100.jpg\" /></a></div>";
				exchange +="<a class=\"pr-con-bt f-l\" href=\"#\">"+n.title+"</a>";
				exchange +="<div class=\"pr-con-sz1 f-l\"><p >"+n.price+"</p></div>";
				exchange +="<p class=\"pr-con-sl f-l\">"+n.num+"</p>";
				exchange +="<div class=\"pr-con-yf f-l\"><span>"+formatterDate(n.time)+"</span></div>";
				exchange +="<input type=\"hidden\" id=\""+i+"\" value=\""+n.id+"\">";
				exchange +="<div class=\"pr-con-sz1 f-l\"><p>"+(n.num)*(n.price)+"</p></div>";
				exchange +="<div class=\"pr-con-pj f-l\"><a href=\"#\" id=\"goToComment\">未评价</a><a class=\"pr-a2\" href=\"#\" id=\"delItem\">删除</a></div>";
				exchange +="<div style=\"clear: both;\"></div>";
				exchange +="</div>";
				exchange +="</div>";
			}	
				
		});
		exchange +=	"<script type=\"text/javascript\">$(\"#exchangeAll a[id=\'delItem\']\").click(function(e){$(\"#exchangeAll div[class=\'\pr-info']\").click(function(){if(window.confirm(\"是否确定删除?\")){var exId = $(this).find(\"input[type=\'hidden\']\").val();$.getJSON(\"/user\",{action:\"delOrder\",exId:exId,date:new Date()},function(data){if(data.flag==\"true\"){alert(\"删除成功\");location.href=\"/customer/orderList.jsp\";}});}});});</script>";
		exchange +=	"<script type=\"text/javascript\">$(\"#exchangeAll a[id=\'goToComment\']\").click(function(e){$(\"#exchangeAll div[class=\'\pr-info']\").click(function(){if(window.confirm(\"是否确定评价?\")){var commentId = $(this).find(\"input[type=\'hidden\']\").val();location.href=\"/customer/myCommnet.jsp?id=\"+commentId;}});});</script>";
		$("#exchangeAll").html(exchange);
	});
	
	$("#search").click( function () {
		var itemSearch = $("#itemSearch").val();
		itemSearch = encodeURI(encodeURI(itemSearch));
		if(itemSearch!=""){
			location.href ="/searchDetails.jsp?search="+itemSearch;
		}
	});
	
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
		  $(".btn1").after("<button class=\"btn2\">商家入口    ></button><script type=\"text/javascript\">$(\".btn2\").click(function(){location.href =\"/admin/index.jsp\";})</script>");
	});
	
});

function formatterDate(val) {
	//alert(val);
    var date = new Date(val);
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    return date.getFullYear() + '-' + (m<10?('0'+m):m) + '-' + (d<10?('0'+d):d) + ' ' + (h<10?('0'+h):h) + ':' + (mi<10?('0'+mi):mi + ":" + (s<10?('0'+s):s));
}


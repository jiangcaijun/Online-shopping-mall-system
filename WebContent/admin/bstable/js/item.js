	function save(){
		var info = UM.getEditor('myEditor').getContent();
		var id = getIdSelections();
		$.get("/item",{action:"item_edit",field:"info",id:""+id,value:info});
		$table.bootstrapTable('refresh');
	}

	$('#exampleModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget);
	  var modal = $(this);
	})
	
	var $table = $('#table'),
        $up = $('#up'),
        $down = $('#down'),
        $edit = $('#edit'),
        selections = [];

    function initTable() {
        $table.bootstrapTable({
            height: getHeight(),
            columns: [
                [
					{
					    field: 'check',
					    checkbox: true,
					    align: 'center',
					    valign: 'middle'
					},
					{
					    title: '商品编号',
					    field: 'id',
					    align: 'center',
					    valign: 'middle',
					    footerFormatter: totalTextFormatter
					},
                    {
                        field: 'title',
                        title: '商品名称',
                        editable: true,
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    }, 
                    {
                        field: 'price',
                        title: '所需积分',
                        editable: true,
                        align: 'center',
                        footerFormatter: totalPriceFormatter
                    }, 
                    {
                        field: 'num',
                        title: '库存',
                        editable: true,
                        align: 'center',
                    }, 
                    {
                        field: 'operate',
                        title: '热门操作',
                        align: 'center',
                        events: operateEvents,
                        formatter: operateFormatter
                    },
                    {
                        field: 'state',
                        title: '状态操作√:上架×:下架',
                        sortable: false,
                        editable: false,
                        events: stateEvents,
                        formatter: stateFormatter,
                        align: 'center'
                    }
                ]
            ]
        });
        setTimeout(function () {
            $table.bootstrapTable('resetView');
        }, 200);
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $up.prop('disabled', !$table.bootstrapTable('getSelections').length);
            selections = getIdSelections();
        });
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $down.prop('disabled', !$table.bootstrapTable('getSelections').length);
            selections = getIdSelections();
        });
        $table.on('check.bs.table uncheck.bs.table ', function () {
            $edit.prop('disabled', $table.bootstrapTable('getSelections').length>1||!$table.bootstrapTable('getSelections').length);
            selections = getIdSelections();
        });
        $table.on('all.bs.table', function (e, name, args) {
            console.log(name, args);
        });
        $up.click(function () {
            var ids = getIdSelections();
            $.get("/item",{action:"changeState",ids:""+ids,state:"1"});
            $table.bootstrapTable('refresh');
        });
        $down.click(function () {
            var ids = getIdSelections();
            $.get("/item",{action:"changeState",ids:""+ids,state:"0"});
            $table.bootstrapTable('refresh');
        });
        $edit.click(function () {
            var id = getIdSelections();
            $.get("/item",{action:"info_edit",id:""+id},function(data){
            	$("#myEditor").append(data.info);
            },"json")
        });
        $(window).resize(function () {
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });
    }

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.check = $.inArray(row.id, selections) !== -1;
        });
        return res;
    }

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    function operateFormatter(value, row, index) {
        if(row.hot=="0"){
	        return [
	            '<a class="like" href="javascript:void(0)" title="Like" hidden>',
	            '<i class="glyphicon glyphicon-star"></i>',
	            '</a>  ',
	            '<a class="remove" href="javascript:void(0)" title="Remove">',
	            '<i class="glyphicon glyphicon-star-empty"></i>',
	            '</a>'
	        ].join('');
        }else if(row.hot=="1"){
        	return [
    	            '<a class="like" href="javascript:void(0)" title="Like">',
    	            '<i class="glyphicon glyphicon-star"></i>',
    	            '</a>  ',
    	            '<a class="remove" href="javascript:void(0)" title="Remove" hidden>',
    	            '<i class="glyphicon glyphicon-star-empty"></i>',
    	            '</a>'
    	        ].join('');
        }
    }
    function stateFormatter(value, row, index) {
        if(row.state=="1"){
	        return [
	            '<a class="up" href="javascript:void(0)" title="Up" hidden>',
	            '<i class="glyphicon glyphicon-ok"></i>',
	            '</a>  ',
	            '<a class="down" href="javascript:void(0)" title="Down">',
	            '<i class="glyphicon glyphicon-remove"></i>',
	            '</a>'
	        ].join('');
        }else if(row.state=="0"){
        	return [
    	            '<a class="up" href="javascript:void(0)" title="Up">',
    	            '<i class="glyphicon glyphicon-ok"></i>',
    	            '</a>  ',
    	            '<a class="down" href="javascript:void(0)" title="Down" hidden>',
    	            '<i class="glyphicon glyphicon-remove"></i>',
    	            '</a>'
    	        ].join('');
        }
    }
    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            $.get("/item",{action:"changeHot",id:row.id,hot:"0"});
            $table.bootstrapTable('refresh');
        },
        'click .remove': function (e, value, row, index) {
        	$.get("/item",{action:"changeHot",id:row.id,hot:"1"});
        	$table.bootstrapTable('refresh');
        }
    };
    
    window.stateEvents = {
            'click .up': function (e, value, row, index) {
                $.get("/item",{action:"changeState",ids:row.id,state:"1"});
                $table.bootstrapTable('refresh');
            },
            'click .down': function (e, value, row, index) {
            	$.get("/item",{action:"changeState",ids:row.id,state:"0"});
            	$table.bootstrapTable('refresh');
            }
        };
    function totalTextFormatter(data) {
        return 'Total';
    }

    function totalNameFormatter(data) {
        return data.length;
    }

    function totalPriceFormatter(data) {
        var total = 0;
        $.each(data, function (i, row) {
            total += +(row.price.substring(1));
        });
        return '$' + total;
    }

    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }

    $(function () {
            var scripts = [
                           location.search.substring(1) || 'bstable/js/bootstrap-table.js',
                           'bstable/js/bootstrap-table-export.js',
                           'bstable/js/tableExport.js',
                           'bstable/js/bootstrap-table-editable-item.js',
                           'http://rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/js/bootstrap-editable.js'
            ],
            eachSeries = function (arr, iterator, callback) {
                callback = callback || function () {};
                if (!arr.length) {
                    return callback();
                }
                var completed = 0;
                var iterate = function () {
                    iterator(arr[completed], function (err) {
                        if (err) {
                            callback(err);
                            callback = function () {};
                        }
                        else {
                            completed += 1;
                            if (completed >= arr.length) {
                                callback(null);
                            }
                            else {
                                iterate();
                            }
                        }
                    });
                };
                iterate();
            };

        eachSeries(scripts, getScript, initTable);
    });
	
    window.onload = function(){
		setTimeout(addOption(),200);//1000毫秒=1秒后执行test方法
	}
	function addOption(){
		 $("#select").append("<option value ='title'>商品名称</option>");
		 $("#select").append("<option value ='type'>类别</option>");
		 $("#select").append("<option value='state'>状态</option>");
	}
	
    function getScript(url, callback) {
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.src = url;

        var done = false;
        // Attach handlers for all browsers
        script.onload = script.onreadystatechange = function() {
            if (!done && (!this.readyState ||
                    this.readyState == 'loaded' || this.readyState == 'complete')) {
                done = true;
                if (callback)
                    callback();
                // Handle memory leak in IE
                script.onload = script.onreadystatechange = null;
            }
        };

        head.appendChild(script);

        // We handle everything using the script element injection
        return undefined;
    }
    
    //实例化编辑器
    var um = UM.getEditor('myEditor');
    //按钮的操作
    function createEditor() {
        enableBtn();
        um = UM.getEditor('myEditor');
    }
    /*function getContent() {
        $("#info").val(UM.getEditor('myEditor').getContent());
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getPlainTxt());
        alert(arr.join('\n'))
    }*/
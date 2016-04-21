	var $table = $('#table'),
        $remove = $('#remove'),
        $find = $('#find'),
        selections = [];

    function initTable() {
        $table.bootstrapTable({
            height: getHeight(),
            columns: [
                [
					{
					    field: 'state',
					    checkbox: true,
					    align: 'center',
					    valign: 'middle'
					},
					{
					    title: 'ID',
					    field: 'id',
					    align: 'center',
					    valign: 'middle',
					    footerFormatter: totalTextFormatter
					}, 
					{
					    title: '用户名',
					    field: 'username',
					    align: 'center',
					    valign: 'middle',
					    footerFormatter: totalTextFormatter
					},
                    {
                        field: 'tel',
                        title: '手机号',
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    },
					{
                        field: 'credit',
                        title: '积分',
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    },
                    {
                        field: 'email',
                        title: '邮箱',
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    },
                    {
                        field: 'auth',
                        title: '权限',
                        editable: true,
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    },
                    {
                        field: 'del',
                        title: '状态',
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    }
                ]
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            $table.bootstrapTable('resetView');
        }, 200);
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
            selections = getIdSelections();
        });
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $find.prop('disabled', !$table.bootstrapTable('getSelections').length);
            selections = getIdSelections();
        });
        $table.on('all.bs.table', function (e, name, args) {
            console.log(name, args);
        });
        $remove.click(function () {
        	var ids = getIdSelections();
            $.get("/user",{action:"changeState",type:"del",ids:""+ids,state:"0"},function(data){
            	if(data){
            		$table.bootstrapTable('refresh');
            	}
            });  
        });
        $find.click(function () {
        	var ids = getIdSelections();
            $.get("/user",{action:"changeState",type:"del",ids:""+ids,state:"1"},function(data){
            	if(data){
            		$table.bootstrapTable('refresh');
            	}
            }); 
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
            row.state = $.inArray(row.id, selections) !== -1;
        });
        return res;
    }

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
                           'bstable/js/bootstrap-table-editable-staff.js',
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
    window.onload = function(){
		setTimeout(addOption(),200);//1000毫秒=1秒后执行test方法
	}
	function addOption(){
		 $("#select").append("<option value ='username'>客户姓名</option>");
		 $("#select").append("<option value ='tel'>手机号</option>");
		 $("#select").append("<option value ='email'>邮箱</option>");
	}
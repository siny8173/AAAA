$(function() {
	InitLeftMenu();
})

function InitLeftMenu() {

	$('.easyui-accordion li a').click(function() {

		var tabTitle = $(this).text();
		var url = $(this).attr("href");
		addTab(tabTitle, url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});

	$(".easyui-accordion").accordion();
}

function addTab(subtitle, url) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			width : $('#mainPanle').width() - 10,
			height : $('#mainPanle').height() - 26
		});
	} else {
		$('#tabs').tabs('select', subtitle);
	}
	// tabClose();
}

function createFrame(url) {
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function logout() {
	$('#logout').submit();
}

function passwordmodify() {
	$('#password-modify-win').window({
		title : "修改密码",
		width : 400,
		height : 200,
		padding : 5,
		minimizable : false,
		maximizable : false,
		resizable : false,
		collapsible : false,
		modal : true,
		href : "passwordModify.html"
	});
}

$.extend($.fn.datagrid.defaults.editors, {
	aaa : {
		init : function(container, options) {
			var input = $('<input type="text">').appendTo(container);
			return input.numberspinner(options);
		},
		destroy : function(target) {
			$(target).numberspinner('destroy');
		},
		getValue : function(target) {
			return $(target).numberspinner('getValue');
		},
		setValue : function(target, value) {
			$(target).numberspinner('setValue', value);
		},
		resize : function(target, width) {
			$(target).numberspinner('resize', width);
		}
	}
});

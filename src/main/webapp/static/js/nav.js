
$(function(){
	InitLeftMenu();
//	tabClose();
	tabCloseEven();

})

//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});

    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
        $.each(n.children, function(j, o) {
			menulist += '<li><div><a ref="'+o.id+'" href="#" rel="' + contextPath + o.url + '" ><span class="icon '+o.iconCls+'" >&nbsp;</span><span class="nav">' + o.text + '</span></a></div></li> ';
        })
		menulist += '</ul>';

		$('#nav').accordion('add', {
            title: n.text,
            content: menulist,
            iconCls: 'icon ' + n.iconCls
        });

    });

	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var id = $(this).attr("ref");

		addTab(tabTitle,url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}

function addTab(subtitle,url){
	// +url防止同名
	if(!$('#tabs').tabs('exists',subtitle + url)){ 
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true
			
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseleft').click();
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
//	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

/**
 * 弹出信息窗口
 * @param title	标题
 * @param msgString	提示信息
 * @param msgType	信息类型 [error,info,question,warning]
 */
function msgShow(title, msgString, msgType) {
	$.messager.alert(title,msgString,msgType,function(){});
	// 不是错误消息，自动关闭
	if (msgType != 'error') {
		var interval;  
		var time=1000;  
		var x=3;    //设置时间3s
		interval=setInterval(fun,time);  
	      function fun(){
	      --x;  
	      if(x==0){
	    	  clearInterval(interval);  
	    	  $(".messager-body").window('close');    
	      }  
	}; 
	}
}

//关闭选项卡
function closeTab(subtitle){
    $('#tabs').tabs('close', subtitle);//subtitle为选项卡的标题
}

//关闭选项卡并刷新
function closeAndReloadTab(subtitle,reloadText) {
	var tabs = $("#tabs");
	tabs.tabs("close", subtitle);
	var tab = tabs.tabs("getTab", reloadText);
	if (tab != null){
		tab.panel('refresh');
	}
}

//打开新标签页
function addNewTab(subtitle,url){
	// +url防止同名
	if(!$('#tabs').tabs('exists',subtitle + url)){ 
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true
			
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
//	tabClose();
}


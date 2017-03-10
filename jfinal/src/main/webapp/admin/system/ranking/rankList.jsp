<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link rel="stylesheet" href="${CONTEXT_PATH}/static/rank/css/main.css" type="text/css">
</head>

<body>
<div class="top_box">
	<div class="time_con">
    	<p>距离<b>2017</b>年还有<b>3</b>月</p>
        <p>距离<span>2016</span>年<span>11</span>月还有<span>3</span>天</p>
    </div>
    <div class="jb_con">
    	<p>2016年11月</p>
    </div>
</div>

<div class="main_box">
	<!-- 上月销售前三名 -->
	<div class="top3_con">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/top3.jpg" /></h2>
        <div class="top3_c">
        	
        </div>
    </div>
    <!-- 上月新客户前三名 -->
    <div class="top3_con" style="float:right">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/top3.jpg" /></h2>
        <div class="top3_c">
        	
        </div>
    </div>
    
    <div class="clear"></div>
    
</div>

<div class="main_box2">
    <!-- 销售目标完成度 -->
    <div class="team_con">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/fin_1.jpg" /></h2>
        <div class="team_c scrollDiv2" id="p1">
        <ul>
        
        </ul>
        </div>
        <div class="clear"></div>
        <div class="scrollDiv" id="s1">
            <ul>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
           </ul>
        </div>
    </div>
    <!-- 新客户完成度 -->
    <div class="team_con">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/fin_2.jpg" /></h2>
        <div class="team_c scrollDiv2" id="p2">
        <ul>
        	
        </ul>
        </div>
        <div class="clear"></div>
        <div class="scrollDiv" id="s2">
            <ul>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
           </ul>
        </div>
    </div>
    <!-- 电话目标完成度 -->
    <div class="team_con">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/fin_3.jpg" /></h2>
        <div class="team_c scrollDiv2" id="p3">
        <ul>
        	
        </ul>
        </div>
        <div class="clear"></div>
        <!-- <div class="scrollDiv" id="s3">
            <ul>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
                <li><a href="javascript:void(0);"></a></li>
           </ul>
        </div> -->
    </div>
</div>
<div class="clear"></div>
<div class="foot_box"></div>
<script type="text/javascript" src="${CONTEXT_PATH}/static/rank/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${CONTEXT_PATH}/static/rank/js/index.js"></script>
<script>
$(document).ready(function() {
	initTimeInfo();
	
	initRankInfo();
	setInterval(initRankInfo, 60000);
});

function initRankInfo() {
	$.ajax({
		url:"ranking",
		data:{},
		success:function(result){
			var datas = eval(result.sales);
			if(datas.length > 12) {
				setInterval('AutoScroll2("#p1")',3000);
				setInterval('AutoScroll2("#p2")',3000);
				setInterval('AutoScroll2("#p3")',3000);
			}
			var datass;
			// 销售目标完成度
			var p1 = $("#p1").find("ul");
			p1.empty();
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				p1.append(initOlLiHtml(datass[0],datass[1],datass[2],datass[3]));
			}
			// 新客户完成度
			var p2 = $("#p2").find("ul");
			p2.empty();
			datas = eval(result.newcl);
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				p2.append(initOlLiHtml(datass[0],datass[1],datass[2],datass[3]));
			}
			// 电话目标完成度
			var p3 = $("#p3").find("ul");
			p3.empty();
			datas = eval(result.dialing);
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				p3.append(initOlLiHtml(datass[0],datass[1],datass[2],datass[3]));
			}
			// 上个月前3
			datas = eval(result.last);
			var datasss;
			var top1 = $(".top3_con:first").find(".top3_c");// 销售
			top1.empty();
			datass = datas[0];
			for(var i = 0; i < datass.length; i++) {
				datasss = datass[i];
				top1.append(initDivLiHtml(datasss[0],datasss[1],datasss[2],datasss[3]));
			}
			
			var top2 = $(".top3_con:last").find(".top3_c");// 新客户
			top2.empty();
			datass = datas[1];
			for(var i = 0; i < datass.length; i++) {
				datasss = datass[i];
				top2.append(initDivLiHtml(datasss[0],datasss[1],datasss[2],datasss[3]));
			}
			
			// 新订单
			datas = eval(result.order);
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				$("#s1").find("li a").eq(i).text(initOrderLiTxt(datass[0], datass[1], datass[2].substring(8,16)));
			}
			
			// 新客户
			datas = eval(result.newClient);initCewClientLiTxt
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				$("#s2").find("li a").eq(i).text(initCewClientLiTxt(datass[0], datass[1].substring(8,16), datass[2]));
			}
		}
	});	
}

function initTimeInfo() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var lastday = new Date(year,month - 1,0);
	$(".time_con p:first").find("b").eq(0).html(year + 1);
	$(".time_con p:first").find("b").eq(1).html(12 - month);
	$(".time_con p:last").find("span").eq(0).html(year);
	$(".time_con p:last").find("span").eq(1).html(month + 1);
	$(".time_con p:last").find("span").eq(2).html(lastday.getDate() - day);
	$(".jb_con").find("p").html(year + "年" + month + "月");
	
}

function initOlLiHtml(n, name, per, flag) {
	var gifUrl = "";
	var html = "";
	if(n/4 == 0) {
		html += "<ol>";
	}
	html+= "<li><dt>"
	html+= "	<img src=\"${CONTEXT_PATH}/static/rank/images/#flag#\" />"
	html+= "	<img src=\"${CONTEXT_PATH}/static/rank/images/top_" + n + ".png\" /></dt>"
	html+= "	<div class=\"font_c\">"
	html+= "		<h4>姓名 #name#</h4>"
	html+= "		<p>完成率 #per#</p>"
	html+= "	</div>";
	html += "</li>";
	if((n+1)/4 == 0) {
		html += "</ol>";
	}
	if(parseInt(flag) == 1) {
		gifUrl = "arrow.gif";
	} else if(parseInt(flag) == -1) {
		gifUrl = "arrow2.gif";
	} else if(parseInt(flag) == 0) {
		gifUrl = "";
	}
	html = html.replace("#flag#", gifUrl).replace("#name#", name).replace("#per#", per);
	return html;
}

function initDivLiHtml(n, name, per, imageUrl) {
	var html = "";
		html += "<li>";
		html += "<h3><img src=\"${CONTEXT_PATH}/static/rank/images/no" + n + ".png\"></h3>";
		html += "	<div class=\"img_c\">";
		html += "		<b></b>";
		html += "		<img src=\"http://wlto2o.chinapis.com/#imageUrl#\">";
		html += "	</div>";
		html += "	<div class=\"font_c\">";
		html += "		<h4>姓名 #name#</h4>";
		html += "		<p>完成率 #per#</p>";
		html += "	</div>";
		html += "</li>";
	html = html.replace("#name#", name).replace("#per#", per).replace("#imageUrl#", imageUrl);
	return html;
}

function initOrderLiTxt(name, num, time) {
	var txt = "【#name#】于 #d#日#h#时#m#分 购买了 #num# 套产品";
	txt = txt.replace("#name#", name).replace("#num#", num).replace("#d#", time.substring(0,2))
		.replace("#h#", time.substring(3,5)).replace("#m#", time.substring(6,8));
	return txt;
}

function initCewClientLiTxt(name, time, shopsName) {
	var txt = "有新客户啦! #d#日#h#时#m#分(#name#)【#shopsName#】";
	txt = txt.replace("#name#", name).replace("#shopsName#", shopsName).replace("#d#", time.substring(0,2))
		.replace("#h#", time.substring(3,5)).replace("#m#", time.substring(6,8));
	return txt;
}
</script>
</body>
</html>
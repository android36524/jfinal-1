<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>销售部排行榜</title>
    <link rel="stylesheet" href="${CONTEXT_PATH}/static/rank/css/main.css" type="text/css" />
</head>

<body>
<div class="top_box">
<!-- <button id="btn">播放声音</button> -->
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
        <ul style="height: 100%;">
        
        </ul>
        </div>
        <div class="clear"></div>
        <div class="scrollDiv" id="s1">
            <ul>
                <li><a href="javascript:void(0);"></a></li>
           </ul>
        </div>
    </div>
    
    <div class="team_con">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/fin_2.jpg" /></h2>
        <div class="team_c scrollDiv2" id="p2">
        <ul style="height: 100%;">
		
        </ul>
        </div>
        <div class="clear"></div>
        <div class="scrollDiv" id="s2">
            <ul>
                <li><a href="javascript:void(0);"></a></li>
           </ul>
        </div>
    </div>
    
    <div class="team_con no_pad">
    	<h2><img src="${CONTEXT_PATH}/static/rank/images/fin_3.jpg" /></h2>
        <div class="team_c scrollDiv2" id="p3">
        <ul style="height: 100%;">
		
        </ul>
        </div>
        <div class="clear"></div>
        <!-- <div class="scrollDiv" id="s3">
            <ul>
                <li><a href="javascript:void(0);"></a></li>
           </ul>
        </div> -->
    </div>
    <div class="clear"></div>
</div>
<div class="clear"></div>
<div class="foot_box"></div>
    <script type="text/javascript" src="${CONTEXT_PATH}/static/rank/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/static/rank/js/index.js"></script>
</body>
<audio id="chatAudio" volume=1>
	<%-- <source src="${CONTEXT_PATH}/static/rank/notify.ogg" type="audio/ogg"> --%>
	<source src="${CONTEXT_PATH}/static/rank/notify.mp3" type="audio/mpeg">
	<%-- <source src="${CONTEXT_PATH}/static/rank/notify.wav" type="audio/wav"> --%>
</audio>
<script>
$(document).ready(function() {
	var height = $(window).height();
	$("body").css("height", height);
	$("#btn").click(function() {
		$("#chatAudio")[0].play();
	})
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
			/* console.log(datas.length);
			if(datas.length > 16) {
				setInterval('AutoScroll2("#p1")',10000);
				setInterval('AutoScroll2("#p2")',10000);
				setInterval('AutoScroll2("#p3")',10000);
			} */
			setInterval('AutoScroll2("#p1")',100);
			setInterval('AutoScroll2("#p2")',100);
			setInterval('AutoScroll2("#p3")',100);
			var datass;
			// 销售目标完成度
			var p1 = $("#p1").find("ul");
			p1.empty();
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				p1.append(initOlLiHtml(datass[0],datass[1],datass[2],datass[3], '完成率'));
			}
			// 新客户完成度
			var p2 = $("#p2").find("ul");
			p2.empty();
			datas = eval(result.newcl);
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				p2.append(initOlLiHtml(datass[0],datass[1],datass[2],datass[3], '完成率'));
			}
			// 电话目标完成度
			var p3 = $("#p3").find("ul");
			p3.empty();
			datas = eval(result.dialing);
			for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				p3.append(initOlLiHtml(datass[0],datass[1],datass[2],datass[3], '完成次数'));
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
			
			var _old_txt = '';
			// 新订单
			datas = eval(result.order);
			/* for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				$("#s1").find("li a").eq(i).text(initOrderLiTxt(datass[0], datass[1], datass[2].substring(8,16)));
			} */
			datass = eval(datas[0]);
			_old_txt = $("#s1").find("li a").eq(0).text();
			if(initOrderLiTxt(datass[0], datass[1], datass[2].substring(8,16)) != _old_txt) {
				$("#s1").find("li a").eq(0).text(initOrderLiTxt(datass[0], datass[1], datass[2].substring(8,16)));
				$("#chatAudio")[0].play();
			}
			
			// 新客户
			datas = eval(result.newClient);
			/* for(var i = 0; i < datas.length; i++) {
				datass = eval(datas[i]);
				$("#s2").find("li a").eq(i).text(initCewClientLiTxt(datass[0], datass[1].substring(8,16), datass[2]));
			} */
			datass = eval(datas[0]);
			_old_txt = $("#s2").find("li a").eq(0).text();
			if(initCewClientLiTxt(datass[0], datass[1].substring(8,16), datass[2]) != _old_txt) {
				$("#s2").find("li a").eq(0).text(initCewClientLiTxt(datass[0], datass[1].substring(8,16), datass[2]));
				$("#chatAudio")[0].play();
			}
		}
	});	
}

function initTimeInfo() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var lastday = new Date(year,month,0);
	$(".time_con p:first").find("b").eq(0).html(year + 1);
	$(".time_con p:first").find("b").eq(1).html(12 - month);
	if(month + 1 > 12) {
		$(".time_con p:last").find("span").eq(0).html(year + 1);
		$(".time_con p:last").find("span").eq(1).html(1);
	} else {
		$(".time_con p:last").find("span").eq(0).html(year);
		$(".time_con p:last").find("span").eq(1).html(month + 1);
	}
	$(".time_con p:last").find("span").eq(2).html(lastday.getDate() - day);
	$(".jb_con").find("p").html(year + "年" + month + "月");
	
}

function initOlLiHtml(n, name, per, flag, txt) {
	var gifUrl = "";
	var html = "";

	html+= "<li ";
	
	if(n == 0)
		html += "class=\"b_font\"";
	html += ">"
	html += "	<img src=\"${CONTEXT_PATH}/static/rank/images/top_" + n + ".png\" align=\"absmiddle\"/>"
	html += "		#name# <span>#txt# #per#</span>"
	html += "</li>";

	if(parseInt(flag) == 1) {
		gifUrl = "arrow.gif";
	} else if(parseInt(flag) == -1) {
		gifUrl = "arrow2.gif";
	} else if(parseInt(flag) == 0) {
		gifUrl = "";
	}
	html = html.replace("#flag#", gifUrl).replace("#name#", name).replace("#per#", per).replace("#txt#", txt);
	return html;
}

function initDivLiHtml(n, name, per, imageUrl) {
	var html = "";
		html += "<li>";
		html += "<h3><img src=\"${CONTEXT_PATH}/static/rank/images/no" + n + ".png\"></h3>";
		html += "	<div class=\"img_c\">";
		html += "		<b></b>";
		html += "		<img src=\"#imageUrl#\">";
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
	var txt = "【#shopsName#】商家于 #d#日#h#时#m#分 成为(#name#)的拿货新客户";
	txt = txt.replace("#name#", name).replace("#shopsName#", shopsName).replace("#d#", time.substring(0,2))
		.replace("#h#", time.substring(3,5)).replace("#m#", time.substring(6,8));
	return txt;
}
</script>
</html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>仓库库存</h5>
				</div>
				<div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-content">
	                        <div class="echarts" id="echarts-stock-begin"></div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-content">
	                        <div class="echarts" id="echarts-stock-into"></div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-content">
	                        <div class="echarts" id="echarts-stock"></div>
	                    </div>
	                </div>
	            </div>
				<div class="ibox-content">
					<div class="row">
	                    <div class="ibox-content">
	                        <form role="form" id="form1" class="form-inline" action="list">
	                        	<input type="hidden" name="pageNumber" value="${stock.pageNumber}">
<!-- 		                            <div class="form-group"> -->
<!-- 		                                <label for="stock.name" class="sr-only">店名</label> -->
<%-- 		                                <input id="stock.name" type="text" placeholder="请输入店名" name="stock.name" value="${stock.name}" class="form-control"> --%>
<!-- 		                            </div> -->
								<button type="submit" class="btn btn-primary">搜索</button>
	                        </form>
	                    </div>
					</div>
					<div class="row">
<!-- 					<button class="btn btn-info " onclick="add();" type="button"><i class="fa fa-plus"></i> 添加</button> -->
<!--					<button class="btn btn-danger " onclick="cancel();" type="button"><i class="fa fa-gg"></i> 作废</button> -->
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>仓库</th>
									<th>商品</th>
									<th>规格</th>
									<th>单位</th>
									<th>数量</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
								<tr>
									<td><input type="radio" class="i-checks" name="checksIds" value="${v.id}"></td>
									<td>${v.get("depotName")}</td>
									<td>${v.get("productName")}</td>
									<td>${v.get("productDivider")}</td>
									<td>${v.get("productUnit")}</td>
									<td>${v.num}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div id="page"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${CONTEXT_PATH}/static/hplus/js/plugins/echarts/echarts-all.js"></script>
<script>
var stockJson = eval('(' + '${stockJson}' + ')');
var stock = echarts.init(document.getElementById("echarts-stock")),
stockOption = {
	title: {
		text: "总库存量",
		subtext: "",
		x: "center"
	},
	tooltip: {
		trigger: "item",
		formatter: "{a} <br/>{b} : {c} ({d}%)"
	},
	calculable: !0,
	series: [{
		name: "数量",
		type: "pie",
		radius: "55%",
		center: ["50%", "60%"],
		data: stockJson
	}]
};
stock.setOption(stockOption), $(window).resize(stock.resize);

var stockBeginJson = eval('(' + '${stockBeginJson}' + ')');
var stockBegin = echarts.init(document.getElementById("echarts-stock-begin")),
stockBeginOption = {
	title: {
		text: "期初库存",
		subtext: "",
		x: "center"
	},
	tooltip: {
		trigger: "item",
		formatter: "{a} <br/>{b} : {c} ({d}%)"
	},
	calculable: !0,
	series: [{
		name: "数量",
		type: "pie",
		radius: "55%",
		center: ["50%", "60%"],
		data: stockBeginJson
	}]
};
stockBegin.setOption(stockBeginOption), $(window).resize(stockBegin.resize);

var stockIntoJson = eval('(' + '${stockIntoJson}' + ')');
var stockInto = echarts.init(document.getElementById("echarts-stock-into")),
stockIntoOption = {
	title: {
		text: "入货库存",
		subtext: "",
		x: "center"
	},
	tooltip: {
		trigger: "item",
		formatter: "{a} <br/>{b} : {c} ({d}%)"
	},
	calculable: !0,
	series: [{
		name: "数量",
		type: "pie",
		radius: "55%",
		center: ["50%", "60%"],
		data: stockIntoJson
	}]
};
stockInto.setOption(stockIntoOption), $(window).resize(stockInto.resize);

</script>
<%@ include file="/common/footer.jsp"%>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div class="row">
						<div class="tabs-container">
		                    <ul class="nav nav-tabs">
		                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">全部</a>
		                        </li>
		                    </ul>
		                </div>
		                <div class="tab-content">
		                	<div class="panel-heading">
                            	<strong>基本情况</strong>
                            </div>
                            <div class="panel-body">
			                	<div class="col-md-2">
			                		<h3>出货笔数：<strong>${into.get('num')}</strong></h3>
								</div>
			                	<div class="col-md-2">
			                		<h3>出货总额：<strong style="color: red;">￥${into.get('price')}</strong></h3>
								</div>
			                	<div class="col-md-6">
			                		<h3>出货额最高的商品：<span>${maxP.get('pName')}</span></h3>
			                		<h3>出货最多的商品：<span>${most.get('pName')}</span></h3>
			                		<h3>出货额最高的供应商：<span>${total.get('sName')}</span></h3>
								</div>
                            </div>
						</div>
		                <div class="tab-content">
	                        <div id="tab-1" class="tab-pane active">
	                            <div class="panel-body">
	                                <strong>按单据</strong>
	                                <div class="ibox-content">
				                        <table class="table table-bordered">
				                            <thead>
				                                <tr>
				                                	<th>序号</th>
				                                    <th>日期</th>
													<th>出货笔数</th>
													<th>出货额(元)</th>
													<th>操作</th>
				                                </tr>
				                            </thead>
				                            <tbody>
				                            	<c:forEach items="${list}" var="v" varStatus="s">
				                                <tr>
				                                    <td>${s.index+1}</td>
				                                    <td><fmt:formatDate value="${v.get('time')}" pattern="yyyy-MM-dd" /></td>
				                                    <td>${v.get('num')}</td>
				                                    <td>${v.get('price')}</td>
				                                    <td></td>
				                                </tr>
				                            	</c:forEach>
				                            </tbody>
				                        </table>
				                    </div>
	                            </div>
	                        </div>
	                    </div>
					</div>
					<div id="page"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function cancel(){
	var ids = checksIds();
	if(ids){
		location.href = "cancelUpdate?ids="+ids; 
	}
}
function view(id){
	if(!id){
		var ids = checksIds();
		if(ids){
			location.href = "view?ids="+ids; 
		}
	}else{
		location.href = "view?ids="+id+",";
	}
}
var start = {
		elem: "#start",
		format: "YYYY-MM-DD hh:mm:ss",
//	 	min: laydate.now(),
		max: "2099-06-16 23:59:59",
		istime: true,
		istoday: false,
		choose: function(datas) {
			end.min = datas;
			end.start = datas
		}
	};
	var end = {
		elem: "#end",
		format: "YYYY-MM-DD hh:mm:ss",
//	 	min: laydate.now(),
		max: "2099-06-16 23:59:59",
		istime: true,
		istoday: false,
		choose: function(datas) {
			start.max = datas
		}
	};
	laydate(start);
	laydate(end);
</script>
<%@ include file="/common/footer.jsp"%>

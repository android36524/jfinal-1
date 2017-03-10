<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeIn">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>出货记录</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="ibox-content">
							<form role="form" id="form1" class="form-inline" action="list">
								<input type="hidden" name="pageNumber" value="${out.pageNumber}">
		                        <div class="form-group">单号:
		                        	<input id="out.code" type="text" placeholder="请输入单号" name="out.code" value="${out.code}" class="form-control">
		                        </div>
		                        <div class="form-group">客户名称:
		                            <input id="cName" type="text" placeholder="请输入客户名称" name="cName" value="${out.cName}" class="form-control">
		                        </div>
		                        <div class="form-group">日期：
                                	<input placeholder="开始日期" name="startTime" value="<fmt:formatDate value="${out.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="form-control layer-date" id="start">
                               		<input placeholder="结束日期" name="endTime" value="<fmt:formatDate value="${out.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="form-control layer-date" id="end">
                                </div>
								<button type="submit" class="btn btn-primary">搜索</button>
		        			</form>
		 				</div>
					</div>
					<div class="row">
						<button class="btn btn-info" onclick="add();" type="button"><i class="fa fa-plus"></i> 添加</button>
						<button class="btn btn-danger" onclick="cancel();" type="button"><i class="fa fa-gg"></i> 作废</button>
						<button class="btn btn-info" onclick="view();" type="button"><i class="fa fa-search"></i> 查看</button>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>单号</th>
									<th>客户</th>
									<th>出库人</th>
									<th>出库时间</th>
									<th>总价</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="radio" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td>${v.code}</td>
										<td>${v.get("cName")}</td>
										<td>${v.outUser}</td>
										<td><fmt:formatDate value="${v.outTime}" pattern="yyyy-MM-dd HH:mm" /> </td>
										<td>${v.priceTotal}</td>
										<td>
											<c:choose>  
											   <c:when test="${v.state == 'OUT'}">
											   	已出库
											   </c:when>  
											   <c:when test="${v.state == 'OUTCANCEL'}">
												已作废
											   </c:when>  
											   <c:otherwise>${v.state}  
											   </c:otherwise>  
											</c:choose>  
										</td>
										<td>
											<button class="btn btn-info " onclick="view(${v.id})" type="button"><i class="fa fa-search"></i> 查看</button>
										</td>
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
// 	min: laydate.now(),
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
// 	min: laydate.now(),
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

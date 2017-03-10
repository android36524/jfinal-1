<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>入仓单记录</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
	                    <div class="ibox-content">
	                        <form role="form" id="form1" class="form-inline" action="list">
	                        	<input type="hidden" name="pageNumber" value="${into.pageNumber}">
	                            <div class="form-group">单号:
	                                <input id="into.code" type="text" placeholder="请输入单号" name="into.code" value="${into.code}" class="form-control">
	                            </div>
	                            <div class="form-group">供货商:
	                                <select id="into.supplier_id" data-placeholder="选择供货商..." name="into.supplier_id" class="form-control">
			                           	<option value="">请选择供货商</option>
		                               	<c:forEach items="${supplier}" var="v">
			                               	<option value="${v.id}" <c:if test="${v.id == into.supplierId}" >selected="selected"</c:if>>${v.name}</option>
		                            	</c:forEach>
									</select>
	                            </div>
	                            <div class="form-group">日期：
                                	<input placeholder="开始日期" name="startTime" value="<fmt:formatDate value="${into.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="form-control layer-date" id="start">
                               		<input placeholder="结束日期" name="endTime" value="<fmt:formatDate value="${into.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="form-control layer-date" id="end">
                                </div>
								<button type="submit" class="btn btn-primary">搜索</button>
	                        </form>
	                    </div>
					</div>
					<div class="row">
						<button class="btn btn-info" onclick="add();" type="button"><i class="fa fa-plus"></i> 添加新入货单</button>
						<button class="btn btn-info" onclick="addReturn();" type="button"><i class="fa fa-plus"></i> 添加退货单</button>
						<button class="btn btn-danger" onclick="cancel();" type="button"><i class="fa fa-gg"></i> 作废</button>
						<button class="btn btn-info" onclick="view();" type="button"><i class="fa fa-search"></i> 查看</button>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>单号</th>
									<th>供货商</th>
									<th>入库人</th>
									<th>入库时间</th>
									<th>总价</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
								<tr>
									<td><input type="radio" class="i-checks" name="checksIds" value="${v.id}"></td>
									<td>${v.code}</td>
									<td>${v.get("supplierName")}</td>
									<td>${v.intoUser}</td>
									<td><fmt:formatDate value="${v.intoTime}" pattern="yyyy-MM-dd HH:mm" /> </td>
									<td style="color: red;">￥${v.priceTotal}</td>
									<td>
										<c:choose>  
										   <c:when test="${v.state == 'CREATE'}">
										   	<span style="color: green;">创建  </span>
										   </c:when>										
										   <c:when test="${v.state == 'PUT'}">
										   	<span style="color: blue;">已入库 </span>
										   </c:when>  
										   <c:when test="${v.state == 'INTOCANCEL'}">
											<span style="color: red;">已作废</span>
										   </c:when>  
										   <c:otherwise>
										   	${v.state}
										   </c:otherwise>
										</c:choose>  
									</td>
									<td>
										<c:if test="${v.state == 'CREATE'}">
											<button class="btn btn-info " onclick="edit(${v.id})" type="button"><i class="fa fa-edit"></i> 编辑</button>
											<button class="btn btn-info " onclick="intoStock(${v.id})" type="button"><i class="fa fa-spin fa-spinner"></i> 入库</button>
										</c:if>
										<c:if test="${v.state != 'INTOCANCEL'}">
											<button class="btn btn-danger " onclick="cancel(${v.id})" type="button"><i class="fa fa-gg"></i> 作废</button>
										</c:if>
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
function cancel(id){
	if(id){
		location.href = "cancelUpdate?ids="+id+",";
	}else{
		var ids = checksIds();
		if(ids){
			location.href = "cancelUpdate?ids="+ids; 
		}
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
function addReturn(){
	location.href = "edit?into.state=RETURN"
}
function intoStock(id){
	location.href = "saveIntoStock?id="+id;
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

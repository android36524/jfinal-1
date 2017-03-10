<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>商品列表</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
		                    <div class="ibox-content">
		                        <form role="form" id="form1" class="form-inline" action="list">
		                        	<input type="hidden" name="pageNumber" value="${product.pageNumber}">
		                        	<div class="form-group">商品编号:
		                                <input id="product.code" type="text" placeholder="请输入商品编号" name="product.code" value="${product.code}" class="form-control">
		                            </div>
		                            <div class="form-group">商品名称:
		                                <input id="product.name" type="text" placeholder="请输入商品名称" name="product.name" value="${product.name}" class="form-control">
		                            </div>
		                            <div class="form-group">分类:
		                                <select  id="product.category_id" data-placeholder="选择分类..." name="product.category_id" class="form-control">
				                           	<option value="">请选择分类</option>
			                               	<c:forEach items="${category}" var="v">
				                               	<option value="${v.id}" <c:if test="${v.id == product.categoryId}" >selected="selected"</c:if>>${v.name}</option>
			                            	</c:forEach>
										</select>
		                            </div>
									<button type="submit" class="btn btn-primary">搜索</button>
									<button type="reset" class="btn btn-primary">重置</button>
		                        </form>
		                    </div>
					</div>
					<div class="row">
						<div class="col-sm-4 m-b-xs">
							<button class="btn btn-info " onclick="add();" type="button"><i class="fa fa-plus"></i> 添加</button>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>图片</th>
									<th>编号</th>
									<th>名称</th>
									<th>分类</th>
									<th>单位</th>
									<th>规格</th>
									<th>价格</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="radio" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td ><img height="50px" alt="" src="${CONTEXT_PATH}/${v.img}"></td>	
										<td>${v.code}</td>
										<td>${v.name}</td>
										<td>${v.state}</td>
										<td>${v.unit}</td>
										<td>${v.divider}</td>
										<td>
											零售价(元):${v.priceRetail}<br>
											批发价(元):${v.priceWholesale}<br>
											最低售价(元):${v.priceLowest}<br>
											参考进货价(元):${v.priceReference}
										</td>
										<td>
					                        <button class="btn btn-info " onclick="edit(${v.id})" type="button"><i class="fa fa-paste"></i> 编辑</button>
<%-- 					                        <button class="btn btn-warning " onclick="del(${v.id})" type="button"><i class="fa fa-warning"></i> <span class="bold">警告</span></button> --%>
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
function authorization(id){
	location.href = "authorization?id="+id;
}
function roleToUser(id){
	location.href = "roleToUser?roleId="+id;
}
</script>
<%@ include file="/common/footer.jsp"%>

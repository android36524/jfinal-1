<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>供货商列表</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="table_basic.html#"> <i class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="table_basic.html#">选项1</a></li>
							<li><a href="table_basic.html#">选项2</a></li>
						</ul>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="row">
		                    <div class="ibox-content">
		                        <form role="form" id="form1" class="form-inline" action="list">
		                        	<input type="hidden" name="pageNumber" value="${supplier.pageNumber}">
		                            <div class="form-group">供货商名字:
		                                <input id="supplier.name" type="text" placeholder="请输入供货商名字" name="supplier.name" value="${supplier.name}" class="form-control">
		                            </div>
		                            <div class="form-group">联系方式:
		                                <input id="supplier.mobile" type="text" placeholder="请输入联系方式" name="supplier.mobile" value="${supplier.mobile}" class="form-control">
		                            </div>
									<button type="submit" class="btn btn-primary">搜索</button>
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
									<th>名称</th>
									<th>联系人</th>
									<th>联系方式</th>
									<th>联系地址</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="radio" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td>${v.name}</td>
										<td>${v.appellation}</td>
										<td>${v.mobile}</td>
										<td>${v.addr}</td>
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

</script>
<%@ include file="/common/footer.jsp"%>

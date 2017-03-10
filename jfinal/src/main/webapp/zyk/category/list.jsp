<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>资源分类列表</h5>
					<div class="ibox-tools">
						<%--
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
						 --%>
					</div>
				</div>
				<div class="ibox-content">
					<div class="row">
		                    <div class="ibox-content">
		                        <form role="form" id="form1" class="form-inline" action="list">
		                        	<input type="hidden" name="pageNumber" value="1">
		                            <div class="form-group">
		                                <label for="name" class="sr-only">分类名</label>
		                                <input id="name" type="text" placeholder="请输入分类名" name="category.name" value="${category.name }" class="form-control">
		                            </div>
									<button type="submit" class="btn btn-primary">搜索</button>
		                        </form>
		                    </div>
					</div>
					<div class="row">
						<div class="col-sm-4 m-b-xs">
							<div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                                    <button type="button" onclick="add();" class="btn btn-outline btn-default" title="新增">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <%--
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
                                    </button>
                                     --%>
                                    <button type="button" class="btn btn-outline btn-default" title="删除">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                    </button>
                             </div>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>排序</th>
									<th class="col-md-1">图标</th>
									<th>分类名</th>
									<th>备注</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="v">
									<tr>
										<td><input type="checkbox" class="i-checks"
											name="checksIds" value="${v.id}"></td>
										<td>${v.idx}</td>
										<td><img src="<c:url value='${v.icon}'/>" style="width:100%;"/></td>
										<td>${v.name}</td>
										<td>${v.comments}</td>
										<td>${v.state}</td>
										<td>
											<button class="btn btn-info " onclick="edit(${v.id})" type="button"><i class="fa fa-paste"></i> 编辑</button>
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
<%@ include file="/common/footer.jsp"%>

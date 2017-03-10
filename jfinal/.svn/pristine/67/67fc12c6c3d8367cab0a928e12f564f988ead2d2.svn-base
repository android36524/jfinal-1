<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${act}供货商信息</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown"
						href="form_basic.html#"> <i class="fa fa-wrench"></i>
					</a>
					<ul class="dropdown-menu dropdown-supplier">
						<li><a href="form_basic.html#">选项1</a></li>
						<li><a href="form_basic.html#">选项2</a></li>
					</ul>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="ibox-content">
				<form class="form-horizontal m-t" id="form1" action='update' method="post" >
					<input type="hidden" name="supplier.id" value="${supplier.id}">
					<input type="hidden" name="supplier.version" value="${supplier.version}">
					<input type="hidden" name="suplier.state" value="<c:if test="${empty suplier.state}">USE</c:if><c:if test="${!empty suplier.state}">${suplier.state}</c:if>">
					<div class="form-group">
						<label class="col-sm-3 control-label">供货商名称：</label>
						<div class="col-sm-8">
							<input id="supplier.name" name="supplier.name" value="${supplier.name}"  required="required" class="form-control"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">联系人：</label>
						<div class="col-sm-8">
							<input id="supplier.appellation" name="supplier.appellation" value="${supplier.appellation}" class="form-control"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">联系电话：</label>
						<div class="col-sm-8">
							<input id="supplier.mobile" name="supplier.mobile" value="${supplier.mobile}"  required="required" class="form-control"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">联系地址：</label>
						<div class="col-sm-8">
							<input id="supplier.addr" name="supplier.addr" value="${supplier.addr}" class="form-control"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">备注说明：</label>
						<div class="col-sm-8">
							<input id="supplier.remarks" name="supplier.remarks" value="${supplier.remarks}" class="form-control"
								type="text" aria-required="true" aria-invalid="true"
								class="error">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-3">
							<button class="btn btn-primary" type="submit">提交</button>
							<button class="btn btn-white" type="button" onclick="backList();">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
</script>
<%@ include file="/common/footer.jsp"%>

<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${act}用户</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown"
						href="form_basic.html#"> <i class="fa fa-wrench"></i>
					</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="form_basic.html#">选项1</a></li>
						<li><a href="form_basic.html#">选项2</a></li>
					</ul>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="ibox-content">
				<form class="form-horizontal m-t" id="form1" onsubmit="return check();" method="post" >
					<div class="form-group">
						<label class="col-sm-3 control-label">原密码：</label>
						<div class="col-sm-8">
							<input id="pwd" name="pwd"  value="" required="required" class="form-control" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">新密码：</label>
						<div class="col-sm-8">
							<input id="pwd1" name="pwd1" value="" required="required" class="form-control" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">再次录入密码：</label>
						<div class="col-sm-8">
							<input id="pwd2" name="pwd2" value="" required="required" class="form-control" type="password">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-3">
							<button class="btn btn-primary" type="submit" id="btn">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="${CONTEXT_PATH}/static/js/DM5.js"></script>
<script>
$(document).ready(function() {
	
});

	function check() {
		if (pwd1.value.length < 6) {
			pwd1.setCustomValidity("密码至少6位");
		} else if (pwd1.value == pwd2.value) {
			ajaxSubmit();
		} else {
			pwd2.setCustomValidity("两次输入的密码不匹配");
		}
		return false;
	}
	function ajaxSubmit() {
		$.ajax({
			url : "updatePaw",
			data : {
				"pwd" : MD5(pwd.value),
				"pwd1" : MD5(pwd1.value),
				"pwd2" : MD5(pwd2.value)
			},
			async : false,
			success : function(result) {
				layer.msg(result.msg)
			}
		});
	}
</script>
<%@ include file="/common/footer.jsp"%>

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
				<form class="form-horizontal m-t" id="form1" action='updateUserInfo' method="post" >
					<input type="hidden" name="user.id" value="${user.id}"> 
					<input type="hidden" name="user.version" value="${user.version}">
					<div class="form-group">
						<label class="col-sm-3 control-label">账号：</label>
						<div class="col-sm-8">
							<input id="user.userCode" value="${user.userCode}" readonly="readonly" class="form-control"
								type="text"> <span class="help-block m-b-none"><i
								class="fa fa-info-circle"></i> 这里写点提示的内容</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">用户名：</label>
						<div class="col-sm-8">
							<input id="user.userName" name="user.userName" value="${user.userName}" class="form-control"
								type="text" aria-required="true" aria-invalid="true"
								class="error">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">电话：</label>
						<div class="col-sm-8">
							<input id="user.phone" name="user.phone" value="${user.phone}" class="form-control" type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">参考图片：</label>
						<div class="col-sm-8">
						    <input type="file" name="image" id="imageFile">
						    <button type="button" id="upload">上传</button>
						    <input type="hidden" name="product.img" id="pictureSrc">
						    <div id="show">
						    	<img alt="" src="${CONTEXT_PATH}/upload/images/head/${user.userCode}.jpg">
						    </div>
						</div>
					</div>
					
					
					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-3">
							<button class="btn btn-primary" type="submit">提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
    <script type="text/javascript" src="${CONTEXT_PATH}/static/js/ajaxfileupload.js"></script>
<script>
$(document).ready(function() {
    $('#upload').click(function() {
    	if($('#imageFile').val() == null && $('#imageFile').val() == ""){
			layer.msg("上传的图片不能为空！");
    	}else{
	    	upload($('#imageFile').val());
    	}
    });
});
function upload(fileName) {
	$('#show').html("");  //显示图片
    $.ajaxFileUpload({
        url : '${CONTEXT_PATH}/upload/uploadHead',   //提交的路径
        secureuri : false, // 是否启用安全提交，默认为false
        fileElementId : 'imageFile', // file控件id
        
        dataType : 'json',
        data : {
            fileName : fileName   //传递参数，用于解析出文件名
        }, // 键:值，传递文件名
        success : function(data, status) {
            if (data.error == 0) {
                var src = data.src;
                $('#show').html("<img src='${CONTEXT_PATH}" + src + "'>");  //显示图片

                // 存储已上传图片地址
                var oldSrc = $('#pictureSrc').val();
                var newSrc = "";
                if (oldSrc != "")
                    newSrc = oldSrc + ";" + src;
                else
                    newSrc = src;
                $('#pictureSrc').val(newSrc);   //保存路径
            } else {
                alert(data.message);
            }
        },
        error : function(data, status) {
            alert(data.message);
        }
    });
}
</script>
<%@ include file="/common/footer.jsp"%>

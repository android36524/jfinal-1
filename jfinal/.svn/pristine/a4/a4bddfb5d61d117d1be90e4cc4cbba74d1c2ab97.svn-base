<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/weui-0.4.2/weui.min.css'/>"/>
<style type="text/css">
.offset0_1 {
	margin-left: 1em;
}
.weui_uploader_files {
	margin: 0;
}
.weui_uploader_file {
	background-image: url(<c:url value='/portal/images/ico_img.png'/>);
	background-color: #fff;
}
.weui_uploader_status i {
	text-indent: 0;
}
.weui_uploader_input_wrp .weui_uploader_input {
	height: 100%;
	width: 100%;
}
.weui_uploader_status .weui_uploader_status_content {
	left: 80%;
	top: 20%;
}
.color-org {
	color:orange;
	font-size:2em;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${act}资源分类</h5>
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
				<form class="form-horizontal m-t" id="form1" action='update' method="post" >
					<input type="hidden" name="category.id" value="${category.id}"> 
					<input type="hidden" name="category.version" value="${category.version}">
					<div class="form-group">
						<label class="col-sm-3 control-label">分类名：</label>
						<div class="col-sm-8">
							<input id="category.name" name="category.name" value="${category.name}" class="form-control"
								type="text"> <span class="help-block m-b-none"><i
								class="fa fa-info-circle"></i> 填写分类的名字</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">图标：</label>
						<div class="col-sm-8">
							<div class="weui_cells weui_cells_form">
						        <div class="weui_cell">
						            <div class="weui_cell_bd weui_cell_primary">
						                <div class="weui_uploader">
						                	<%--
						                    <div class="weui_uploader_hd weui_cell">
						                        <div class="weui_cell_bd weui_cell_primary">图片上传</div>
						                        <div class="weui_cell_ft"><span>0</span>/5</div>
						                    </div>
						                     --%>
						                    <div class="weui_uploader_bd">
						                        <ul class="weui_uploader_files">
						                        	<c:if test="${not empty category.icon}">
											            <li style="background-image:url('${CONTEXT_PATH}${category.icon}')" class="weui_uploader_file weui_uploader_status">
							                                <div class="weui_uploader_status_content">
							                                    <i class="weui_icon_cancel"></i>
							                                </div>
							                                <input type="hidden" name="category.icon" value="${category.icon }"/>
							                            </li>
						                            </c:if>
						                        </ul>
						                        <div class="weui_uploader_input_wrp">
						                            <input type="file" id="uploadFile" name="fileName" accept="image/*" class="weui_uploader_input"/>
						                        </div>
						                    </div>
						                </div>
						            </div>
						        </div>
						    </div>
						</div>
						<script type="text/javascript" src="<c:url value='/static/js/jquery.ajaxfileupload.js'/>"></script>
						<script type="text/javascript">
						$(function(){
							$("#uploadFile").ajaxfileupload({
								action: '${CONTEXT_PATH}/upload/uploadCatIcon',
								valid_extensions : ['jpg','jpeg','png','bmp','gif'],
								params: {
								  extra: 'info'
								},
								onComplete: function(data) {
									if (data.error == 0) {
						                var src = data.src;
										//weui_uploader_files
						            	var imgUrl = '${CONTEXT_PATH}'+src;
						            	var html = '<li style="background-image:url(\''+imgUrl+'\')" class="weui_uploader_file weui_uploader_status">'
											     + '      	<div class="weui_uploader_status_content">'
											     + '   		<i class="weui_icon_cancel"></i>'
											     + '  	</div>'
											     + '  	<input type="hidden" name="category.icon" value="'+src+'"/>'
											     + '</li>';
						            	$(".weui_uploader_files").append(html);
						            	initFileField();
						            } else {
						                alert(data.message);
						            }
								},
								onStart: function() {
								  //if(weWantedTo) return false; // cancels upload
								},
								onCancel: function() {
								  console.log('no file selected');
								}
							});
							
							$("body").on("click",".weui_icon_cancel",function(){
						        var img_item = $(this).parents(".weui_uploader_file");
						        if(confirm("删除这张图片？")){
						            img_item.remove();
						            initFileField();
						        }
						        return false;
						    });
							initFileField();
						});
						function initFileField(){
							if($(".weui_uploader_files li").size()>0){
				            	$(".weui_uploader_input_wrp").hide();
				            } else {
				            	$(".weui_uploader_input_wrp").show();
				            }
						}
						</script>
					</div>
					 
					<div class="form-group">
						<label class="col-sm-3 control-label">备注：</label>
						<div class="col-sm-8">
							<input id="category.comments" name="category.comments" value="${category.comments}" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">上级分类：</label>
						<div class="col-sm-8">
							<select class="form-control" name="category.pid">
								<option value="">无</option>
								<c:forEach items="${pcatList}" var="v">
	                            	<option value="${v.id}">${v.name}</option>
								</c:forEach>
                            </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">排序：</label>
						<div class="col-sm-8">
							<input id="category.idx" name="category.idx" value="${category.idx}" class="form-control" type="text">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">状态：</label>
						<div class="col-sm-8">
							<div class="radio">
								<label><input type="radio" <c:if test="${category.state == 'USE' || empty category.state }">checked="checked"</c:if>  required="required"
									value="USE" id="radio1" name="category.state">启用</label>
							</div>
							<div class="radio">
								<label><input type="radio"  <c:if test="${category.state == 'STOP'}">checked="checked"</c:if> required="required"
									value="STOP" id="radio2"  name="category.state">停用</label>
							</div>
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

<%@ include file="/common/footer.jsp"%>

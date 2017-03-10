<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>微信微客服</h5>
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
						<div class="col-sm-4 m-b-xs">
							<div class="btn-group hidden-xs" id="exampleToolbar" role="group">
                                    <button type="button" onclick="add();" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
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
									<th>id</th>
									<th>客服账号</th>
									<th>昵称</th>
									<th>微信号</th>
								</tr>
							</thead>
							<tbody  id="tbody">
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
$(document).ready(function() {
// 	{"kf_list":[{"kf_account":"kf2002@VICTON-TPMS","kf_headimgurl":"http:\/\/mmbiz.qpic.cn\/mmbiz\/BXShae78u1kUllDSHWTMJuKxmfp2yibicFMzekF2Wqspqvge7LiaMvgtMicKPpNRibp80XCw2bnaOCNjvs0FT4YZGRQ\/300?wx_fmt=jpeg","kf_id":2002,"kf_nick":"zejie","kf_wx":"ZeJie24"},{"kf_account":"kf2010@VICTON-TPMS","kf_headimgurl":"http:\/\/mmbiz.qpic.cn\/mmbiz_png\/BXShae78u1mgto05GeuYKgKGanpGdibbI83iaV7yCEUxD0vRKibepWS7UacxxypoPwnASBopbTtSEJDYI7Ke7ov6g\/300?wx_fmt=png","kf_id":2010,"kf_nick":"free","kf_wx":"FreeCL"}]}
	var json = eval("("+'${json}'+")");
	var list = json.kf_list;
	var html = '';
	for(var i=0,l=list.length;i<l;i++){
		html += '<tr>';
		html += '<td><input type="radio" class="i-checks" name="checksIds" value=""></td>';
		html += '<td>'+list[i]["kf_id"]+'</td>';
		html += '<td>'+list[i]["kf_account"]+'</td>';
		html += '<td>'+list[i]["kf_nick"]+'</td>';
		html += '<td>'+list[i]["kf_wx"]+'</td>';
		html += '</tr>';
	}
	$("#tbody").html(html);
	
});
</script>
<%@ include file="/common/footer.jsp"%>

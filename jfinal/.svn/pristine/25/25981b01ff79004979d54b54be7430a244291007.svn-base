<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<style>
.table th, .table td { 
	text-align: center; 
	height:38px;
}

</style>
<link href="${CONTEXT_PATH}/static/hplus/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>入库单</h5>
                    </div>
                    <div class="ibox-content">
                    	<form class="form-horizontal m-t" id="form1" action='saveInto' method="post" >
                    		<input type="hidden" name="into.id" value="${into.id}">
                    		<input type="hidden" name="into.state" value="CREATE">
                    		<div class="form-group" >
	                            <label class="col-md-1 control-label">仓库:</label>
	                            <div class="col-md-2">
	                                <select data-placeholder="选择仓库..." name="depotId" required="required" class="form-control">
	                                	<option value="">请选择仓库</option>
                                    	<c:forEach items="${depot}" var="v">
	                                    	<option value="${v.id}" <c:if test="${into.depotId == v.id }">selected="selected"</c:if> >${v.name}</option>
                                    	</c:forEach>
                                	</select>
	                            </div>
	                        </div>
                    		<div class="form-group" >
	                            <label class="col-md-1 control-label">供货商:</label>
	                            <div class="col-md-2">
	                                <select data-placeholder="选择供应商..." name="supplierId" required="required" class="form-control">
                                    	<option value="">请选择供货商</option>
                                    	<c:forEach items="${supplier}" var="v">
	                                    	<option value="${v.id}" <c:if test="${into.supplierId == v.id }">selected="selected"</c:if>>${v.name}</option>
                                    	</c:forEach>
                                	</select>
	                            </div>
	                            <div class="col-md-1">
			                        <a onclick="openWin();" href="javascript:void(0);" class="btn btn-primary">添加新的供货商</a>
	                            </div>
	                            <label class="col-md-1 control-label">入库日期:</label>
	                            <div class="col-md-2 date">
	                            	<input placeholder="开始日期" id="data_1"  name="date" value="<fmt:formatDate value="${into.intoTime}" pattern="yyyy-MM-dd HH:mm:ss" />" class="form-control layer-date" id="start">
	                            </div>
	                        </div>
	                        <div class="form-group" >
	                            <label class="col-md-1 control-label">入库单号:</label>
	                            <div class="col-md-2">
	                                <input type="text" class="form-control" required="required" name="code" value="${into.code}">
	                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> jxc是一个例子</span>
	                            </div>
	                            <div class="col-md-1">
	                            </div>
	                            <label class="col-md-1 control-label">入库人:</label>
	                            <div class="col-md-2">
	                                <input type="text" class="form-control" required="required" name="intoUser" value="<shiro:principal property="userName"></shiro:principal>">
	                            </div>
	                        </div>
	                        <table class="table table-striped table-bordered table-hover " id="editable">
	                            <thead>
	                                <tr>
	                                    <th>序号</th>
	                                    <th>商品名称/规格</th>
	                                    <th>商品编号</th>
	                                    <th>单位</th>
	                                    <th>数量</th>
	                                    <th>单价(元)</th>
	                                    <th>金额(元)</th>
	                                </tr>
	                            </thead>
	                            <tbody id="tabeldata">
	                            	<c:forEach items="${list}" var="v" varStatus="status">
	                                <tr id="tr_${status.index + 1}">
		                            	<td class="_num col-md-1">${status.index + 1}<span style="position: fixed;"></span></td>
		                                <td>
		                                	<div class="input-group">
						                        <input type="text" value="${v.get("pname")}" class="form-control _testNoBtn">
						                        <div class="input-group-btn">
						                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                                <span class="caret"></span>
						                            </button>
						                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
						                            </ul>
						                        </div>
						                        <!-- /btn-group -->
						                    </div>
		                                </td>
		                                <td class="_code"></td>
		                                <td class="_unit"><input  type="text" name="unit" value="${v.unit}" class="_in_unit form-control"/></td>
		                                <td class="_num"><input type="number" name="num"  value="${v.num}" class="_in_num form-control"/><input type="hidden" value="${v.get('pid')}" name="pid"/><input type="hidden" value="${v.id}" name="id"/></td>
		                                <td class="_price"><input type="number" name="price" value="${v.priceInto}"  class="_in_price form-control" /></td>
		                                <td class="_total_price"><input type="number" name="totalPrice" value="${v.priceTotal}" class="_in_total_price form-control" /></td>
	                                </tr>
	                                </c:forEach>
	                                <c:if test="${empty list }">
		                            <c:forEach begin="1" end="6" var="v">
	                                <tr id="tr_${v}">
		                            	<td class="_num col-md-1">${v}<span style="position: fixed;"></span></td>
		                                <td>
		                                	<div class="input-group">
						                        <input type="text" class="form-control _testNoBtn">
						                        <div class="input-group-btn">
						                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                                <span class="caret"></span>
						                            </button>
						                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
						                            </ul>
						                        </div>
						                        <!-- /btn-group -->
						                    </div>
		                                </td>
		                                <td class="_code"></td>
		                                <td class="_unit"><input  type="text" name="unit" class="_in_unit form-control"/></td>
		                                <td class="_num"><input type="number" name="num" class="_in_num form-control"/><input type="hidden" name="pid"/></td>
		                                <td class="_price"><input type="number" name="price" class="_in_price form-control" /></td>
		                                <td class="_total_price"><input type="number" name="totalPrice" class="_in_total_price form-control" /></td>
	                                </tr>
	                                </c:forEach>
	                                </c:if>
	                            </tbody>
	                        </table>
	                        <div class="row">
	                            <div class="col-md-12">
					         		<div class="row">
					         			<div class="col-md-2">
					                    	<a onclick="addrow();" href="javascript:void(0);" class="btn btn-primary ">添加行</a>
					                	</div>
						            	<div class="col-md-6">
						            		<div class="form-group" >
					                            <label class="col-md-1 control-label">备注:</label>
					                            <div class="col-md-8">
					                                <input type="text" class="form-control" name="remarks"value="${into.remarks}">
					                            </div>
					                        </div>
						            	</div>
						            	<div class="col-md-4">
						            		<div class="form-group" >
					                            <label class="col-md-2 control-label">总额:</label>
					                            <div class="col-md-4">
					                                <input type="text" class="form-control" name="priceTotal"value="${into.priceTotal}">
					                            </div>
					                        </div>
						            	</div>
					         		</div>
					         	</div>
					        </div>
	                        <div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button class="btn btn-primary" type="submit" > 提 交 </button>
									<button class="btn btn-white" type="button" onclick="backList();">返回</button>
								</div>
							</div>
                        </form>
                    </div>
                </div>
			</div>
		</div>
	</div>
</div>
<script src="${CONTEXT_PATH}/static/hplus/js/plugins/clockpicker/clockpicker.js"></script>
<script>
$(document).ready(function() {
	$("._num").mouseenter(function(){
		$(this).find("span").html('<button class="btn btn-danger btn-rounded btn-xs" onclick="delrow(this)" type="button"><i class="fa fa-times"></i></button>');
	}).mouseleave(function(){
		$(this).find("span").html('');
	});
	/**
     * 测试(首次从 URL 获取数据，显示 header，不显示按钮，忽略大小写)
     */
	//选择商品下拉
  	bsSuggest();
	//数量加减时计算金额
	$("._in_num").change(function(){
		var num = $(this).val();
		var price = $(this).parent().parent().find("._in_price").val();
		$(this).parent().parent().find("._in_total_price").val(num*price);
	});
	$("._in_price").change(function(){
		var price = $(this).val();
		var num = $(this).parent().parent().find("._in_num").val();
		$(this).parent().parent().find("._in_total_price").val(num*price);
	});
	//设置默认日期
	var now = new Date();
	<c:if test="${empty into.code}">
	$("#data_1").val(now.getFullYear() + "-" + (now.getMonth()-0+1) + "-" + now.getDate() + " "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds());
	//设置单号
	$("input[name='code']").val("jxcinto"+now.getFullYear() + (now.getMonth()-0+1) + now.getDate());
	</c:if>
	
	//所有input的值改变都会重新计算金额
	$("input").change(function(){
		price();
	});
});
//计算整个单的金额
function price(){
	var price = 0;
	$("input[name='totalPrice']").each(function(){
		if($(this).val()){
			price += $(this).val()-0;
		}
	});
	$("input[name='priceTotal']").val(price);
}
function bsSuggest(){
	var data = eval('('+'${product}'+')');
	var trId = "tr_1";
	 $("._testNoBtn").bsSuggest({
//      url: "${CONTEXT_PATH}/static/hplus/demo/demo.json",
//		url: "getProduct",
		data: {
      		value: data.value
  		},
      	searchFields: ["code"],
   	    effectiveFields: ["code", "name","price_reference"],
    	effectiveFieldsAlias:{code: "编号",name:"名称",price_reference:"参考进货格",unit:"单位"},
      	ignorecase: true,
      	showHeader: true,
      	showBtn: false,//不显示下拉按钮
      	delayUntilKeyup: true,//获取数据的方式为 firstByUrl 时，延迟到有输入/获取到焦点时才请求数据
      	idField: "id",
      	keyField: "name"
  	}).on('onDataRequestSuccess', function (e, result) {
      	console.log('onDataRequestSuccess: ', result);
  	}).on('onSetSelectValue', function (e, v) {
      	$.ajax({
			url:"getProductById",
			data:{"id":v.id},
			success:function(result){
				if(result.re.code == 'errer')
					layer.alert(result.re.msg)
				else{
					var data = result.re.data;
					$("#"+trId).find("._code").html(data.code);
					$("#"+trId).find("input[name='unit']").val(data.unit);
					$("#"+trId).find("input[name='num']").val(1);
					$("#"+trId).find("input[name='pid']").val(data.id);
					$("#"+trId).find("._price").find("input").val(data.price_reference);
					$("#"+trId).find("._total_price").find("input").val(data.price_reference);
					price();
				}
			}
		});	
      	
  	}).on('onUnsetSelectValue', function () {
     	 console.log("onUnsetSelectValue");
  	}).on('click',function(){
  		trId = $(this).parent().parent().parent().attr("id");
  	});
}
function delrow(obj){
	$(obj).parent().parent().parent().remove();
}
var i = 7;
function addrow(){
	var h = '<tr id="tr_'+ i +'">';
    h += '<td class="_num col-md-1">'+ i +'<span style="position: fixed;"></span></td>';
    h += '<td>';
    h += '<div class="input-group">';
    h += '    <input type="text" class="form-control _testNoBtn">';
    h += '    <div class="input-group-btn">';
    h += '        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">';
    h += '            <span class="caret"></span>';
    h += '        </button>';
    h += '        <ul class="dropdown-menu dropdown-menu-right" role="menu">';
    h += '        </ul>';
    h += '    </div>';
    h += '</div>';
    h += '</td>';
    h += '<td class="_code"></td>';
    h += '<td class="_unit"></td>';
    h += '<td class="_num"><input type="number" class="_in_num form-control"  /></td>';
    h += '<td class="_price"><input type="number" class="_in_price form-control" /></td>';
    h += '<td class="_total_price"><input type="number" class="_in_total_price form-control" /></td>';
    h += '</tr>';
    $("#tabeldata").append(h);
    i++;
    bsSuggest();
}
function openWin(){
	layer.open({
		  type: 2,
		  title: '添加供货商',
		  shadeClose: false,
		  shade: 0.8,
		  area: ['50%', '80%'],
		  content: '${CONTEXT_PATH}/erp/jxc/supplier/edit' //iframe的url
		  ,cancel: function(){ 
			//右上角关闭回调
			location.reload()
		  }
		}); 
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
	laydate(start);
</script>
<%@ include file="/common/footer.jsp"%>

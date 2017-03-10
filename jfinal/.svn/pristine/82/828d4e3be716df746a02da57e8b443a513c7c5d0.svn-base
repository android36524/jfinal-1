<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<style>
.table th, .table td { 
	text-align: center; 
	height:38px;
}

</style>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>入库单 状态：
                        <c:choose>  
						   <c:when test="${into.state == 'PUT'}">
						   	<span style="color: green;"><strong>入库</strong></span> 
						   </c:when>  
						   <c:when test="${into.state == 'INTOCANCEL'}">
							<span style="color: red;"><strong>已作废</strong></span>
						   </c:when>  
						   <c:otherwise>${into.state}  
						   </c:otherwise>  
						</c:choose>  
                        </h5>
                    </div>
                    <div class="ibox-content">
                    	<form class="form-horizontal m-t" >
                    		<div class="form-group" >
	                            <label class="col-md-1 control-label">仓库:</label>
	                            <div class="col-md-2">
	                            	<input type="text" class="form-control"  value="${into.get('dname')}">
	                            </div>
	                        </div>
                    		<div class="form-group" >
	                            <label class="col-md-1 control-label">供货商:</label>
	                            <div class="col-md-2">
	                                <input type="text" class="form-control"  value="${into.get('sname')}">
	                            </div>
	                            <label class="col-md-1 control-label">入库日期:</label>
	                            <div class="col-md-2 date">
	                                <input type="text" class="form-control"  value="${into.intoTime}">
	                            </div>
	                        </div>
	                        <div class="form-group" >
	                            <label class="col-md-1 control-label">入库单号:</label>
	                            <div class="col-md-2">
	                                <input type="text" class="form-control" required="required" value="${into.code}">
	                            </div>
	                            <label class="col-md-1 control-label">入库人:</label>
	                            <div class="col-md-2">
	                            	<input type="text" class="form-control" required="required" value="${into.intoUser}">
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
	                                <tr >
		                            	<td class="_num col-md-1">${status.index + 1}</td>
		                                <td>${v.get("pname")}</td>
		                                <td class="_code">${v.get("pcode")}</td>
		                                <td class="_unit">${v.unit}</td>
		                                <td class="_num">${v.num}</td>
		                                <td class="_price">${v.priceInto}</td>
		                                <td class="_total_price">${v.priceTotal}</td>
	                                </tr>
	                                </c:forEach>
	                            </tbody>
	                        </table>
	                        <div class="row">
	                            <div class="col-md-12">
					         		<div class="row">
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
					         		<div class="form-group">
										<div class="col-sm-8 col-sm-offset-3">
											<button class="btn btn-white" type="button" onclick="backList();">返回</button>
										</div>
									</div>
					         	</div>
					        </div>
						</form>
                    </div>
                </div>
			</div>
		</div>
	</div>
</div>
<script src="${CONTEXT_PATH}/static/hplus/js/plugins/suggest/bootstrap-suggest.min.js"></script>
<script src="${CONTEXT_PATH}/static/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script>
$(document).ready(function() {
});
</script>
<%@ include file="/common/footer.jsp"%>

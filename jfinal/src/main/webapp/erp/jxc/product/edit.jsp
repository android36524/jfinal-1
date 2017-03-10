<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${act}商品信息</h5>
			</div>
			<div class="ibox-content" >
				<form class="form-horizontal col-md-6" id="form1" action='update' method="post" >
					<input type="hidden" name="product.id" value="${product.id}">
					<input type="hidden" name="product.version" value="${product.version}">
					<input type="hidden" name="product.img" value="${product.img}" id="pictureSrc">
					<input type="hidden" value="${beginStock.id}" name="beginStock.id">
					<div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse">基本信息</a>
                        </h4>
				    </div>
					<div class="form-group">
						<label class="col-sm-3 control-label">编号：</label>
						<div class="col-sm-8">
							<input id="product.code" name="product.code" value="${product.code}" <c:if test="${!empty product.id}">disabled="disabled"</c:if> required="required" class="form-control"
								type="text"> <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 方便查询用</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">商品名称：</label>
						<div class="col-sm-8">
							<select data-placeholder="选择分类..." name="product.category_id" required="required" class="form-control">
	                           	<option value="">请选择分类</option>
                               	<c:forEach items="${category}" var="v">
	                               	<option value="${v.id}" <c:if test="${v.id == product.categoryId}" >selected="selected"</c:if>>${v.name}</option>
                            	</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">商品名称：</label>
						<div class="col-sm-8">
							<input id="product.name" name="product.name" value="${product.name}"  required="required" class="form-control"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">单位：</label>
						<div class="col-sm-8">
							<input id="product.unit" name="product.unit" value="${product.unit}"  required="required" class="form-control"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">规格：</label>
						<div class="col-sm-8">
							<input id="product.divider" name="product.divider" value="${product.divider}" class="form-control"
								type="text">
						</div>
					</div>
					<div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse">销售价格</a>
                        </h4>
				    </div>
					<div class="form-group">
						<label class="col-sm-3 control-label">零售价(元)：</label>
						<div class="col-sm-8">
							<input id="product.price_retail" name="product.price_retail" value="${product.priceRetail}" class="form-control"
								type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">批发价(元)：</label>
						<div class="col-sm-8">
							<input id="product.price_wholesale" name="product.price_wholesale" value="${product.priceWholesale}" class="form-control"
								type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">最低售价(元)：</label>
						<div class="col-sm-8">
							<input id="product.price_lowest" name="product.price_lowest" value="${product.priceLowest}" class="form-control"
								type="number">
						</div>
					</div>
					
					<div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse">进货价格</a>
                        </h4>
				    </div>
					<div class="form-group">
						<label class="col-sm-3 control-label">参考进货价(元)：</label>
						<div class="col-sm-8">
							<input id="product.price_reference" name="product.price_reference" value="${product.priceReference}" class="form-control"
								type="number">
						</div>
					</div>
					<div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse">库存预警</a>
                        </h4>
				    </div>
				    <div class="form-group">
						<label class="col-sm-3 control-label">最低库存量：</label>
						<div class="col-sm-8">
							<input id="product.stock_low" name="product.stock_low"  value="${product.stockLow}" class="form-control" type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">最高库存量：</label>
						<div class="col-sm-8">
							<input id="product.stock_high" name="product.stock_high"  value="${product.stockHigh}" class="form-control" type="number">
						</div>
					</div>
					<div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse">期初信息</a>
                        </h4>
				    </div>
					<div class="form-group">
						<label class="col-sm-3 control-label">期初库存数量：</label>
						<div class="col-sm-8">
							<input value="${beginStock.num}" name="beginStock.num" class="form-control" type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">期初成本价（元）：</label>
						<div class="col-sm-8">
							<input value="${beginStock.priceCost}" name="beginStock.price_cost" class="form-control" type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">期初总金额（元）：</label>
						<div class="col-sm-8">
							<input value="${beginStock.priceTotal}" name="beginStock.price_total" class="form-control" type="number">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">备注说明：</label>
						<div class="col-sm-8">
							<input id="product.remarks" name="product.remarks" value="${product.remarks}" class="form-control"
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
				<div class="form-group col-md-6">
					<label class="col-sm-3 control-label">参考图片：</label>
					<div class="col-sm-8">
					    <input type="file" name="image" id="imageFile">
					    <button type="button" id="upload">上传</button>
					    <div id="show">
					    	<img alt="" src="${CONTEXT_PATH}/${product.img}">
					    </div>
					</div>
				</div>
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
    $.ajaxFileUpload({
        url : '${CONTEXT_PATH}/upload/imageUpload',   //提交的路径
        secureuri : false, // 是否启用安全提交，默认为false
        fileElementId : 'imageFile', // file控件id
        dataType : 'json',
        data : {
            fileName : fileName   //传递参数，用于解析出文件名
        }, // 键:值，传递文件名
        success : function(data, status) {
            if (data.error == 0) {
                var src = data.src;
                $('#show').html("<img src='${CONTEXT_PATH}/" + src + "'>");  //显示图片
                $('#pictureSrc').val(src);   //保存路径
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

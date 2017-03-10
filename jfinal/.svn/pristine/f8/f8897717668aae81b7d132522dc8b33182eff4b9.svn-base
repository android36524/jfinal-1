<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style media="screen">
  /*ueditor*/
  .list-paddingleft-2 {
    padding-left: 30px;
  }
  /*weui*/
  .weui-msg__extra-area {
    position: static;
  }

  .article-view-header {
    margin-bottom: 2rem;
  }

  .article-view-header-area {
    padding: 0.5rem;
  }

  .article-view-header-area i {
    font-style: normal;
    margin-right: 1.5rem;
  }

  .article-view-content {
    padding: 1.2rem;
  }
  .article-view-info {
    -moz-box-orient: vertical;
    color: #999999;
    display: -webkit-box;
    font-size: 13px;
    line-height: 1.2;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .article-view-info .article-view-info-count {
    font-style: normal;
    margin-right: 1.5rem;
  }
  .icon-good.red {
  	color: red;
  }
  </style>
</head>

<body>
	
  <div class="article-view-content">
    <div class="article-view-header">
      <h3>${template.title}${article.title }</h3>
      <div class="article-view-header-area"><i class="article-view-subtime"><fmt:formatDate value="${template.createTime }" pattern="yyyy-MM-dd"/><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd"/></i><i class="article-view-author">${article.shopsName}</i></div>
    </div>
    <div class="article-view-content-main">
      ${template.content}${article.content }
    </div>
    <p class="article-view-info" style="margin-top:10px;"><span class="article-view-info-count"><i class="iconfont icon-browse"></i><i>${template.hits}${article.hits }</i></span><span class="article-view-info-up"><i class="iconfont icon-good" data-id="${template.id}${article.id}" data-cat="${not empty template?'T':'A' }"></i><i>${(not empty template)?((not empty template.voteUp)?template.voteUp:0):((not empty article.voteUp)?article.voteUp:0)}</i></span></p>
  </div>

  <div class="weui-msg__extra-area">
      <div class="weui-footer">
          <p class="weui-footer__links">
              <a href="javascript:void(0);" class="weui-footer__link"></a>
          </p>
          <p class="weui-footer__text">Copyright &copy; 2008-2016 ?</p>
      </div>
  </div>
<script type="text/javascript">
$("body").on("click",".icon-good",function(){
	var _this = $(this);
	var id = _this.data("id");
	var cat = _this.data("cat");
	var url = "<c:url value='/weixin/voteUp'/>";
	var data = "id="+id+"&cat="+cat;
	$.ajax({url:url,data:data,dataType:"json",success:function(result){
		if(result&&result.success) {
			_this.addClass("red");
			var num = _this.next("i").text()||0;
			_this.next("i").text(parseInt(num)+1);
		} else if(result&&result.errcode=="1001"){
			alert(result.errmsg||"系统错误");
			_this.addClass("red");
		} else {
			alert("系统错误");
		}
	}});
});

</script>
</body>
</html>
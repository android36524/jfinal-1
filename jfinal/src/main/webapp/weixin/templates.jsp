<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>公共资源库</title>
    <style media="screen">
        .drawer-menu {
            margin-top: 1px;
        }
        
        .drawer--right .drawer-nav--right {
            right: -23rem;
            width: 23rem;
        }
        
        .top-nav {
            position: fixed;
            top: 0px;
            width: 100%;
            z-index: 1;
        }
        
        .top-nav .top-nav-header {
            border-bottom: 1px solid #ddd;
            background-color: #fff;
            text-align: center;
        }
        
        .top-nav .top-nav-header .top-nav-brand {
            display: inline-block;
            line-height: 5.5rem;
        }
        
        .top-nav .top-nav-filter {
            right: 0;
            position: fixed;
            z-index: 104;
            top: 0;
            display: block;
            box-sizing: content-box;
            width: 3.5rem;
            padding: 0;
            padding-top: 18px;
            padding-right: .75rem;
            padding-bottom: 30px;
            padding-left: .75rem;
            border: 0;
            outline: 0;
            background-color: transparent;
        }
        
        .main-panels {
            margin-top: 5.5rem;
        }
        
        .panel-button-area {
            display: inline-block;
            position: absolute;
            bottom: 0;
            left: auto;
            right: auto;
            width: 100%;
            text-align: center;
            z-index: 200;
            padding: 2rem;
            background-color: #fff;
            border-top: 1px solid #D9D9D9;
        }
        
        .panel-button {
            padding: .75rem;
            display: inline-block;
            border-radius: 5%;
            color: #333;
            background-color: #fff;
            border: 1px solid #adadad;
        }
        
        .panel-button:hover {
            color: #333;
            background-color: #e6e6e6;
            border-color: #adadad;
        }
        
        .drawer-hamburger {
            width: 3.5rem;
        }
        /*weui*/

        .weui-cell {
            padding-left: 5px;
            padding-right: 5px;
        }
        
        .weui-media-box__desc span {
            padding: 0 0.5rem;
        }
        
        .weui-cells {
            margin-top: 0.5rem;
        }
        
        .weui-cell.more-info::before {
            border: none;
        }
        
        .drawer-nav--right .weui-cell_access .weui-cell__ft::after {
            transform: matrix(-0.71, 0.71, -0.71, -0.71, 0, 0);
            -webkit-transform: matrix(-0.71, 0.71, -0.71, -0.71, 0, 0);
            transition: transform .2s ease, -webkit-transform .2s ease;
            -webkit-transition: transform .2s ease, -webkit-transform .2s ease;
        }
        
        .drawer-nav--right .weui-cell_access.open .weui-cell__ft::after {
            transform: matrix(0.71, -0.71, 0.71, 0.71, 0, 0);
            -webkit-transform: matrix(0.71, -0.71, 0.71, 0.71, 0, 0);
        }
        
        .drawer-menu-item {
            font-size: 1rem;
            display: block;
            padding: .75rem;
            text-decoration: none;
            color: #222;
        }
        
        .drawer-menu-item:hover {
            text-decoration: none;
            color: #555;
            background-color: transparent;
        }
        
        .drawer-menu-item.title {
            font-size: 1.7rem;
            font-weight: bold;
            text-align: center;
        }
        
        .drawer-menu-item p {
            margin: 0;
        }
        
        a:hover,
        a:focus {
            color: inherit;
            text-decoration: none;
        }
        /*! drawer-caret */
        
        .drawer-dropdown .drawer-caret {
            display: inline-block;
            width: 0;
            height: 0;
            margin-left: 4px;
            -webkit-transition: opacity .2s ease, -webkit-transform .2s ease;
            transition: opacity .2s ease, -webkit-transform .2s ease;
            transition: transform .2s ease, opacity .2s ease;
            transition: transform .2s ease, opacity .2s ease, -webkit-transform .2s ease;
            -webkit-transform: rotate(0deg);
            -ms-transform: rotate(0deg);
            transform: rotate(0deg);
            vertical-align: middle;
            border-top: 4px solid;
            border-right: 4px solid transparent;
            border-left: 4px solid transparent;
        }
        /*! open */
        
        .drawer-dropdown.open .drawer-caret {
            -webkit-transform: rotate(180deg);
            -ms-transform: rotate(180deg);
            transform: rotate(180deg);
        }
        /*! labelauty*/
        
        input.myLabel+label {
            color: #1a1a1a;
            background-color: #fff;
            border: 1px solid #bdbdbd;
            display: inline-block;
        }
        
        .favorite-area {
            position: absolute;
            right: 1.5rem;
            display: inline-block;
            width: 2rem;
            height: 2rem;
        }
        
        .favorite-area.success {
            color: red;
        }
        .icon-favoritesfilling {
        	color: red;
        }
        
        .data-not-found {
        	text-align: center;
    		padding: 2rem;
    		color: #999999;
    		font-size: 14px;
        }
    </style>
</head>
<body>
	<div class="drawer drawer--left">
        <header role="banner">
          <button type="button" class="drawer-toggle drawer-hamburger drawer-toggle--left">
            <span class="sr-only">toggle navigation</span>
            <span class="drawer-hamburger-icon"></span>
          </button>
          <nav class="drawer-nav drawer-nav--left" role="navigation">
            <ul class="drawer-menu">
              <li class="drawer-menu-item title">公共资源库分类</li>
              <li class="drawer-menu-item">
                <div class="weui-cells">
                  <c:forEach items="${cats }" var="cat">
                  	<a class="weui-cell weui-cell_access ls-cat-list-item" href="javascript:;" data-cat-id="${cat.id }">
                      <div class="weui-cell__bd">
                          <p>${cat.name }</p>
                      </div>
                      <div class="weui-cell__ft"></div>
                  	</a>
                  </c:forEach>
                </div>
              </li>
            </ul>
            <div class="panel-button-area">
              <button type="button" class="panel-button ls-btn-myRs">我的资源库</button>
            </div>
          </nav>
        </header>
      </div>
      <div class="drawer drawer--right" id="right">
        <header role="banner">
          <!-- hide the toggle button
          <button type="button" class="drawer-toggle drawer-hamburger drawer-toggle--right">
            <span class="sr-only">toggle navigation</span>
            <span class="iconfont icon-filter drawer-hamburger-icon"></span>
          </button>
-->
          <form name="form1" action="templates">
          <nav class="drawer-nav drawer-nav--right" role="navigation">
            <ul class="drawer-menu">
              <li class="drawer-menu-item title">筛选</li>
              <li class="drawer-menu-item">
                <div class="weui-cells">
                  <a class="weui-cell weui-cell_access show-all" href="javascript:;">
                      <div class="weui-cell__bd">
                          <p>分类</p>
                      </div>
                      <div class="weui-cell__ft">全部</div>
                  </a>
                  <div class="weui-cell">
                    <div class="weui-cell__bd">
                      <c:forEach items="${cats }" var="cat" end="2">
                      	<input type="checkbox" data-labelauty="${cat.name }" class="label-checkbox" name="catIds" value="${cat.id }"/>
                      </c:forEach>
                    </div>
                  </div>
                  <div class="weui-cell more-info" style="display: none;">
                    <div class="weui-cell__bd">
                      <c:forEach items="${cats }" var="cat" begin="3">
                      	<input type="checkbox" data-labelauty="${cat.name }" class="label-checkbox" name="catIds" value="${cat.id }"/>
                      </c:forEach>
                    </div>
                  </div>

                </div>
              </li>
              <li class="drawer-menu-item">
                <div class="weui-cells">
                  <a class="weui-cell weui-cell_access" href="javascript:;">
                      <div class="weui-cell__bd">
                          <p>时间</p>
                      </div>
                      <!--div class="weui-cell__ft"></div-->
                  </a>
                  <div class="weui-cell">
                    <div class="row">
                      <div class="col-sm-9">
                        <div class="input-daterange input-group" id="datepicker">
                            <input type="text" class="input-sm form-control" name="startTime" value="${startTime }"/>
                            <span class="input-group-addon">到</span>
                            <input type="text" class="input-sm form-control" name="endTime" value="${endTime }"/>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </li>
              <%--
              <li class="drawer-menu-item">
                <div class="weui-cells">
                  <a class="weui-cell weui-cell_access" href="javascript:;">
                      <div class="weui-cell__bd">
                          <p>关键字</p>
                      </div>
                      <!--div class="weui-cell__ft"></div-->
                  </a>
                  <div class="weui-cell">
                    <div class="weui-cell__bd">
                      <input class="form-control" type="text" name="keyWords" placeholder="关键字">
                    </div>
                  </div>
                </div>
              </li>
 			  --%>
            </ul>
            <div class="panel-button-area">
              <button type="reset" class="btn btn-default">重置</button>
              <button type="submit" class="btn btn-success">确定</button>
            </div>
          </nav>
          </form>
        </header>
      </div>
      
      <div class="body">
        <header class="top-nav">
          <nav class="top-nav-header">
            <div class="top-nav-brand">公共资源库</div>
          </nav>
          <button type="button" class="top-nav-filter">
            <span class="sr-only">filter resource</span>
            <span class="iconfont icon-filter"></span>
          </button>
        </header>
        <div class="main-panels">
          <div class="weui-panel weui-panel_access">
              <div class="weui-panel__bd template-list">
              <c:if test="${empty page.list }">
              <div class="data-not-found">未找到数据</div>
              </c:if>
              	  <c:forEach items="${page.list}" var="v">
	                  <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg ls-list-item" data-id="${v.id }">
	                      <div class="weui-media-box__hd">
	                          <img class="weui-media-box__thumb" src="${not empty v.mainPic?CONTEXT_PATH.concat(v.mainPic):CONTEXT_PATH.concat('/static/images/ico_img.png') }" alt="">
	                      </div>
	                      <div class="weui-media-box__bd">
	                          <h4 class="weui-media-box__title">${v.title }</h4>
	                          <p class="weui-media-box__desc">${v.description }</p>
	                          <p class="weui-media-box__desc" style="margin-top:10px;"><span class=""><fmt:formatDate value="${v.createTime}" pattern="yyyy-MM-dd"/></span><span><i class="iconfont icon-browse"></i><i>${(not empty v.hits)?v.hits:0 }</i></span><span><i class="iconfont icon-good"></i><i>${(not empty v.voteUp)?v.voteUp:0 }</i></span></p>
	                      </div>
	                      <div class="favorite-area">
	                        <i class="iconfont ${xf:contains(favlist,v.id)?'icon-favoritesfilling':'icon-favorite' }" data-id="${v.id }"></i>
	                      </div>
	                  </a>
              	  </c:forEach>
              </div>
              <c:if test="${not empty page.list }">
              <div class="weui-panel__ft">
                  <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link template-paginate" data-page-number="1">
                      <div class="weui-cell__bd">查看更多</div>
                      <span class="weui-cell__ft"></span>
                  </a>
              </div>
              </c:if>
          </div>
        </div>
      </div>
      
		<script id="more-template" type="text/template">
		{@each list as v}
		<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg ls-list-item" data-id="&{v.id }">
	    	<div class="weui-media-box__hd">
	    		<img class="weui-media-box__thumb" src="&{v|initPic}" alt="">
	    	</div>
	    	<div class="weui-media-box__bd">
	    		<h4 class="weui-media-box__title">&{v.title }</h4>
	    		<p class="weui-media-box__desc">&{v.description }</p>
	     		<p class="weui-media-box__desc" style="margin-top:10px;"><span class="">&{v.createTime}</span><span><i class="iconfont icon-browse"></i><i>&{v.hits}</i></span><span><i class="iconfont icon-good"></i><i>&{v.voteUp}</i></span></p>
	    	</div>
	    	<div class="favorite-area">
	    		<i class="iconfont &{v|initFav}" data-id="&{v.id }"></i>
	    	</div>
	    </a>
		{@/each}
		</script>

      <script type="text/javascript">
        $(function(){
          initData();
          $("body").on("click",".drawer-menu-item.title",function(){
        	location.href="templates";  
          }).on("click",".ls-cat-list-item",function(){
            var _this = $(this);
            location.href="templates?catIds="+_this.data("catId");
          }).on("click",".ls-btn-myRs",function(){
        	location.href="sResources";  
          }).on("click",".ls-list-item",function(){
        	var _this = $(this);
        	location.href="template/"+_this.data("id");
          });
          $(".drawer--left").drawer({
            class: {
              nav: 'drawer-nav--left',
              toggle: 'drawer-toggle--left',
              overlay: 'drawer-overlay',
              open: 'drawer-open',
              close: 'drawer-close',
              dropdown: 'drawer-dropdown'
            }
          });
          $(".drawer--right").drawer({
            class: {
              nav: 'drawer-nav--right',
              toggle: 'drawer-toggle--right',
              overlay: 'drawer-overlay',
              open: 'drawer-open',
              close: 'drawer-close',
              dropdown: 'drawer-dropdown'
            }
          });
          $(".top-nav-filter").on("click",function(){
            drawerRight();
          });

          $("#datepicker").datepicker({
            format: "yyyy-mm-dd",
            clearBtn: true,
            language: "zh-CN"
          });
          $(":checkbox").labelauty({icon:false,class:'labelauty myLabel'});
          
          $("body").on("click",".show-all",function(){
        	  var _this = $(this);
        	  var more_info = $(".more-info");
        	  
        	  if(_this.is(".open")){
        		  more_info.hide();
        		  _this.removeClass("open");
        	  } else {
        		  more_info.show();
        		  _this.addClass("open");
        	  }
          });
          
          
          $(".template-list").on("click",".icon-favorite",function(event){
        	  event.stopPropagation();
        	  var _this = $(this);
        	  //_this.toggleClass("success");
        	  var url = "favorite";
        	  var data = "id="+_this.data("id");
        	  $.ajax({url:url,data:data,dataType:"json",success:function(result){
        		  if (result&&result.success) {
        			  _this.removeClass("icon-favorite").addClass("icon-favoritesfilling");
        			  alert(result.errmsg||"操作成功");
        		  } else if (result&&result.errcode=="1001") {
        			  _this.removeClass("icon-favorite").addClass("icon-favoritesfilling");
        			  alert(result.errmsg||"已经存在");  			  
        		  } else {
        			  alert(result.errmsg||"操作失败");
        		  }
        	  }});
          });
          
          $(".template-paginate").on("click",function(){
        	  console.log("加载更多...");
        	  
        	  var _this = $(this);
        	  var cur_page_number = (_this.data("pageNumber") || 1) + 1;
        	  
        	  var url = "templatesData";
        	  var data = $(form1).serialize()+"&pageNum="+cur_page_number;
        	  //templatesData
        	  
        	  //#pageNumber
        	  //template-list
        	  //template-paginate
        	  $.ajax({url:url,data:data,dateType:"json",success:function(result){
        		  console.log(result);
        		  //if success
        		  if(result&&result.list&&result.list.length){
	        		  _this.data("pageNumber",cur_page_number);
	        		  juicer.register('initFav',initFav);
	        		  juicer.register('initPic',initPic);
	        		  var html = juicer(document.getElementById('more-template').innerHTML, result);
	        		  $(".template-list").append(html);
        		  } else {
        			  alert("没有更多数据");
        			  _this.hide();
        		  }
        	  }});
        	  
          });
        });
        function initData(){
       		var catIds = "${catIds}";
        	var cIds = catIds.split(",");
        	cIds.forEach(function(x){
	        	if(x){
	        		$("[name=catIds]:field-value('"+x+"')").prop("checked",true);
	        	}
        	});
        }
        var initFav = function(data){
        	var favs = "${favs}";
        	if(!favs){
        		return 'icon-favorite';
        	} else {
        		var arr = favs.split(",");
        		if(arr.indexOf((data.id+""))>=0){
        			return 'icon-favoritesfilling';
        		} else {
        			return 'icon-favorite';
        		}
        	}
        }
        var initPic = function(data){
        	var mainPic = data.mainPic || "/static/images/ico_img.png";
        	return "${CONTEXT_PATH}"+mainPic;
        }
        function drawerRight(){
          $("#right").drawer("open");
        }
      </script> 
</body>
</html>
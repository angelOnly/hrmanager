<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HR管理系统</title>
<script src="js/jquery-1.10.2.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="logo.gif" />
<link rel="stylesheet" href="css/index.css" />
<script src="dist/js/bootstrap.min.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/prefixfree.min.js"></script>
<style>
	.u{
		cursor: pointer;
	}
</style>

<script>
	$(function() {
		$(".contactusdiyou").hover(function() {
			$(".hoverimg").attr("src", "images/hoverbtnbg1.gif");
			$('.diyoumask').fadeIn();
			$('.contactusdiyou').animate({
				right : '0'
			}, 300);
		}, function() {
			$(".hoverimg").attr("src", "images/hoverbtnbg.gif");
			$('.contactusdiyou').animate({
				right : '-230px'
			}, 300, function() {
			});
			$('.diyoumask').fadeOut();
		});
	});

	function openNewPage(labelName, url) {

		//获得所有“定义标题”
		var dts = document.getElementById("pageLabel").getElementsByTagName(
				"dt");
		//循环更改行高样式
		for (i = 0; i < dts.length; i++) {
			//获得当前“定义标题”
			var dt = dts[i];
			//修改行高样式
			dt.style.lineHeight = "28px";
		}
		//循环判断是否标题
		for (i = 0; i < dts.length; i++) {

			var localDt = dts[i];
			/*alert(labelName+"    "+localDt.getElementsByTagName("a")[0].innerText+"   "+
			    (labelName == localDt.getElementsByTagName("a")[0].innerText));*/
			//console.log(localDt.style.value);
			if (labelName == localDt.getElementsByTagName("a")[0].innerText) {
				togglePage(localDt, url);
				return;
			}
		}
		//创建“定义标题” 对象
		var dt = document.createElement("dt");
		var dts1 = document.getElementById("pageLabel").getElementsByTagName(
				"dt");
		for (i = 0; i < dts1.length; i++) {

			var localDt1 = dts1[i];

			localDt1.style.backgroundColor = "#B0EEE5";
		}
		dt.style.value = labelName;
		dt.style.backgroundColor = "white";

		//“定义标题中插入内容”
		dt.innerHTML = "<a href='javascript:void(0);' target='mainFrame' >"
				+ labelName + "</a><span >x</span>";
		//获得超链接对象
		var superLink = dt.getElementsByTagName("a")[0];
		//给超链接注册事件
		superLink.onclick = function() {
			togglePage(dt, url);
		}
		//获得<span>元素对象
		var span = dt.getElementsByTagName("span")[0];
		//注册事件
		span.onmouseover = function() {
			this.style.color = "red";
		}
		span.onmouseout = function() {
			this.style.color = "black";
		}
		span.onclick = function() {

			closePage(this);
		}
		//修改行高样式
		dt.style.lineHeight = "29px";
		//获得“定义列表”dl元素的对象
		var dl = document.getElementById("pageLabel");
		//“定义标题”添加给“定义列表”
		dl.appendChild(dt);
		//替换网页
		//获得内嵌框架对象
		var mainFrame = document.getElementById("mainFrame");
		//修改src属性
		mainFrame.src = url;

	}

	function togglePage(currentDt, url) {
		//console.log(dt.innerHTML+"   "+url);
		//获得所有“定义标题”
		var dts = document.getElementById("pageLabel").getElementsByTagName(
				"dt");
		//console.log(dts.length);
		//循环修改行高样式
		for (i = 0; i < dts.length; i++) {
			//获得当前定义标题
			var dt = dts[i];
			//console.log(dt);
			//修改行高样式
			dt.style.lineHeight = "28px";
			dt.style.backgroundColor = "#B0EEE5";
		}
		//修改当前的行高样式
		currentDt.style.lineHeight = "29px";
		currentDt.style.backgroundColor = "white";
		//替换网页
		//获得内嵌框架对象
		var mainFrame = document.getElementById("mainFrame");
		//修改src属性
		mainFrame.src = url;
	}

	function closePage(thisz) {
		//console.log(thisz.parentNode);
		//获得所有“定义标题”
		var dts = document.getElementById("pageLabel").getElementsByTagName(
				"dt");
		//获得关闭后，切换到网页的标题
		var targetPageLabel = dts[0];
		//循环比较查找当前的标题，获得切到网页的目标标题
		for (i = 0; i < dts.length; i++) {
			if (dts[i] == thisz.parentNode) {
				targetPageLabel = dts[i - 1];
				break;
			}
		}
		//console.log(targetPage);
		//删除当前的“定义标题”
		thisz.parentNode.parentNode.removeChild(thisz.parentNode);
		//单击目标标题中超链接
		targetPageLabel.getElementsByTagName("a")[0].click();
	}
</script>
</head>

<body>
	<header>
		<img src="img/timg (4).png" width="150px" height="120px" class="hr" />
	<h1  class="title">仙人掌责任有限公司</h1>
		<img src="img/exit副本.png" width="70" height="60" class="u"
			onclick="window.location.href='login?status=loginOut'"> <span
			class="login_message">${user}</span>
	</header>
	<aside>
		<ul class="drawer">
			<li><a href="#"> <i class="fa fa-info-circle"></i> <span>人员档案</span>
			</a>
				<ul>
					<li><a
						href="javascript:openNewPage('人员基本信息','staff?status=staffInformation')">
							<i class="fa fa-folder-open"></i> <span>人员基本信息</span>
					</a></li>
					<li><a
						href="javascript:openNewPage('员工档案',' record?status=staffRecord')">
							<i class="fa fa-question-circle"></i> <span>员工档案</span>
					</a></li>
					<li><a
						href="javascript:openNewPage('员工合同','contrctdownload?status=query')">
							<i class="fa fa-phone-square"></i> <span>员工合同</span>
					</a></li>
				</ul></li>
			<li><a href="#"> <i class="fa fa-folder"></i> <span>人事调配</span>
			</a>
				<ul>
					<li><a
						href="javascript:openNewPage('人事变动','chang?status=changView')">
							<i class="fa fa-flash"></i> <span>人事变动</span>
					</a></li>
				</ul></li>
			<li><a href="#"> <i class="fa fa-share-alt"></i> <span>系统管理</span>
			</a>
				<ul>
					<li><a
						href="javascript:openNewPage('机构信息维护','deptlist?status=deptlist')">
							<i class="fa fa-codepen"></i> <span>机构信息维护</span>
					</a></li>
				</ul></li>
			<li style="height: 123px;"></li>

			<li class="footer_li"><a href="#" target="_blank"> <i
					class="fa fa-codepen"></i> <span>关于我们</span>
			</a></li>
			<li><a href="#" target="_blank"> <i class="fa fa-twitter"></i>
					<span>问题反馈</span>
			</a></li>
		</ul>

	</aside>

	<article style="margin: 34px 0px; z-index: -199;">
		<section class="label">
			<dl id="pageLabel">
				<dt class="page_dt" style="background-color: white;">
					<a href="javascript:void(0);"
						onclick="togglePage(this.parentNode, 'welcome.jsp') ">首页</a>
				</dt>
			</dl>
		</section>
		<section class="pageBody">
			<!-- 内嵌框架 -->
			<iframe id="mainFrame" scrolling="yes" name="mainFrame" src="welcome.jsp"
				width="100%" height="470" frameborder="0" ></iframe>
		</section>
	</article>
</body>

</html>
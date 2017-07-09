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
<title></title>
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/welcome.css" />
<link rel="stylesheet" href="css/calendar.css" />
<link rel="stylesheet" href="css/artqus.css" />
</head>

<body>
	<div id="main">
		<h2>
			<img src="img/logo.png" style="height: 50px; height: 40;" /> Welcome
			to Cactus Technologies Limited
		</h2>

		<div id="content">
			<div id="newsdynamic">
				<h3>
					<li>最新动态</li>
					<li>常见问题</li>
					<li>人事变更</li>
				</h3>
			</div>
			<div class="menu">
				<ul>
					<li><span class="pan">恭喜娱乐部邓超获得感动公司年度大奖</span><span class="bg2">2017-11-03</span></li>
					<li><span class="pan">恭喜娱乐部邓超获得感动公司年度大奖</span><span class="bg2">2017-11-03</span></li>
					<li><span class="pan">恭喜娱乐部邓超获得感动公司年度大奖</span><span class="bg2">2017-11-03</span></li>
					<li><span class="pan">恭喜娱乐部邓超获得感动公司年度大奖</span><span class="bg2">2016-11-03</span></li>
					<li><span class="pan">增加字段参数设置、投稿限制和美化附件发布界面</span><span class="bg2">2016-11-03</span></li>
					<li><span class="pan">发布或调用处的类别选项更改为ajax方式</span><span class="bg2">2016-11-03</span></li>
					<li><span class="pan">增加了列表页和内容页的静态选项</span><span class="bg2">2016-11-03</span></li>
					<li><span class="pan">增加了置顶信息天数设置、会员页和搜索页模板自定义</span><span class="bg2">2016-11-03</span></li>
					<li><span class="pan">增加了工作流步骤的邮件通知功能</span><span class="bg2">2016-11-03</span></li>
				</ul>
			</div>
		</div>

		<div id="calendar" class="calendar"></div>
		<script type='text/javascript' src="js/calendar.js"></script>

		<div id="leftbar">
			<p>
				<strong>最新公告</strong>
			</p>
			<marquee behavior="scroll" direction="up" height="140px" width="200px"
				scrollamount="2">
				&nbsp;&nbsp;本公司将与2017年10月1日，即国庆节期间，在达斯达克上市！
				&nbsp;&nbsp;届时凡工龄不超过一周的员工都将获得一辆阿斯顿马汀所有权的公司福利！ 哈哈哈哈哈哈哈哈 <br />
				热线：1234567890 <br /> 传真：1234566666 <br /> QQ：666666
			</marquee>
		</div>
</body>

</html>
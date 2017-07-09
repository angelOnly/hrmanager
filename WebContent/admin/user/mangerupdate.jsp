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
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/edit.css" />
<script>
	function Return() {
		window.location.href = "usermanger.html";
	}
</script>
</head>
<body>
	<section class="content">
		<form id="form1" method="get" action="usermanger.html"
			class="form-group form-inline">
			<dl>
				<dt>用户信息管理</dt>
				<dd>
					<span>姓名</span><input type="text" name="userName"
						class="form-control input-size" required autofocus value="admin" />
				</dd>
				<dd>
					<span>密码</span><input type="password" name="password"
						class="form-control input-size" required />
				</dd>
				<dd>
					<span>状态</span> <input type="radio" name="sta" checked="checked"
						value="离职" />离职 <input type="radio" name="sta" value="在职"
						class="input-radio" />在职
				</dd>
				<dd class="btn-groups">
					<input type="submit" value="确 定" class="btn btn-primary" /> <input
						type="reset" value="重 置" class="btn btn-danger" />
				</dd>
			</dl>
		</form>
	</section>
</body>
</html>
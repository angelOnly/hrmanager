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
<link rel="shortcut icon" type="image/x-icon" href="logo.gif" />
<link rel="stylesheet" href="css/logo.css" />
<link rel="stylesheet" href="css/init.css" />
</head>

<body>
	<div id="back">
		<div id="loginer">
			<form method="post" action="login?status=login">
				<dl>
					<dt>用户帐号登录</dt>
					<dd>
						<input type="text" name="userName" class="input input-user"
							placeholder="帐号" autofocus required value="${param.userName}" />
					</dd>
					<dd>
						<input type="password" name="password" value="${param.password}"
							class="input input-password" placeholder="密码" required />
					</dd>
					<dd>
						<span style="color: red;">${error}</span>
					</dd>
					<dd>
						<input type="submit" value="登 录" class="btn" />
					</dd>
				</dl>
			</form>
		</div>
	</div>
</body>

</html>
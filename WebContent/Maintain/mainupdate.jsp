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
<script>
	function Return() {
		window.location.href = "mainupdate.jsp";
	}
</script>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改部门详细信息</title>
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/edit.css" />
</head>
<body>
	<section class="content">
		<form id="form1" method="post" action="deptlist?status=deptupdate"
			class="form-group form-inline">
			<dl>
				<dt>修改部门详细信息</dt>
				<dd>
					<span>部门ID</span><input type="text" name="dept_id"
						value="0${list.deptId}" class="form-control input-size" />
				</dd>
				<dd>
					<span>部门名称</span><input type="text" name="dept_name"
						class="form-control input-size" value="${list.deptName}" />
				</dd>
				<dd>
					<span>职位</span><input type="text" name="dept_memo"
						class="form-control input-size" value="${list.deptMemo}" />
				</dd>
				<dd class="btn-groups">
					<input type="submit" value="确 定" class="btn btn-primary" /> <input
						type="reset" value="重 置" class="btn btn-danger" /> <input
						type="button" value="返回" class="btn btn-success"
						onclick="Return()" />
				</dd>
			</dl>
		</form>
	</section>
</body>
</html>
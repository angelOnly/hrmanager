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
<title>添加部门详细信息</title>
<link rel="stylesheet" href="css/edit.css" />
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<script>
	function Return() {
		window.location.href = "Maintain/maintainMain.jsp";
	}
</script>
</head>
<body>
	<section class="content">
		<form id="form1" method="post" action="deptlist?status=addview"
			class="form-group form-inline">
			<dl>
				<dt>添加部门详细信息</dt>
				<dd>
					<span>部门ID</span><input type="text" name="dept_id"
						value="例如：1001001"
						onclick="if(value==defaultValue){value='';this.style.color='#000'}"
						onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
						style="color: #999" class="form-control input-size" required
						autofocus />
				</dd>
				<dd>
					<span>部门名称</span><input type="text" name="dept_name"
						class="form-control input-size" required autofocus />
				</dd>
				<dd>
					<span>职位</span><input type="text" name="dept_memo"
						class="form-control input-size" required autofocus />
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
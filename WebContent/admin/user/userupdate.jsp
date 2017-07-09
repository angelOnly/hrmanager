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
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/edit.css" />
</head>
<body>
	<section class="content">
	<form method="post" action="chang?status=userUpdate" class="form-group form-inline">
		<dl>
			<dt>用户变动信息</dt>
			<dd><input type = "hidden" name="userId"  value="${staffList.staffId }" />
				<span>姓名</span><input type="text" name="userName"
					class="form-control input-size" required autofocus value="${staffList.staffName }" />
			</dd>
			<dd>
				<span>密码</span><input type="password" name="password"
					class="form-control input-size" required value="${staffList.staffPassword }" />
			</dd>
			<dd>
				<span>性别</span> <input type="radio" name="gender" checked="checked"
					value="男" />男 <input type="radio" name="gender" value="女"
					class="input-radio" />女
			</dd>
			<dd>
				<span>所在部门</span><input type="text" name=""
					class="form-control input-size" required autofocus value="${staffList.TDeptInfo.deptName }" />
			</dd>
			<dd>
				<span>职位</span><input type="text" name=""
					class="form-control input-size" required autofocus value="${staffList.TDeptInfo.deptMemo }" />
			</dd>
			<dd>
				<span>调配部门</span><select name="dept2"
					class="form-control input-size">
					<!--下拉菜单-->
					<c:forEach var="dep" items="${deptList }">
							<option value="${dep.deptId}"  >${dep.deptName } ${dep.deptMemo }</option>
					</c:forEach>
				</select>
			</dd>
			<dd>
				<span>入职时间</span><input type="date" name="inTime"
					class="form-control input-size" required value="2016-10-28" />
			</dd>

			<dd class="btn-groups">
				<input type="submit" value="确 定" class="btn btn-primary" />
				<input type="reset" value="重 置" class="btn btn-danger" />
			</dd>
		</dl>
	</form>
	</section>
</body>
</html>
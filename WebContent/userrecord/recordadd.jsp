<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<!-- 设置基准路径       解决相对路径或者404这类路径问题 -->
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="dist/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="css/add.css" />
		<link rel="stylesheet" href="css/init.css" />
	</head>
	<body>
		<section>
			<form method="post" action="record?status=recordAdd" class="form-group form-inline">
				<dl>
					<dt>添加档案信息</dt>
					<dd><span>档案名称：</span><!--档案名称即员工名称-->
						<input type="text" name="recordName" 
						class="form-control input-size" required autofocus />
					</dd>
					<dd><span>员工姓名：</span>
						<select name="staffId" class="form-control input-size">
							<c:forEach var="lis" items="${list }">
							<option value="${lis.staffId }" >${lis.staffName }</option>
							</c:forEach>
					    </select>
					</dd>
					<dd><span>原职位：</span>
						<select name="oldMemo" class="form-control input-size">
							<c:forEach var="dep" items="${dept }">
							<option value="${dep.deptMemo }" ${dep.deptMemo == oldmemo?"selected":"" } >${dep.deptMemo }</option>
						</c:forEach>
					    </select>
					</dd>
					<dd><span>现职位：</span>
						<select name="newMemo" class="form-control input-size">
							<c:forEach var="dep" items="${dept }">
							<option value="${dep.deptMemo }" ${dep.deptMemo == oldmemo?"selected":"" } >${dep.deptMemo }</option>
						</c:forEach>
					    </select>
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
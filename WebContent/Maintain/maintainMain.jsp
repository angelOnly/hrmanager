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
<title>部门信息表</title>
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/list.css" />
<script src="js/jquery-1.8.3.min.js"></script>
<script>
	/**
	 * 全选或反选
	 * @param {Object} thisz
	 */
	function checkAll(thisz) {
		//获得name="id"所有复选框
		var checkboxs = $("input[name=id]");
		//console.log(checkboxs.length);
		//修改复选框的选择状态
		checkboxs.prop("checked", thisz.checked);
	}



	/**
	 * 添加
	 */

	function add() {
		window.location.href = "deptlist?status=add";
	}

	/**
	 * 修改
	 */
	function update() {
		//后期修改
		var checkboxs = $("input[name=id]:checked");
		if (checkboxs.length == 1) {
			var form = $("#form2").get(0);
			console.log(form);
			form.action = "deptlist?status=deptupdateview";
			form.submit();
		} else {
			alert("请选择要修改的信息！");
		}
		
	}
</script>
</head>

<body>
	<section class="content">
		<section>
			<form id="form1" method="post" action="deptlist?status=query"
				class="form-group form-inline">
				<input type="text" name="deptName" placeholder="${param.deptName}"
					class="form-control" /> <input type="submit" value="搜  索"
					class="btn btn-warning" />
			</form>
		</section>
		<form id="form2" method="post" action="">
			<table class="table table-hover table-bordered">
				<tr>
					<th><input type="checkbox" id="idAll" onclick="checkAll(this)" /></th>
					<th>部门ID</th>
					<th>部门名称</th>
					<th>职位</th>
				</tr>

				<c:forEach var="dept" items="${list}" varStatus="st">
					<tr>
						<td><input type="checkbox" name="id" value="${dept.deptId}" /></td>
						<th>${dept.deptId}</th>
						<th>${dept.deptName}</th>
						<th>${dept.deptMemo}</th>
					</tr>
				</c:forEach>

			</table>
			<p class="btn-groups">
				<input type="button" value="添 加" class="btn btn-primary"
					onclick="add()" />
				<!--btn-sm-->
				<input type="button" value="修 改" class="btn btn-success"
					onclick="update()" />
				<!--btn-lg-->
			</p>
		</form>
	</section>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link rel="stylesheet" href="css/staffInformation.css" />
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />

<script src="js/jquery-1.9.1.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<title></title>
<script>
	/*$(function() {
		$("#myAlert").css("margin-top", "200px");
		$("#myConfirm").css("margin-top", "200px");
		//$("#myConfirm").modal("show");
	});*/

	function checking(thisz) {
		var checkboxs = $("input[name=id]");
		checkboxs.prop("checked", thisz.checked);

	}

	function add() {
		window.location.href = "staff?status=addView";
	}

	function update() {
		var checkboxs = $("input[name=id]:checked");
		if (checkboxs.length == 1) {
			var form = $("#form2").get(0);
			form.action = "staff?status=userchangeView";
			//提交表单
			form.submit();
		} else {
			alert("请选择要修改的信息！");
			return;
		}

	}

	function Delete() {
		//获取选中记录的数量
		var count = $("input[name=id]:checked").size();
		//判断是否选中要删除的记录
		if (count == 0) { //未选中
			alert("请选择需要删除的信息！");
			return; //阻断方法执行
		}
		var form = $("#form2").get(0);
		form.action = "staff?status=delete";
		//提交表单
		form.submit();
	}
</script>
</head>

<body>
	<section class="content">
		<section>

		</section>
		<form id="form2" method="post" action="">
			<table class="table table-hover table-bordered">
				<tr class="tr-header">
					<td><input type="checkbox" id="idAll" onclick="checking(this)" /></td>
					<td>员工ID</td>
					<td>姓名</td>
					<td>性别</td>
					<td>学历</td>
					<td>入职时间</td>
				</tr>
				<c:forEach var="staff" items="${list }" varStatus="st">
				<input type = "hidden" name="deptId" value="${staff.TDeptInfo.deptId }" />
					<tr>
						<td><input type="checkbox" name="id"
							value="${staff.staffId }" /></td>
						<td>${staff.staffId }</td>
						<td>${staff.staffName }</td>
						<td>${staff.staffGender }</td>
						<td>${staff.staffEdu }</td>
						<td><fmt:formatDate value="${staff.inTime }"
								pattern="yyyy年MM月dd日" /></td>
					</tr>
				</c:forEach>
			</table>
			<p class="btn-groups">
				<input type="button" value="添 加" class="btn-primary" onclick="add()" />
				<!--btn-sm-->
				<input type="button" value="修 改" class="btn-success"
					onclick="update()" />
				<!--btn-lg-->
				<input type="button" value="删 除" class="btn-danger"
					onclick="Delete()" />
			</p>
		</form>
	</section>

</body>

</html>
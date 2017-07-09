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

	function update() {
		//后期修改

		var checkboxs = $("input[name=id]:checked");
		if (checkboxs.length == 1) {
			window.location.href = "mangerupdate.html";
		} else {
			alert("请选择要修改的信息！");
		}

	}
</script>
</head>
<body>
	<section class="content">
		<section>
			<form id="form1" method="get" action=""
				class="form-group form-inline">
				<input type="text" name="suserName" placeholder="按用户名搜索!"
					class="form-control" /> <input type="submit" value="搜 索"
					class="btn btn-warning" />
			</form>
		</section>
		<form id="form2" method="get" action="">
			<table class="table table-hover table-bordered">
				<tr class="tr-header">
					<th><input type="checkbox" id="idAll" onclick="checkAll(this)" /></th>

					<th>姓名</th>
					<th>性别</th>
					<th>状态</th>
				</tr>
				
			</table>
			<p class="btn-groups">

				<input type="button" value="修 改" class="btn btn-success"
					onclick="update()" />
				<!--btn-lg-->

			</p>
		</form>
	</section>
</body>
</html>
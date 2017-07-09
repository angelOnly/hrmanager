<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<!--设置基准路径 -->
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>合同信息表</title>
<link rel="stylesheet" href="css/list.css" />
<link rel="stylesheet" href="dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/init.css" />
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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
		var form = $("#form2").get(0);
		form.action = "contrctdownload?status=isdownload";
		form.submit();
		//console.log(form);
	}
	/**
	 * 删除
	 */
	function upload() {

	}
</script>
<style type="text/css">
.content {
	position: relative;
	margin: 50px auto 0px;
	width: 90%;
	min-width: 800px;
	/* 最小宽度 */
}

.content form table tr th, td {
	text-align: center;
}

.content section {
	position: relative;
	width: 100%;
}

.content section form {
	text-align: right;
}

.tr-header {
	background-color: #E8E8E8 !important;
}

.btn-groups {
	text-align: center;
}

.btn-groups input {
	margin: 10px 20px 0px;
}
</style>
</head>

<body>
	<section class="content">
		<section>
			<form id="form1" method="post" action="contrctdownload?status=query"
				class="form-group form-inline">
				<input type="text" name="contratType" placeholder="按用户名搜索!"
					class="form-control" value="${param.contratType }" /> <input
					type="submit" value="搜 索" class="btn btn-warning" />
			</form>
		</section>
		<form id="form2" method="post" action="">
			<table class="table table-hover table-bordered">
				<tr class="tr-header">
					<th><input type="checkbox" id="idAll" onclick="checkAll(this)" /></th>
					<th>合同ID</th>
					<th>员工ID</th>
					<th>开始日期</th>
					<th>合同时间</th>
					<th>结束时间</th>
					<th>合同类型</th>
				</tr>
				<c:forEach var="contractInfo" items="${list }" varStatus="st">
					<tr>
						<td><input type="checkbox" name="id"
							value="${contractInfo.contractId }" /></td>
						<td>${contractInfo.contractId }</td>
						<td>${contractInfo.TStsffInfo.staffId }</td>
						<td><fmt:formatDate
								value="${contractInfo.contractTimeStart }" pattern="yyyy年MM月dd日" /></td>
						<td>${contractInfo.contractTime }</td>
						<td><fmt:formatDate value="${contractInfo.contractTimeEnd }"
								pattern="yyyy年MM月dd日" /></td>
						<td>${contractInfo.contratType }</td>
					</tr>
				</c:forEach>

			</table>
		</form>
		<p class="btn-groups">
			<input type="button" value="下 载" class="btn btn-primary"
				onclick="add()" />
		</p>
		<form method="post" enctype="multipart/form-data" action="contrctdownload?status=upload">
			<input type="file" id="file" name="file"/><br /> 
			<input type="submit" value="上传" class="btn btn-warning"/> ${result }
		</form>
	</section>
</body>

</html>
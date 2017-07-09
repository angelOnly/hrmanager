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
<link rel="stylesheet" href="css/add.css" />
</head>
<script>
	function Return() {
		window.location.href = "staffInformation.jsp";
	}
</script>

<body>
	<section class="content">
			<form method="post" action="staff?status=userchange" class="form-group form-inline">
				<dl>
					<dt>修改员工信息</dt>
					<dd><span>姓       名：</span>
						<input type="text" name="staffName"
						class="form-control input-size" required autofocus value="${staff.staffName }" />
						<input type = "hidden" name="staffId" value="${staff.staffId }" />
						<input type = "hidden" name="deptId" value="${staff.TDeptInfo.deptId }" />
					</dd>
					<dd><span>性       别：</span>
						<input type="radio" name="gender" checked="checked" value="男" ${staff.staffGender =="男"?"checked":"" } />男
						<input type="radio" name="gender" value="女" class="input-radio" ${staff.staffGender =="女"?"checked":"" }/>女
					</dd>
					<dd><span>学       历：</span>
						<select name="staffEdu" class="form-control input-size" >
						<option value="博士生" ${staff.staffEdu == "博士生"?"selected":"" } >博士生</option>
						<option value="硕士生" ${staff.staffEdu == "硕士生"?"selected":"" } >硕士生</option>
						<option value="研究生" ${staff.staffEdu == "研究生"?"selected":"" } >研究生</option>
						<option value="本科生" ${staff.staffEdu == "本科生"?"selected":"" } >本科生</option>
					    </select>
					</dd>
					<dd><span>入职时间:</span>
						<input type="date" name="inTime" class="form-control input-size" required value="${staff.inTime }" />
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
</body>

</html>
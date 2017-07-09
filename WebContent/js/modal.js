/**
 * 打开提示框
 */
function openAlert(message) {
	$("#alertMsg", parent.document).text(message);
	$("#myAlert", parent.document).modal("show");
}

/**
 * 打开选择对话框
 */
function openConfirm(message, formId, url) {
	// 设置显示提示信息
	$("#confirmMsg", parent.document).text(message);
	// 确定按钮注册单击事件
	$("#confirmSubmit", parent.document).get(0).onclick = function(event) {
		// alert(1);
		// 获得表单对象
		var form = $("#" + formId).get(0);
		// 设置提交路径
		form.action = url;
		// 提交表单
		form.submit();
		// 关闭（隐藏）选择对话框
		$("#myConfirm", parent.document).modal("hide");
	};
	// 显示选择对话框
	$("#myConfirm", parent.document).modal("show");
}
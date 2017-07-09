package com.hr.data;

import java.io.Serializable;

/**
 * 部门表持久化类
 * 
 * @author YYH
 * @createTime 2017年6月25日 下午7:38:53
 * @version 1.0.0
 */
public class TDeptInfo implements Serializable {

	// 部门表属性
	private Integer deptId; // 部门ID
	private String deptName; // 部门名称
	private String deptMemo; // 职位（市场经理/市场专员）
	private String beiYong31; // 备用1
	private String beiYong32; // 备用2

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptMemo() {
		return deptMemo;
	}

	public void setDeptMemo(String deptMemo) {
		this.deptMemo = deptMemo;
	}

	public String getBeiYong31() {
		return beiYong31;
	}

	public void setBeiYong31(String beiYong31) {
		this.beiYong31 = beiYong31;
	}

	public String getBeiYong32() {
		return beiYong32;
	}

	public void setBeiYong32(String beiYong32) {
		this.beiYong32 = beiYong32;
	}

}

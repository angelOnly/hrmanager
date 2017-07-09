package com.hr.data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 员工表持久化类
 * 
 * @author YYH
 * @createTime 2017年6月25日 下午7:40:29
 * @version 1.0.0
 */
public class TStaffInfo implements Serializable {

	// 员工表属性
	private Integer staffId; // 员工ID
	private TDeptInfo TDeptInfo;// 部门对象
	private String staffName; // 员工姓名
	private String staffPassword; // 员工密码
	private String staffGender; // 员工性别
	private String staffEdu;// 员工学历
	private Date inTime;// 员工入职时间
	private String isdel;// 逻辑删除，1为删除，0为未删除
	private String beiYong11;// 备用
	private String beiYong12;// 备用

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public TDeptInfo getTDeptInfo() {
		return TDeptInfo;
	}

	public void setTDeptInfo(TDeptInfo tDeptInfo) {
		TDeptInfo = tDeptInfo;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	public String getStaffGender() {
		return staffGender;
	}

	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}

	public String getStaffEdu() {
		return staffEdu;
	}

	public void setStaffEdu(String staffEdu) {
		this.staffEdu = staffEdu;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getBeiYong11() {
		return beiYong11;
	}

	public void setBeiYong11(String beiYong11) {
		this.beiYong11 = beiYong11;
	}

	public String getBeiYong12() {
		return beiYong12;
	}

	public void setBeiYong12(String beiYong12) {
		this.beiYong12 = beiYong12;
	}

}

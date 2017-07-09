package com.hr.data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 合同表持久化类
 * @author YYH
 * @createTime 2017年6月25日 下午7:38:32
 * @version 1.0.0
 */
public class TContractInfo implements Serializable {

	// 合同表属性
	private Integer contractId; // 合同ID
	private TStaffInfo TStsffInfo; // 员工对象
	private Date contractTimeStart; // 开始时间
	private String contractTime;// 合同时间
	private Date contractTimeEnd;// 结束时间
	private String contratType;// 合同类型
	private String bieYoung51;// 备用
	private String beiYoung52;//备用

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public TStaffInfo getTStsffInfo() {
		return TStsffInfo;
	}

	public void setTStsffInfo(TStaffInfo tStsffInfo) {
		TStsffInfo = tStsffInfo;
	}

	public Date getContractTimeStart() {
		return contractTimeStart;
	}

	public void setContractTimeStart(Date contractTimeStart) {
		this.contractTimeStart = contractTimeStart;
	}

	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}

	public Date getContractTimeEnd() {
		return contractTimeEnd;
	}

	public void setContractTimeEnd(Date contractTimeEnd) {
		this.contractTimeEnd = contractTimeEnd;
	}

	public String getContratType() {
		return contratType;
	}

	public void setContratType(String contratType) {
		this.contratType = contratType;
	}

	public String getBieYoung51() {
		return bieYoung51;
	}

	public void setBieYoung51(String bieYoung51) {
		this.bieYoung51 = bieYoung51;
	}

	public String getBeiYoung52() {
		return beiYoung52;
	}

	public void setBeiYoung52(String beiYoung52) {
		this.beiYoung52 = beiYoung52;
	}

}

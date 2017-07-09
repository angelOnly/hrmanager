package com.hr.data;

import java.io.Serializable;

/**
 * 档案信息持久类
 * 
 * @author YYH
 * @createTime 2017年6月25日 下午7:40:06
 * @version 1.0.0
 */
public class TRecordInfo implements Serializable {
	private Integer recordId;// 档案ID
	private TStaffInfo TStsffInfo;// 员工对象
	private String recordName;// 档案名称
	private String recordPaper;// 内容摘要
	private String beiyong41;// 备用41
	private String beiyong42;// 备用42
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public TStaffInfo getTStsffInfo() {
		return TStsffInfo;
	}
	public void setTStsffInfo(TStaffInfo tStsffInfo) {
		TStsffInfo = tStsffInfo;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getRecordPaper() {
		return recordPaper;
	}
	public void setRecordPaper(String recordPaper) {
		this.recordPaper = recordPaper;
	}
	public String getBeiyong41() {
		return beiyong41;
	}
	public void setBeiyong41(String beiyong41) {
		this.beiyong41 = beiyong41;
	}
	public String getBeiyong42() {
		return beiyong42;
	}
	public void setBeiyong42(String beiyong42) {
		this.beiyong42 = beiyong42;
	}

}

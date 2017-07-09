package com.hr.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hr.data.TRecordInfo;
import com.hr.data.TStaffInfo;
import com.hr.database.DataSource;
import com.hr.database.IDaoService;

public class RecordInfoDao implements IDaoService<TRecordInfo> {
	/**
	 * 查询所有
	 */
	@Override
	public List<TRecordInfo> query() throws Exception {
		String sql = "select * from t_record_info r,t_staff_info s where  r.staff_id=s.staff_id and s.isdel =0 order by record_id desc ";
		return query(sql);
	}

	
	/**
	 * 条件查询
	 */
	@Override
	public List<TRecordInfo> query(TRecordInfo sre) throws Exception {
		// 定义条件查询的SQL
		String sql = "select * from t_record_info where ";
		// 追加查询条件
		if (sre.getRecordId() != null) {
			sql += " record_id=" + sre.getRecordId() + " ";
		}
		if (sre.getRecordName() != null && !"".equals(sre.getRecordName())) {
			if (sre.getRecordName().startsWith(" like '%")) {
				sql += " record_name " + sre.getRecordName() + " ";
			} else {
				sql += " and record_name='" + sre.getRecordName() + "' ";
			}
		}

		sql += " order by record_id desc";
		System.out.println(sql);
		// 返回封装用户数据的集合
		return query(sql);
	}

	public List<TRecordInfo> query(String sql) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 查询用户数据，返回结果集
		ResultSet rs = stm.executeQuery(sql);
		// 定义封装用户数据的集合
		List<TRecordInfo> list = new ArrayList<>();
		// 遍历结果集
		while (rs.next()) {
			// 新建员工对象
			TRecordInfo re = new TRecordInfo();
			// 提取数据行中每列数据设置给用户对象的相应的属性
			re.setRecordId(rs.getInt("record_id"));
			// 创建部门对象
			TStaffInfo staff = new TStaffInfo();
			// 封装员工的属性
			staff.setStaffId(rs.getInt("staff_id"));
			//staff.setStaffPassword(rs.getString("staff_password"));
			//staff.setStaffName(rs.getString("staff_name"));
			//staff.setStaffGender(rs.getString("staff_gender"));
			//staff.setStaffEdu(rs.getString("staff_edu"));
			//staff.setInTime(rs.getDate("in_time"));
			//staff.setIsdel(rs.getString("isdel"));
			// 设置关联
			re.setTStsffInfo(staff);
			re.setRecordName(rs.getString("record_name"));
			re.setRecordPaper(rs.getString("record_paper"));
			//re.setRecordPaper(rs.getString("record_paper"));
			//System.out.println(re.getRecordId() + "\t" + staff.getStaffId() + "\t" + staff.getInTime()  + "\t" + staff.getIsdel() + "\t" + re.getRecordName());
			// 添加
			list.add(re);

		}
		conn.close();
		return list;
	}

	/**
	 * 添加数据
	 */
	@Override
	public void add(TRecordInfo re) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建预编译的语句对象
		PreparedStatement stm = conn
				.prepareStatement("insert into t_record_info(staff_id,record_name,record_paper) values(?,?,?)");
		// 设置语句的参数
		stm.setInt(1, re.getTStsffInfo().getStaffId());
		stm.setString(2, re.getRecordName());
		stm.setString(3, re.getRecordPaper());
		// 执行语句
		stm.execute();
		// 关闭连接
		conn.close();

	}

	/**
	 * 修改数据
	 */
	@Override
	public void update(TRecordInfo re) throws Exception {

		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建预编译的语句对象
		PreparedStatement stm = conn
				.prepareStatement("update t_record_info set staff_id=?,record_name=?,record_paper=? where record_id=?");
		// 设置语句的参数
		stm.setInt(1, re.getTStsffInfo().getStaffId());
		stm.setString(2, re.getRecordName());
		stm.setString(3, re.getRecordPaper());
		stm.setInt(4, re.getRecordId());
		// 执行语句
		stm.execute();
		// 关闭连接
		conn.close();
	}

	/**
	 * 删除数据
	 */
	@Override
	public void delete(String... ids) throws Exception {
		System.out.println("------------1");
		// 定义SQL的in语句的变量
		String inSql = "";
		// 遍历ids数组，拼接SQL子句
		for (int i = 0; i < ids.length; i++) {
			if (i == ids.length - 1) {
				inSql += ids[i];
			} else {
				inSql += ids[i] + ",";
			}
		}
		// 定义SQL语句
		String sql = "delete from t_record_info where record_id in (" + inSql + ")";
		System.out.println(sql);
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 执行SQL语句，逻辑删除部门数据
		stm.execute(sql);
		// 关闭连接
		conn.close();
	}


	@Override
	public List<TRecordInfo> queryadd(TRecordInfo Object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int querys(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}

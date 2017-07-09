package com.hr.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hr.data.TContractInfo;
import com.hr.data.TStaffInfo;
import com.hr.database.DataSource;
import com.hr.database.IDaoService;


public class ContractInfoDao implements IDaoService<TContractInfo> {
	/**
	 * 查询所有合同数据
	 */
	@Override
	public List<TContractInfo> query() throws Exception {
		// 定义条件查询的SQL
		String sql = "select * from t_contract_info c,t_staff_info s where c.staff_id=s.staff_id order by c.contract_id desc ";
		// 返回封装
		return query(sql);
	}

	@Override
	public List<TContractInfo> query(TContractInfo scont) throws Exception {
		// 定义条件查询的SQL
		String sql = "select * from t_contract_info c,t_staff_info s where c.staff_id=s.staff_id";
		// 追加查询条件
		if (scont.getContractId() != null) {
			sql += " and c.contract_type=" + scont.getContractId() + " ";
		}
		// 追加查询条件
		if (scont.getContratType() != null && !"".equals(scont.getContratType())) {
			if (scont.getContratType().startsWith("like '%")) {
				sql += " and c.contract_type " + scont.getContratType() + " ";
			} else {
				sql += " and c.contract_type='" + scont.getContratType() + "' ";
			}
		}
		
		sql += " order by c.contract_time_end desc";
		 System.out.println(sql);
		// 返回封装用户数据的集合
		return query(sql);
	}

	/**
	 * 查询数据库的数据
	 */
	public List<TContractInfo> query(String sql) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 查询用户数据，返回结果集
		ResultSet rs = stm.executeQuery(sql);
		// 定义封装用户数据的集合
		List<TContractInfo> list = new ArrayList<>();
		// 遍历结果集
		while (rs.next()) {
			TContractInfo cont = new TContractInfo();
			cont.setContractId(rs.getInt("contract_id"));
			TStaffInfo staff = new TStaffInfo();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffPassword(rs.getString("staff_password"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffGender(rs.getString("staff_gender"));
			staff.setStaffEdu(rs.getString("staff_edu"));
			staff.setInTime(rs.getDate("in_time"));
			staff.setIsdel(rs.getString("s.isdel"));
			// 设置关联
			cont.setTStsffInfo(staff);
			cont.setContractId(rs.getInt("contract_id"));
			cont.setContractTimeStart(rs.getDate("contract_time_start"));
			cont.setContractTime(rs.getString("contract_time"));
			cont.setContractTimeEnd(rs.getDate("contract_time_end"));
			cont.setContratType(rs.getString("contract_type"));
			// 添加对象
			list.add(cont);

		}
		conn.close();
		return list;
	}

	/**
	 * 添加数据
	 */
	@Override
	public void add(TContractInfo cont) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建预编译的语句对象
		PreparedStatement stm = conn.prepareStatement(
				"insert into t_contract_info(contract_id,staff_id,contract_time_start,contract_time,contract_time_end,contract_type) values(?,?,?,?,?,?)");
		// 设置语句的参数
		stm.setInt(1, cont.getContractId());
		stm.setInt(2, cont.getTStsffInfo().getStaffId());
		stm.setDate(3, cont.getContractTimeStart());
		stm.setString(4, cont.getContractTime());
		stm.setDate(5, cont.getContractTimeEnd());
		stm.setString(6, cont.getContratType());
		// 执行语句
		stm.execute();
		// 关闭连接
		conn.close();

	}

	/**
	 * 修改数据
	 */
	@Override
	public void update(TContractInfo cont) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建预编译的语句对象
		PreparedStatement stm = conn.prepareStatement(
				"update t_contract_info set staff_id=?,contract_time_start=?,contract_time=?,contract_time_end=?,contract_type=? where contract_id=?");
		// 设置语句的参数
		stm.setInt(1, cont.getTStsffInfo().getStaffId());
		stm.setDate(2, cont.getContractTimeStart());
		stm.setString(3, cont.getContractTime());
		stm.setDate(4, cont.getContractTimeEnd());
		stm.setString(5, cont.getContratType());
		stm.setInt(6, cont.getContractId());
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
		String sql = "update t_contract_info set isdel=1 where contract_id in(" + inSql + ")";
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
	public List<TContractInfo> queryadd(TContractInfo Object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int querys(int id) throws Exception {
		Connection conn = DataSource.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rset = statement.executeQuery("select count(*) contract_type from t_contract_info where contract_id = "+id); 
		int rowCount = 0; 
		if(rset.next()) { 
		   rowCount=rset.getInt("contract_type"); 
		}
		System.out.println("合同影响行数："+rowCount);
		return rowCount;
	}

}

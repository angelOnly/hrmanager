package com.hr.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hr.data.TDeptInfo;
import com.hr.data.TStaffInfo;
import com.hr.database.DataSource;
import com.hr.database.IDaoService;

public class StaffInfoDao implements IDaoService<TStaffInfo> {

	/**
	 * 查询所有的员工数据
	 */
	@Override
	public List<TStaffInfo> query() throws Exception {
		String sql = "select * from t_staff_info s,t_dept_info d where s.isdel=0 and s.dept_id=d.dept_id";
		return query(sql);
	}

	/**
	 * 条件查询员工数据
	 */
	@Override
	public List<TStaffInfo> query(TStaffInfo sstaff) throws Exception {
		// 定义条件查询的SQL
		String sql = "select * from t_staff_info s,t_dept_info d where s.isdel=0" + " and s.dept_id=d.dept_id ";
		// 追加查询条件
		if (sstaff.getStaffId() != null) {
			sql += " and s.staff_id=" + sstaff.getStaffId() + " ";
		}
		if (sstaff.getStaffName() != null && !"".equals(sstaff.getStaffName())) {
			System.out.println("错误");
			if (sstaff.getStaffName().startsWith(" like '%")) {
				sql += " and s.staff_name " + sstaff.getStaffName() + " ";
			} else {
				sql += " and s.staff_name='" + sstaff.getStaffName() + "' ";
			}
		}
		if (sstaff.getStaffPassword() != null && !"".equals(sstaff.getStaffPassword())) {
			sql += " and s.staff_password='" + sstaff.getStaffPassword() + "' ";
		}
		sql += " order by s.in_time desc";
		
		System.out.println(sql);
		 System.out.println(sql + "正确");
		// 返回封装用户数据的集合
		return query(sql);
	}

	public List<TStaffInfo> query(String sql) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 查询用户数据，返回结果集
		System.out.println(sql);
		ResultSet rs = stm.executeQuery(sql);
		// 定义封装用户数据的集合
		List<TStaffInfo> list = new ArrayList<>();
		// 遍历结果集
		while (rs.next()) {
			// 新建员工对象
			TStaffInfo staff = new TStaffInfo();
			// 提取数据行中每列数据设置给用户对象的相应的属性
			staff.setStaffId(rs.getInt("staff_id"));
			// 创建部门对象
			TDeptInfo dept = new TDeptInfo();
			// 封装部门的属性
			dept.setDeptId(rs.getInt("dept_id"));
			dept.setDeptName(rs.getString("dept_name"));
			dept.setDeptMemo(rs.getString("dept_memo"));
			// 设置关联
			staff.setTDeptInfo(dept);
			staff.setStaffPassword(rs.getString("staff_password"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffGender(rs.getString("staff_gender"));
			staff.setStaffEdu(rs.getString("staff_edu"));
			staff.setInTime(rs.getDate("s.in_time"));
			staff.setIsdel(rs.getString("s.isdel"));
			// 添加
			System.out.println(staff.getStaffId() + staff.getStaffName());
			list.add(staff);

		}
		System.out.println("StaffInfoDao.query()");
		conn.close();
		return list;
	}

	/**
	 * 添加员工数据
	 */
	@Override
	public void add(TStaffInfo staff) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建预编译的语句对象
		/*PreparedStatement stm = conn.prepareStatement(
				" insert into t_staff_info(dept_id,staff_password,staff_name,staff_gender,staff_edu,in_time,isdel) value(?,?,?,?,?,?,?)");*/
		PreparedStatement stm = conn.prepareStatement(
				" insert into t_staff_info(dept_id,staff_password,staff_name,staff_gender,staff_edu,in_time,isdel) value(?,?,?,?,?,?,?)");
		// 设置语句参数
		stm.setString(1, "0"+staff.getTDeptInfo().getDeptId());
		stm.setString(2, staff.getStaffPassword());
		stm.setString(3, staff.getStaffName());
		stm.setString(4, staff.getStaffGender());
		stm.setString(5, staff.getStaffEdu());
		stm.setDate(6, staff.getInTime());
		stm.setString(7, "0");
		// 执行语句
		stm.execute();
		// 关闭数据库
		conn.close();
	}

	/**
	 * 修改数据
	 */
	@Override
	public void update(TStaffInfo staff) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		PreparedStatement stm = conn.prepareStatement(
				" update t_staff_info set staff_name=?,"
				+ "staff_gender=?,staff_edu=?,in_time=?,dept_id=? where staff_id=?");

		stm.setString(1, staff.getStaffName());
		stm.setString(2, staff.getStaffGender());
		stm.setString(3, staff.getStaffEdu());
		stm.setString(5, "0"+staff.getTDeptInfo().getDeptId());
		stm.setDate(4, staff.getInTime());
		stm.setInt(6, staff.getStaffId());
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
		String sql = "update t_staff_info set isdel=1 where staff_id in(" + inSql + ")";
		System.out.println(sql);
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 执行SQL语句
		//stm.executeQuery(sql);
		stm.execute(sql);
		// 关闭连接
		conn.close();
	}

	@Override
	public List<TStaffInfo> queryadd(TStaffInfo obeject) throws Exception {
		String sql = "select t_staff_info.staff_id,"
				+ " t_staff_info.staff_name from("
				+ " select t_staff_info.*, t_record_info.record_id as tempcolum"
				+ " from t_staff_info left join t_record_info on t_staff_info.staff_id=t_record_info.record_id)"
				+ "as t_staff_info where t_staff_info.tempcolum is null";
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 查询用户数据，返回结果集
		System.out.println(sql);
		ResultSet rs = stm.executeQuery(sql);
		// 定义封装用户数据的集合
		List<TStaffInfo> list = new ArrayList<>();
		// 遍历结果集
		while (rs.next()) {
			TStaffInfo staff = new TStaffInfo();
			// 提取数据行中每列数据设置给用户对象的相应的属性
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			list.add(staff);
		}
		conn.close();
		return list;
	}

	@Override
	public int querys(int id) throws Exception {
		Connection conn = DataSource.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rset = statement.executeQuery("select count(*) staff_name from t_staff_info where staff_id = "+id); 
		int rowCount = 0; 
		if(rset.next()) { 
		   rowCount=rset.getInt("staff_name"); 
		}
		System.out.println("员工影响行数："+rowCount);
		return rowCount;
	}

}

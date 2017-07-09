package com.hr.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hr.data.TDeptInfo;
import com.hr.database.DataSource;
import com.hr.database.IDaoService;

public class DeptInfoDao implements IDaoService<TDeptInfo> {
	/**
	 * 查询所有部门数据
	 */
	@Override
	public List<TDeptInfo> query() throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 查询部门数据，返回结果集
		ResultSet rs = stm.executeQuery("select * from t_dept_info order by dept_id desc ");
		// 定义封装部门数据集合
		List<TDeptInfo> list = new ArrayList<>();
		// 遍历结果集
		while (rs.next()) {
			// 新建员工对象
			TDeptInfo dept = new TDeptInfo();
			// 提交数据行中每列数据设置给部门对象的相应的属性
			dept.setDeptId(rs.getInt("dept_id"));
			dept.setDeptName(rs.getString("dept_name"));
			dept.setDeptMemo(rs.getString("dept_memo"));

			// 添加部门对象集合
			list.add(dept);
		}
		// 关闭数据库连接
		conn.close();
		// 返回封装员工
		return list;
	}

	/**
	 * 条件查询数据
	 */
	@Override
	public List<TDeptInfo> query(TDeptInfo sdept) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 定义查询的SQL
		String sql = "select * from t_dept_info where  ";
		// 追加查询条件
		if (sdept.getDeptId() != null && sdept.getDeptId() != 0 ) {
			sql += "  dept_id='0" + sdept.getDeptId() + "' ";
		}
		if(sdept.getDeptName()!=null && !"".equals(sdept.getDeptName())){
			sql += "  dept_name " + sdept.getDeptName() + "";
		}
		System.out.println(sql);
		// 查询员工
		ResultSet rs = stm.executeQuery(sql);
		// 定义封装用户的集合
		List<TDeptInfo> list = new ArrayList<>();
		// 遍历结果集
		while (rs.next()) {
			// 新建部门对象
			TDeptInfo dept = new TDeptInfo();
			// 提交数据行中每列数据设置给部门对象的相应的属性
			dept.setDeptId(rs.getInt("dept_id"));
			dept.setDeptName(rs.getString("dept_name"));
			dept.setDeptMemo(rs.getString("dept_memo"));
			// 添加员工对象集合
			list.add(dept);
		}
		// 关闭数据库连接
		conn.close();
		// 返回封装员工
		return list;
	}

	/**
	 * 添加部门数据
	 */
	@Override
	public void add(TDeptInfo dept) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建预编译的语句对象
		PreparedStatement stm = conn
				.prepareStatement(" insert into t_dept_info(dept_id,dept_name,dept_memo) value(?,?,?)");
		// 设置语句参数
		stm.setInt(1, dept.getDeptId());
		stm.setString(2, dept.getDeptName());
		stm.setString(3, dept.getDeptMemo());
		// 执行语句
		stm.execute();
		// 关闭数据库
		conn.close();

	}

	/**
	 * 修改部门数据
	 */
	@Override
	public void update(TDeptInfo dept) throws Exception {
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 获得预编译的语句对象
		PreparedStatement stm = conn
				.prepareStatement(" update t_dept_info set dept_name=?,dept_memo=? where dept_id=?");
		// 设置语句参数
		stm.setString(1, dept.getDeptName());
		stm.setString(2, dept.getDeptMemo());
		stm.setInt(3, dept.getDeptId());
		// 执行SQL语句
		stm.execute();
		// 关闭连接
		conn.close();

	}

	/**
	 * 删除部门数据
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
		String sql = "update t_dept_info set isdel=1 where dept_id in(" + inSql + ")";
		// 获得数据库连接
		Connection conn = DataSource.getConnection();
		// 创建操纵数据库的语句对象
		Statement stm = conn.createStatement();
		// 执行SQL语句
		stm.executeQuery(sql);
		// 关闭连接
		conn.close();
	}

	@Override
	public List<TDeptInfo> queryadd(TDeptInfo Object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int querys(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.hr.database;

import java.sql.Connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 数据库类
 * @author YYH
 * @createTime 2017年6月25日 下午7:56:41
 * @version 1.0.0
 */
public class DataSource {
	
	private final static String url = "jdbc:mysql://localhost:3306/hr?characterEncoding=utf-8";
	private final static String user = "root";
	private final static String password = "123456";
	
	public static Connection getConnection() throws Exception {
		//创建MySQL的数据源对象
		MysqlDataSource dataSource = new MysqlDataSource();
		//设置url连接路径
		dataSource.setURL(url);
		//设置用户名
		dataSource.setUser(user);
		//设置密码
		dataSource.setPassword(password);
		//打开并返回数据库连接
		return dataSource.getConnection();
	}
}
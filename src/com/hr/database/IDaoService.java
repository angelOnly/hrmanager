package com.hr.database;

import java.util.List;


/**
 * 数据访问的Dao公共接口
 * @author YYH
 * @createTime 2017年6月25日 下午7:51:28
 * @version 1.0.0
 */
public interface IDaoService<T> {

	/**
	 * 查询并返回数据列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> query() throws Exception;
	
	/**
	 * 条件查询并返回数据列表
	 * @param Object
	 * @return
	 * @throws Exception
	 */
	public List<T> query(T Object) throws Exception;

	/**
	 * 添加数据 向数据库表中添加一行新纪录
	 * 
	 * @param object
	 * @throws Exception
	 */
	public void add(T object) throws Exception;

	/**
	 * 修改数据 修改数据库表中指定行的数据
	 * 
	 * @param object
	 * @throws Exception
	 */
	public void update(T object) throws Exception;

	/**
	 * 批量删除数据 支持删除数据库表中的单行数据、多行数据
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String... ids) throws Exception;

	/**
	 * 复杂查询
	 * @param Object
	 * @return
	 * @throws Exception
	 */
	public List<T> queryadd(T Object) throws Exception;
	
	public int querys(int parseInt) throws Exception;

}

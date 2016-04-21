package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Log;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class LogDAO extends BaseDAO implements Interface<Log> {
	QueryRunner runner;
	
	public LogDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#save(java.lang.Object)
	 * 添加日志
	 */
	@Override
	public boolean save(Log log) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_log(uid,time,operate,obj) values(?,?,?,?)";
		Object[] params = {log.getUid(),new Date(),log.getOperate(),log.getObj()};
		try {
			int rows = runner.update(conn, sql, params);
			if(rows > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		getConnection();
		boolean flag = false;
		
		String sql = "delete from tb_log where id = ?";
		Object[] params = {id};
		try {
			int rows = runner.update(conn, sql, params);
			if(rows > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(Log t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Log findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#findAll()
	 * 查看所有操作日志
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Log> findAll() {
		getConnection();
		List<Log> list = null;
		String sql = "select * from tb_log";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Log.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 根据单个字段进行模糊查询(操作用户，操作时间，操作类型等条件)
	 * @param field 要查询的字段条件，对应easyui界面的查询框的查询条件名
	 * @param value 对应字段所要查询的内容，对应easyui界面的查询框的输入的查询内容
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> findAll(String field, String value, int page, int pageSize){
		String where="";
		getConnection();
		List<Log> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page-1)*pageSize;
		try {
			String sqlCount = "select count(1) from tb_log where 1=1";//查询记录总数量
			String sql = "select * from tb_log where 1=1";//查询所有的记录
			if(value!=null && !value.equals("")) {
				//模糊查询
				where += " and " + field + " like '%"+value+"%'";
				sqlCount += where;//每页显示的数量
				sql += where;
			}

			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			map.put("total", total);
			
			if(total>0) {
				String sqlLimit = " limit "+startCount + "," + pageSize;
				list = runner.query(conn, sql + " order by id desc "+sqlLimit, new BeanListHandler<>(Log.class));
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

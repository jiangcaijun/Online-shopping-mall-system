package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Role;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class RoleDAO extends BaseDAO implements Interface<Role> {
	QueryRunner runner;
	
	public RoleDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	@Override
	public boolean save(Role role) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_role(uid,auth) values(?,?)";
		Object[] params = {role.getUid(),role.getAuth()};
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
		
		String sql = "delete from tb_role where id = ?";
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
	public boolean update(Role role) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_role set auth=? where id = ?";
		Object[] params = {role.getAuth(),role.getId()};
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

	@SuppressWarnings("deprecation")
	@Override
	public Role findBy(int id) {
		getConnection();
		Role role = null;
		String sql = "select * from tb_role where id = ?";
		Object[] params = {id};
		try {
			role = runner.query(conn, sql, params, new BeanHandler<>(Role.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Role> findAll() {
		getConnection();
		List<Role> list = null;
		String sql = "select * from tb_role";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Role.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 根据单个字段进行模糊查询
	 * 
	 * @param field
	 *            要查询的字段条件，对应bootstrap界面的查询框的查询条件名
	 * @param value
	 *            对应字段所要查询的内容，对应bootstrap界面的查询框的输入的查询内容
	 * @param page
	 * @param pageSize
	 * @return
	 * @author hunterzhou
	 */
	public HashMap<String, Object> findAll(String field, String value, int page, int pageSize) {
		String where = "";
		getConnection();
		List<Role> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_role where 1=1";// 查询记录总数量
			String sql = "select * from tb_role where 1=1";// 查询所有的记录
			if (value != null && !value.equals("")) {
				// 模糊查询
				where += " and " + field + " like '%" + value + "%'";
				sqlCount += where;// 每页显示的数量
				sql += where;
			}

			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			map.put("total", total);

			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + " order by id desc " + sqlLimit, new BeanListHandler<>(Role.class));
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

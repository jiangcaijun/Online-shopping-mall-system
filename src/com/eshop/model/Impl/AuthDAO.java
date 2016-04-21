package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Auth;
import com.eshop.bean.AuthManage;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;
import com.eshop.utils.ChangeUtils;

public class AuthDAO extends BaseDAO implements Interface<Auth>{
	
	QueryRunner runner;
	
	public AuthDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#save(java.lang.Object)
	 * 增加权限访问的页面
	 */
	@Override
	public boolean save(Auth auth) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_auth(auth,url) values(?,?)";
		Object[] params = {auth.getAuth(),auth.getUrl()};
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

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#delete(int)
	 * 删除某个权限的耨条访问页面
	 */
	@Override
	public boolean delete(int id) {
		getConnection();
		boolean flag = false;
		
		String sql = "delete from tb_auth where id = ?";
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

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#update(java.lang.Object)
	 * 更新某个权限的某一个访问页面
	 */
	@Override
	public boolean update(Auth auth) {
		getConnection();
		boolean flag = false;
		String sql = "update tb_auth set url=? where id=?";
		Object[] params = {auth.getUrl(),auth.getId()};
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
	public Auth findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auth> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 查找某一个权限对应的所有的访问页面
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Auth> findAllUrl(int auth) {
		getConnection();
		List<Auth> list = null;
		String sql = "select * from tb_auth where auth = ?";
		Object[] params = {auth};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Auth.class));
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
		List<Auth> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_auth where 1=1";// 查询记录总数量
			String sql = "select * from tb_auth where 1=1";// 查询所有的记录
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
				list = runner.query(conn, sql + " order by id desc " + sqlLimit, new BeanListHandler<>(Auth.class));
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 查询用户的所有访问页面
	 * @param uid
	 * @return
	 */
	public List<String> findUserUrl(int uid) {
		getConnection();
		List<AuthManage> list = null;
		try {
			String sql = "select u.username,a.auth,a.url from tb_user u,tb_role r,tb_auth a "
					+ "where r.auth=a.auth and u.id=r.uid and r.uid="+ uid +"";
			System.out.println(sql);

			list = runner.query(conn, sql, new BeanListHandler<>(AuthManage.class));
			int size = list.size();
			List<String> listUrl = new ArrayList<String>();
			for (int i = 0; i < size; i++) {
				listUrl.add(list.get(i).getUrl());
			}
			List<String> listTrueUrl = ChangeUtils.setTolist(ChangeUtils.listToSet(listUrl));
			return listTrueUrl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

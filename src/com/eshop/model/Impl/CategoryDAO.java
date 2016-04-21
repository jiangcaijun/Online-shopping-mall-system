package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Category;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class CategoryDAO extends BaseDAO implements Interface<Category>{
	QueryRunner runner;
	
	public CategoryDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#save(java.lang.Object)
	 * 添加商品类别
	 */
	@Override
	public boolean save(Category category) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_category(type) values(?)";
		Object[] params = {category.getType()};
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
		
		String sql = "delete from tb_category where id = ?";
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
	public boolean update(Category category) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_category set type=? where id = ?";
		Object[] params = {category.getType(),category.getId()};
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
	public Category findBy(int id) {
		getConnection();
		Category category = null;
		String sql = "select * from tb_category where id = ?";
		Object[] params = {id};
		try {
			category = runner.query(conn, sql, params, new BeanHandler<>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Category> findAll() {
		getConnection();
		List<Category> list = null;
		String sql = "select * from tb_category";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


}

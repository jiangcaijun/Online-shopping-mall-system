package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Pub;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

/**
 * 商品发布DAO
 * @author uname
 *
 */
public class PubDAO extends BaseDAO implements Interface<Pub> {
	QueryRunner runner;
	
	public PubDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	@Override
	public boolean save(Pub pub) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_pub(itemId,startTime,endTime) values(?,?,?)";
		Object[] params = {pub.getItemId(),pub.getStartTime(),pub.getEndTime()};
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
		
		String sql = "delete from tb_pub where id = ?";
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
	public boolean update(Pub pub) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_pub set itemId=?,startTime=?,endTime=? where id = ?";
		Object[] params = {pub.getItemId(),pub.getStartTime(),pub.getEndTime(),pub.getId()};
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
	public Pub findBy(int id) {
		getConnection();
		Pub pub = null;
		String sql = "select * from tb_pub where id = ?";
		Object[] params = {id};
		try {
			pub = runner.query(conn, sql, params, new BeanHandler<>(Pub.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pub;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Pub> findAll() {
		getConnection();
		List<Pub> list = null;
		String sql = "select * from tb_pub";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Pub.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


}

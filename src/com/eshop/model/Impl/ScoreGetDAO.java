package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.ScoreGet;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class ScoreGetDAO extends BaseDAO implements Interface<ScoreGet> {
	QueryRunner runner;
	
	public ScoreGetDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}

	@Override
	public boolean save(ScoreGet scoreGet) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_scoreGet(uid,source,time,num) values(?,?,?,?)";
		Object[] params = {scoreGet.getUid(),scoreGet.getSource(),scoreGet.getTime(),scoreGet.getNum()};
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
		
		String sql = "delete from tb_scoreGet where id = ?";
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
	public boolean update(ScoreGet scoreGet) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_scoreGet set uid=?,source=?,time=?,num=? where id = ?";
		Object[] params = {scoreGet.getUid(),scoreGet.getSource(),scoreGet.getTime(),scoreGet.getNum(),scoreGet.getId()};
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
	public ScoreGet findBy(int id) {
		getConnection();
		ScoreGet scoreGet = null;
		String sql = "select * from tb_scoreGet where id = ?";
		Object[] params = {id};
		try {
			scoreGet = runner.query(conn, sql, params, new BeanHandler<>(ScoreGet.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreGet;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ScoreGet> findAll() {
		getConnection();
		List<ScoreGet> list = null;
		String sql = "select * from tb_scoreGet";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(ScoreGet.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}

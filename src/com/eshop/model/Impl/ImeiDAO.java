package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Imei;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class ImeiDAO extends BaseDAO implements Interface<Imei>{
	QueryRunner runner;

	public ImeiDAO() {
		runner = new QueryRunner(dataSource);// 创建查询实例对象
	}

	@Override
	public boolean save(Imei imei) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_imei(device,imei) values(?,?)";
		Object[] params = {imei.getDevice(),imei.getImei()};
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
		
		String sql = "delete from tb_imei where id = ?";
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
	public boolean update(Imei imei) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_imei set device=?,imei=? where id = ?";
		Object[] params = {imei.getDevice(),imei.getImei(),imei.getId()};
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
	public Imei findBy(int id) {
		getConnection();
		Imei imei = null;
		String sql = "select * from tb_imei where id = ?";
		Object[] params = {id};
		try {
			imei = runner.query(conn, sql, params, new BeanHandler<>(Imei.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imei;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Imei> findAll() {
		getConnection();
		List<Imei> list = null;
		String sql = "select * from tb_imei";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Imei.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 根据设备条形码查询用户所绑定设备是否存在
	 * @param im 设备条形码
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Imei findByImei(String im) {
		getConnection();
		Imei imei = null;
		String sql = "select * from tb_imei where imei = ?";
		Object[] params = {im};
		try {
			imei = runner.query(conn, sql, params, new BeanHandler<>(Imei.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imei;
	}
}

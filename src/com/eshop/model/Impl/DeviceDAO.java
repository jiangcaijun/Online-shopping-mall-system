package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Device;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class DeviceDAO extends BaseDAO implements Interface<Device> {
	QueryRunner runner;
	
	public DeviceDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}

	@Override
	public boolean save(Device device) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_device(imei,uid,time) values(?,?,now())";
		Object[] params = {device.getImei(),device.getUid()};
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
		
		String sql = "delete from tb_device where id = ?";
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
	public boolean update(Device device) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_device set imei=?,uid=?,time=? where id = ?";
		Object[] params = {device.getImei(),device.getUid(),device.getTime(),device.getId()};
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
	public Device findBy(int id) {
		getConnection();
		Device device = null;
		String sql = "select * from tb_device where id = ?";
		Object[] params = {id};
		try {
			device = runner.query(conn, sql, params, new BeanHandler<>(Device.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return device;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Device> findAll() {
		getConnection();
		List<Device> list = null;
		String sql = "select * from tb_device";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Device.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 查找用户的所有设备
	 * @param uid
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Device> findByUser(int uid) {
		getConnection();
		List<Device> list = null;
		String sql = "select i.device,i.imei,q.time,q.onlineTime,q.onlineCredit,q.current,q.currentCredit from tb_imei i right join (select imei,time,onlineTime,onlineCredit,current,currentCredit from tb_device "
					+ "where uid=?) q on q.imei=i.imei";
		Object[] params = {uid};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Device.class));
			System.out.println(list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据设备条形码查询是否已有用户绑定过该设备
	 * @param im
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Device findByImei(String im) {
		getConnection();
		Device device = null;
		String sql = "select * from tb_device where imei = ?";
		Object[] params = {im};
		try {
			device = runner.query(conn, sql, params, new BeanHandler<>(Device.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return device;
	}
	

	/**
	 * 查找用户的所有绑定的设备信息分页版
	 * @param uid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> findByUid(int uid,int page, int pageSize) {
		@SuppressWarnings("unused")
		String where = "";
		getConnection();
		List<Device> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_imei i right join (select imei from tb_device w"
					+ "here uid=" + uid + ") q on q.imei=i.imei";// 查询记录总数量
			String sql = "select i.device,i.imei,q.time from tb_imei i right join (select imei,time from tb_device "
					+ "where uid=" + uid + ") q on q.imei=i.imei";// 查询所有的记录
			System.out.println(sqlCount);
			System.out.println(sql);

			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			System.out.println("total:"+total);
			map.put("total", total);

			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + sqlLimit, new BeanListHandler<>(Device.class));
				System.out.println("size:"+list.size());
				map.put("rows", list);
			}
			System.out.println(map);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}	
}

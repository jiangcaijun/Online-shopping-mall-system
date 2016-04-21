package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.NewAddress;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

/**
 * 用户新增地址DAO
 * 
 * @author uname
 *
 */
public class NewAddressDAO extends BaseDAO implements Interface<NewAddress> {
	QueryRunner runner;

	public NewAddressDAO() {
		runner = new QueryRunner(dataSource);// 创建查询实例对象
	}

	@Override
	public boolean save(NewAddress newAddress) {
		getConnection();// 创建连接
		boolean flag = false;
		String sql = "insert into tb_newAddress(uid,tel,city,address,postcode,gainName,status) values(?,?,?,?,?,?,?)";
		Object[] params = {newAddress.getUid(),newAddress.getTel(),newAddress.getCity(),newAddress.getAddress(),newAddress.getPostcode(),newAddress.getGainName(),newAddress.getStatus()};
		try {
			int rows = runner.update(conn, sql, params);
			if (rows > 0) {
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

		String sql = "delete from tb_newAddress where id = ?";
		Object[] params = { id };
		try {
			int rows = runner.update(conn, sql, params);
			if (rows > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(NewAddress newAddress) {
		getConnection();

		boolean flag = false;
		String sql = "update tb_newAddress set uid=?,tel=?,city=?,address=?,postcode=? where id=?";
		Object[] params = {newAddress.getUid(),newAddress.getTel(),newAddress.getCity(),newAddress.getAddress(),newAddress.getPostcode(),newAddress.getId()};
		try {
			int rows = runner.update(conn, sql, params);
			if (rows > 0) {
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
	public NewAddress findBy(int id) {
		getConnection();
		NewAddress newAddress = null;
		String sql = "select * from tb_newAddress where id = ?";
		Object[] params = { id };
		try {
			newAddress = runner.query(conn, sql, params, new BeanHandler<>(NewAddress.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newAddress;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<NewAddress> findAll() {
		getConnection();
		List<NewAddress> list = null;
		String sql = "select * from tb_newAddress";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(NewAddress.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	/**通过地址查找
	 * @param address
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public NewAddress findByAdd(String address) {
		getConnection();
		NewAddress newAddress = null;
		String sql = "select * from tb_newAddress where address = ?";
		Object[] params = { address };
		try {
			newAddress = runner.query(conn, sql, params, new BeanHandler<>(NewAddress.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newAddress;
	}
	
	@SuppressWarnings("deprecation")
	public List<NewAddress> findByAdd(int id) {
		getConnection();
		List<NewAddress> list = null;
		String sql = "select * from tb_newAddress where id=?";
		Object[] params = {id};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(NewAddress.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	/**取消该用户默认地址
	 * @param uid
	 * @param status
	 * @return
	 */
	public boolean update(int uid,int status) {
		getConnection();
		boolean flag = false;
		String sql = "update tb_newAddress set status =? where uid=?";
		Object[] params = {status,uid};
		try {
			int rows = runner.update(conn, sql, params);
			if (rows > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateMain(int id,int status) {
		getConnection();
		boolean flag = false;
		String sql = "update tb_newAddress set status =? where id=?";
		Object[] params = {status,id};
		try {
			int rows = runner.update(conn, sql, params);
			if (rows > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}

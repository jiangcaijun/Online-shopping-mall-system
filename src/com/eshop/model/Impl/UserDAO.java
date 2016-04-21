package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.NewAddress;
import com.eshop.bean.User;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class UserDAO extends BaseDAO implements Interface<User> {
	QueryRunner runner;
	
	public UserDAO() {
		runner = new QueryRunner(dataSource);
		//runner = new QueryRunner();
	}

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#save(java.lang.Object)
	 * 增加用户
	 */
	@Override
	public boolean save(User user) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_user(username,password,tel,url,credit,email,auth) values(?,?,?,?,?,?,?)";
		Object[] params = {user.getUsername(),user.getPassword(),user.getTel(),user.getUrl(),user.getCredit(),user.getEmail(),user.getAuth()};
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
	 * 物理删除用户
	 */
	@Override
	public boolean delete(int id) {
		getConnection();
		boolean flag = false;
		
		String sql = "delete from tb_user where id = ?";
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
	 * 修改用户(逻辑删除是只要更新delete为0即可)
	 */
	@Override
	public boolean update(User user) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_user set tel=?,url=?,credit=?,email=?,auth=?,del=? where id = ?";
		Object[] params = {user.getTel(),user.getUrl(),user.getCredit(),user.getEmail(),user.getAuth(),user.getDel(),user.getId()};
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
	 * @see com.eshop.model.Interface#findBy(int)
	 * 查找用户信息
	 */
	@SuppressWarnings("deprecation")
	@Override
	public User findBy(int id) {
		getConnection();
		User user = null;
		String sql = "select * from tb_user where id = ?";
		Object[] params = {id};
		try {
			user = runner.query(conn, sql, params, new BeanHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/**查找该用户所有地址
	 * @param uid
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<NewAddress> findAll(int uid) {
		getConnection();
		List<NewAddress> list = null;
		String sql = "select * from tb_newaddress where uid=? order by status desc";
		Object[] params = {uid};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(NewAddress.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#findAll()
	 * 查找所有用户信息
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<User> findAll() {
		getConnection();
		List<User> list = null;
		String sql = "select * from tb_user";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public User login(User user){
		getConnection();
		User u = null;
		try {
			u = runner.query(conn, "select * from tb_user where username=? and password=? and del=1", new Object[]{user.getUsername(), user.getPassword()}, 
					new BeanHandler<User>(User.class));
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @param pw 新密码
	 * @return
	 */
	public boolean change(User user,int uid) {
		getConnection();
		//boolean flag = true;
		try {
			int row = runner.update(conn, "update tb_user set password=? where id =?", user.getPassword(),uid);
			if(row>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 查找用户姓名
	 * @param user
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public User findByName(User user){
		getConnection();
		User u = null;
		try {
			u = runner.query(conn, "select * from tb_user where username=?", new Object[]{user.getUsername()}, 
					new BeanHandler<User>(User.class));
			return u;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 更新用户的邮件信息
	 * @param user
	 * @return
	 */
	public boolean updateEmail(User user){
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_user set email=? where username = ?";
		Object[] params = {user.getEmail(),user.getUsername()};
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
	
	
	/**
	 * 根据用户名删除用户
	 * @param username
	 * @return
	 */
	public boolean delete(String username) {
		getConnection();
		boolean flag = false;
		
		String sql = "delete from tb_user where username = ?";
		Object[] params = {username};
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
	
	/**修改用户头像
	 * @param user
	 * @return
	 */
	public boolean updateHead(User user) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_user set url=? where id = ?";
		Object[] params = {user.getUrl(),user.getId()};
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
	 */
	public HashMap<String, Object> findAll(String field, String value, int page, int pageSize,int auth) {
		String where = "";
		getConnection();
		List<User> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_user";// 查询记录总数量
			String sql = "select * from tb_user";// 查询所有的记录
			if(auth==0){
				sqlCount = sqlCount+" where auth=0";
				sql = sql+" where auth=0";
			}else{
				sqlCount = sqlCount+" where auth<>0";
				sql = sql+" where auth<>0";
			}
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
				list = runner.query(conn, sql + " order by id desc " + sqlLimit, new BeanListHandler<>(User.class));
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 更新个人积分
	 * @param user
	 * @return
	 */
	public boolean updateCredit(User user) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_user set credit=?  where id = ?";
		Object[] params = {user.getCredit(),user.getId()};
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
	
	/**
	 * 检测管理员
	 * @param name
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean checkAdmin(String name){
		getConnection();
		User u = null;
		try {
			u = runner.query(conn, "select * from tb_user where username=? and auth=1 and del=1", new Object[]{name}, 
					new BeanHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if(u==null)return false;
		else return true;
	}

	/*public static void main(String[] args) {

	
	/**
	 * 处理用户逻辑删除
	 * @param uid
	 * @param field
	 * @param del （0逻辑删除了，1未逻辑删除）
	 * @return
	 */
	public boolean update(int uid,String field,int del){
		getConnection();
		boolean flag = false;
		String sql = "update tb_user set "+field+"=? where id = ?";
		System.out.println(sql);
		Object[] params = {del,uid};
		try {
			int rows = runner.update(conn,sql,params);
			if (rows > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 修改用户信息（电话和头像）
	 * @param field
	 * @param value
	 * @param id
	 * @return
	 */
	public boolean update(String field,String value,int id){
		getConnection();
		boolean flag = false;
		String sql = "update tb_user set "+field+"=? where id = ?";
		System.out.println(sql);
		Object[] params = {value,id};
		try {
			int rows = runner.update(conn,sql,params);
			if (rows > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查询是否存在该电话号码
	 * @param tel
	 * @return
	 */
	public boolean findByTel(String tel) {
		getConnection();
		boolean flag = false;
		String sql = "select * from tb_user where tel = ?";
		Object[] params = {tel};
		try {
			@SuppressWarnings("deprecation")
			User user = runner.query(conn, sql, params, new BeanHandler<>(User.class));
			if(user != null){
				flag = true;
			}else{
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}

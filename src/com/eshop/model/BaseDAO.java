package com.eshop.model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * BaseDAO：封装了JDBC的常用CRUD操作
 * 
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class BaseDAO {
	public static Connection conn;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
	CallableStatement cs;
	static String url, user, password, driver;
	public static ComboPooledDataSource dataSource;
	

	static {
		try {
			/**
			 * JDBC连接Oracle几种方式： 1 thin模式，这是推荐的方式，不需要安装oracle客户端 2
			 * oci模式，性能比thin更高，但是要求必须安装oracle client 3 jdbc-odbc桥模式
			 * 需要添加驱动jar包到工程，jar包在oracle的安装目录下，可自行搜索classes12.jar
			 */
			Properties pro = new Properties();
			pro.load(BaseDAO.class.getResourceAsStream("/com/eshop/config/db.properties"));
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			driver = pro.getProperty("driver");
			
			dataSource = new ComboPooledDataSource();
			dataSource.setJdbcUrl(url);
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setDriverClass(driver);
			dataSource.setInitialPoolSize(5);
            dataSource.setMinPoolSize(1);
            dataSource.setMaxPoolSize(1000);
            dataSource.setMaxStatements(50);
            dataSource.setMaxIdleTime(60);
			//Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			//conn = DriverManager.getConnection(url, user, password);
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}

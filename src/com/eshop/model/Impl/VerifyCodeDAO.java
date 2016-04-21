package com.eshop.model.Impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import com.eshop.bean.VerifyCode;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class VerifyCodeDAO extends BaseDAO implements Interface<VerifyCode> {

	QueryRunner runner;
	
	public VerifyCodeDAO() {
		runner = new QueryRunner(dataSource);
	}

	@Override
	public boolean save(VerifyCode verifyCode) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_verifyCode(uid,code,expireDate) values(?,?,?)";
		Object[] params = {verifyCode.getUid(),verifyCode.getCode(),verifyCode.getExpireDate()};
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
		
		String sql = "delete from tb_verifyCode where id = ?";
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
	public boolean update(VerifyCode verifyCode) {
		/*getConnection();
		
		boolean flag = false;
		String sql = "update tb_item set exprireDate=? where id = ?";
		Object[] params = {verifyCode.getExpireDate(),verifyCode.getId()};
		try {
			int rows = runner.update(conn, sql, params);
			if(rows > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;*/
		return false;
	}

	@Override
	public VerifyCode findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VerifyCode> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 验证动态码是否正确
	 * @param uid
	 * @param code
	 * @return
	 */
	public boolean verifyCode(long uid, String code){
		getConnection();
		boolean flag = false;
		try {
			@SuppressWarnings("deprecation")
			Object[] result = runner.query(conn, "select id from tb_verifycode where uid=? and code=? and now()<expireDate", new Object[]{uid,code}, new ArrayHandler());
			int id = 0;
			try {
				id = (result!=null&&(int)result[0]>0)?(int)result[0]:0;
			} catch (ArrayIndexOutOfBoundsException e) {
				id = 0;
			}
					
			flag = id>0?true:false;
			if(flag)	runner.update(conn, "delete from tb_verifycode where id=?", new Object[]{id});//删除验证过的动态码
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 创建一条验证码记录
	 * @param uid
	 * @param code
	 */
	public void createVerifyCode(long uid, String code){
		getConnection();
		try {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+15);
			Date date = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(date);
			
			runner.update(conn, "delete from tb_verifycode where uid=?", new Object[]{uid});//删除该用户所有之前未使用过的动态码
			runner.update(conn, "insert into tb_verifycode(code,expireDate,uid) values(?,?,?)", new Object[]{code, strDate, uid});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}

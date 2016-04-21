package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.eshop.bean.EmailTime;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;
import com.eshop.utils.GetNextDateUtils;

/**
 * 库存不足时发送邮件次数控制DAO
 * @author uname
 *
 */
public class EmailTimeDAO extends BaseDAO implements Interface<EmailTime>{
	QueryRunner runner;
	
	public EmailTimeDAO() {
		runner = new QueryRunner(dataSource);
	}

	@Override
	public boolean save(EmailTime t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(EmailTime t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmailTime findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailTime> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 判断是否继续发送邮件提醒后台题型对应的商品库存不足
	 * @param itemId  对应商品id
	 * @param day 配置参数（天）
	 * @return true表示可以发送邮件
	 */
	public boolean sendNotice(int itemId,int day){
		getConnection();
		boolean flag = false;
		String sql = "select niticeTime from tb_emailtime where itemId = ?";
		Object[] params = {itemId};
		try {
			@SuppressWarnings("deprecation")
			EmailTime emailTime = runner.query(conn, sql, params, new BeanHandler<>(EmailTime.class));
			
			System.out.println(GetNextDateUtils.getNextDay(emailTime.getNiticeTime(),day));
			System.out.println(new Date());
			//判断给定时间是否在规定的时间之后，设为在第一次发送邮件时间的24小时范围内
			if(emailTime != null && GetNextDateUtils.getNextDay(emailTime.getNiticeTime(),day).before(new Date())){
				flag = true;
			}else{
				flag = false;
			}
			System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}

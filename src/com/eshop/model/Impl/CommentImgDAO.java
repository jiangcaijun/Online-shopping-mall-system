package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.CommentImg;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class CommentImgDAO extends BaseDAO implements Interface<CommentImg> {
	QueryRunner runner;
	
	public CommentImgDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	
	
	@Override
	public boolean save(CommentImg commentImg) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_commentImg(commentId,curl) values(?,?)";
		Object[] params = {commentImg.getCommentId(),commentImg.getCurl()};
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
	 * 删除评论图片
	 */
	@Override
	public boolean delete(int id) {
		getConnection();
		boolean flag = false;
		
		String sql = "delete from tb_commentImg where id = ?";
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
	public boolean update(CommentImg commentImg) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_commentImg set commentId=?,curl=? where id = ?";
		Object[] params = {commentImg.getCommentId(),commentImg.getCurl(),commentImg.getId()};
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
	public CommentImg findBy(int id) {
		getConnection();
		CommentImg commentImg = null;
		String sql = "select * from tb_commentImg where id = ?";
		Object[] params = {id};
		try {
			commentImg = runner.query(conn, sql, params, new BeanHandler<>(CommentImg.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentImg;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CommentImg> findAll() {
		getConnection();
		List<CommentImg> list = null;
		String sql = "select * from tb_commentImg";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(CommentImg.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


}

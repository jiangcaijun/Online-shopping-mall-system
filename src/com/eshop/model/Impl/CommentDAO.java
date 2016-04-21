package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Comment;
import com.eshop.bean.CommentImg;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

/**
 * @author uname
 *
 */
public class CommentDAO extends BaseDAO implements Interface<Comment> {
	QueryRunner runner;
	
	
	public CommentDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	@Override
	public boolean save(Comment comment) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_comment(id,uid,itemId,content,time,rank) values(?,?,?,?,now(),?)";
		Object[] params = {comment.getId(),comment.getUid(),comment.getItemId(),comment.getContent(),comment.getRank()};
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
		
		String sql = "delete from tb_comment where id = ?";
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
	public boolean update(Comment comment) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_comment set content=?,rank=? where id = ?";
		Object[] params = {comment.getContent(),comment.getRank(),comment.getId()};
		try{
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
	public Comment findBy(int id) {
		getConnection();
		Comment comment = null;
		String sql = "select * from tb_comment where id = ?";
		Object[] params = {id};
		try {
			comment = runner.query(conn, sql, params, new BeanHandler<>(Comment.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Comment> findAll() {
		getConnection();
		List<Comment> list = null;
		String sql = "select * from tb_comment";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Comment.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}

	/**根据商品编号佐联表查询评论以及相应的图片和用户姓名
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public HashMap<String, Object> findAllComment(int id,int page, int pageSize) {
		getConnection();
		List<Comment> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_comment where 1=1";// 查询记录总数量
			String sql = "select a.uid,c.username,b.curl,a.content,a.time,a.rank,c.url from tb_comment as a left join tb_commentimg as b on a.id=b.commentId left join tb_user as c on a.uid = c.id where a.itemId = ?";// 查询所有的记录
			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			//System.out.println(total);
			map.put("total", total);
			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + " order by a.id desc " + sqlLimit, new Object[]{id},new BeanListHandler<Comment>(Comment.class));
				
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * 根据商品id查询对应商品的评论以及评论的图片
	 * @param itemId
	 * @return
	 */
	public List<Comment> findByItemId(int itemId) {
		getConnection();
		List<Comment> list = null;
		try {
			String sql = "select c.itemId,c.content,p.curl from tb_comment c,tb_commentimg p "
					+ "where c.id=p.commentId and c.itemId="+ itemId +" order by c.time desc";
			System.out.println(sql);

			list = runner.query(conn, sql, new BeanListHandler<>(Comment.class));
			System.out.println("size:"+list.size());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据根据商品id查询对应商品的评论分页版
	 * @param itemId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> findComment(int itemId, int page, int pageSize) {
		getConnection();
		List<Comment> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_user u "
					+ "right join(select c.uid,c.content,c.time,p.curl from tb_comment c "
					+ "left join(select commentId,curl from tb_commentimg)p on p.commentId=c.id "
					+ "where c.itemId=" + itemId + " )q on q.uid=u.id order by q.time desc";// 查询记录总数量
			String sql = "select u.username,u.url,q.content,q.time,q.curl from tb_user u "
					+ "right join(select c.uid,c.content,c.time,p.curl from tb_comment c "
					+ "left join(select commentId,curl from tb_commentimg)p on p.commentId=c.id "
					+ "where c.itemId=" + itemId + " )q on q.uid=u.id order by q.time desc";// 查询所有的记录

			System.out.println(sqlCount);
			System.out.println(sql);
			
			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			System.out.println(total);
			map.put("total", total);

			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + sqlLimit, new BeanListHandler<>(Comment.class));
				System.out.println(list.size());
				map.put("rows", list);
				System.out.println(map);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 处理商品品论及评论带的图片的事物
	 * @param comment 
	 * @param commentImgList
	 */
	public void commentInput(Comment comment, List<CommentImg> commentImgList) {
		getConnection();
		try {
			conn.setAutoCommit(false);//设置不自动提交事物
			//添加一条商品评论记录
			String sql = "insert into tb_comment(id,uid,itemId,content,time,rank) values(?,?,?,?,now(),?)";
			Object[] params = {comment.getId(),comment.getUid(),comment.getItemId(),comment.getContent(),comment.getRank()};
			int rows1 = runner.update(conn, sql, params);
			if(rows1 > 0){
				System.out.println("rows1:"+rows1);
			}
			//获取商品评论图片对应的商品评论ID
			System.out.println("CommentId:"+comment.getId());
			String CommentId = comment.getId();
			//将商品的图片信息加入到商品图表中
			for (CommentImg commentImg : commentImgList) {
				sql = "insert into tb_commentImg(commentId,curl) values(?,?)";
				Object[] param = {CommentId,commentImg.getCurl()};
				int rows2 = runner.update(conn, sql, param);
				if(rows2 > 0){
					System.out.println("rows2:"+rows2);
				}
			}
			//提交事务
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("商品评论信息出错！");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}

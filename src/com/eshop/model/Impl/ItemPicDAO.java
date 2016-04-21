package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.ItemPic;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

public class ItemPicDAO extends BaseDAO implements Interface<ItemPic> {
	
	QueryRunner runner;
	
	public ItemPicDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	
	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#save(java.lang.Object)
	 * 增加一条商品图片信息
	 */
	@Override
	public boolean save(ItemPic itemPic) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_itemPic(itemId,url) values(?,?)";
		Object[] params = {itemPic.getItemId(),itemPic.getUrl()};
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
	 * 根据商品图片id删除一张商品图片
	 */
	@Override
	public boolean delete(int id) {
		getConnection();
		boolean flag = false;
		
		String sql = "delete from tb_itemPic where id = ?";
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
	public boolean update(ItemPic t) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#findBy(int)
	 * 根据id查询
	 */
	@SuppressWarnings("deprecation")
	@Override
	public ItemPic findBy(int id) {
		getConnection();
		ItemPic itemPic = null;
		String sql = "select * from tb_itemPic where id = ?";
		Object[] params = {id};
		try {
			itemPic = runner.query(conn, sql, params, new BeanHandler<>(ItemPic.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemPic;
	}

	/* (non-Javadoc)
	 * @see com.eshop.model.Interface#findAll()
	 * 查询所有的记录
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<ItemPic> findAll() {
		getConnection();
		List<ItemPic> list = null;
		String sql = "select * from tb_itemPic";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(ItemPic.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据商品id查询商品图片及对应的商品信息
	 * @param itemId
	 * @return
	 */
	public List<ItemPic> findByItemId(int itemId) {
		getConnection();
		List<ItemPic> list = null;
		try {
			String sql = "select * from tb_item where id= "+itemId;
			list = runner.query(conn, sql, new BeanListHandler<>(ItemPic.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.eshop.model;

import java.util.List;

/**
 * 表格的基本操作的接口
 * @author uname
 * @param <T>
 *
 */
public interface Interface<T> {
	
	/**
	 * 保存商品信息
	 * @return
	 */
	public boolean save(T t);
	
	/**
	 * 根据id删除商品信息
	 * @return
	 */
	public boolean delete(int id);
	/**
	 * 修改新商品信息
	 * @return
	 */
	public boolean update(T t);
	/**
	 * 根据id查询商品信息
	 * @return
	 */
	public T findBy(int id);
	/**
	 * 查询所有商品信息
	 * @return
	 */
	public List<T> findAll();
}

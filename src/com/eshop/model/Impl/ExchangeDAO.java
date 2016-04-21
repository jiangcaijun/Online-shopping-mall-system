package com.eshop.model.Impl;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eshop.bean.Exchange;
import com.eshop.bean.ItemTypeChart;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

/**
 * @author uname
 *
 */
/**
 * @author uname
 *
 */
public class ExchangeDAO extends BaseDAO implements Interface<Exchange> {
	QueryRunner runner;
	
	public ExchangeDAO() {
		runner = new QueryRunner(dataSource);//创建查询实例对象
	}
	@Override
	public boolean save(Exchange exchange) {
		getConnection();//创建连接
		boolean flag = false;
		String sql = "insert into tb_exchange(itemId,price,num,state,uid,time) values(?,?,?,?,?,now())";
		Object[] params = {exchange.getItemId(),exchange.getPrice(),exchange.getNum(),exchange.getState(),exchange.getUid()};
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
		
		String sql = "delete from tb_exchange where id = ?";
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
	public boolean update(Exchange exchange) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_exchange set itemId=?,price=?,num=?,state=?,uid=?,time=? where id = ?";
		Object[] params = {exchange.getItemId(),exchange.getPrice(),exchange.getNum(),exchange.getState(),exchange.getUid(),exchange.getTime(),exchange.getId()};
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
	public Exchange findBy(int id) {
		getConnection();
		Exchange exchange = null;
		String sql = "select * from tb_exchange where id = ?";
		Object[] params = {id};
		try {
			exchange = runner.query(conn, sql, params, new BeanHandler<>(Exchange.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exchange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Exchange> findAll() {
		getConnection();
		List<Exchange> list = null;
		String sql = "select * from tb_exchange";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Exchange.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 按照条件进行查询用户兑换记录
	 * @param condition （最近一个星期、最近一个月）
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> findByCondition(String condition,int page, int pageSize) {
		String where = "";
		getConnection();
		List<Exchange> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_item i "
					+ "right join (select e.itemId eid,e.price,e.num num,e.time time from tb_exchange e where";// 查询记录总数量
			String sql = "select i.title,i.type,q.price,q.num,q.time from tb_item i "
					+ "right join (select e.itemId eid,e.price,e.num num,e.time time from tb_exchange e where";// 查询所有的记录
			if(condition.equals("最近一个星期")){
				where += " date_sub(curdate(),interval 7 day) <= e.time) q on i.id=q.eid order by q.num desc";
				sqlCount += where;
				sql += where;
			}else if(condition.equals("最近一个月")){
				where += " date_sub(curdate(),interval 1 month) <= e.time) q on i.id=q.eid order by q.num desc";
				sqlCount += where;
				sql += where;
			}
			System.out.println(sqlCount);
			System.out.println(sql);

			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			System.out.println("total:"+total);
			map.put("total", total);

			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + sqlLimit, new BeanListHandler<>(Exchange.class));
				System.out.println("sizefaax:"+list.size());
				map.put("rows", list);
			}
			System.out.println(map);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照商品类型查询
	 * @param type 商品类型
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> findByType(String type,int page, int pageSize) {
		String where = "";
		getConnection();
		List<Exchange> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_exchange e "
					+ "right join(select i.id id,i.title title,i.type type,i.price price from tb_item i where ";// 查询记录总数量
			String sql = "select q.title,q.type,q.price,e.num,e.time from tb_exchange e right join(select i.id id,i.title title,i.type type,i.price price from tb_item i where ";// 查询所有的记录
			if(type != null && !type.equals("")){
				where += "i.type like '"+"%"+type+"%"+"') q on e.itemId=q.id order by e.num desc";
				sqlCount += where;
				sql += where;
			}
			System.out.println(sqlCount);
			System.out.println(sql);

			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			System.out.println("total:"+total);
			map.put("total", total);

			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + sqlLimit, new BeanListHandler<>(Exchange.class));
				System.out.println("size:"+list.size());
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照商品类型查询用户兑换情况
	 * @return
	 */
	public List<HashMap<String, Object>> findByType() {
		@SuppressWarnings("unused")
		String where = "";
		getConnection();
		List<ItemTypeChart> list = null;
		
		List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
		try {
			String sql = "select i.type,sum(e.num) sum from tb_exchange e,tb_item i "
					+ "where i.id=e.itemId group by i.type order by sum desc";// 查询所有的记录
			System.out.println(sql);

			list = runner.query(conn, sql, new BeanListHandler<>(ItemTypeChart.class));
			int size = list.size();
			for (int i = 0; i < size; i++) {
				String type = list.get(i).getType();
				int sum = list.get(i).getSum();
				//新建一个map，不然会出错
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("sum", sum);
				map.put("type", type);				
				list2.add(map);
			}
			System.out.println(list2);
			return list2;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 查找兑换
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Exchange> findByExId(int id) {
		getConnection();
		List<Exchange> list = null;
		String sql = "select * from tb_exchange where itemId = ?";
		Object[] params = { id };
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Exchange.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	@SuppressWarnings("deprecation")
	public HashMap<String, Object> findByUid(int uid,int page, int pageSize) {
		getConnection();
		List<Exchange> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_comment where 1=1";// 查询记录总数量
			String sql = "select a.id,a.price,a.num,a.state,a.time,b.title,b.mainPic from tb_exchange as a left join tb_item as b on a.itemId=b.id where a.uid = ?";// 查询所有的记录
			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			//System.out.println(total);
			map.put("total", total);
			if (total > 0) {
				String sqlLimit = " limit " + startCount + "," + pageSize;
				list = runner.query(conn, sql + " order by a.id desc " + sqlLimit, new Object[]{uid},new BeanListHandler<Exchange>(Exchange.class));
				
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**我的评论
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public HashMap<String, Object> findByExIdO(int id) {
		getConnection();
		List<Exchange> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		long total = 0;
		try {
			String sqlCount = " select count(1) from tb_exchange as a inner join  tb_item as b on a.itemId = b.id inner join tb_comment as c on b.id = c.itemId where a.id=?";
			String sqlStars= "select sum(rank) from tb_exchange as a inner join  tb_item as b on a.itemId = b.id inner join tb_comment as c on b.id = c.itemId where a.id=?";
			String sql = "select b.mainPic, b.title,a.price,a.id from tb_exchange as a left join tb_item as b on a.itemId=b.id where a.id = ?";
			Object[] row = runner.query(conn, sqlCount, new Object[]{id}, new ArrayHandler());
			//System.out.println(row);
			if(row!=null){
				total = (long) row[0];
				if(total>0){
					Object[] rows = runner.query(conn, sqlStars, new Object[]{id}, new ArrayHandler());
					//System.out.println(rows);
					DecimalFormat df = new DecimalFormat( "0.0");
					double stars = 0;
					if(rows!=null){
						double star1 = (double)rows[0];
						stars = star1/total;
					}
					map.put("stars",df.format(stars));
				}
			}else{
				total=0;
			}
			
				
		
			map.put("total", total);
			list = runner.query(conn, sql, new Object[]{id}, new BeanListHandler<Exchange>(Exchange.class));
			map.put("rows", list);
			return  map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照商品类型查询用户兑换情况方法2
	 * @return
	 */
	public List<ItemTypeChart> findByType2() {
		@SuppressWarnings("unused")
		String where = "";
		getConnection();
		List<ItemTypeChart> list = null;
		
		try {
			String sql = "select i.type,sum(e.num) sum from tb_exchange e,tb_item i "
					+ "where i.id=e.itemId group by i.type order by sum desc";// 查询所有的记录
			System.out.println(sql);

			list = runner.query(conn, sql, new BeanListHandler<>(ItemTypeChart.class));
			System.out.println(list.size());
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**评价商品
	 * @param exchange
	 * @return
	 */
	public boolean updateCom(Exchange exchange) {
		getConnection();
		
		boolean flag = false;
		String sql = "update tb_exchange set state=? where id = ?";
		Object[] params = {exchange.getState(),exchange.getId()};
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
	 * 查看用户是否有过兑换记录
	 * @param uid
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Exchange findByUid(int uid) {
		getConnection();
		Exchange exchange = null;
		String sql = "select * from tb_exchange where uid = ?";
		Object[] params = {uid};
		try {
			exchange = runner.query(conn, sql, params, new BeanHandler<>(Exchange.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exchange;
	}
}

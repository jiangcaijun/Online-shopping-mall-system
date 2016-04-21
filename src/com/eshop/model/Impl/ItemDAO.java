package com.eshop.model.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.eshop.bean.Item;
import com.eshop.bean.ItemPic;
import com.eshop.bean.PopItem;
import com.eshop.model.BaseDAO;
import com.eshop.model.Interface;

/**
 * 商品信息处理的DAO
 * 
 * @author uname
 *
 */
/**
 * @author uname
 *
 */
public class ItemDAO extends BaseDAO implements Interface<Item> {
	QueryRunner runner;
	ExchangeDAO exchangeDAO = new ExchangeDAO();
	public ItemDAO() {
		runner = new QueryRunner(dataSource);// 创建查询实例对象
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eshop.model.Interface#save(java.lang.Object) 增加一条商品信息
	 */
	@Override
	public boolean save(Item item) {
		getConnection();// 创建连接
		boolean flag = false;
		String sql = "insert into tb_item(title,type,price,mainPic,info,state,num,storeNum,hot) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = { item.getTitle(), item.getType(), item.getPrice(), item.getMainPic(), item.getInfo(),
				item.getState(), item.getNum(), item.getStoreNum(),item.getHot()};
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eshop.model.Interface#delete(int) 根据id删除商品
	 */
	@Override
	public boolean delete(int id) {
		getConnection();
		boolean flag = false;

		String sql = "delete from tb_item where id = ?";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eshop.model.Interface#update(java.lang.Object) 修改商品信息
	 */
	@Override
	public boolean update(Item item) {
		getConnection();

		boolean flag = false;
		String sql = "update tb_item set price=?,info=?,state=?,num=?,storeNum=?,hot=? where id = ?";
		Object[] params = { item.getPrice(), item.getInfo(), item.getState(), item.getNum(), item.getStoreNum(),
				item.getHot(), item.getId() };
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eshop.model.Interface#findBy() 按照某个条件查询某一条商品信息
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Item findBy(int id) {
		getConnection();
		Item item = null;
		String sql = "select * from tb_item where id = ?";
		Object[] params = { id };
		try {
			item = runner.query(conn, sql, params, new BeanHandler<>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eshop.model.Interface#findAll() 查询所有的商品信息
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Item> findAll() {
		getConnection();
		List<Item> list = null;
		String sql = "select * from tb_item";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	
	/**
	 * 根据单个字段进行模糊查询
	 * 
	 * @param field
	 *            要查询的字段条件，对应easyui界面的查询框的查询条件名
	 * @param value
	 *            对应字段所要查询的内容，对应easyui界面的查询框的输入的查询内容
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public HashMap<String, Object> findAll(String field, String value, int page, int pageSize) {
		String where = "";
		getConnection();
		List<Item> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sqlCount = "select count(1) from tb_item where 1=1";// 查询记录总数量
			String sql = "select * from tb_item where 1=1";// 查询所有的记录
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
				list = runner.query(conn, sql + " order by id desc " + sqlLimit, new BeanListHandler<>(Item.class));
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取近一个月内的兑换热门的商品
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Item> hotExchangeItem() {
		getConnection();
		List<Item> list = null;
		String sql = "select i.*,sum(e.num) s from tb_item i,tb_exchange e "
				+ "where i.id=e.itemId and DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= time "
				+ "group by itemId order by s desc limit 0,5";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 手动上的热门商品
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Item> recommendItem() {
		getConnection();
		List<Item> list = null;

		String sql = "select * from tb_item where hot=1";
		Object[] params = {};
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 库存提醒
	 * 
	 * @param item
	 * @param sellNum
	 *            销售的数量
	 * @return
	 */
	public boolean storeNumeNotice(Item item, int sellNum) {
		getConnection();

		boolean flag = false;
		int remainNum = item.getNum() - sellNum;// 商品卖掉后的库存数量
		String sql = "select * from tb_item where id = ?";
		Object[] params = { item.getId() };

		try {
			@SuppressWarnings("deprecation")
			Item i = runner.query(conn, sql, params, new BeanHandler<>(Item.class));
			if (i != null) {
				if (remainNum < i.getStoreNum()) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 根据商品名模糊查询到同类商品销售量为前limit的商品
	 * 
	 * @param title
	 * @param limit
	 * @return
	 */
	public List<Item> findCategoryItem(String type,String title, int limit) {
		getConnection();
		List<Item> list = null;
		String sql = "select i.*,sum(e.num) s from tb_item i ,tb_exchange e " + "where i.id=e.itemId and i.type = "
				+ "(select type from tb_item where 1=1 ";
		if(title!=null&&!title.equals("")){
			sql +=" and title like '%"+title+"%' or type like '%"+title+"%' ";
			if(type!=null&&!type.equals(""))
				sql += " or type like '%"+type+"%' ";
		}else{
			sql += " and type like '%"+type+"%' ";
		}
		sql += " limit 0,1) group by i.title order by s desc limit 0,"+limit;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		return list;
	}

	/**
	 * 根据一定字段排序获取值，模糊查询，根据销量或价格排序
	 * 
	 * @param field
	 *            字段
	 * @param value
	 *            字段值
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            页面尺寸
	 * @param low high
	 *            价格区间
	 * @param order
	 *            0代表升序，1代表降序
	 * @return
	 */
	public HashMap<String, Object> findAllOrderBy(String field, String value, int page, int pageSize, double low,double high,
			String orderBy ,int order) {
		getConnection();
		List<Item> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startCount = (page - 1) * pageSize;
		try {
			String sale="";
			String search = "";
			String lowPrice="";
			String highPrice = "";
			String orderBySearch="";
			String orderSearch = "";
			String sqlCount = "select count(1) from tb_item it ";
			String limit = "";
			sale = " left join (select ex.itemId iid,sum(ex.num) n from tb_exchange ex where TO_DAYS(NOW()) - TO_DAYS(ex.time) <= 30 group by ex.itemId) f on it.id=f.iid ";
			sqlCount += sale + "where 1=1 ";
			if(value!=null&&!value.equals("")){
				search += " and it.title like '%"+value+"%' ";
				search += " or it.type like '%"+value+"%' ";
			}
			if(field!=null&&!field.equals("")){
				search +=" and it.type like '%"+field+"%' ";
			}
			if(low!=0)
				lowPrice += " and it.price >= " +low;
			if(high!=0)
				highPrice +=  " and it.price<= " +high;
			sqlCount += search + lowPrice + highPrice;

			Object[] row = runner.query(conn, sqlCount, new ArrayHandler());
			long total = (long) row[0];
			map.put("total", total);
			if (total > 0) {
				String sql = "select it.id,it.title,it.type,it.price,it.mainPic,it.info,it.state,it.num,it.storeNum,it.hot,f.n from tb_item it ";
				sql += sale + "where 1=1 ";
				if(orderBy!=null){
					if(orderBy.equals("sale"))
						orderBySearch = " f.n ";
					else
						orderBySearch = " it.price ";
					if(order==1)
						orderSearch = " desc, ";// 查询记录总数量
					if(order == 0)
						orderSearch = " asc, ";
				}
				limit = " limit " + startCount +"," + pageSize ;
				sql += search + lowPrice + highPrice +" order by " +orderBySearch + orderSearch + "it.id desc" + limit;
				list = runner.query(conn, sql, new BeanListHandler<>(Item.class));
				map.put("rows", list);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 更新热门商品和上下架状态
	 * @param id
	 * @param field
	 * @param value
	 * @author hunterzhou
	 */
	public boolean update(int id,String field,String value){
		getConnection();
		boolean flag = false;
		String sql = "update tb_item set "+field+"=? where id = ?";
		Object[] params = {value,id};
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
	

	/**查找订单商品
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Item> findByItemId(int id) {
		getConnection();
		List<Item> list = null;
		String sql = "select * from tb_item where id = ?";
		Object[] params = { id };
		try {
			list = runner.query(conn, sql, params, new BeanListHandler<Item>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查找前10的流行商品
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<HashMap<String, Object>> findPop() {
		@SuppressWarnings("unused")
		String where = "";
		getConnection();
		List<PopItem> list = null;
		
		List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
		try {
			String sql = "select i.title,q.sum from tb_item i "
					+ "right join(select e.itemId id,sum(e.num) sum from tb_exchange e group by itemId limit 0,10) q "
					+ "on i.id=q.id order by q.sum desc";// 查询所有的记录
			System.out.println(sql);

			list = runner.query(conn, sql, new BeanListHandler<>(PopItem.class));
			int size = list.size();
			for (int i = 0; i < size; i++) {
				String title = list.get(i).getTitle();
				int sum = list.get(i).getSum();
				//新建一个map，不然会出错
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("sum", sum);
				map.put("title", title);				
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
	 * 更新库存数量
	 * @param item
	 * @return
	 */
	public boolean updateNum(Item item) {
		getConnection();

		boolean flag = false;
		String sql = "update tb_item set num=? where id = ?";
		Object[] params = {item.getNum(), item.getId() };
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

	/**
	 * 猜用户的喜欢商品
	 * @param uid
	 * @param limit
	 * @return
	 */
	public List<Item> findLikeItem(int uid,int limit) {
		String where = "";
		getConnection();
		List<Item> list = null;
		try {
			String sql = "select i.id,i.title,i.price,i.mainPic,i.info,q.sum from tb_item i "
					+ "right join(select uid,itemId,price,time,sum(num) sum from tb_exchange "
					+ "where 1=1 and date_sub(curdate(),interval 1 month)<=time ";// 查询所有的记录
			//判断该用户是否有兑换记录
			if(exchangeDAO.findByUid(uid)!=null){
				System.out.println("Y");
				where += "and uid=" + uid + " group by itemId)q on i.id=q.itemId "
						+ "order by q.sum desc limit 0," + limit +"";
				sql += where;
			}else{
				System.out.println("N");
				where += " group by itemId)q on i.id=q.itemId "
						+ "order by q.sum desc limit 0," + limit +"";
				sql += where;
			}
			
			list = runner.query(conn, sql, new BeanListHandler<>(Item.class));
			System.out.println("1:"+list.size());
			//获取的数量小于要展示的数据
			if(list.size() < limit){
				System.out.println("YY");
				String type = list.get(0).getType();//以 第一个的类型进行查询作为可能喜欢的商品
				if(type != null && !type.equals("")){
					sql = "select i.id,i.title,i.price,i.mainPic,i.type,i.info,sum(e.num) sum "
							+ "from tb_exchange e,tb_item i "
							+ "where e.itemId=i.id and date_sub(curdate(),interval 1 month)<e.time "
							+ "and type='" + type + "' group by i.title order by sum desc limit 0," + limit + "";
					list = runner.query(conn, sql, new BeanListHandler<>(Item.class));
					System.out.println("2:"+list.size());
				}else{
					System.out.println("该类型不存在吗！！！");
				}
			}
			System.out.println(sql);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 处理商品信息及与商品图片的同步处理的事物
	 * @param item 商品
	 * @param ItemPicList 商品对应的图片信息
	 */
	public void itemInput(Item item, List<ItemPic> ItemPicList) {
		getConnection();
		try {
			conn.setAutoCommit(false);//设置不自动提交事物
			//添加一条商品记录
			String sql = "insert into tb_item(title,type,price,mainPic,info,state,num,storeNum,hot) values(?,?,?,?,?,?,?,?,?)";
			Object[] params = { item.getTitle(), item.getType(), item.getPrice(), item.getMainPic(), item.getInfo(),
					item.getState(), item.getNum(), item.getStoreNum(),item.getHot()};
			int rows1 = runner.update(conn, sql, params);
			if(rows1 > 0){
				System.out.println("rows1:"+rows1);
			}
			//获取商品的信息的最大ID
			int itemPicId = runner.query(conn,"select max(id) from tb_item",new ScalarHandler<Integer>());
			System.out.println("itemPicId:"+itemPicId);
			//将商品的图片信息加入到商品图表中
			for (ItemPic itemPic : ItemPicList) {
				sql = "insert into tb_itemPic(itemId,url) values(?,?)";
				Object[] param = {itemPicId,itemPic.getUrl()};
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
				System.out.println("商品信息出错！");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据商品id获取同类型的商品
	 * @param itemId
	 * @param limit
	 * @return
	 */
	public List<Item> findRecommentItem(int itemId, int limit) {
		getConnection();
		List<Item> list = null;
		String sql = "select i.*,sum(e.num) sum from tb_item i ,tb_exchange e "
				+ "where i.id=e.itemId and i.type = (select type from tb_item where id=" + itemId + ") "
				+ "group by i.id order by sum desc limit 0," + limit + "";
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(Item.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		return list;
	}
	public static void main(String[] args) {
		ItemDAO d = new ItemDAO();
		System.out.println(d.findRecommentItem(1, 2));
	}
}

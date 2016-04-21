package com.eshop.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.bean.Item;
import com.eshop.bean.ItemPic;
import com.eshop.bean.Log;
import com.eshop.model.Impl.CommentDAO;
import com.eshop.model.Impl.ItemDAO;
import com.eshop.model.Impl.ItemPicDAO;
import com.eshop.model.Impl.LogDAO;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ItemServlet extends HttpServlet{
	Item item1 = new Item();
	String message = "";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		Gson gson = new Gson();
		int page;
		int rows;
		int id;
		int uid=-1;
		String field = null;
		String value = null;
		ItemDAO itemDao = new ItemDAO();
		
		if(session.getAttribute("uid")!=null && !session.getAttribute("uid").equals("")){
			uid=(int) session.getAttribute("uid");
		}
		System.out.println("看看"+request.getParameter("action"));
		switch (request.getParameter("action")){
		case "item_list" :
			if(request.getParameter("search")!=null&& !request.getParameter("search").equals("")){
				String[] obj = request.getParameter("search").split("@@@");
				field = obj[0];
				if(obj.length==1){
					value = null;
					field= null;
				}else{
					value = obj[1];
				}
			}
			if(request.getParameter("offset")==null){
				page = 1;
			}else{
				page = Integer.parseInt(request.getParameter("offset"));
			}
			if(request.getParameter("limit")==null){
				rows = 10;
			}else{
				rows = Integer.parseInt(request.getParameter("limit"));
			}
			if (page != 0) {// 获取页数
				page = page / rows;
			}
			page+=1;
			HashMap<String, Object> items = new HashMap<String, Object>();
			items = itemDao.findAll(field,value,page,rows);
			String list = gson.toJson(items);
			out.print(list);
			break;
		case "changeHot":
			String hot = null;
			id = -1;
			if(request.getParameter("hot")!=null && !request.getParameter("hot").equals("")){
				hot = request.getParameter("hot");
			}
			if(request.getParameter("id")!=null && !request.getParameter("id").equals("")){
				id = Integer.parseInt(request.getParameter("id"));
			}
			if(hot!=null && id!=-1){
				itemDao.update(id,"hot",hot);
				Log log = new Log(uid,"更新了热门商品",String.valueOf(id));
				LogDAO logDao = new LogDAO();
				logDao.save(log);
			}
			break;
		case "changeState":
			String[] ids=null;
			String state = null;
			if(request.getParameter("ids")!=null && !request.getParameter("ids").equals("")){
				ids = request.getParameter("ids").split(",");
			}
			if(request.getParameter("state")!=null && !request.getParameter("state").equals("")){
				state = request.getParameter("state");
			}
			if(ids!=null && state!=null){
				for(int i=0;i<ids.length;i++){
					itemDao.update(Integer.parseInt(ids[i]), "state", state);
					Log log = new Log(uid,"更新了商品上下架状态",String.valueOf(ids[i]));
					LogDAO logDao = new LogDAO();
					logDao.save(log);
				}
			}
			break;
		case "item_edit":
			id=-1;
			if(request.getParameter("field")!=null && !request.getParameter("field").equals("")){
				field = request.getParameter("field");
			}
			if(request.getParameter("value")!=null && !request.getParameter("value").equals("")){
				value = request.getParameter("value");
			}
			if(request.getParameter("id")!=null && !request.getParameter("id").equals("")){
				id = Integer.parseInt(request.getParameter("id"));
			}
			if(field!=null && value!=null && id!=-1){
				itemDao.update(id, field, value);
				Log log = new Log(uid,"更新商品信息",String.valueOf(id));
				LogDAO logDao = new LogDAO();
				logDao.save(log);
			}
			break;
		case "info_edit":
			item1=null;
			if(request.getParameter("id")!=null && !request.getParameter("id").equals("")){
				id = Integer.parseInt(request.getParameter("id"));
				item1 = itemDao.findBy(id);
			}
			String edit = gson.toJson(item1); 
			out.print(edit);
			break;
		case "item_add":
			String itemTitle = null;
			if(request.getParameter("title")!=null && !request.getParameter("title").equals("")){
				itemTitle = request.getParameter("title");
			}
			String info = null;
			if(request.getParameter("info")!=null && !request.getParameter("info").equals("")){
				info = request.getParameter("info");
			}
			String mainUrl = null;
			if(request.getAttribute("mainUrl")!=null && !request.getAttribute("mainUrl").equals("")){
				mainUrl = (String) request.getAttribute("mainUrl");
			}
			String type = null;
			if(request.getParameter("type")!=null && !request.getParameter("type").equals("")){
				type = request.getParameter("type");
			}
			int price = 1000000;
			if(request.getParameter("price")!=null && !request.getParameter("price").equals("")){
				price = Integer.parseInt(request.getParameter("price"));
			}
			int num = 0;
			if(request.getParameter("num")!=null && !request.getParameter("num").equals("")){
				num = Integer.parseInt(request.getParameter("num"));
			}
			int storeNum = 0;
			if(request.getParameter("storeNum")!=null && !request.getParameter("storeNum").equals("")){
				storeNum = Integer.parseInt(request.getParameter("storeNum"));
			}
			if(itemTitle!=null && info!=null && mainUrl!=null && type!=null){
				Item itemInfo = new Item();
				itemInfo.setTitle(itemTitle);
				itemInfo.setInfo(info);
				itemInfo.setMainPic(mainUrl);
				itemInfo.setType(type);
				itemInfo.setPrice(price);
				itemInfo.setNum(num);
				itemInfo.setStoreNum(storeNum);
				itemInfo.setHot(0);
				itemInfo.setState(0);
				if(itemDao.save(itemInfo)){
					Log log = new Log(uid,"添加了商品",String.valueOf("1000000"));
					LogDAO logDao = new LogDAO();
					logDao.save(log);
				}
			}
			
			boolean flag=false;
			ItemPic pic = new ItemPic();
			ItemPicDAO picDao = new ItemPicDAO();
			String[] urls = (String[]) request.getAttribute("url");
			for(String url:urls){
				pic.setUrl(url);
				pic.setItemId(1000000);
				boolean b = picDao.save(pic);
				pic.setUrl(url+"_95×100.jpg");
				pic.setItemId(1000000);
				boolean b1 = picDao.save(pic);
				pic.setUrl(url+"_359×351.jpg");
				pic.setItemId(1000000);
				boolean b2 = picDao.save(pic);
				pic.setUrl(url+"_191×157.jpg");
				pic.setItemId(1000000);
				boolean b3 = picDao.save(pic);
				if(b&&b1&&b2&&b3){
					flag=true;
				}else{
					flag=false;
				}
			}
			if(flag){
				out.print(true);
			}else{
				out.print(false);
			}
			break;
		case "hotItem"://热门商品
			List<Item> hotItems = itemDao.hotExchangeItem();
			String hotlist = gson.toJson(hotItems);
			out.print(hotlist);
			break;
		case "recommen"://首页推荐
			List<Item> recommen = itemDao.recommendItem();
			String recommenlist = gson.toJson(recommen);
			out.print(recommenlist);
			break;
		case "findAll"://查询所有商品
			//page = Integer.parseInt(request.getParameter("page"));
			//rows = Integer.parseInt(request.getParameter("pageSize"));
			page = 1;
			rows = 8;
			HashMap<String, Object> itemsAll = itemDao.findAll(null, null, page, rows);
			String AllList = gson.toJson(itemsAll);
			out.print(AllList);
			break;
		case "drink"://查询所有饮料
			//page = Integer.parseInt(request.getParameter("page"));
			//rows = Integer.parseInt(request.getParameter("pageSize"));
			page = 1;
			rows = 8;
			HashMap<String, Object> drink = itemDao.findAll("type","运动用品" , page, rows);
			String drinks = gson.toJson(drink);
			out.print(drinks);
			break;
		case "Digital"://查询所有数码产品
			//page = Integer.parseInt(request.getParameter("page"));
			//rows = Integer.parseInt(request.getParameter("pageSize"));
			page = 1;
			rows = 8;
			HashMap<String, Object> Digital = itemDao.findAll("type","数码3C" , page, rows);
			String Digitals = gson.toJson(Digital);
			out.print(Digitals);
			break;
		case "goods"://查询所有生活用品
			//page = Integer.parseInt(request.getParameter("page"));
			//rows = Integer.parseInt(request.getParameter("pageSize"));
			page = 1;
			rows = 8;
			HashMap<String, Object> goods = itemDao.findAll("type","生活用品" , page, rows);
			String goodss = gson.toJson(goods);
			out.print(goodss);
			break;
		case "findOne"://点击进入某个商品
			int id1 = Integer.parseInt(request.getParameter("id"));
			String item = gson.toJson(itemDao.findBy(id1));
			out.print(item);
			break;
		case "getComment"://获取评论
			int idForComment = 1;
			int pageForComment=1;
			try{
				idForComment = Integer.parseInt(request.getParameter("idForComment"));
			}catch (NumberFormatException e){
				
			}
			try{
				pageForComment = Integer.parseInt(request.getParameter("pageForComment"));
			}catch(NumberFormatException e){
				pageForComment =1;
			}
			HashMap<String, Object> comm = new CommentDAO().findComment(idForComment, pageForComment, 6);
			String comms =  gson.toJson(comm);
			System.out.println(comms);
			out.print(comms);
			break;
		case "exchange":
			int id3 = Integer.parseInt(request.getParameter("id"));
			int purNum =Integer.parseInt(request.getParameter("num"));
			item1 = itemDao.findBy(id3);
			if(item1.getNum()<purNum){
				message ="{\"flag\":\"false\"}";
				out.print(message);
			}else{
				int nowNum = item1.getNum()-purNum;
				item1.setNum(nowNum);
				item1.setId(id3);
				itemDao.updateNum(item1);
				message ="{\"flag\":\"true\",\"num\":\"nowNum\"}";
				out.print(message);
			}
			break;
		case "search"://搜索商品
			String value1 = null;
			value1 = request.getParameter("value");
			if(value1!=null){
				value1 = value1.trim();
			}
			String type1 = null;
			type1 = request.getParameter("type");
			if(type1!=null){
				type1 = type1.trim();
			}
			double high=0;
			double low= 0;
			/*if(type1!=null&&type1.equals("")){
				type1=null;
			}*/
			try{
				page = Integer.parseInt(request.getParameter("page"));
			}catch(NumberFormatException e){
				page=1;
			}
			try{
				low = Integer.parseInt(request.getParameter("low"));
			}catch(NumberFormatException e){
				low = 0;
			}
			try{
				high = Integer.parseInt(request.getParameter("high"));
			}catch(NumberFormatException e){
				high = 0;
			}
			int order = 0;
			try{
				order = Integer.parseInt(request.getParameter("order"));
			}catch(NumberFormatException e){
			}
			String orderBy = request.getParameter("orderBy");
			/*rows = Integer.parseInt(request.getParameter("pageSize"));*/
			rows=12;
			String searchList="";
			if(order==0||order==1){
				HashMap<String, Object> search = itemDao.findAllOrderBy(type1, value1, page, rows, low, high,orderBy, order);
				searchList = gson.toJson(search);
				out.print(searchList);
			}
			break;
		case "CategoryRecommend":
			String val = request.getParameter("valRecommend");
			String typ = request.getParameter("typeRecommend");
			int limit = 3;
			List<Item> cateRecItem = itemDao.findCategoryItem(typ,val, limit);
			String cateRecItems = gson.toJson(cateRecItem);
			out.println(cateRecItems);
			break;
			
		case "purChase":
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			List<Item> purItem = itemDao.findByItemId(itemId);
			String purChase = gson.toJson(purItem);
			out.print(purChase);
			break;
		case "itemInfo":
			int itemInfoId = 0;
			try{
				itemInfoId = Integer.parseInt(request.getParameter("itemid"));
			}catch(NumberFormatException e){
				System.out.println("error");
			};
			List<ItemPic> detilsList = new ItemPicDAO().findByItemId(itemInfoId); 
			String detilsLists = gson.toJson(detilsList);
			out.print(detilsLists);
			break;
		case "like":
			int uidLike = (int) session.getAttribute("uid");
			int limitLike = 8;
			List<Item> like = itemDao.findLikeItem(uidLike, limitLike);
			String likes = gson.toJson(like);
			out.print(likes);
			break;
		case "recommendId":
			
			int recommendId =1;
			try{
				recommendId = Integer.valueOf(request.getParameter("recommendId"));
			}catch(NumberFormatException e){
				System.out.println("error");
			}
			
			int recommenLimit = 3;
			List<Item> recom = itemDao.findRecommentItem(recommendId, recommenLimit);
			String recoms = gson.toJson(recom);
			out.print(recoms);
		default:
			break;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

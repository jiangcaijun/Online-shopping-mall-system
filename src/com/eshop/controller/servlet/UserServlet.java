package com.eshop.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.bean.Comment;
import com.eshop.bean.CommentImg;
import com.eshop.bean.Device;
import com.eshop.bean.Imei;
import com.eshop.bean.Exchange;
import com.eshop.bean.Item;
import com.eshop.bean.Log;
import com.eshop.bean.NewAddress;
import com.eshop.bean.User;
import com.eshop.model.Impl.CommentDAO;
import com.eshop.model.Impl.CommentImgDAO;
import com.eshop.model.Impl.DeviceDAO;
import com.eshop.model.Impl.ImeiDAO;
import com.eshop.model.Impl.ExchangeDAO;
import com.eshop.model.Impl.ItemDAO;
import com.eshop.model.Impl.LogDAO;
import com.eshop.model.Impl.NewAddressDAO;
import com.eshop.model.Impl.UserDAO;
import com.eshop.model.Impl.VerifyCodeDAO;
import com.eshop.utils.MD5Util;
import com.eshop.utils.SendEmilToSys;
import com.eshop.utils.SendSms;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User(); //变为全局
	NewAddress newAddress = new NewAddress();
	UserDAO userDao = new UserDAO();
	NewAddressDAO newAddressDAO = new NewAddressDAO();
	Item item = new Item();
	ItemDAO Id = new ItemDAO();
	Exchange ec = new Exchange();
	ExchangeDAO ed = new ExchangeDAO();
	NewAddressDAO nad = new NewAddressDAO();
	NewAddress na = new NewAddress();
	DeviceDAO deviceDAO = new DeviceDAO();
	ImeiDAO imeiDAO = new ImeiDAO();
	Comment com = new Comment();
	CommentDAO comDao = new CommentDAO();
	CommentImg comI = new CommentImg();
	CommentImgDAO comIdao = new CommentImgDAO();
	String []url;
	String []comUrl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	int expiry = -1;
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String message = null;
		String message1 = null;
		String action = request.getParameter("action")==null?"info":request.getParameter("action");
		//System.out.println(request.getParameter("action"));
		//hunterzhou添加
		Gson gson = new Gson();
		int page;
		int rows;
		@SuppressWarnings("unused")
		int id;
		String field = null;
		String value = null;
		boolean flag = false;
		VerifyCodeDAO codeDao = new VerifyCodeDAO();
		int uid = -1;
		if(session.getAttribute("uid")!=null && !session.getAttribute("uid").equals("")){
			uid=(int) session.getAttribute("uid");
		}
		
		switch (action) {
		case "login":
			if(request.getParameter("identifyCode")!=null && !request.getParameter("identifyCode").equals("")) {
				if(!request.getParameter("identifyCode").equals((String)session.getAttribute("identifyCode"))) {
					message = "验证码错误";
				} else {
					user.setUsername(request.getParameter("username"));
					user.setPassword(MD5Util.MD5(request.getParameter("password")));
					User u = userDao.login(user);
					if(u!=null && u.getUsername()!=null){
						boolean check = userDao.checkAdmin(request.getParameter("username"));
						if(check){
							session.setAttribute("admin", u);
							session.setAttribute("adminId", u.getId());
							session.setAttribute("adminName", u.getUsername());
						}
						session.setAttribute("user", u);
						session.setAttribute("uid", u.getId());
						session.setAttribute("username", u.getUsername());
						if(request.getParameter("autoLogin")!=null && request.getParameter("autoLogin").equals("1")) {
							Cookie cookieCustomName = new Cookie("username", u.getUsername());
							Cookie cookieCustomPswd = new Cookie("password", u.getPassword());
							cookieCustomName.setMaxAge(expiry);
							cookieCustomPswd.setMaxAge(expiry);
							//cookie1.setDomain("tao.com");//设置这个可以实现跨域访问
							//cookie2.setDomain("tao.net");
							cookieCustomName.setPath("/");//设置path可以实现该路径下的页面都可以访问cookie，默认路径是生成cookie的路径才能访问
							cookieCustomPswd.setPath("/");
							response.addCookie(cookieCustomName);
							response.addCookie(cookieCustomPswd);
						} else {
							Cookie[] cookies = request.getCookies();
							if(cookies!=null && cookies.length>0) {
								for(Cookie c:cookies){
									c.setMaxAge(0);//移除cookie
									if(c.getName().equals("username") || (c.getName().equals("password"))) {
										//保留jSESSIONID这个cookie，才能让session找到那台客户端。否则会造成session在生命周期内失效
										response.addCookie(c);
									}
								}
							}
						}
						response.sendRedirect("/homePage.jsp");return;
					} else {
						message = "请输入正确的登陆信息";
					}
				}
			} else {
				message = "请输入验证码";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			break;
		case "reg":
			//System.out.println("进来了");
			if(request.getParameter("identifyCode")!=null && !request.getParameter("identifyCode").equals("")) {
				if(!request.getParameter("identifyCode").equals((String)session.getAttribute("identifyCode"))) {
					message = "{\"message\":\"false\"}";
					out.print(message);
				}else{
					
					user.setUsername(request.getParameter("username"));
					user.setPassword(MD5Util.MD5(request.getParameter("password1")));
					user.setAuth(0);
					user.setCredit(0);
					flag= userDao.save(user);
					if(flag==true){
						session.setAttribute("usernameZc", request.getParameter("username"));
						message="{\"flag\":\"true\"}";
						out.print(message);
					}
				}
			}
			break;
	
		case "checkOldPw":
			//System.out.println("1111");
			String oldPw = request.getParameter("oldPw");
			String userName="king";   //以后用session代替
			user.setUsername(userName);
			user = userDao.findByName(user);
			if(!oldPw.equals(user.getPassword())){
				message ="{\"flag\":\"false\"}";
				out.print(message);
			}else{
				message ="{\"flag\":\"true\"}";
				out.print(message);
			}
			break;
			
		case "change":
			//System.out.println("进来了");
			user.setPassword(request.getParameter("pw2"));
			uid = (int) session.getAttribute("uid");/*(int) session.getAttribute("uid");*/
			boolean flag1 = userDao.change(user, uid);
			if(flag1 == true){
				message="{\"message\":\"修改成功\"}";
				out.print(message);
			}
			break;
			
		case "newAddress":
			//System.out.println("进来了");
			newAddress.setGainName(request.getParameter("newName"));
			
			newAddress.setPostcode(Integer.parseInt(request.getParameter("newPostCode")));
			
			newAddress.setTel(request.getParameter("newTel"));
			newAddress.setAddress(request.getParameter("newAdd"));
			newAddress.setCity(request.getParameter("newCity"));
			newAddress.setStatus(0);
			uid = (int) session.getAttribute("uid");;//(int) session.getAttribute("uid");
			newAddress.setUid(uid);
			flag = newAddressDAO.save(newAddress);
			System.out.println(flag);
			if(flag == true){
				message="{\"message\":\"添加地址成功\",\"flag\":\"true\"}";
				out.print(message); 
			}
			break;
			
		/*case "newAddress_1":                    //对应address.js中邮编没有填写的情况
			newAddress.setGainName(request.getParameter("gainName"));
			newAddress.setTel(request.getParameter("tel"));
			newAddress.setAddress(request.getParameter("address"));
			newAddress.setCity("河南省");
			newAddress.setStatus(0);
			uid = 1;(int) session.getAttribute("uid");
			newAddress.setUid(uid);
			newAddress.setPostcode("");
			
			flag = newAddressDAO.save(newAddress);
			if(flag == true){
				message="{\"message\":\"添加地址成功\"}";
				out.print(message); 
			}
			break;*/
		
		case "findPw":
			
			if(request.getParameter("identifyCode")!=null && !request.getParameter("identifyCode").equals("")) {
				if (!request.getParameter("identifyCode").equals((String) session.getAttribute("identifyCode"))) {
					message1 = "验证码错误";
					request.setAttribute("message1", message1);
					request.getRequestDispatcher("findPw_1.jsp").forward(request, response);
				
				} else {
					String username = request.getParameter("userid");
					//System.out.println(username);
					user.setUsername(username);
					User u1 = userDao.findByName(user);
					//System.out.println(u1);
					if (u1!=null) {
						session.setAttribute("email", u1.getEmail());
						session.setAttribute("uid1", u1.getId());
						session.setAttribute("phone", u1.getTel());
						message = username;
						request.setAttribute("message", message);
						request.getRequestDispatcher("findPw_2.jsp").forward(request, response);
					} else {
						message = "该用户名不存在";
						request.setAttribute("message", message);
						request.getRequestDispatcher("findPw_1.jsp").forward(request, response);
					}
				}
			}else{
				message = "请填写验证码";
				request.setAttribute("message", message);
				request.getRequestDispatcher("findPw_1.jsp").forward(request, response);
			}
			break;
		
		case "findName":
			user.setUsername(request.getParameter("username"));
			User user1=userDao.findByName(user);
			if(user1!=null){
				message = "该用户名已存在";
			}else{
				message = "该用户名可以使用";
			}
			out.print(message);
			break;
		case "purChase":
			//System.out.println(request.getParameter("nums"));
			int nums = Integer.parseInt(request.getParameter("nums"));
			int itemId =Integer.parseInt(request.getParameter("itemId"));
			String address = request.getParameter("address");
			na = nad.findByAdd(address);
			
			int addressId = na.getId();  //选择发货地址
			//System.out.println(addressId);
			item = Id.findBy(itemId);
			int price = item.getPrice();
			int uid3 = (int) session.getAttribute("uid");					//后期用session；
			user = userDao.findBy(uid3);
			int credit = user.getCredit();
			if((credit-(nums*price))<0){
				message="{\"flag\":\"false\"}";
				out.print(message);
			}else{
				if(item.getSum()-nums<=item.getNum()){
					new SendEmilToSys().sendMail(item.getId(), item.getTitle());
				}
				credit = credit-(nums*price);
				user.setCredit(credit);
				user.setId(uid3);
				userDao.updateCredit(user);
				ec.setItemId(itemId);
				ec.setUid(uid3);
				ec.setNum(nums);
				ec.setPrice(price);
				ec.setState(0);
				ed.save(ec);
				message="{\"flag\":\"true\",\"addressId\":"+addressId+",\"itemId\":"+itemId+"}";
				out.print(message);
			}
			break;
		case "findAddress":
			//System.out.println(session.getAttribute("uid"));
			//int uid1 = (int) session.getAttribute("uid");		//session
			int uid1 =1;
			List<NewAddress> listAll = userDao.findAll(uid1);
			String addlist = gson.toJson(listAll);
			out.print(addlist);
			break;
		case "setMainAdd":
			//System.out.println("进来了");
			int uid5 =(int) session.getAttribute("uid"); //session
			int addId = Integer.parseInt(request.getParameter("addId"));
			nad.update(uid5,0);
			boolean flag3 = nad.updateMain(addId, 1);
			//System.out.println(flag3);
			if(flag3==true){
				message="{\"message\":\"true\"}";
				out.print(message);
			}
			break;
		
		case "delAdd":
			int delId = Integer.parseInt(request.getParameter("addId"));
			boolean flag4 =nad.delete(delId);
			System.out.println(flag4);
			if(flag4==true){
				message="{\"message\":\"true\"}";
				out.print(message);
			}
			break;
			
		case "changeAdd":
			int changeId = Integer.parseInt(request.getParameter("addrId"));
			//System.out.println(changeId);
			newAddress.setGainName(request.getParameter("newName"));
			newAddress.setId(changeId);
			newAddress.setPostcode(Integer.parseInt(request.getParameter("newPostCode")));
			int uid4 = (int) session.getAttribute("uid");
			newAddress.setUid(uid4);
			newAddress.setTel(request.getParameter("newTel"));
			newAddress.setAddress(request.getParameter("newAdd"));
			newAddress.setCity(request.getParameter("newCity"));
			boolean flag5 = nad.update(newAddress);
			if(flag5==true){
				message="{\"flag\":\"true\"}";
				out.print(message);
			}
			break;
		
		case "orderList":  //兑换记录
			int uid8 = (int) session.getAttribute("uid"); 
			page = 1;
			rows = 25;
			HashMap<String, Object> exchangeAll = ed.findByUid(uid8, page, rows);
			String AllList = gson.toJson(exchangeAll);
			out.print(AllList);
			break;
		
		case "delOrder":  //删除记录
			int orderId = Integer.parseInt(request.getParameter("exId"));
			boolean flagOrder = ed.delete(orderId);
			if(flagOrder==true){
				message = "{\"flag\":\"true\"}";
				out.print(message);
			}
			break;
		
		case "editCom":
			int editId = Integer.parseInt(request.getParameter("eid"));
			HashMap<String, Object> editMesaage = ed.findByExIdO(editId);
			String mess = gson.toJson(editMesaage);
			out.print(mess);
			break;
			
		case "editComment"://评价商品
			if(session.getAttribute("commentId")!=null&&session.getAttribute("commentId")!=""){
				session.setAttribute("commentId", "");
				double star = Double.parseDouble(request.getParameter("star"));
				String text = request.getParameter("text");
				int exchangeId = Integer.parseInt(request.getParameter("exchangeId"));
				String commentId = (String) session.getAttribute("commentId");
				ec = ed.findBy(exchangeId);
				com.setId(commentId);
				com.setContent(text);
				com.setRank(star);
				com.setItemId(ec.getItemId());
				com.setUid(ec.getUid());
				boolean flagCom = comDao.save(com);
				if(flagCom==true){
					ec.setId(exchangeId);
					ec.setState(1);
					boolean flagec = ed.updateCom(ec);
					if(flagec==true){
						message = "{\"message\":\"true\"}";
						out.print(message);
					}
				}
			}else{
				double star = Double.parseDouble(request.getParameter("star"));
				String text = request.getParameter("text");
				int exchangeId = Integer.parseInt(request.getParameter("exchangeId"));
				//String commentId = (String) session.getAttribute("commentId");
				String commentId =getUuid();
				ec = ed.findBy(exchangeId);
				com.setId(commentId);
				com.setContent(text);
				com.setRank(star);
				com.setItemId(ec.getItemId());
				com.setUid(ec.getUid());
				boolean flagCom = comDao.save(com);
				if(flagCom==true){
					ec.setId(exchangeId);
					ec.setState(1);
					boolean flagec = ed.updateCom(ec);
					if(flagec==true){
						message = "{\"message\":\"true\"}";
						out.print(message);
					}
				}
			}
			
			break;
		case "clear":
			//用户没注册完成
			String username1 = (String) session.getAttribute("username");
			if(username1!=null&&username1!=""){
				userDao.delete(username1);
			}
			break;
		case "findPurItem":
			int itemId1 = Integer.parseInt(request.getParameter("itemId"));
			List<Item> list3 =  Id.findByItemId(itemId1);
			String itemPurList =  gson.toJson(list3);
			out.print(itemPurList);
			break;
		case "findPurAdd":
			int addressId1 = Integer.parseInt(request.getParameter("addressId"));
			List<NewAddress> list4 =  nad.findByAdd(addressId1);
			String addPurList =  gson.toJson(list4);
			out.print(addPurList);
			break;
		case "findEx":
			int itemId2 = Integer.parseInt(request.getParameter("itemId"));
			List<Exchange> list5 =  ed.findByExId(itemId2);
			String itemExList =  gson.toJson(list5);
			out.print(itemExList);
			break;
		case "mailBox":
			//System.out.println("进来了");
			System.out.println(request.getParameter("email"));
			user.setEmail((String)request.getParameter("email"));
			user.setUsername((String)session.getAttribute("usernameZc"));
			flag =userDao.updateEmail(user);
			System.out.println(flag);
			if(flag==true){
				message ="{\"flag\":\"true\"}";
				out.print(message);
			}
			break;
		//hunterzhou添加	
		case "user_list":
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
			int auth=-1;
			if(request.getParameter("auth")!=null && !request.getParameter("auth").equals("")){
				auth=Integer.parseInt(request.getParameter("auth"));
			}
			HashMap<String, Object> users = new HashMap<String, Object>();
			users = userDao.findAll(field,value,page,rows,auth);
			String list = gson.toJson(users);
			out.print(list);
			break;
			//hunterzhou添加
		case "changeState":
			String[] ids=null;
			String state = null;
			String type = null;
			if(request.getParameter("type")!=null && !request.getParameter("type").equals("")){
				type = request.getParameter("type");
			}
			if(request.getParameter("ids")!=null && !request.getParameter("ids").equals("")){
				ids = request.getParameter("ids").split(",");
			}
			System.out.println(request.getParameter("state"));
			if(request.getParameter("state")!=null && !request.getParameter("state").equals("")){
				state = request.getParameter("state");
			}
			if(ids!=null && state!=null && type!=null){
				for(int i=0;i<ids.length;i++){
					if(userDao.update(Integer.parseInt(ids[i]),type,Integer.parseInt(state))){
						Log log = new Log(uid,"更新了员工或客户状态",String.valueOf(ids[i]));
						LogDAO logDao = new LogDAO();
						logDao.save(log);
						out.print(true);
					}
				}
			}
			break;
		case "device":
			System.out.println(request.getParameter("action"));
			String imei = request.getParameter("imei");
			boolean flag2;
			if(imei != null && !imei.equals("")){
				Imei im = imeiDAO.findByImei(imei);
				if(im != null){
					Device d = deviceDAO.findByImei(imei);System.out.println(d);
					if(d==null){
						Device device = new Device();
//						int uid6 = 2;//用户id
						int uid6 = (int) session.getAttribute("uid");
						device.setUid(uid6);
						device.setImei(imei);
						flag2 = deviceDAO.save(device);
						if(flag2 == true){
							message = "绑定成功";
						}else{
							message = "绑定失败";
						}
					}else{
						message = "该设备已被绑定";
					}
				}else{
					message = "该设备不存在";
				}
			}
			//request.setAttribute("msg", message);
			out.print(message);
			break;
		case "checkAdmin":
			if(session.getAttribute("adminName")!=null){
				String name = String.valueOf(session.getAttribute("adminName"));
				boolean check = userDao.checkAdmin(name);
				out.print(check);
			}
			else
				out.print(false);
			break;
		case "checkLogin":
			Object name = session.getAttribute("username");
			System.out.println("验证"+name);
			if(name !=null)
				out.print(true);
			else
				out.print(false);
			break;
		case "findDevice":

			//System.out.println("进来了");

			System.out.println(request.getParameter("action"));
//			int uid2 = 1;//用户id
			System.out.println("进入findDevice");

			int uid2 = (int) session.getAttribute("uid");//用户id
			//HashMap<String,Object> map = deviceDAO.findByUid(uid2, 1, 10);
			List<Device> list6 = deviceDAO.findByUser(uid2);
			String devices = gson.toJson(list6);
			out.print(devices);
			break;
			
		case "personal":
			System.out.println("进来了");
			int uid7 = 2;//用户id
			User u = userDao.findBy(uid7);
			String userMsg = gson.toJson(u);
			out.print(userMsg);
			break;
			
		case "sendSmsCodeBind":
			
			int idBind = (int) session.getAttribute("uid");
			String smsTo = request.getParameter("smsToBind");
			String time = "30"; 
			String c3 = new SendSms().send(smsTo,time);
			codeDao.createVerifyCode(idBind, c3);
			break;
		case "verify":
			String code = request.getParameter("newCodeBind");
			int idSave = (int) session.getAttribute("uid");
			if(codeDao.verifyCode(idSave, code)){
				//System.out.println("验证通过");
				out.print("ok");
			} else {
				out.print("fail");
			}
			break;
		case "logout":
			session.setAttribute("admin", null);
			session.setAttribute("adminId", null);
			session.setAttribute("adminName", null);
			session.setAttribute("user", null);
			session.setAttribute("uid", null);
			session.setAttribute("username", null);
			response.sendRedirect("/homePage.jsp");
			break;
		case "findOne":
			int idf = (int) session.getAttribute("uid"); /*(int) session.getAttribute("uid");*/
			user  = userDao.findBy(idf);
			String userGson = gson.toJson(user);
			System.out.println(userGson);
			out.print(userGson);
			break;
		case "tel":
			String phone = request.getParameter("tel");
			int idf2 = (int) session.getAttribute("uid");
			boolean result = userDao.update("tel", phone, idf2);
			out.print(result);
			break;
		case "telVerify":
			String phone1 = request.getParameter("phoneNo");
			out.print(userDao.findByTel(phone1));
		default:
			break;
		}
		
		if(request.getAttribute("action")!=null&&request.getAttribute("action").equals("saveHead")){
			//修改头像
			//System.out.println("进入写入数据库");
			url = (String[]) request.getAttribute("url");
			for(String s:url){
				int uid9 = (int) session.getAttribute("uid");
				user.setId(uid9);
				user.setUrl(s);
				userDao.updateHead(user);
			}
			
		}else if(request.getAttribute("action")!=null&&request.getAttribute("action").equals("saveComPic")){
			comUrl = (String[])request.getAttribute("url");
			String commentId = (String) session.getAttribute("commentId");
			for(String s:comUrl){
				comI.setCommentId(commentId);
				comI.setCurl(s);
				comIdao.save(comI);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try{
			expiry = Integer.parseInt(config.getInitParameter("expiry"))*60*60*24;
		}catch(NumberFormatException e){
			expiry = -1;
		}
	}
	
	private String getUuid(){
		String s = UUID.randomUUID().toString(); 
		 return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
}

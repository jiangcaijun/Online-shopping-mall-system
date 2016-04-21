package com.eshop.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.bean.Auth;
import com.eshop.bean.Log;
import com.eshop.bean.Role;
import com.eshop.model.Impl.AuthDAO;
import com.eshop.model.Impl.LogDAO;
import com.eshop.model.Impl.RoleDAO;
import com.google.gson.Gson;

public class SysMngServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		Auth auth = new Auth();
		Role role = new Role();
		AuthDAO authDao = new AuthDAO();
		RoleDAO roleDao = new RoleDAO();
		int page;
		int rows;
		String field = null;
		String value = null;
		int uid = -1;
		if(session.getAttribute("uid")!=null && !session.getAttribute("uid").equals("")){
			uid=(int) session.getAttribute("uid");
		}
		switch (request.getParameter("action")){
		case "auth_list":
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
			HashMap<String, Object> authes = new HashMap<String, Object>();
			authes = authDao.findAll(field,value,page,rows);
			String auth_list = gson.toJson(authes);
			out.print(auth_list);
			break;
		case "role_list":
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
			HashMap<String, Object> roles = new HashMap<String, Object>();
			roles = roleDao.findAll(field,value,page,rows);
			String role_list = gson.toJson(roles);
			out.print(role_list);
			break;
		case "role_add":
			if(request.getParameter("authId")!=null && !request.getParameter("authId").equals("")){
				role.setAuth(Integer.parseInt(request.getParameter("authId")));
			}
			if(request.getParameter("uid")!=null && !request.getParameter("uid").equals("")){
				role.setUid(Integer.parseInt(request.getParameter("uid")));
			}
			if(roleDao.save(role)){
				Log log = new Log(uid,"添加了员工权限",String.valueOf(auth.getId()));
				LogDAO logDao = new LogDAO();
				logDao.save(log);
			}
			break;
		case "auth_add":
			if(request.getParameter("authId")!=null && !request.getParameter("authId").equals("")){
				auth.setAuth(Integer.parseInt(request.getParameter("authId")));
			}
			if(request.getParameter("authUrl")!=null && !request.getParameter("authUrl").equals("")){
				auth.setUrl(request.getParameter("authUrl"));
			}
			if(authDao.save(auth)){
				Log log = new Log(uid,"添加了权限",String.valueOf(auth.getId()));
				LogDAO logDao = new LogDAO();
				logDao.save(log);
			}
			break;
		case "auth_del":
			String[] ids=null;
			/*String state = null;*/
			if(request.getParameter("ids")!=null && !request.getParameter("ids").equals("")){
				ids = request.getParameter("ids").split(",");
			}
			if(ids!=null){
				for(int i=0;i<ids.length;i++){
					if(authDao.delete(Integer.parseInt(ids[i]))){
						out.print(true);
						Log log = new Log(uid,"删除了权限",String.valueOf(ids[i]));
						LogDAO logDao = new LogDAO();
						logDao.save(log);
					}else{
						out.print(false);
					}
				}
			}
			break;
		case "role_del":
			ids=null;
			/*String state = null;*/
			if(request.getParameter("ids")!=null && !request.getParameter("ids").equals("")){
				ids = request.getParameter("ids").split(",");
			}
			if(ids!=null){
				for(int i=0;i<ids.length;i++){
					if(roleDao.delete(Integer.parseInt(ids[i]))){
						out.print(true);
						Log log = new Log(uid,"删除了权限",String.valueOf(ids[i]));
						LogDAO logDao = new LogDAO();
						logDao.save(log);
					}else{
						out.print(false);
					}
				}
			}
			break;
		case "auth_url":
			if(uid!=-1){
				ArrayList<String> arr = new ArrayList<>();
				arr = (ArrayList<String>) authDao.findUserUrl(uid);
				for(int i=0;i<arr.size();i++){
					map.put("row"+i, arr.get(i));
				}
				String str = gson.toJson(map);
				out.print(str);
			}
			break;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}

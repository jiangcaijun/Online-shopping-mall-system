package com.eshop.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.model.Impl.LogDAO;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class LogServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		LogDAO logDao = new LogDAO();
		Gson gson = new Gson();
		int page;
		int rows;
		String field = null;
		String value = null;
		
		switch (request.getParameter("action")){
		case "log_list":
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
			HashMap<String, Object> logs = new HashMap<String, Object>();
			logs = logDao.findAll(field,value,page,rows);
			String auth_list = gson.toJson(logs);
			out.print(auth_list);
			break;
		}
	}
}

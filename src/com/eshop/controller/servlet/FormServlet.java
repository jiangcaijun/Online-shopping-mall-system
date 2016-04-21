package com.eshop.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.model.Impl.ExchangeDAO;
import com.eshop.model.Impl.ItemDAO;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class FormServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		ExchangeDAO exchangeDao = new ExchangeDAO();
		int page;
		int rows;
		String condition = null;
		switch (request.getParameter("action")){
		case "exchange_list" :
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
			System.out.println(request.getParameter("condition"));
			if(request.getParameter("condition")!=null && !request.getParameter("condition").equals("")){
				condition = request.getParameter("condition");
			}
			page+=1;
			if(condition!=null){
				HashMap<String, Object> exchanges = new HashMap<String, Object>();
				exchanges = exchangeDao.findByCondition(condition,page,rows);
				String list = gson.toJson(exchanges);
				out.print(list);
			}
			break;
		case "like_form":
			ArrayList<HashMap<String, Object>> list = new ArrayList<>();
			ItemDAO itemDao = new ItemDAO();
			list = (ArrayList<HashMap<String, Object>>) itemDao.findPop();
			String list1 = gson.toJson(list);
			out.print(list1);
			break;
		case "type_form":
			list = new ArrayList<>();
			exchangeDao = new ExchangeDAO();
			list = (ArrayList<HashMap<String, Object>>) exchangeDao.findByType();
			list1 = gson.toJson(list);
			out.print(list1);
			break;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}

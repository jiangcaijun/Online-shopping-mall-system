package com.eshop.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 转换工具
 * @author uname
 *
 */
public class ChangeUtils {
	/**
	 * 将list转换成set
	 * @param list
	 * @return
	 */
	public static Set<String> listToSet(List<String> list){
		Set<String> set = new HashSet<String>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			set.add(list.get(i));
		}
		return set;
	}
	
	public static List<String> setTolist(Set<String> set){
		System.out.println("ceshi:"+set.size());
		List<String> list = new ArrayList<String>();
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			String obj = it.next();
			list.add(obj);
		}
		return list;
	}
/*	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("additem.html");
		list.add("classifyform.html");
		list.add("customer.html");
		list.add("exchange.html");
		list.add("index.jsp");
		list.add("items.html");
		list.add("login.html");
		list.add("additem.html");
		list.add("classifyform.html");
		list.add("customer.html");
		list.add("exchange.html");
		list.add("index.jsp");
		list.add("items.html");
		list.add("login.html");
		System.out.println(list.size());
		Set<String> set = listToSet(list);
		System.out.println(set.size());
		//System.out.println(listToSet(list));
		System.out.println(setTolist(set));
		
	}*/
}

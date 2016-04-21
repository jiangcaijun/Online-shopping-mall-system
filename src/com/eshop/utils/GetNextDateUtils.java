package com.eshop.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 获取n天后的时间
 * @author uname
 *
 */
public class GetNextDateUtils {
	/**
	 * 获取配置后的时间
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getNextDay(Date date,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +day);//+1今天的时间加一天
		date = calendar.getTime();
		return date;
	}
}

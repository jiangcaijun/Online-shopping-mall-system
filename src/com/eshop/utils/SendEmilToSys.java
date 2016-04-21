package com.eshop.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.eshop.model.BaseDAO;
import com.eshop.model.Impl.EmailTimeDAO;

public class SendEmilToSys {
	static String AlertEmailTo;
	static int StockAlertTime;
	static {
		try {
			/**
			 * JDBC连接Oracle几种方式： 1 thin模式，这是推荐的方式，不需要安装oracle客户端 2
			 * oci模式，性能比thin更高，但是要求必须安装oracle client 3 jdbc-odbc桥模式
			 * 需要添加驱动jar包到工程，jar包在oracle的安装目录下，可自行搜索classes12.jar
			 */
			Properties pro = new Properties();
			pro.load(BaseDAO.class.getResourceAsStream("/com/eshop/config/config.properties"));
			AlertEmailTo = pro.getProperty("AlertEmailTo");
			StockAlertTime = Integer.valueOf(pro.getProperty("StockAlertTime"));
		}catch(Exception e){
			StockAlertTime=1;
			e.printStackTrace();
		}
		if(StockAlertTime<=0){
			StockAlertTime=1;
		}
	}
	public String sendMail(int id,String title) {
		String code = null;
		
		
		try {
			if(new EmailTimeDAO().sendNotice(id, StockAlertTime)){
				Properties props = new Properties();
				// 开启debug调试
				props.setProperty("mail.debug", "true");
				// 发送服务器需要身份验证
				props.setProperty("mail.smtp.auth", "true");
				// 设置邮件服务器主机名
				props.setProperty("mail.host", "smtp.163.com");
				// 发送邮件协议名称
				props.setProperty("mail.transport.protocol", "smtp");

				// 设置环境信息
				Session session = Session.getInstance(props);

				// 创建邮件对象
				Message msg = new MimeMessage(session);
				msg.setSubject("赫马积分商城库存不足提示");
				// 设置邮件内容
				String contentTem = "赫马积分商城商品库存不足，请及时更新进货。商品ID："+id+",商品名："+title+"。时间："+new Date();
				code = String.valueOf((int)(1000000 * Math.random()));
				msg.setText(contentTem + String.valueOf(code));
				// 设置发件人
				msg.setFrom(new InternetAddress("zhouguodong@163.com"));

				Transport transport = session.getTransport();
				// 连接邮件服务器
				transport.connect("smtp.163.com", "zhouguodong", "zhou19930428");
				// 发送邮件
				transport.sendMessage(msg, new Address[] { new InternetAddress(AlertEmailTo) });
				// 关闭连接
				transport.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return code;

	}

}

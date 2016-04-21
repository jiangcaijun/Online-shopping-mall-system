package com.eshop.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public String sendMail(String emailTo) {
		String code = null;
		try {
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
			msg.setSubject("赫马积分商城");
			// 设置邮件内容
			String contentTem = "你正在通过赫马积分商城找回密码：请在15分钟内输入下面的数字：";
			code = String.valueOf((int)(1000000 * Math.random()));
			msg.setText(contentTem + String.valueOf(code));
			// 设置发件人
			msg.setFrom(new InternetAddress("zhouguodong@163.com"));

			Transport transport = session.getTransport();
			// 连接邮件服务器
			transport.connect("smtp.163.com", "zhouguodong", "zhou19930428");
			// 发送邮件
			transport.sendMessage(msg, new Address[] { new InternetAddress(emailTo) });
			// 关闭连接
			transport.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return code;

	}

}

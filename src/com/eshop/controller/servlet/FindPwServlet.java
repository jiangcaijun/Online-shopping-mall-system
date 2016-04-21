package com.eshop.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.bean.User;
import com.eshop.model.Impl.UserDAO;
import com.eshop.model.Impl.VerifyCodeDAO;
import com.eshop.utils.SendEmail;
import com.eshop.utils.SendSms;


/**
 * Servlet implementation class FindPwServlet
 */
@WebServlet("/FindPwServlet")
public class FindPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	VerifyCodeDAO codeDao = new VerifyCodeDAO();
	User user = new User();
	UserDAO userDao = new UserDAO();
	String message;
	int uid3;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action = request.getParameter("action")==null?"info":request.getParameter("action");
		System.out.println(action);
		//System.out.println("123");
		if(action!=null && !action.equals("")){
			switch (action) {
			case "info"://显示输入验证码的界面
				//1、显示登录用户的手机号或者Email
				//2、转发到界面
				request.getRequestDispatcher("findPw_2.jsp").forward(request, response);
				break;
			case "verify":
				String code = request.getParameter("newCode");
				int uid1 = (int) session.getAttribute("uid1");
				if(codeDao.verifyCode(uid1, code)){
					//System.out.println("验证通过");
					request.getRequestDispatcher("findPw_3_2.jsp").forward(request, response);
				} else {
					message = "请输入正确的动态码";
					request.setAttribute("message", message);
					request.getRequestDispatcher("findPw_3_1.jsp").forward(request, response);
				}
				break;

			case "sendEmailCode"://发送邮件
				String emailTo = (String) session.getAttribute("email");
				int uid = (int) session.getAttribute("uid1");
				String c = new SendEmail().sendMail(emailTo);
				codeDao.createVerifyCode(uid, c);
				request.getRequestDispatcher("findPw_3_1.jsp").forward(request, response);
				break;
			case "sendSmsCode"://发送短信验证码
				String smsTo = (String) session.getAttribute("phone");
				int uid3 = (int) session.getAttribute("uid1");
				String time = "30"; 
				String c3 = new SendSms().send(smsTo,time);
				codeDao.createVerifyCode(uid3, c3);
				request.getRequestDispatcher("findPw_3_3.jsp").forward(request, response);
			case "changePw"://重置密码
				user.setPassword(request.getParameter("password2"));
				int uid2 = (int) session.getAttribute("uid1");
				boolean flag = userDao.change(user, uid2);
				if(flag == true){
					request.getRequestDispatcher("findPw_4.jsp").forward(request, response);
				}
				break;
			default:
				break;
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

}

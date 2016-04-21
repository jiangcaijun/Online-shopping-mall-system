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

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VerifyCodeDAO codeDao = new VerifyCodeDAO();
	User user = new User();
	UserDAO userDao = new UserDAO();
	String message;
	int uid3;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action = request.getParameter("action")==null?"info":request.getParameter("action");
		if(action!=null && !action.equals("")){
			switch (action) {
			case "sendRegCode":
				String regEmail = request.getParameter("email");
				String username = (String) session.getAttribute("usernameZc");
				user.setUsername(username);
				User ureg = userDao.findByName(user);
				if(ureg!=null){
					uid3 = ureg.getId();
					session.setAttribute("uid1", uid3);
					message ="{\"message\":\"发送成功\"}";
					out.print(message);
				}
				String c1 = new SendEmail().sendMail(regEmail);
				codeDao.createVerifyCode(uid3, c1);
				
				break;
				
			case "regVerify":
				String reCode = request.getParameter("recode");
				/*String email = request.getParameter("email");*/
				int uid3 = (int) session.getAttribute("uid1");
				if(codeDao.verifyCode(uid3, reCode)){
					message = "{\"flag\":\"true\"}";
					out.print(message);
					/*request.setAttribute("action", "mailBox");
					request.setAttribute("email", email);
					request.getRequestDispatcher("/user").forward(request, response);*/
				} else {
					message = "{\"message\":\"请输入正确的动态码\",\"flag\":\"false\"}";
					out.print(message);
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

package com.revature.liam.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.liam.services.SecurityService;
import com.revature.models.User;
import com.revature.web.objects.Message;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader read = new BufferedReader(request.getReader());
		ObjectMapper mapper = new ObjectMapper();
		LoginInfo login = mapper.readValue(read, LoginInfo.class);
		SecurityService security = new SecurityService();
		User myUser = security.login(login.getUsername(), login.getPassword());
		if(myUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("User", myUser);
			PrintWriter out = response.getWriter();
			out.println(User.marshalToJson(myUser));
		}
		else {
			response.setStatus(400);
			Message m = new Message();
			m.setMessage("Invalid Credentials");
			PrintWriter out = response.getWriter();
			out.println(Message.marshalToJson(m));
		}
	}

}

class LoginInfo {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
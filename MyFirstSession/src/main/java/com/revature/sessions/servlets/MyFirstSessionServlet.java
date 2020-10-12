package com.revature.sessions.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;;

/**
 * Servlet implementation class MyFirstSessionServlet
 */
public class MyFirstSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFirstSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("User");
		}
		if (user !=null) {
			response.getWriter().println(user.username + " is logged in!");
		}
		else {
			response.getWriter().println("no one is logged in!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User liam = new User("LNunes","Liam","Nunes");
		BufferedReader read = new BufferedReader(request.getReader());
		ObjectMapper mapper = new ObjectMapper();
		LoginInfo login = mapper.readValue(read, LoginInfo.class);
		if(liam.username.equals(login.getUserName()) && login.getPassword().equals("Password")){
			HttpSession session = request.getSession();
			session.setAttribute("User", liam);
		}
		
	}

}

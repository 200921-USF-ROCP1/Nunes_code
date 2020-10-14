package com.revature.liam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.*;
import com.revature.web.objects.Message;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				Message m = new Message();
				m.setMessage("You have successfully logged out " + user.getUserName());
				PrintWriter out = response.getWriter();
				out.println(Message.marshalToJson(m));
				session.invalidate();
			}
			else {
				response.setStatus(400);
				Message m = new Message();
				m.setMessage("There was no user logged into the session");
				PrintWriter out = response.getWriter();
				out.println(Message.marshalToJson(m));
			}
		}
		else {
			response.setStatus(400);
			Message m = new Message();
			m.setMessage("There was no user logged into the session");
			PrintWriter out = response.getWriter();
			out.println(Message.marshalToJson(m));
		}
	}

}

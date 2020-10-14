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
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		HttpSession session = request.getSession();
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				if (user.getMyRole().getRoleID() == 1) {
					BufferedReader read = new BufferedReader(request.getReader());
					ObjectMapper mapper = new ObjectMapper();
					RegisterUser register = mapper.readValue(read, RegisterUser.class);
					SecurityService security = new SecurityService();
					
					
					User myUser = security.register(register.getUserName(), register.getPassword(), 
							register.getFirstName(), register.getLastName(), register.getEmail(), 
							register.getRoleID());
					if (myUser == null){
						response.setStatus(400);
						Message m = new Message();
						m.setMessage("Invalid fields");
						PrintWriter out = response.getWriter();
						out.println(Message.marshalToJson(m));
					}
					else {
						response.setStatus(201);
						PrintWriter out = response.getWriter();
						out.println(User.marshalToJson(myUser));
					}
				}
				else {
					response.setStatus(401);
					Message m = new Message();
					m.setMessage("The requested action is not permitted");
					PrintWriter out = response.getWriter();
					out.println(Message.marshalToJson(m));
				}
			}
			else {
				response.setStatus(401);
				Message m = new Message();
				m.setMessage("The requested action is not permitted");
				PrintWriter out = response.getWriter();
				out.println(Message.marshalToJson(m));
			}
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
class RegisterUser {
	String userName; String password; String firstName; String lastName; String email; int roleID;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
}

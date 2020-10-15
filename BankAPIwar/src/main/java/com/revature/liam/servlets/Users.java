package com.revature.liam.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.liam.services.UserServices;
import com.revature.models.User;
import com.revature.web.objects.Message;

/**
 * Servlet implementation class Users
 */
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setup work
		PrintWriter out = response.getWriter();
		UserServices us = new UserServices();
		String path = request.getRequestURI();
		String[] parts = path.split("/");
		HttpSession session = request.getSession();
		User user = null;
		//check to see if user is logged in
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				if(parts.length == 4) {//get user by id if id matches id or admin or employee
					int id = Integer.parseInt(parts[3]);
					if (user.getMyRole().getRoleID() == 1 || user.getMyRole().getRoleID() == 2 ||
							user.getUserID()== id) {
						User getUser = us.getUserByID(id);
						response.setStatus(200);
						out.println(User.marshalToJson(getUser));
					}//not an employee admin or the user
					else {
						response.setStatus(401);
						Message m = new Message();
						m.setMessage("The requested action is not permitted");
						out.println(Message.marshalToJson(m));
					}
				}
				else {//print all users if person logged in is an employee or admin
					if (user.getMyRole().getRoleID() == 1 || user.getMyRole().getRoleID() == 2) {
						List<User> users = us.getAllUsers();
						response.setStatus(200);
						for(int i = 0 ; i < users.size() ; ++i) {
							out.println(User.marshalToJson(users.get(i)));
						}
					}//not an employee or admin
					else {
						response.setStatus(401);
						Message m = new Message();
						m.setMessage("The requested action is not permitted");
						out.println(Message.marshalToJson(m));
					}
				}
			}
			//if no user is logged in
			else {
				response.setStatus(401);
				Message m = new Message();
				m.setMessage("The requested action is not permitted");
				out.println(Message.marshalToJson(m));
			}
		}
		//if no user is logged in
		else {
			response.setStatus(401);
			Message m = new Message();
			m.setMessage("The requested action is not permitted");
			out.println(Message.marshalToJson(m));
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				BufferedReader read = new BufferedReader(request.getReader());
				ObjectMapper mapper = new ObjectMapper();
				TempUser tempUser = mapper.readValue(read, TempUser.class);
				if (user.getMyRole().getRoleID() == 1 || user.getUserID() == tempUser.getUserID()) {
					UserServices us = new UserServices();
					//check if it is a valid id
					if (us.getUserByID(tempUser.getUserID()) == null){
						response.setStatus(400);
						Message m = new Message();
						m.setMessage("Invalid fields");
						out.println(Message.marshalToJson(m));
					}
					else {
						//uodate user
						User updatedUser = us.updateUser(tempUser.getUserID(),
								tempUser.getUserName(), tempUser.getPassword(), 
								tempUser.getFirstName(), tempUser.getLastName(),
								tempUser.getEmail(), tempUser.getRoleID());
						response.setStatus(200);
						out.println(User.marshalToJson(updatedUser));
					}
				}
				else {
					response.setStatus(401);
					Message m = new Message();
					m.setMessage("The requested action is not permitted");
					out.println(Message.marshalToJson(m));
				}
			}
			else {
				response.setStatus(401);
				Message m = new Message();
				m.setMessage("The requested action is not permitted");
				out.println(Message.marshalToJson(m));
			}
		}
		else {
			response.setStatus(401);
			Message m = new Message();
			m.setMessage("The requested action is not permitted");
			out.println(Message.marshalToJson(m));
		}
	}

}
class TempUser{
	private int userID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int roleID;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
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
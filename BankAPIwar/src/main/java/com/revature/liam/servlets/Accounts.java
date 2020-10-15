package com.revature.liam.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.AccountDAO;
import com.revature.liam.services.AccountEditingService;
import com.revature.liam.services.AccountInformationService;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.web.objects.Message;

/**
 * Servlet implementation class Accounts
 */
public class Accounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accounts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initiate setup
		String path = request.getRequestURI();
		String[] parts = path.split("/");
		HttpSession session = request.getSession();
		User user = null;
		PrintWriter out = response.getWriter();
		AccountInformationService ais = new AccountInformationService();
		AccountDAO ad = new AccountDAO();
		
		//check to see if user is logged in
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				
				if(parts.length >= 4) {//check if the path has 4 param
					if(parts[3].equals("owner")) {//forward to servlet for getting accounts by owner
						RequestDispatcher dispatcher = getServletContext()
							      .getRequestDispatcher("/accountsByOwner");
						dispatcher.forward(request, response);
					}
					else if(parts[3].equals("status")) {
						// forward to servlet for getting by status id
						RequestDispatcher dispatcher = getServletContext()
							      .getRequestDispatcher("/AccountsByStatus");
						dispatcher.forward(request, response);
					}
					else {
						//check if account is owned by user or admin or employee then get account by number
						int id = Integer.parseInt(parts[3]);
						Account account = ais.getAccount(id);
						List<Account> accounts = ad.getAccountsByUserID(user.getUserID());
						boolean userOwns = false;
						//check if accounts owned by user includes this account
						for (int i = 0 ; i < accounts.size() ; ++i){
							if(accounts.get(i).getAccountID() == id) {
								userOwns = true;
							}
						}
						if (user.getMyRole().getRoleID() == 1 || user.getMyRole().getRoleID() == 2 || userOwns) {
							//send back account
							response.setStatus(200);
							out.println(Account.marshalToJson(account));
						}//not an employee or admin
						else {
							response.setStatus(401);
							Message m = new Message();
							m.setMessage("The requested action is not permitted");
							out.println(Message.marshalToJson(m));
						}
					}
				}
				else {//get all if no other paramas given / check if admin or employee
					if (user.getMyRole().getRoleID() == 1 || user.getMyRole().getRoleID() == 2) {
						List<Account> accounts = ais.getAllAccounts();
						response.setStatus(200);
						for(int i = 0 ; i < accounts.size() ; ++i) {
							out.println(Account.marshalToJson(accounts.get(i)));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getRequestURI();
		String[] parts = path.split("/");
		HttpSession session = request.getSession();
		User user = null;
		//check to see if user is logged in
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				if(parts.length == 4) {//check if the path has 4 param
					// forward to servlet that handels money
					RequestDispatcher dispatcher = getServletContext()
						      .getRequestDispatcher("/BalanceManager");
					dispatcher.forward(request, response);
				}
				else {//check user then add an account
					BufferedReader read = new BufferedReader(request.getReader());
					ObjectMapper mapper = new ObjectMapper();
					CreateAccount createAccount = mapper.readValue(read, CreateAccount.class);
					if(user.getMyRole().getRoleID() == 1 || user.getMyRole().getRoleID() == 2 ||
							user.getUserID() == createAccount.getOwnerID()) {
						AccountEditingService aes = new AccountEditingService();
						Account newAccount = aes.submitAccount(createAccount.getBalance(), 
								createAccount.getStatusID(), createAccount.getTypeID(), createAccount.getOwnerID());
						response.setStatus(200);
						out.println(Account.marshalToJson(newAccount));
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
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		AccountEditingService aes = new AccountEditingService();
		AccountInformationService ais = new AccountInformationService();
		User user = null;
		//check to see if user is logged in
		if (session != null) {
			user = (User) session.getAttribute("User");
			if (user != null) {
				if(user.getMyRole().getRoleID() == 1) {//update account and send back
					BufferedReader read = new BufferedReader(request.getReader());
					ObjectMapper mapper = new ObjectMapper();
					UpdateAccount tempAccount = mapper.readValue(read, UpdateAccount.class);
					Account temp = ais.getAccount(tempAccount.getId());
					if(temp != null) {
						Account account = aes.updateAccount(tempAccount.getId(), 
								tempAccount.getBalance(), tempAccount.getStatusID(), tempAccount.getTypeID());
						response.setStatus(200);
						out.println(Account.marshalToJson(account));
					}
					else {
						response.setStatus(400);
						Message m = new Message();
						m.setMessage("Invalid fields");
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
}
class UpdateAccount{
	int id; float balance; int statusID; int typeID;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

}
class CreateAccount{
	float balance; int statusID; int typeID; int ownerID;

	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
}
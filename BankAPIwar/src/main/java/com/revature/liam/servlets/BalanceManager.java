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
import com.revature.liam.services.AccountInformationService;
import com.revature.liam.services.MoneyServices;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.web.objects.Message;

/**
 * Servlet implementation class BalanceManager
 */
public class BalanceManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountInformationService ais = new AccountInformationService();
		MoneyServices ms = new MoneyServices();
		PrintWriter out = response.getWriter();
		//String path = request.getRequestURI();
		String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
		String[] parts = path.split("/");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		List<Account> UsersAccounts = ais.accountsByUser(user.getUserID());
		
		
		//Separate transfer deposit and withdraw
		
		//System.out.println(parts.length);
		//System.out.println(path);
		if (parts[3].equals("withdraw")) {//withdraw
			BufferedReader read = new BufferedReader(request.getReader());
			ObjectMapper mapper = new ObjectMapper();
			BalanceChange withdraw = mapper.readValue(read, BalanceChange.class);
			
			boolean userOwns = false;
			//check if accounts owned by user includes this account
			for (int i = 0 ; i < UsersAccounts.size() ; ++i){
				if(UsersAccounts.get(i).getAccountID() == withdraw.getAccountid()) {
					userOwns = true;
				}
			}//if owned by user or user is admin continue
			if(user.getMyRole().getRoleID() == 1 || userOwns) {
				ms.withdraw(withdraw.getAccountid(), withdraw.getAmount());
				String myMessage = "$" + withdraw.getAmount() + " has been withdrawn from Account #" + withdraw.getAccountid();
				Message message = new Message();
				response.setStatus(200);
				message.setMessage(myMessage);
				out.println(Message.marshalToJson(message));
			}
			else {//error response user not permitted acess
				response.setStatus(401);
				Message m = new Message();
				m.setMessage("The requested action is not permitted");
				out.println(Message.marshalToJson(m));
			}
			
		}
		else if(parts[3].equals("deposit")) {//deposit
			BufferedReader read = new BufferedReader(request.getReader());
			ObjectMapper mapper = new ObjectMapper();
			BalanceChange deposit = mapper.readValue(read, BalanceChange.class);
			boolean userOwns = false;
			//check if accounts owned by user includes this account
			for (int i = 0 ; i < UsersAccounts.size() ; ++i){
				if(UsersAccounts.get(i).getAccountID() == deposit.getAccountid()) {
					userOwns = true;
				}
			}//if owned by user or user is admin continue
			if(user.getMyRole().getRoleID() == 1 || userOwns) {
				ms.deposit(deposit.getAccountid(), deposit.getAmount());
				String myMessage = "$" + deposit.getAmount() + " has been deposited to Account #" + deposit.getAccountid();
				Message message = new Message();
				response.setStatus(200);
				message.setMessage(myMessage);
				out.println(Message.marshalToJson(message));
			}
			else {//error response user not permitted acess
				response.setStatus(401);
				Message m = new Message();
				m.setMessage("The requested action is not permitted");
				out.println(Message.marshalToJson(m));
			}
			
		}
		else if(parts[3].equals("transfer")){//transfer
			BufferedReader read = new BufferedReader(request.getReader());
			ObjectMapper mapper = new ObjectMapper();
			Transfer transfer = mapper.readValue(read, Transfer.class);
			boolean userOwns = false;
			//check if accounts owned by user includes this account
			for (int i = 0 ; i < UsersAccounts.size() ; ++i){
				if(UsersAccounts.get(i).getAccountID() == transfer.getSourceAccountId()) {
					userOwns = true;
				}
			}//if owned by user or user is admin continue
			if(user.getMyRole().getRoleID() == 1 || userOwns) {
				ms.transfer(transfer.getSourceAccountId(), transfer.getTargetAccountId(), transfer.getAmount());
				String myMessage = "$" + transfer.getAmount() + " has been transferred from Account #" + transfer.getSourceAccountId() +
						" to Account #" + transfer.getTargetAccountId();
				Message message = new Message();
				response.setStatus(200);
				message.setMessage(myMessage);
				out.println(Message.marshalToJson(message));
			}
			else {//error response user not permitted acess
				response.setStatus(401);
				Message m = new Message();
				m.setMessage("The requested action is not permitted");
				out.println(Message.marshalToJson(m));
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
class BalanceChange{
	int accountid; float amount;

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
class Transfer{
	int sourceAccountId; int targetAccountId; float amount;

	public int getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(int sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public int getTargetAccountId() {
		return targetAccountId;
	}

	public void setTargetAccountId(int targetAccountId) {
		this.targetAccountId = targetAccountId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}

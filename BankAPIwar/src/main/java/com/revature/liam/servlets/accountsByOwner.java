package com.revature.liam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.liam.services.AccountInformationService;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.web.objects.Message;

/**
 * Servlet implementation class accountsByOwner
 */
public class accountsByOwner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accountsByOwner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
		String[] parts = path.split("/");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		PrintWriter out = response.getWriter();
		AccountInformationService ais = new AccountInformationService();
		int ownerID = Integer.parseInt(parts[4]);
		if(user.getMyRole().getRoleID() == 1 || user.getMyRole().getRoleID() == 2 || user.getUserID() == ownerID) {
			List<Account> accounts = ais.accountsByUser(ownerID);
			response.setStatus(200);
			for(int i = 0 ; i < accounts.size() ; ++i) {
				out.println(Account.marshalToJson(accounts.get(i)));
			}
		}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

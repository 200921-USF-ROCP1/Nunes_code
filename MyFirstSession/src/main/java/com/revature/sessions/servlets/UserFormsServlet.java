package com.revature.sessions.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserFormsServlet
 */
public class UserFormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFormsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		FormModel fm = null;
		if (session != null) {
			fm = (FormModel) session.getAttribute("Form");
		}
		if (fm !=null) {
			response.getWriter().println("User Name- " + fm.username + " Password- " + fm.password +" Favorite food- " + fm.food + " spoken languages- ");
			for (int i = 0 ; i<fm.languages.length ; ++i) {
				response.getWriter().println(fm.languages[i] + ", ");
			}
		}
		else {
			response.getWriter().println("No form has been submitted!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormModel model = new FormModel();
		//BufferedReader read = new BufferedReader(request.getReader());
		//ObjectMapper mapper = new ObjectMapper();
		//FormModel model = mapper.readValue(read, FormModel.class);
		model.username = request.getParameter("username");
		//response.getWriter().println(model.username);
		model.password = request.getParameter("password");	
		//response.getWriter().println(model.password);
		model.food = request.getParameter("food");
		//response.getWriter().println(model.food);
		model.languages = request.getParameterValues("language");
		//response.getWriter().println(model.languages);
		
		HttpSession session = request.getSession();
		session.setAttribute("Form", model);
	}

}

class FormModel {
	public String username;
	public String password;
	public String food;
	public String[] languages;
}




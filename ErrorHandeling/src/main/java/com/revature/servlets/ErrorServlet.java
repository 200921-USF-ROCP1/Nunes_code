  
package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    // Set response content type so it will always render to HTML
	    response.setContentType("text/html");
	    
	    // Do error stuff here!
	    //Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
		
	    
	    int errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    
	    if(errorCode == 404) {
	    	response.getWriter().println("Status code: " + errorCode + "<br />");
	    	response.getWriter().println("Message: " + request.getAttribute("javax.servlet.error.message")+ "<br />");
	    	response.getWriter().println("The page you are looking for does not exist");
	    	
	    }
	    else {
		    response.getWriter().println("Status code: " +errorCode + "<br />");
		    response.getWriter().println("Type: " + request.getAttribute("javax.servlet.error.exception_type") + "<br />");
		    response.getWriter().println("Message: " + request.getAttribute("javax.servlet.error.message"));
	    }
	    
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
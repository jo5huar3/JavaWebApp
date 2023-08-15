package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Sample servlet class for login related methods
 */

public class LoginServlet extends HttpServlet
{	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String returnString = new com.model.LogIn().validate(userName, password);
		if(returnString.isEmpty())
			returnString = "Invalid username or email";
		else
			returnString = "Welcome back " + returnString;
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<p>" + returnString + "</p>");
		out.println("<p>Working</p>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}

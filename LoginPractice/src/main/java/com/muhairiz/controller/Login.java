package com.muhairiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muhairiz.dao.EmployeeDao;
import com.muhairiz.model.Employee;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
	     PrintWriter out=response.getWriter();
		
			String username = request.getParameter("uname");
			String password = request.getParameter("pass");
		
			EmployeeDao ed=new EmployeeDao();
			try {
				Employee emp=ed.checkLogin(username, password);
				 request.getRequestDispatcher("login.jsp").include(request, response);
				 
				if(emp !=null) {
					HttpSession session =request.getSession();
					session.setAttribute("name", emp.getName());
					session.setAttribute("roll", emp.getRoll());
					
					String myRoll =(String)session.getAttribute("roll");
					
					if(myRoll.equals("teacher")) {
						response.sendRedirect("teacher/teacherIndex.jsp");
						
					}else if(myRoll.equals("staff")) {
						response.sendRedirect("staff/staffIndex.jsp");
						
					}else {
						response.sendRedirect("academic/academicIndex.jsp");
					}
					//request.getRequestDispatcher("Index.jsp").include(request, response);
					//String destPage1 ="Index.jsp";
				}else {
					out.println("invalid");
					 //out.println("<p align='center'>Invalid username or password</p>");
			            //request.getRequestDispatcher("loginForm.jsp").include(request, response);
				}
				//RequestDispatcher rd =request.getRequestDispatcher(destPage1);
				//rd.forward(request, response);
				
			}catch(SQLException e){	
				System.out.print(e);
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

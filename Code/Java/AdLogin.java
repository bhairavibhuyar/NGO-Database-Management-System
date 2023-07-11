package com.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adlogin")
public class AdLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("S_id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql:///ngo","root","Mysql@123");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM STAFF WHERE S_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// Record exists, redirect to next page
				response.sendRedirect("Admin.html");
			} else {
				// Record does not exist, display error message
				response.getWriter().write("Record not found.");
			}

			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			response.getWriter().write("An error occurred: " + ex.getMessage());
		}
	}
}

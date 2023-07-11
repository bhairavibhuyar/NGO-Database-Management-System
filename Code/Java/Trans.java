package com.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Trans")
public class Trans extends HttpServlet {

	private static final long serialVersionUID = 6L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// Connect to database and execute query
			Connection conn = DriverManager.getConnection("jdbc:mysql:///ngo","root","Mysql@123");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TRANSACTION");

			// Generate HTML output
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("<th>" + "ID" + "</th>");
			sb.append("<th>" + "Funds" + "</th>");
			sb.append("<th>" + "DoT" + "</th>");
			sb.append("<th>" + "Balance" + "</th>");
			sb.append("</tr>");
			while (rs.next()) {
				sb.append("<tr>");
				sb.append("<td>" + rs.getString("ID") + "</td>");
				sb.append("<td>" + rs.getString("Funds") + "</td>");
				sb.append("<td>" + rs.getString("DoT") + "</td>");
				sb.append("<td>" + rs.getString("Balance") + "</td>");
				sb.append("</tr>");
			}

			// Write output to response
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());

			// Close resources
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			response.getWriter().write("An error occurred: " + ex.getMessage());
		}
	}
}
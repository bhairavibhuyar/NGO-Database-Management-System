package com.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Events")
public class Events extends HttpServlet {

	private static final long serialVersionUID = 4L;

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM EVENTS");

			// Generate HTML output
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("<th>" + "E_id" + "</th>");
			sb.append("<th>" + "E_name" + "</th>");
			sb.append("<th>" + "E_date" + "</th>");
			sb.append("<th>" + "E_location" + "</th>");
			sb.append("<th>" + "Budget" + "</th>");
			sb.append("<th>" + "Status" + "</th>");
			sb.append("<th>" + "V_id" + "</th>");
			sb.append("</tr>");
			while (rs.next()) {
				sb.append("<tr>");
				sb.append("<td>" + rs.getString("E_id") + "</td>");
				sb.append("<td>" + rs.getString("E_name") + "</td>");
				sb.append("<td>" + rs.getString("E_date") + "</td>");
				sb.append("<td>" + rs.getString("E_location") + "</td>");
				sb.append("<td>" + rs.getString("Budget") + "</td>");
				sb.append("<td>" + rs.getString("Status") + "</td>");
				sb.append("<td>" + rs.getString("V_id") + "</td>");
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
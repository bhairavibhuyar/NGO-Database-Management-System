package com.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Staff")
public class Staff extends HttpServlet {

	private static final long serialVersionUID = 5L;

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM STAFF");

			// Generate HTML output
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("<th>" + "S_id" + "</th>");
			sb.append("<th>" + "S_name" + "</th>");
			sb.append("<th>" + "S_phno" + "</th>");
			sb.append("<th>" + "S_email" + "</th>");
			sb.append("<th>" + "S_addr" + "</th>");
			sb.append("<th>" + "S_DOJ" + "</th>");
			sb.append("<th>" + "S_salary" + "</th>");
			sb.append("</tr>");
			while (rs.next()) {
				sb.append("<tr>");
				sb.append("<td>" + rs.getString("S_id") + "</td>");
				sb.append("<td>" + rs.getString("S_name") + "</td>");
				sb.append("<td>" + rs.getString("S_phno") + "</td>");
				sb.append("<td>" + rs.getString("S_email") + "</td>");
				sb.append("<td>" + rs.getString("S_addr") + "</td>");
				sb.append("<td>" + rs.getString("S_DOJ") + "</td>");
				sb.append("<td>" + rs.getString("S_salary") + "</td>");
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
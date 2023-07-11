package com.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Vol")
public class Vol extends HttpServlet {

	private static final long serialVersionUID = 7L;

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM VOLUNTEER");

			// Generate HTML output
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("<th>" + "V_id" + "</th>");
			sb.append("<th>" + "V_name" + "</th>");
			sb.append("<th>" + "V_age" + "</th>");
			sb.append("<th>" + "V_gender" + "</th>");
			sb.append("<th>" + "V_phno" + "</th>");
			sb.append("<th>" + "V_email" + "</th>");
			sb.append("<th>" + "V_occupation" + "</th>");
			sb.append("</tr>");
			while (rs.next()) {
				sb.append("<tr>");
				sb.append("<td>" + rs.getString("V_id") + "</td>");
				sb.append("<td>" + rs.getString("V_name") + "</td>");
				sb.append("<td>" + rs.getString("V_age") + "</td>");
				sb.append("<td>" + rs.getString("V_gender") + "</td>");
				sb.append("<td>" + rs.getString("V_phno") + "</td>");
				sb.append("<td>" + rs.getString("V_email") + "</td>");
				sb.append("<td>" + rs.getString("V_occupation") + "</td>");
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
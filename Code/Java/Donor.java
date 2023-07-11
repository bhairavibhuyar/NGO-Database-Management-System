package com.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Donor")
public class Donor extends HttpServlet {

	private static final long serialVersionUID = 3L;

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM DONOR");

			// Generate HTML output
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("<th>" + "D_id" + "</th>");
			sb.append("<th>" + "D_name" + "</th>");
			sb.append("<th>" + "D_DOB" + "</th>");
			sb.append("<th>" + "D_Addr" + "</th>");
			sb.append("<th>" + "D_phno" + "</th>");
			sb.append("<th>" + "D_email" + "</th>");
			sb.append("<th>" + "Amount" + "</th>");
			sb.append("<th>" + "D_DOD" + "</th>");
			sb.append("</tr>");
			while (rs.next()) {
				sb.append("<tr>");
				sb.append("<td>" + rs.getString("D_id") + "</td>");
				sb.append("<td>" + rs.getString("D_name") + "</td>");
				sb.append("<td>" + rs.getString("D_DOB") + "</td>");
				sb.append("<td>" + rs.getString("D_Addr") + "</td>");
				sb.append("<td>" + rs.getString("D_phno") + "</td>");
				sb.append("<td>" + rs.getString("D_email") + "</td>");
				sb.append("<td>" + rs.getString("Amount") + "</td>");
				sb.append("<td>" + rs.getString("D_DOD") + "</td>");
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
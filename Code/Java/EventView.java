package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/eventview")
public class EventView extends HttpServlet {
	
	private static final long serialVersionUID = 2L;
	
	private static final String VIEW_QUERY="select * from eventview;";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ngo","root","Mysql@123");
                PreparedStatement ps = con.prepareStatement(VIEW_QUERY);
				){
			ResultSet rs=ps.executeQuery();
			
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("<th>" + "E_id" + "</th>");
			sb.append("<th>" + "E_name" + "</th>");
			sb.append("<th>" + "E_date" + "</th>");
			sb.append("<th>" + "E_location" + "</th>");
			sb.append("<th>" + "Status" + "</th>");
			sb.append("</tr>");
			while (rs.next()) {
				sb.append("<tr>");
				sb.append("<td>" + rs.getString("E_id") + "</td>");
				sb.append("<td>" + rs.getString("E_name") + "</td>");
				sb.append("<td>" + rs.getString("E_date") + "</td>");
				sb.append("<td>" + rs.getString("E_location") + "</td>");
				sb.append("<td>" + rs.getString("Status") + "</td>");
				sb.append("</tr>");
			}
			pw.println(sb.toString());


//            //execute the query
//            int count = ps.executeUpdate();
//
//            if(count==0) {
//                pw.println("Record Not Stored Into Database");
//            }else {
//            	res.sendRedirect("volunteer.html");
//            }
        }catch(SQLException se) {
            pw.println(se.getMessage());
            se.printStackTrace();
        }catch(Exception e) {
            pw.println(e.getMessage());
            e.printStackTrace();
        }

        pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/evententry")
public class EventEntry extends HttpServlet {

	private static final long serialVersionUID = 9L;
	
	private static final String INSERT_QUERY ="INSERT INTO EVENTS(E_id, E_name, E_date, E_location, Budget, Status, V_id) VALUES(?,?,?,?,?,?,?)";
	private static final String query = "select * from events where E_id=?" ;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		int eid = Integer.parseInt(req.getParameter("eid"));
		String name = req.getParameter("name");
		String E_date;
		String E_location;
		int Budget;
		String Status;
		int vid = Integer.parseInt(req.getParameter("vid"));

		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ngo","root","Mysql@123");
                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				PreparedStatement prep_stmt=con.prepareStatement(query);
				Statement stmt = con.createStatement();
				){
            //set the values
			
			//PreparedStatement prep_stmt=con.prepareStatement(query);
			prep_stmt.setInt(1,eid);
			ResultSet rss=prep_stmt.executeQuery();
			while(rss.next()) {
				E_date =  rss.getString("E_date");
				E_location = rss.getString("E_location");
				Budget = rss.getInt("Budget");
				Status = rss.getString("Status");
				ps.setInt(1, eid);
	            ps.setString(2, name);
	            ps.setString(3, E_date);
	            ps.setString(4, E_location);
	            ps.setInt(5, Budget);
	            ps.setString(6, Status);
	            ps.setInt(7, vid);
	            
	            

	            //execute the query
	            
			}
			int count = ps.executeUpdate();
			
	          
            if(count==0) {
                pw.println("Record Not Stored Into Database");
            }else {
                pw.println("Record Stored into Database");
            }
			
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
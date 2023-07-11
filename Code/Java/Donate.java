package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/donate")
public class Donate extends HttpServlet {
	
	private static final long serialVersionUID = 2L;
	
	private static final String INSERT_QUERY ="INSERT INTO DONOR(D_id, D_name, D_DOB, D_Addr, D_phno, D_email, Amount, D_DOD) VALUES(?,?,?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String addr = req.getParameter("addr");
		Long mob = Long.parseLong(req.getParameter("mob"));
		String email = req.getParameter("email");
		int amt = Integer.parseInt(req.getParameter("amt"));
		String dod = req.getParameter("dod");

		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ngo","root","Mysql@123");
                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				){
            //set the values
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dob);
            ps.setString(4, addr);
            ps.setLong(5, mob);
            ps.setString(6, email);
            ps.setInt(7, amt);
            ps.setString(8, dod);

            //execute the query
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

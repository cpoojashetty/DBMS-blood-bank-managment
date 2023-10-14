package a1;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst = null;

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("txt_uid");
		String name = request.getParameter("txt_uname");
		String pwd= request.getParameter("txt_pwd");
		String bloodtype= request.getParameter("txt_blood");
		int age = Integer.parseInt(request.getParameter("txt_age"));
		String phone =request.getParameter("txt_phone");
		String address = request.getParameter("txt_address");
		String sex = request.getParameter("txt_sex");
		
		
		
		try {
			pst.setString(1, uid);
			pst.setString(2, name);
			pst.setString(3, pwd);
			pst.setString(4, bloodtype);
			pst.setInt(5, age);
			pst.setString(6, phone);
			pst.setString(7, address);
			pst.setString(8, sex);
			int status=-1;
			try {
			status=pst.executeUpdate();
			}catch(Exception ex)
			{
				PrintWriter out = response.getWriter();
				out.println("<p style=color:black;font-size:18px;text-align:center>User already exits OR invalid Userid/phone number</p>");
				
				RequestDispatcher rd = request.getRequestDispatcher("signup.html");
				rd.include(request, response);
			}
			if(status>0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("cwelcome.jsp");
				rd.forward(request, response);
			}
			/*else {
				PrintWriter out = response.getWriter();
				out.println("<p style=color:black;font-size:18px;text-align:center>UNEXPECTED ERROR OCCURED!!! TRY AGAIN></p>");
				
				RequestDispatcher rd = request.getRequestDispatcher("signup.html");
				rd.include(request, response);
			}*/
			
			
			
		}catch(Exception ex) {
	                       System.out.println(ex.toString());
                  }	
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
/*/*
		try {
			pst1.setString(1, bid);
			pst1.setString(2, uid);
			pst2.setInt(1, qnt);
			pst2.setString(2,bid);
			pst2.setString(3, bloodtype);
			pst3.setString(1, bid);
			
			int status=-1, status2=-1, status3=-1;
			try {
			status=pst1.executeUpdate();
			status2=pst2.executeUpdate();
			status3=pst3.executeUpdate();
			
			}catch(Exception ex)
			{
				PrintWriter out = response.getWriter();
				out.println("<p style=color:black;font-size:18px;text-align:center>Unexpected error occured, try again!!!!</p>");
				
				RequestDispatcher rd = request.getRequestDispatcher("donar");
				rd.include(request, response);
			}
			if(status>0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("thankyou");
				rd.forward(request, response);
			}
		
	}catch(Exception ex) {
        System.out.println(ex.toString());
	}*/

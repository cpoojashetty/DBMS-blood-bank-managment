package a1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteaccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst = null;
	private static PreparedStatement pst1 = null;
	public void init(ServletConfig config) throws ServletException {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("DELETE FROM USERS WHERE U_ID=?" );
			pst1 = con.prepareStatement(" \r\n" + 
					"INSERT INTO BACK_UP(B_ID, B_NAME,B_PASS, B_BLOODTYPE, B_AGE, B_PHONE, B_ADDRESS, B_SEX) \r\n" + 
					"SELECT U_ID, U_NAME,U_PASS, U_BLOODTYPE, U_AGE, U_PHONE, U_ADDRESS, U_SEX FROM USERS WHERE U_ID=?;");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		

		
		try {
		
			pst.setString(1, uid);
			pst1.setString(1, uid);
			
			int status1=-1,status2=-1;
			

			    status1=pst1.executeUpdate();
			    status2=pst.executeUpdate();
				
			    if(status1>0 && status2>0)
			    {
			    
			    	PrintWriter out = response.getWriter();
					out.println("<p style=color:black;font-size:18px;text-align:center>ACCOUNT DELETED</p>");
			    	RequestDispatcher rd = request.getRequestDispatcher("home.html");
					rd.forward(request, response);
			    }
			    else
			    {
			    	PrintWriter out = response.getWriter();
					out.println("<p style=color:black;font-size:18px;text-align:center>Authentication Failed!!! Invalid User Name/ Password</p>");

					RequestDispatcher rd = request.getRequestDispatcher("account.html");
					rd.include(request, response);
			    }
			
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

@WebServlet("/c_pass")
public class C_Pass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst = null;
	
	public void init(ServletConfig config) throws ServletException {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("UPDATE USERS SET U_PASS = ? WHERE U_ID = ?" );
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		
		
		String npass = request.getParameter("new-password");
		String cpass = request.getParameter("confirm-password");
		System.out.println(npass);
		
		try {
			pst.setString(1, npass);
			pst.setString(2, uid);
			
			int status=-1;
			
			if(npass.equals(cpass)) {
			    status=pst.executeUpdate();
			    System.out.println(status);
				
			    if(status>0)
			    {
			    	System.out.println("if\n");
			    	PrintWriter out = response.getWriter();
					out.println("<p style=color:black;font-size:18px;text-align:center>Password Updated</p>");
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
			}else {
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

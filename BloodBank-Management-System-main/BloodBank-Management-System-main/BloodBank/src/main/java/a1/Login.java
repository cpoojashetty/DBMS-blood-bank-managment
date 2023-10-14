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


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst = null;
	
	public void init(ServletConfig config) throws ServletException {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("select * from users where U_ID=? and U_PASS=?");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the form data (username & password values)
		String uname = request.getParameter("txt_name");
		String uid = request.getParameter("txt_uid");
		String upwd = request.getParameter("txt_pwd");
		
		try {
			pst.setString(1, uid);
			pst.setString(2, upwd);
			
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				request.setAttribute("uname", uname);
				request.setAttribute("uid", uid);
				request.setAttribute("upwd", upwd);
				RequestDispatcher rd = request.getRequestDispatcher("cwelcome.jsp");
				rd.forward(request, response);
			}else {
				//not an authenticated user
				PrintWriter out = response.getWriter();
				out.println("<p style=color:black;font-size:18px;text-align:center>Authentication Failed!!! Invalid User Name/ Password</p>");
				
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
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

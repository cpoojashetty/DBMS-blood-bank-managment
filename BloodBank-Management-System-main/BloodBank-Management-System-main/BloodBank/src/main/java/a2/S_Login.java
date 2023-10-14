package a2;

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


@WebServlet("/s_login")
public class S_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst = null;
	
	public void init(ServletConfig config) throws ServletException {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("select * from staff where S_NAME=? and S_PASS=?");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sname = request.getParameter("txt_name");
		String sbid = request.getParameter("txt_id");
		String spwd = request.getParameter("txt_pwd");
		
		try {
			pst.setString(1, sname);
			pst.setString(2, spwd);
			
			
			ResultSet rs = pst.executeQuery();
			System.out.println("hg");
			
			if(rs.next()) {
				request.setAttribute("sname", sname);
				request.setAttribute("sbid", sbid);
				request.setAttribute("spwd", spwd);
				request.getRequestDispatcher("staff_welcome.jsp").forward(request, response);

			}else {
			
				PrintWriter out = response.getWriter();
				out.println("<p style=color:black;font-size:18px;text-align:center>Authentication Failed!!! Invalid User Name/ Password</p>");
				
				RequestDispatcher rd = request.getRequestDispatcher("staff_login.html");
			
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


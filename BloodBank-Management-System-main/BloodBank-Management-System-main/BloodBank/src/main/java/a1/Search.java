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

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;

	
	public void init(ServletConfig config) throws ServletException {
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("SELECT DISTINCT D_NAME,D_PHONE,D_ADDRESS FROM DONAR WHERE D_BLOODTYPE=?");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String btype = request.getParameter("btype"); 
	   // System.out.println(btype);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>"); 

		out.println("<div style='position: absolute; top: 50%; left: 60%; transform: translate(-50%, -50%);'>");
		out.println("<table style='width:800px; height:900px;'>");
		out.println("<style>");
		out.println("td { text-align: center; vertical-align: middle; }");
		out.println("</style>");
		out.println("<div style='position: relative; top: 0; left: 0; transform: translate(0, 0);'>");


		try {
		    pst.setString(1, btype);
	
		    ResultSet rs=pst.executeQuery();
		   
		    out.println("<table border=1 width=50% height=50%>");  
		    out.println("<tr><th>name</th><th>phone_no</th><th>address</th><tr>"); 
		    while(rs.next())
		    {
		        String name = rs.getString(1);  
		    	String phone =rs.getString(2);  
		        String address = rs.getString(3);  

		        out.println("<tr><td>" + name + "</td><td>" +phone+"</td><td>"+address+ "</td></tr>");  
		    } 

		    out.println("</table>");  
		   
		    out.println("</html></body>");
		    out.println("</html></body>");  

		} catch (Exception e) {
		    System.out.println(e.toString());
		}

		
		
	}	
	
	
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

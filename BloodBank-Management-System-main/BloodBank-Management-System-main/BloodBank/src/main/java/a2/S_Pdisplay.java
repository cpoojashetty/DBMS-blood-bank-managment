package a2;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







@WebServlet("/s_pdisplay")
public class S_Pdisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;
	private static PreparedStatement pst2=null;

	public void init(ServletConfig config) throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("SELECT P_NAME,P_PHONE,P_ADDRESS FROM PATIENT WHERE BA_ID=? ");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sname = (String)session.getAttribute("sname");
		String sbid = (String)session.getAttribute("sbid");
		String spwd = (String)session.getAttribute("spwd");
		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>"); 
		out.println("<table style='width:300px; height:400px; position:absolute; top:50%; left:50%; transform:translate(-50%, -50%)'>");
		out.println("<style>");
		out.println("td { text-align: center; vertical-align: middle; }");
		out.println("</style>");
	 try {
			pst.setString(1, sbid);
		
			
		ResultSet rs=pst.executeQuery();
		
		 out.println("<table border=1 width=100% height=100%>");  
         out.println("<tr><th>Name</th><th>Phone_No</th><th>Address</th><tr>"); 
		while(rs.next())
		{
			 String name = rs.getString(1);  
             String pno = rs.getString(2);  
             String address = rs.getString(3);  
            
             out.println("<tr><td>" + name + "</td><td>" + pno + "</td><td>" +address + "</td></tr>");

         }  
		
         out.println("</table>");  
         out.println("</html></body>");  
       	
		}
		
		
	 catch (Exception e) {
		System.out.println(e.toString());
		
		
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
//out.print("<a href='servlet2?uname="+n+"'>visit</a>"); 

//out.println("<tr><td>" + n + "</td><td>" + nm +"</td><td >"+"<a href=\"/BloodBank/src/main/webapp/images/bg.jpg\">view</a>"+"</td></tr>" ); 

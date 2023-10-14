package a1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/p_dis")
public class P_dis extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;
	private static PreparedStatement pst2=null;
	private static PreparedStatement pst3=null;

	public void init(ServletConfig config) throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("SELECT B.B_NAME,D.NO_DONATION FROM BLOOD_BANK B  JOIN DONAR D ON B_ID=BA_ID WHERE D_ID=?");
			pst2 = con.prepareStatement("SELECT B.B_NAME,D.NO_RECEIVE FROM BLOOD_BANK B  JOIN PATIENT D ON B_ID=BA_ID WHERE P_ID=?");
	
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String uid = (String)session.getAttribute("uid");
		
		System.out.println(uid);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>"); 
		out.println("<table style='width:100px; height:200px; position:absolute; top:50%; left:50%; transform:translate(-50%, -50%)'>");
		out.println("<style>");
		out.println("td { text-align: center; vertical-align: middle; }");
		out.println("</style>");
		
	 try {
		 pst.setString(1, uid);
		ResultSet rs=pst.executeQuery();
		
		 out.println("<table border=1 width=100% height=100%>");  
         out.println("<tr><th>Bank Name</th><th>no_DONATIONS</th><tr>"); 
		while(rs.next())
		{
			 String name = rs.getString(1);  
             String nd = rs.getString(2);  
            
             out.println("<tr><td>" + name + "</td><td>" + nd +  "</td></tr>");

         }  
		
         out.println("</table>");  
         out.println("<br>");
         out.println("<br>");
       	
		}
		
		
	 catch (Exception e) {
		System.out.println(e.toString());
		
		
	}
	 try {
		 pst2.setString(1, uid);
		ResultSet rs1=pst2.executeQuery();
		
		 out.println("<table border=1 width=100% height=100%>");  
         out.println("<tr><th>Bank Name</th><th>no_recieve</th><tr>"); 
		while(rs1.next())
		{
			 String pname = rs1.getString(1);  
             String pndd = rs1.getString(2);  
            
             out.println("<tr><td>" + pname + "</td><td>" + pndd +  "</td></tr>");

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
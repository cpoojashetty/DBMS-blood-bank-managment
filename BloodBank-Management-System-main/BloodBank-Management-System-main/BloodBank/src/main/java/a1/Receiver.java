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


@WebServlet("/receiver")
public class Receiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst1 = null;
	private static PreparedStatement pst2 = null;
	private static PreparedStatement pst3 = null;
	private static PreparedStatement pst4 = null;
	private static PreparedStatement pst5 = null;
	private static PreparedStatement pst6 = null;
	private static PreparedStatement pst7 = null;

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			 Statement st=con.createStatement();
			pst1 = con.prepareStatement(" \r\n" + 
					"INSERT INTO PATIENT(P_ID, P_NAME, P_BLOODTYPE, P_AGE, P_PHONE, P_ADDRESS, P_SEX, BA_ID) \r\n" + 
					"SELECT U_ID, U_NAME, U_BLOODTYPE, U_AGE, U_PHONE, U_ADDRESS, U_SEX, ? FROM USERS WHERE U_ID=?;");
			pst2=con.prepareStatement("\r\n" + 
					"UPDATE BLOOD SET QNT_BLOOD=QNT_BLOOD-? WHERE BA_ID=? AND BLOOD_TYPE=?; ");
			pst7=con.prepareStatement("\r\n" + 
					"SELECT QNT_BLOOD FROM BLOOD WHERE BA_ID=? AND BLOOD_TYPE=?; ");
			
			pst3=con.prepareStatement("UPDATE BLOOD_BANK SET NO_RECEIPIENTS=NO_RECEIPIENTS+1 WHERE B_ID=?;");
			pst4 = con.prepareStatement("select * from PATIENT where BA_ID=? and P_ID=?");
			pst5 = con.prepareStatement("UPDATE PATIENT SET NO_RECEIVE=NO_RECEIVE+1 WHERE P_ID=? AND BA_ID=?;");
			pst6 = con.prepareStatement("SELECT U_BLOODTYPE FROM USERS WHERE U_ID=?;");
	
			
			
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("txt_uid");
		String bid = request.getParameter("txt_bid");
		//String bloodtype= request.getParameter("txt_blood");
		int qnt = Integer.parseInt(request.getParameter("txt_qnt"));
		PrintWriter out = response.getWriter();


		try {
			pst1.setString(1, bid);
			pst1.setString(2, uid);
			pst2.setInt(1, qnt);
			pst2.setString(2,bid);
			//pst2.setString(3, bloodtype);
			pst3.setString(1, bid);
			pst4.setString(1, bid);
			pst4.setString(2, uid);
			pst5.setString(1, uid);
			pst5.setString(2, bid);
			pst6.setString(1, uid);
			pst7.setString(1,bid);
			
			ResultSet bt=pst6.executeQuery();
		
			if (bt.next()) { 
			    String value = bt.getString(1); 
			  
			    pst2.setString(3, value);
			    pst7.setString(2, value);
			}
			
		
			ResultSet bq=pst7.executeQuery();
			try {
			if (bq.next()) { 
			     int v = bq.getInt(1);
			     int n=v-qnt;
			   
			 	if(n<0) 
			  {
		
			 		throw new Exception(); 
			 		
			  }
			}
			}catch (Exception e) {
	     
				out.println("<p style=color:black;font-size:18px;text-align:center>Blood out of stock!!!!</p>");
				RequestDispatcher r = request.getRequestDispatcher("cwelcome.jsp");
				
				r.include(request, response);
				
			}
				
		
			ResultSet rs = pst4.executeQuery();
			int s1=-1,s2=-1,s3=-1;
			try {
			if(rs.next()) {
				s1=pst5.executeUpdate();
				s2=pst2.executeUpdate();
				s3=pst3.executeUpdate();
			}
			else {
				s1=pst1.executeUpdate();
				s2=pst2.executeUpdate();
				s3=pst3.executeUpdate();
			}
			}catch (Exception e) {
		
				out.println("<p style=color:black;font-size:18px;text-align:center>Unexpected error occured, try again!!!!</p>"+e);
				
				RequestDispatcher rd = request.getRequestDispatcher("login");
				rd.include(request, response);
			}
			if(s1>0 &&s2>0 &&s3>0 )
			{
				RequestDispatcher rd = request.getRequestDispatcher("wishinguwell.html");
				rd.forward(request, response);
			}
		

		}catch(Exception ex) {
	        System.out.println(ex.toString());
		}
		

		
}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

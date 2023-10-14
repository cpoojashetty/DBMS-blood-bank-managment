package a2;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s_delete")
public class S_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;
	private static PreparedStatement pst1=null;
	

	public void init(ServletConfig config) throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("UPDATE BLOOD SET QNT_BLOOD = QNT_BLOOD-? WHERE BLOOD_TYPE = ? AND BA_ID =? ");
			pst1=con.prepareStatement("\r\n" + 
					"SELECT QNT_BLOOD FROM BLOOD WHERE BA_ID=? AND BLOOD_TYPE=?; ");
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sname = (String)session.getAttribute("sname");
		String sbid = (String)session.getAttribute("sbid");
		String spwd = (String)session.getAttribute("spwd");

		int bqnt = Integer.parseInt(request.getParameter("bqnt"));
		String btype = request.getParameter("btype"); 
		PrintWriter out = response.getWriter();
		
		
		
		try {
			pst.setInt(1, bqnt);
			pst.setString(2, btype);
			pst.setString(3, sbid);
			pst1.setString(1, sbid);
			pst1.setString(2, btype);
			
			

			ResultSet bq=pst1.executeQuery();
			
			if (bq.next()) { 
			     int v = bq.getInt(1);
			     int n=v-bqnt;
			   
			 	if(n<0) 
			  {
		           System.out.println("in exception");
			 		//throw new Exception(); 
			 		out.println("<p style=color:black;font-size:18px;text-align:center>Blood out of stock!!!!</p>");
					
					RequestDispatcher r = request.getRequestDispatcher("staff_delete.jsp");
					r.include(request, response);
			 		
			  }
			}
			/*catch (Exception e) {
	       
				out.println("<p style=color:black;font-size:18px;text-align:center>Blood out of stock!!!!</p>"+e);
				
				RequestDispatcher r = request.getRequestDispatcher("staff_delete.jsp");
				r.include(request, response);
				
			}*/
				
			else {
			int status=-1;
			try {
				status=pst.executeUpdate();
				}catch(Exception ex)
				{
					
					out.println("<p style=color:black;font-size:18px;text-align:center>ERROR!!!!</p>");
					
					RequestDispatcher rd = request.getRequestDispatcher("staff_delete.jsp");
					rd.include(request, response);
				}
				if(status>0)
				{
					
					out.println("<p style=color:black;font-size:18px;text-align:center>done</p>");
					RequestDispatcher rd = request.getRequestDispatcher("staff_welcome");
					rd.forward(request, response);
				}
		}
   
		}catch(Exception ex) {
            System.out.println(ex.toString());
		}
	}
	
	
		
			

			
			
	
	
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
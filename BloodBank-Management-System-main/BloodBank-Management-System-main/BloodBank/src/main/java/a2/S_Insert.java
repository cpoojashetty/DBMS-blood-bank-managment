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

@WebServlet("/s_insert")
public class S_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;
	

	public void init(ServletConfig config) throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "");
			pst = con.prepareStatement("UPDATE BLOOD SET QNT_BLOOD = QNT_BLOOD + ? WHERE BLOOD_TYPE = ? AND BA_ID =? ");
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
		
		/*ServletContext context=getServletContext();
		String sname= (String)context.getAttribute("sname");
		String sbid=(String)context.getAttribute("sbid");
		String spwd=(String)context.getAttribute("spwd");
		int bqnt = Integer.parseInt(request.getParameter("bqnt"));
		String btype = request.getParameter("btype");*/
		
		
		
		try {
			pst.setInt(1, bqnt);
			pst.setString(2, btype);
		
			pst.setString(3, sbid);
	
			int status=-1;
			try {
				status=pst.executeUpdate();
				}catch(Exception ex)
				{
					PrintWriter out = response.getWriter();
					out.println("<p style=color:black;font-size:18px;text-align:center>ERROR!!!!</p>");
					
					RequestDispatcher rd = request.getRequestDispatcher("staff_insert.jsp");
					rd.include(request, response);
				}
				if(status>0)
				{
					PrintWriter out = response.getWriter();
					out.println("<p style=color:black;font-size:18px;text-align:center>done</p>");
					RequestDispatcher rd = request.getRequestDispatcher("staff_welcome.jsp");
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
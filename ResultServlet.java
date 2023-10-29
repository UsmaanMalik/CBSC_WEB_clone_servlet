import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
public class ResultServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter o=res.getWriter();
		o.println("<html><head><link rel='stylesheet' href='table.css'></head");
		
		try{
			
			Connection c=ConnectionProvider.getConnection();
			Statement s=c.createStatement();
			
			ResultSet rs=s.executeQuery("select * from cbse");
			
			ResultSetMetaData rsmd=rs.getMetaData();
			o.println("<body>");
			o.println("<table class='table01' border 1>");
			for(int i=1; i<=rsmd.getColumnCount(); i++){
				o.println("<th>"+rsmd.getColumnName(i)+"</th>");
			}
			while(rs.next()){
				o.println("<tr>");
				for(int i=1;i<=rsmd.getColumnCount();i++){
					o.println("<td>"+rs.getString(i)+"</td>");
					
				}
				o.println("</tr><br>");
			}
			o.println("</table>");
		}
		catch(Exception e){
			o.println("<h3>"+e+"</h3>");
		}
		finally{
			o.println("<a href='index.html'><h3>BACK</h3></a>");
		}
		o.println("</body></html>");
		
	}
}
		
		
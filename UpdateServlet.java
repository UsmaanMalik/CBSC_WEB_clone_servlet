import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class UpdateServlet extends HttpServlet{
	public static String id1;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter o=res.getWriter();
		String id=req.getParameter("s_id");
		try{
			Connection c=ConnectionProvider.getConnection();
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("select * from cbse where id="+id);
			if(rs.next()){
				id1=rs.getString(1);
				String name=rs.getString(2);
				String english=rs.getString(3);
				String science=rs.getString(4);
				String math=rs.getString(5);
				String socials=rs.getString(6);
	
			
				o.println("<html><head><link rel='stylesheet' href='update.css'></head");
				o.println("<body><div><form class= 'formStyle' action ='usd' method='post'>");
				o.println("STUDENT ID:<br><name='id'><h2 style='color:ADFF2F'>"+id1+"</h2>");
				o.println("STUDENT NAME:<br><input type='text' name='S_name' placeholder='"+name+"'>");
				o.println("<br>STUDENT MARKS:<br> out of / 100<br>");
				o.println("1.ENGLISH<input type='number' name='english' placeholder='"+english+"'><br>");
				o.println("2.SCIENCE<input type='number' name='science' placeholder='"+science+"'><br>");
				o.println("3.MATHEMATICS<input type='number' name='math' placeholder='"+math+"'><br>");
				o.println("4.SOCIAL SCIENCE<input type='number' name='sst' placeholder='"+socials+"'><br>");
				o.println("<input type='submit' value='UPDATE' style='text-align:center;'>");
				o.println("</form></div>");
				o.println("<a href='index.html'><h3>HOME</h3></a>");
				o.println("<a href='update.html'><h3>BACK</h3></a>");
				
			}
			else{
				o.println("invlaid id");
				o.println("<a href='index.html'><h3>HOME</h3></a>");
				o.println("<a href='update.html.html'><h3>BACK</h3></a>");
			}
		}
		catch(Exception e){e.printStackTrace();}
		o.println("</body></html");
	}
}

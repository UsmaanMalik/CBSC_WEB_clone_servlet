import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;



public class UpdateServlet1 extends HttpServlet {
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<html><body>");
		String id=UpdateServlet.id1;
        String name=req.getParameter("S_name");
        String engMarks=req.getParameter("english");
        String sciMarks=req.getParameter("science");
        String mathMarks=req.getParameter("math");  
        String sstMarks=req.getParameter("sst");
        try{
            Connection c=ConnectionProvider.getConnection();
            Statement s=c.createStatement();
			ResultSet rs1=s.executeQuery("select * from cbse where id="+id);
			
				rs1.next();
				if(name.equals("")){
					name=rs1.getString(2);
				}
				if(engMarks.equals("")){
					engMarks=rs1.getString(3);
				}
				if(sciMarks.equals("")){
					sciMarks=rs1.getString(4);
				}
				if(mathMarks.equals("")){
					mathMarks=rs1.getString(5);
				}
				if(sstMarks.equals("")){
					sstMarks=rs1.getString(6);
				}
			
			
            s.executeUpdate("UPDATE cbse SET name='"+name+"',english='"+engMarks+"',science='"+sciMarks+"',math='"+mathMarks+"',sst='"+sstMarks+"' where id="+id);
            ResultSet rs=s.executeQuery("select * from cbse where id = "+id);
            ResultSetMetaData rsmd=rs.getMetaData();
            out.println("<table  bgcolor='dimgray' style='color:#ADFF2F' border=1 width=200>");
            out.println("<tr>");
            for(int i=1; i<=rsmd.getColumnCount(); i++)
            {
            out.println("<th>"+rsmd.getColumnName(i)+"</th>");
            }
            rs.next();
            out.println("<tr>");
            out.println("<td>"+rs.getString(1)+"</td>");
            out.println("<td>"+rs.getString(2)+"</td>");
            out.println("<td>"+rs.getString(3)+"</td>");
            out.println("<td>"+rs.getString(4)+"</td>");
            out.println("<td>"+rs.getString(5)+"</td>");
            out.println("<td>"+rs.getString(6)+"</td>");
            out.println("</tr><br></table>");
            

            out.println("<h3>Data Updated Successfully</h2>");
        } 
        catch (Exception e) {
            out.println("<h2>error......</h2>");
            out.println("<h3>Data Updation Fails:</h3>");
            out.println("<h6>"+e+"</h6>");
            
        }
        finally{
            out.println("<a href='index.html'><h3>HOME</h3></a>");
        }
   }
}


import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;



public class AddToDB extends HttpServlet {
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<html><body>");
        String ids=req.getParameter("S_ID");
        int id=Integer.parseInt(ids);
        String name=req.getParameter("S_name");
        String engMarks=req.getParameter("english");
        String sciMarks=req.getParameter("science");
        String mathMarks=req.getParameter("math");  
        String sstMarks=req.getParameter("sst");
        try{
            Connection c=ConnectionProvider.getConnection();
            Statement s=c.createStatement(); 
            s.executeUpdate("insert into cbse values("+id+",'"+name+"','"+engMarks+"','"+sciMarks+"','"+mathMarks+"','"+sstMarks+"')");
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
            

            out.println("<h3>Data Inserted Successfully</h2>");
        } 
        catch (Exception e) {
            out.println("<h2>Error......</h2>");
            out.println("<h3>Data insertion Fails:</h3>");
            out.println("<h6>"+e+"</h 6>");
            
        }
        finally{
            out.println("<a href='index.html'><h3>HOME</h3></a>");
			out.println("<a href='addStudent.html'><h3>BACK</h3></a>");
            
            
        }
   }
}


import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

import javax.servlet.*;
public class percentage extends HttpServlet{

    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String name=req.getParameter("name1");
        String id=req.getParameter("s_id");
        if(id.equals("")){
            id="0";
        }
        try{
            
            Connection c=ConnectionProvider.getConnection();
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("select * from cbse where name='"+name+"' or id="+id);
            ResultSetMetaData rsmd=rs.getMetaData();
            out.println("<table  bgcolor='dimgray' style='color:#ADFF2F' border=1 width=400 >");
            out.println("<tr>");
            out.println("<th>"+rsmd.getColumnName(1)+"</th>");
            out.println("<th>"+rsmd.getColumnName(2)+"</th>");
            out.println("<th>PERCENTAGE</th>");
            if(rs.next()){
                do{
                    String eng=rs.getString("english");
                    int e=Integer.parseInt(eng);
                    String sci=rs.getString("science");
                    int sc=Integer.parseInt(sci);
                    String math=rs.getString("math");
                    int m=Integer.parseInt(math);
                    String sst=rs.getString("sst");
                    int ss=Integer.parseInt(sst);
                    float total=e+sc+m+ss;
                    float percentage=(total/400)*100;
                    
                    out.println("<tr>");
                    out.println("<td>"+rs.getString(1)+"</td>");
                    out.println("<td>"+rs.getString(2)+"</td>");
                    out.println("<td>"+percentage+"</td>");
                    out.println("</tr><br>");
                    
                }
                while(rs.next());
				out.println("</table>");
				
            }
            else{
                out.println("<h1>Student wiht this name/ID is not available</h1>");
            }

        }
        catch(Exception e){
            out.println("<h1>"+e+"</h1>");
        }
        finally{
            out.println("<a href='index.html'><h3>HOME</h3></a>");
			out.println("<a href='percentage.html'><h3>BACK</h3></a>");

        }
		

    }

}
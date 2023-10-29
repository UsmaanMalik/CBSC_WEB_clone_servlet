import java.sql.*;
public class ConnectionProvider{
	private static Connection con;
	private static Statement st;
	public static synchronized Connection getConnection(){
		try{
			if(con==null){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
				return con;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
			
	
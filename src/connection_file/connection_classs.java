package connection_file;

import java.sql.Connection;
import java.sql.DriverManager;


public class connection_classs
{
	public static Connection doConnect()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost/project1","root","");
			System.out.println("Connected....");
		} 
		catch (Exception e) 
		  {
			e.printStackTrace();
		  }
		return con;
	}
	public static void main(String[] args) 
	{
		doConnect();
	}

}

package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection 
{
	public static Connection CreateConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String link="jdbc:mysql://localhost:3306/jdbcsatya";
			String user="root";
			String password="";
			return (Connection) DriverManager.getConnection(link,user,password);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			return null;
		}
		
	}
}

package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUDoperations 
{
	private static Scanner scan = new Scanner(System.in);
	private static Connection con=MyConnection.CreateConnection();
	//------------- Create A Table -------------
	public static String CreateaTable()
	{
		Connection con =  MyConnection.CreateConnection();
		if(con!=null)
		{
			String sql = "CREATE TABLE student "
					+ "(ID INT NOT NULL AUTO_INCREMENT,"
					+ " NAME VARCHAR(50),"
					+ "BRANCH VARCHAR(10),"
					+ "MOBILENUMBER VARCHAR(50),"
					+ "EMAIL VARCHAR(50),"
					+ "PRIMARY KEY(ID))";
			try
			{
				Statement stmt = con.createStatement();
				stmt.execute(sql);
				return "\t\tTable Created Successfully !";
			}
			catch(SQLException e)
			{
				return "\t\tTable Already Present !";
			}
		}
		else
		{
			return "\t\tUnable To Connect To Database !";
		}
	}
	//--------------- Select Data From Table ---------------
	public static void SelectAllStudent()
	{
		Connection con=MyConnection.CreateConnection();
		if(con!=null)
		{
			String sql = "SELECT * FROM student";
			try 
			{
				Statement stmt = con.createStatement();
				ResultSet rst = stmt.executeQuery(sql);
				int count=1;
				while(rst.next())
				{
					System.out.println("Index         "+count);
					System.out.println("ID            "+rst.getString(1));
					System.out.println("NAME          "+rst.getString(2));
					System.out.println("BRANCH        "+rst.getString(3));
					System.out.println("MOBILE NUMBER "+rst.getString(4));
					System.out.println("EMAIL         "+rst.getString(5));
					count++;
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("\n\t\tUnable To Connect To Database !");
		}
	}
	//------------------------ Insert Student ------------------------------
	public static void InsertStudent(String name,String branch,String mobilenumber,String email)
	{
		Connection con=MyConnection.CreateConnection();
		try
		{	
			PreparedStatement ps= con.prepareStatement("INSERT INTO student VALUES(null,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,branch);
			ps.setString(3,mobilenumber);
			ps.setString(4,email);
			ps.executeUpdate();
			System.out.println("\n\t\tData Inserted Successfully !");
		}
		catch(SQLException e)
		{
			System.out.println("\n\t\tUnable To Insert Data In The Database !");
		}
	}
	//--------------------- Delete Student -------------------------------
	public static void DeleteStudent(int id)
	{
		Connection con=MyConnection.CreateConnection();
		try
		{
			if(CRUDoperations.CheckStudent(id)!=0)
			{
				String sql =  "DELETE FROM student WHERE ID="+id;
				Statement stmt = con.createStatement();
				stmt.execute(sql);
				System.out.println("\n\t\tData Deleted Successfully !");
			}
			else
			{
				System.out.println("\n\t\tInvalid Id !");
			}
		}
		catch(SQLException e)
		{
			System.out.println("\n\t\tUnable To Delete Data From The Database !");
		}
	}
	//-------------- Update Student --------------------------------------------
	public static void UpdateStudent(int id)
	{	
		try
		{
			String sql = "SELECT * FROM STUDENT WHERE ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sql);
			String NAME=null,BRANCH=null,MOBILENUMBER=null,EMAIL=null;
			while(rst.next())
			{
				NAME=rst.getString(2);
				BRANCH=rst.getString(3);
				MOBILENUMBER=rst.getString(4);
				EMAIL=rst.getString(5);
			}
			if(CRUDoperations.CheckStudent(id)!=0)
			{
			System.out.println("Enter New Details For Student "+NAME);
			System.out.println("Enter 1 To Update Name");
			System.out.println("Enter 2 To Update Branch");
			System.out.println("Enter 3 To Update Mobile Number");
			System.out.println("Enter 4 To Update Email");
			int option = scan.nextInt();
			PreparedStatement ps= con.prepareStatement("UPDATE student SET NAME=?,BRANCH=?,MOBILENUMBER=?,EMAIL=? WHERE ID=?");
			switch(option)
			{
			case 1:
				System.out.print("Enter Student Name :\t");
				String name=scan.nextLine();
				name=scan.nextLine();
				ps.setString(1,name);
				ps.setString(2,BRANCH);
				ps.setString(3,MOBILENUMBER);
				ps.setString(4,EMAIL);
				break;
			case 2:
				System.out.print("Enter Student Branch : ");
				String branch=scan.next();
				ps.setString(1,NAME);
				ps.setString(2,branch);
				ps.setString(3,MOBILENUMBER);
				ps.setString(4,EMAIL);
				break;
			case 3:
				System.out.print("Enter Student Mobile Number : ");
				String mobilenumber=scan.next();
				ps.setString(1,NAME);
				ps.setString(2,BRANCH);
				ps.setString(3,mobilenumber);
				ps.setString(4,EMAIL);
				break;
			case 4:
				System.out.print("Enter Student Email : ");
				String email=scan.next();
				ps.setString(1,NAME);
				ps.setString(2,BRANCH);
				ps.setString(3,MOBILENUMBER);
				ps.setString(4,email);
				break;
			default:
				System.out.println("\n\t\tInvalid Entry !");
				break;
			}
			ps.setInt(5,id);
			ps.executeUpdate();
			System.out.println("\n\t\tData Updated !");
			}
			else
			{
				System.out.println("\n\t\tInvalid Id !");
			}
		}
		catch(SQLException e)
		{
			System.out.println("\n\t\tUnable To Update Database !");
		}
	}
	//------------- Check Student ----------------------
	public static int CheckStudent(int id)
	{
		String sql = "SELECT * FROM STUDENT WHERE ID="+id;
		int count=0;
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sql);
			while(rst.next())
			{
				count++;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("\n\t\tSomething Went Wrong !");
		}
		return count;
	}
}

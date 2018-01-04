package jdbc;
import java.sql.*;
import java.util.Scanner;
public class MyConnection 
{
	public static void main(String args[])
	{
		MyConnection obj= new MyConnection();
		Scanner br= new Scanner(System.in);
		int option=0;
		boolean flag=true;
		while(flag)
		{
			System.out.println("Enter 1 To Select A Data");
			System.out.println("Enter 2 To Insert A Data");
			System.out.println("Enter 3 To Update A Data");
			System.out.println("Enter 4 To Delete A Data");
			option=br.nextInt();
			switch(option)
			{
				case 1:
					obj.SelectAllStudent();
					break;
				case 2:
					obj.InsertStudent();
					break;
				case 3:
					obj.UpdateStudent(5);
					break;
				case 4:
					obj.DeleteStudent(10);
				break;
			}
			System.out.println("Type to exit");
			String value=br.next();
			if(value.equals("exit"))
			{
				flag=false;
			}
			else
			{
				flag=true;
			}
		}
	}
	public void SelectAllStudent()
	{
		MyConnection obj= new MyConnection();
		Connection con=obj.CreateConnection();
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
	public void InsertStudent()
	{
		MyConnection obj= new MyConnection();
		Connection con=obj.CreateConnection();
		Scanner br= new Scanner(System.in);
		try
		{
			PreparedStatement ps= con.prepareStatement("INSERT INTO student VALUES(10,?,?,?,?)");
			System.out.println("Enter Name");
			String name=br.next();
			name=br.nextLine();
			System.out.println("Enter Branch");
			String branch=br.next();
			System.out.println("Enter Mobile Number");
			String mobilenumber=br.next();
			System.out.println("Enter Email");
			String email=br.next();
			ps.setString(1,name);
			ps.setString(2,branch);
			ps.setString(3,mobilenumber);
			ps.setString(4,email);
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	public void UpdateStudent(int id)
	{
		MyConnection obj= new MyConnection();
		Connection con=obj.CreateConnection();
		try
		{
			String sql = "SELECT * FROM STUDENT WHERE ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sql);
			String ID,NAME=null,BRANCH=null,MOBILENUMBER=null,EMAIL=null;
			while(rst.next())
			{
				ID=rst.getString(1);
				NAME=rst.getString(2);
				BRANCH=rst.getString(3);
				MOBILENUMBER=rst.getString(4);
				EMAIL=rst.getString(5);
			}
			Scanner br= new Scanner(System.in);
			System.out.println("Enter New Details For Student "+NAME);
			System.out.println("Enter 1 To Update Name");
			System.out.println("Enter 2 To Update Branch");
			System.out.println("Enter 3 To Update Mobile Number");
			System.out.println("Enter 4 To Update Email");
			int option = br.nextInt();
			PreparedStatement ps= con.prepareStatement("UPDATE student SET NAME=?,BRANCH=?,MOBILENUMBER=?,EMAIL=? WHERE ID=?");
			switch(option)
			{
			case 1:
				String name=br.next();
				name=br.nextLine();
				ps.setString(1,name);
				ps.setString(2,BRANCH);
				ps.setString(3,MOBILENUMBER);
				ps.setString(4,EMAIL);
				break;
			case 2:
				String branch=br.next();
				ps.setString(1,NAME);
				ps.setString(2,branch);
				ps.setString(3,MOBILENUMBER);
				ps.setString(4,EMAIL);
				break;
			case 3:
				String mobilenumber=br.next();
				ps.setString(1,NAME);
				ps.setString(2,BRANCH);
				ps.setString(3,mobilenumber);
				ps.setString(4,EMAIL);
				break;
			case 4:
				String email=br.next();
				ps.setString(1,NAME);
				ps.setString(2,BRANCH);
				ps.setString(3,MOBILENUMBER);
				ps.setString(4,email);
				break;
			default:
				System.out.println("Invalid Entry !");
				break;
			}
			ps.setInt(5,id);
			ps.executeUpdate();
			System.out.println("Updated..");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	public void DeleteStudent(int ID)
	{
		MyConnection obj= new MyConnection();
		Connection con=obj.CreateConnection();
		try
		{
			String sql =  "DELETE FROM student WHERE ID="+ID;
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			System.out.println("Student Deleted !");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	public Connection CreateConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String link="jdbc:mysql://localhost:3306/satya";
			String user="root";
			String password="";
			return DriverManager.getConnection(link,user,password);
		}
		catch(SQLException | ClassNotFoundException exception)
		{
			System.out.println("Unable To Connect ...");
			return null;
		}
	}
}

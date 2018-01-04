package jdbc;
import java.util.Scanner;
public class Application
{
	private static Scanner scan;
	public static void main(String args[])
	{
		scan = new Scanner(System.in);
		int option=0;
		boolean flag=true;
		
		while(flag)
		{
			System.out.println("------------------------------------------");
			System.out.println("| Enter 1 To Create A Table              |");
			System.out.println("| Enter 2 To View All Student Details    |");
			System.out.println("| Enter 3 To Add A New Student           |");
			System.out.println("| Enter 4 To Update A Student Detail     |");
			System.out.println("| Enter 5 To Delete A Student Detail     |");
			System.out.println("------------------------------------------\n");
			option=scan.nextInt();
			switch(option)
			{
				case 1:
					System.out.println(CRUDoperations.CreateaTable());
					break;
				case 2:
					CRUDoperations.SelectAllStudent();
					break;
				case 3:
					System.out.print("Enter Student Name :\t");
					String name=scan.nextLine();
					name=scan.nextLine();
					System.out.print("Enter Student Branch :\t");
					String branch=scan.next();
					System.out.print("Enter Student MobileNumber :\t");
					String mobilenumber=scan.next();
					System.out.print("Enter Student Email :\t");
					String email=scan.next();
					
					CRUDoperations.InsertStudent(name,branch,mobilenumber,email);
					
					break;
				case 4:
					System.out.print("Enter Student ID :\t");
					int id=scan.nextInt();
					CRUDoperations.UpdateStudent(id);
					break;
				case 5:
					System.out.print("Enter Student ID :\t");
					int _id=scan.nextInt();
					CRUDoperations.DeleteStudent(_id);
				break;
				default:
					System.out.println("\n\t\t Invalid Entry !");
				break;
			}
			
			System.out.println("\n\t\t+ Type exit to Exit or Press Any Key To Continue + ");
			String value=scan.next();
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
}

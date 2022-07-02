//package PowerService;
import java.math.*;
import java.rmi.*;
import java.io.*;

public class PSClient 
{
	public static void main(String arg[])
	{
		PSInterface s = null;
		try
		{
			String qry = new String();
			String record = new String();
			String m[] = new String [4];
			String msg = new String();
			
			s = (PSInterface) Naming.lookup("rmi://localhost:1099/PSInterface");
			System.out.println("PSInterface found");
			
			BigInteger sq = s.Square(5);
			System.out.println(String.valueOf(sq.intValue()));
			
			BigInteger po = s.Power(5, 2);
			System.out.println(String.valueOf(po.intValue()));	
			
			BufferedReader bin = new BufferedReader (new InputStreamReader (System.in));
			
			System.out.println("Enter your selection: ");
			System.out.println(" s. Selection \n a. Addition \n u. Updation \n d. Deletion \n e. Exit");
			
			msg = bin.readLine();
			while(!msg.equals("e"))
			{			
				if (msg.equals("s"))
				{				
					System.out.println("\nSELECTION OF RECORD");
					System.out.println("Enter the SELECT query: ");
					qry = bin.readLine();			
					record = s.RetrieveData(qry);
					System.out.println(record);
				}
				else if(msg.equals("a"))
				{				
					System.out.println("\nADDITION OF RECORD");						
					
					System.out.print("Enter course code: ");
					m[0] = bin.readLine();
					System.out.print("Enter course title: ");
					m[1] = bin.readLine();
					System.out.print("Enter course hour: ");
					m[2] = bin.readLine();
					s.AddData(m[0], m[1], m[2]);
					System.out.println("Record added...");
				}
				else if(msg.equals("u"))
				{			
					System.out.println("\nUPADTION OF RECORD");
					
					System.out.print("Enter course code to change: ");
					m[0] = bin.readLine();
					System.out.print("Enter course code: ");
					m[1] = bin.readLine();			
					System.out.print("Enter course title: ");
					m[2] = bin.readLine();
					System.out.print("Enter course hour: ");
					m[3] = bin.readLine();
					s.EditData(m[1], m[2], m[3], m[0]);
					System.out.println("Record updated...");
				}
				else if(msg.equals("d"))
				{			
					System.out.println("\nDELETION OF RECORD");
					
					System.out.print("Enter course code to delete: ");
					m[0] = bin.readLine();
					s.DeleteData(m[0]);
					record = s.RetrieveData(qry);
					System.out.println("Record deleted...");	
				}
				System.out.println("Enter your selection: ");
				System.out.println(" s. Selection \n a. Addition \n u. Updation \n d. Deletion \n e. Exit");
				msg = bin.readLine();
			}
		}
		catch(Exception e)
		{
			System.err.println("PSInterface Exception: "+e.getMessage());
			e.printStackTrace();
		}		
	}
}
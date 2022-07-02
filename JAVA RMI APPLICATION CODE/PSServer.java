//package PoserService;
import java.math.*;
import java.rmi.*;
import java.rmi.server.*;

import java.net.*;
import java.io.*;
import java.sql.*;


public class PSServer extends UnicastRemoteObject implements PSInterface
{
	private Statement st;
	private Connection con;	
	
	PSServer() throws RemoteException
	{
		super();		
	}
	
	public BigInteger Square(int num) throws RemoteException
	{
		String numrep = String.valueOf(num);
		BigInteger binum = new BigInteger(numrep);				
		binum = binum.multiply(binum);
		return binum;
	}
	
	public BigInteger Power(int num1, int num2) throws RemoteException
	{
		String numrep = String.valueOf(num1);
		BigInteger binum = new BigInteger(numrep);				
		binum = binum.pow(num2);
		return binum;
	}
	
	public static void main(String arg[])
	{
		try
		{
			PSServer s = new PSServer();
			Naming.bind("rmi://localhost:1099/PSInterface", s);
			System.out.println("service bound...");
			s.ConnectToDatabase();
		}
		catch(Exception e) {	}
	}
	
	private void ConnectToDatabase ()
	{
		try
		{
			String dsn = "CourseDSN";
			
			//load driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//make connection
			con = DriverManager.getConnection("jdbc:odbc:" + dsn);
			
			//create statement
			st = con.createStatement();
		}
		catch (Exception e) {}
	}
	
	public String RetrieveData (String qry) throws RemoteException
	{
		try
		{
			//execute query			
			ResultSet rs = st.executeQuery(qry);
			
			//get metadata
			ResultSetMetaData md = rs.getMetaData();
			int colcount = md.getColumnCount();
			
			//extracting data
			String record = new String();
			while (rs.next())
			{
				
				for (int i=1; i<=colcount; i++)
				{
					record += rs.getString(i) + "\t";
				}
				record += "\n";
			}
			return record;
		}
		catch(Exception e) {return null; }
	}
	
	public void AddData (String code, String title, String hour) throws RemoteException
	{
		try
		{
			//prepare statement
			String qry = "Insert into CourseInfo values (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(qry);
			
			ps.setString(1, code);
			ps.setString(2, title);
			ps.setString(3, hour);
			
			ps.executeUpdate();
		}
		catch(Exception e) {	}
	}
	
	public void EditData (String code, String title, String hour, String cond) throws RemoteException
	{
		try
		{
			//prepare statement
			String qry = "Insert into CourseInfo values (?, ?, ?) where CourseCode = ?";
			PreparedStatement ps = con.prepareStatement(qry);
			
			ps.setString(1, code);
			ps.setString(2, title);
			ps.setString(3, hour);
			ps.setString(4, cond);
			
			ps.executeUpdate();
		}
		catch(Exception e) {	}
	}
	
	public void DeleteData (String cond) throws RemoteException
	{
		try
		{
			//prepare statement
			String qry = "Delete from CourseInfo where CourseCode = ?";
			PreparedStatement ps = con.prepareStatement(qry);
			
			ps.setString(1, cond);
			
			ps.executeUpdate();
		}
		catch(Exception e) {	}
	}
}
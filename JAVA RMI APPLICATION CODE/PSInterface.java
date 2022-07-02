//package PowerService;
import java.math.*;
import java.rmi.*;

public interface PSInterface extends java.rmi.Remote
{
	public BigInteger Square (int num) throws RemoteException;
	public BigInteger Power (int num1, int num2) throws RemoteException;
	/*public String GetMessage () throws RemoteException;
	public void SendMessage (String msg) throws RemoteException;*/
	public String RetrieveData (String qry) throws RemoteException;
	public void AddData (String code, String title, String hour) throws RemoteException;
	public void EditData (String code, String title, String hour, String cond) throws RemoteException;
	public void DeleteData (String cond) throws RemoteException;
}
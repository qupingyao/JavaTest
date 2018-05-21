package rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CInterface extends Remote {

	public String sayToUpperCase(String word) throws RemoteException;

}

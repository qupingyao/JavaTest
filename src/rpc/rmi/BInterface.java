package rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BInterface extends Remote {

	public String sayReverse(String word) throws RemoteException;

}

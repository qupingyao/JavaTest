package rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AInterface extends Remote {

	public String say(String word) throws RemoteException;

}

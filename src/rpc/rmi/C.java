package rpc.rmi;

import java.rmi.RemoteException;

public class C implements CInterface {

	@Override
	public String sayToUpperCase(String word) throws RemoteException {
		String str = word.toUpperCase();
		System.out.println("I say:" + str);
		return str;
	}

}

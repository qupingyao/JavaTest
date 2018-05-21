package rpc.rmi;

import java.rmi.RemoteException;

public class A implements AInterface {

	@Override
	public String say(String word) throws RemoteException {
		System.out.println("I say:" + word);
		return word;
	}

}

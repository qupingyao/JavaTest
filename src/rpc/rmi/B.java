package rpc.rmi;

import java.rmi.RemoteException;

public class B implements BInterface {

	@Override
	public String sayReverse(String word) throws RemoteException {
		String str = new StringBuffer(word).reverse().toString();
		System.out.println("I say:" + str);
		return str;
	}

}

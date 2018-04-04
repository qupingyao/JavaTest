package rpc;

public interface RegisterCenterInterface {

	public void register(Class interfaceClass, String host, int port);

	public RegisterMsg getRegisterMsg(Class interfaceClass);

}
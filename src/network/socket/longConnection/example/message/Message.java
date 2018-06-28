package network.socket.longConnection.example.message;

public abstract class Message {

	private byte type;

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public abstract byte[] toByteArr();

}

package network.socket.longConnection.example.message;

import network.socket.longConnection.example.message.Constant;

public class HeartBeatMessage extends Message {

	public HeartBeatMessage() {
		super.setType(Constant.msgTypeHeartBeat);
	}

	@Override
	public byte[] toByteArr() {
		byte[] arr = { super.getType() };
		return arr;
	}

}

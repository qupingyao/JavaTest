package network.socket.longConnection.example.message;

import network.socket.longConnection.example.message.Constant;

public class HeartBeatAckMessage extends Message {

	public HeartBeatAckMessage() {
		super.setType(Constant.msgTypeHeartBeatAck);
	}

	@Override
	public byte[] toByteArr() {
		byte[] arr = { super.getType() };
		return arr;
	}

}

package network.socket.longConnection.example.message;

import network.socket.longConnection.example.message.Constant;

import org.apache.commons.lang.ArrayUtils;

import network.socket.longConnection.example.exception.MessageToLongException;

public class NorMalMessage extends Message {

	private int length;

	private byte[] content;

	public NorMalMessage(byte[] content) throws MessageToLongException {
		if (content.length > Constant.msgMaxLength) {
			throw new MessageToLongException("message too lang");
		}
		super.setType(Constant.msgTypeNormal);
		this.length = content.length;
		this.content = content;
	}

	@Override
	public byte[] toByteArr() {
		byte[] arr = { super.getType(), (byte) length };
		return ArrayUtils.addAll(arr, content);
	}

}

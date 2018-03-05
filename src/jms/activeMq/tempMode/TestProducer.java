package jms.activeMq.tempMode;

public class TestProducer {

	public static void main(String[] args) {
		
		Producer producer = new Producer("queue1");
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;i<1000;i++){
			buffer.append("a");
		}
		producer.sendText(buffer.toString());

	}

}

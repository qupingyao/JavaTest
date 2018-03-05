package jms.activeMq.ptpMode;

public class ConsumerThread implements Runnable{
    
	private Consumer consumer;
	
	private String queueName;
    
	public ConsumerThread(Consumer consumer,String queueName){
        this.consumer = consumer;
        this.queueName = queueName;
    }

    @Override
    public void run() {
        while(true){
            try {
            	consumer.getMessage(queueName);
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

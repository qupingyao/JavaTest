package jms.activeMq.ptpMode;

public class TestProductor {
	
	public static void main(String[] args){
		Productor productor = new Productor();
        Thread t1 = new Thread(new ProductorThread(productor,"t1"));
        Thread t2 = new Thread(new ProductorThread(productor,"t1"));
        Thread t3 = new Thread(new ProductorThread(productor,"t3"));
        t1.start();
        t2.start();
        t3.start();
    }

}

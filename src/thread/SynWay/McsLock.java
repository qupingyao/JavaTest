package thread.SynWay;

import java.util.concurrent.atomic.AtomicReference;
//优点是适用于NUMA系统架构，缺点是释放锁也需要自旋等待，且比CLH读、写、CAS等操作调用次数多
public class McsLock {
	
	private static int num;

	private AtomicReference<Node> tail;

	private ThreadLocal<Node> currentNode;

	public void lock() {
		Node node = currentNode.get();
		Node preNode = tail.getAndSet(node);
		if (preNode != null) {
			node.isLock = true;
			preNode.nextNode = node;
			while (node.isLock) {
			}
		}
		//加锁时自旋在前继节点(其他线程上)
	}

	public void unlock() {
		Node node = currentNode.get();
		if (node.nextNode == null) {
			if (tail.compareAndSet(node, null)){
				return;
			}
			while (node.nextNode == null) {
			}
		}
		node.nextNode.isLock = false;
		node.nextNode = null;
	}

	private class Node {
		private volatile boolean isLock = false;
		private volatile Node nextNode;
	}
	
	public static void main(String[] args) {
		ClhLock lock = new ClhLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<100000000;i++){
					lock.lock();
					num++;
					lock.unlock();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<100000000;i++){
					lock.lock();
					num++;
					lock.unlock();
				}
			}
		});
		long sTime = System.currentTimeMillis();
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long eTime = System.currentTimeMillis();
		System.out.println(num);
		System.out.println(eTime-sTime);
	}
	
}

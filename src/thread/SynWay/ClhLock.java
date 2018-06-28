package thread.SynWay;

import java.util.concurrent.atomic.AtomicReference;

public class ClhLock {
	
	private static int num;

	private ThreadLocal<Node> preNode;

	private ThreadLocal<Node> currentNode;

	private AtomicReference<Node> tail = new AtomicReference<Node>(new Node());

	public ClhLock() {
		currentNode = new ThreadLocal<Node>() {
			@Override
			protected Node initialValue() {
				return new Node();
			}
		};

		preNode = new ThreadLocal<Node>() {
			@Override
			protected Node initialValue() {
				return null;
			}
		};
	}

	public void lock() {
		Node node = currentNode.get();
		node.isLock = true;
		Node pred = tail.getAndSet(node);
		preNode.set(pred);
		while (pred.isLock) {
		}
		//加锁时自旋在前继节点(其他线程上)
	}

	public void unlock() {
		Node node = currentNode.get();
		node.isLock = false;
		currentNode.set(preNode.get());
	}

	private static class Node {
		private volatile boolean isLock;
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
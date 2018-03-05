package thread.SynWay;

import java.util.concurrent.atomic.AtomicReference;

public class CasAbaProblemStack {

	private AtomicReference<Node> top = new AtomicReference<Node>();

	public void push(Node node) {
		Node oldTop;
		do {
			oldTop = top.get();
			node.next = oldTop;
		} while (!top.compareAndSet(oldTop, node));
	}

	public void pop() {
		Node newTop;
		Node oldTop;
		do {
			oldTop = top.get();
			if (oldTop == null) {
				break;
			}
			newTop = oldTop.next;
			if ("t1".equals(Thread.currentThread().getName())) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (!top.compareAndSet(oldTop, newTop));
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Node currentTop = top.get();
		while (currentTop != null) {
			buffer.append(currentTop.val);
			currentTop = currentTop.next;
		}
		return buffer.toString();
	}

	private static class Node {

		private String val;

		private Node next;

		private Node(String val) {
			this.val = val;
		}

	}

	public static void main(String[] args) {
		CasAbaProblemStack stack = new CasAbaProblemStack();
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		stack.push(nodeA);
		stack.push(nodeB);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				stack.pop();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				stack.pop();
				stack.pop();
				stack.push(nodeD);
				stack.push(nodeC);
				stack.push(nodeB);
			}
		}, "t2");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(stack);
	}
}

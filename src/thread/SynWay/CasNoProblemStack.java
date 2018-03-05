package thread.SynWay;

import java.util.concurrent.atomic.AtomicStampedReference;

public class CasNoProblemStack {

	private AtomicStampedReference<Node> top = new AtomicStampedReference<Node>(null, 0);

	public void push(Node node) {
		Node oldTop;
		int oldStamp;
		do {
			oldTop = top.getReference();
			oldStamp = top.getStamp();
			node.next = oldTop;
		} while (!top.compareAndSet(oldTop, node, oldStamp, oldStamp + 1));
	}

	public void pop() {
		Node newTop;
		Node oldTop;
		int oldStamp;
		do {

			oldTop = top.getReference();
			if (oldTop == null) {
				break;
			}
			oldStamp = top.getStamp();
			newTop = oldTop.next;
			if ("t1".equals(Thread.currentThread().getName())) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (!top.compareAndSet(oldTop, newTop, oldStamp, oldStamp + 1));
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Node current = top.getReference();
		while (current != null) {
			buffer.append(current.val);
			current = current.next;
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
		CasNoProblemStack stack = new CasNoProblemStack();
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

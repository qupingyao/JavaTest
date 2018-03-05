/*package thread.SynWay;

import java.util.concurrent.atomic.AtomicReference;

public class McsLock {
	
	private AtomicReference<Node> tail;  
	
    private ThreadLocal<Node> currentNode;  
  
    public void lock() {  
        Node node = currentNode.get();  
        Node preNode = tail.getAndSet(node);  
        if (preNode != null) {  
            node.isLock = true;  
            preNode.nextNode = qnode;  
  
            // wait until predecessor gives up the lock  
            while (qnode.locked) {  
            }  
        }  
    }  
  
    public void unlock() {  
        QNode qnode = myNode.get();  
        if (qnode.next == null) {  
            if (tail.compareAndSet(qnode, null))  
                return;  
              
            // wait until predecessor fills in its next field  
            while (qnode.next == null) {  
            }  
        }  
        qnode.next.locked = false;  
        qnode.next = null;  
    }  
    
	private static class Node {
		
		private boolean isLock;
		
		private Node nextNode;
	}
}
*/
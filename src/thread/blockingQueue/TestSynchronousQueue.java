package thread.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestSynchronousQueue {

	private final static int perTestTimes = 10;

	public static void main(String[] args) throws Exception {
		System.out.println("totalNum\tthreadNum\tqueueCapacity\tlinkedBlockingQueue\tarrayBlockingQueue");
		for (int i = 0; i <= 3; i++) {
			int totalNum = (int) Math.pow(2, 10 + 5 * i);
			for (int j = 0; j < 3; j++) {
				int threadNum = (int) Math.pow(4, j);
				for (int k = 0; k < 4; k++) {
					int length = (int) Math.pow(100, k);
					long t1 = testBlockingQueue(new LinkedBlockingQueue<Integer>(length), totalNum, threadNum,
							perTestTimes);
					long t2 = testBlockingQueue(new ArrayBlockingQueue<Integer>(length), totalNum, threadNum,
							perTestTimes);
					System.out.format("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t\n", totalNum, threadNum, length, t1, t2);
				}
			}
		}
	}

	private static class Producer implements Runnable {

		private int executeNum;

		private BlockingQueue<Integer> queue;

		public Producer(int executeNum, BlockingQueue<Integer> queue) {
			this.executeNum = executeNum;
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int i = 0; i < executeNum; i++) {
				try {
					queue.put(i);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private static class Consumer implements Callable<Integer> {

		private int executeNum;

		private BlockingQueue<Integer> queue;

		public Consumer(int executeNum, BlockingQueue<Integer> queue) {
			this.executeNum = executeNum;
			this.queue = queue;
		}

		@Override
		public Integer call() {
			for (int i = 0; i < executeNum; i++) {
				try {
					queue.take();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			return 0;
		}
	}

	private static long testBlockingQueue(BlockingQueue<Integer> queue, int totalNum, int threadNum, int times)
			throws Exception {
		long sumTime = 0;
		for (int i = 0; i < times; i++) {
			ExecutorService executor = Executors.newFixedThreadPool(threadNum * 2);
			CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
			int executeNum = totalNum / threadNum;
			long startTime = System.nanoTime();
			for (int j = 0; j < threadNum; j++) {
				executor.submit(new Producer(executeNum, queue));
			}
			for (int j = 0; j < threadNum; j++) {
				completionService.submit(new Consumer(executeNum, queue));
			}

			for (int j = 0; j < threadNum; j++) {
				completionService.take().get();
			}
			long endTime = System.nanoTime();
			sumTime += endTime - startTime;
			executor.shutdown();
		}
		return (long) (1000000.0 * totalNum * times / sumTime);
	}
}
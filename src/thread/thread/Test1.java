package thread.thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class Test1 {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("默认UncaughtExceptionHandler -> 捕获到异常！");
				e.printStackTrace();
			}
		});

		ThreadGroup group = new ThreadGroup("my group") {
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("线程组 -> 捕获到异常！");
				if (this.getParent() != null){
					this.getParent().uncaughtException(t, e);
				}else{
					Thread.getDefaultUncaughtExceptionHandler().uncaughtException(t, e);
				}
			}
		};

		Thread thread = new Thread(group, "my thread") {
			public void run() {
				try {
					System.out.println("应用 -> 抛出异常……");
					int i = 1 / 0;
				} catch (RuntimeException e) {
					System.out.println("try catch -> 捕获到异常！");
					throw e;
				}
			}
		};

		thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("线程UncaughtExceptionHandler -> 捕获到异常！");
				t.getThreadGroup().uncaughtException(t, e);
			}
		});
		thread.start();
	}

}

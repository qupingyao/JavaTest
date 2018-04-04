package thread.thread.producerConsumerModel;

import java.util.concurrent.atomic.AtomicInteger;

public class Thing {

	private static AtomicInteger currentId = new AtomicInteger(0);

	private Integer id;

	public Thing() {
		this.id = currentId.getAndAdd(1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

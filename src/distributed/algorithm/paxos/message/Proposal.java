package distributed.algorithm.paxos.message;

import java.util.concurrent.atomic.AtomicInteger;

import distributed.algorithm.paxos.View;

public class Proposal implements Message{

	private AtomicInteger currNum = new AtomicInteger();

	private int id;

	private View view;

	public Proposal(View view) {
		this.id = currNum.getAndIncrement();
		this.view = view;
	}

	public Proposal(int id, View view) {
		this.id = id;
		this.view = view;
	}

	public int getId() {
		return id;
	}

	public View getView() {
		return view;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(id).append(':').append(view).toString();
	}

}

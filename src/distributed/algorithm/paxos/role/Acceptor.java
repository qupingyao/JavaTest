/*package distributed.algorithm.paxos.role;

import java.util.ArrayList;
import java.util.List;

public class Acceptor implements Runnable, Person {

	private int id;
	
	private List<Proposer> allProposeres = new ArrayList<Proposer>();

	public Acceptor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (true) {

		}
	}

	@Override
	public boolean equals(Object ob) {
		if ((ob == null) || !(ob instanceof Acceptor)) {
			return false;
		}
		Acceptor acc = (Acceptor) ob;
		return id == acc.getId();
	}

	public int getId() {
		return id;
	}
	
	public void addAllProposeres(List<Proposer> list){
		allProposeres.addAll(list);
	}

}
*/
/*package distributed.algorithm.paxos;

import java.util.ArrayList;
import java.util.List;
import distributed.algorithm.paxos.role.Acceptor;
import distributed.algorithm.paxos.role.Proposer;

public class Test {
	
	private static final int proposerNum = 5;
	
	private static final int acceptorNum = 5;

	public static void main(String[] args) {
		List<Proposer> proposers = new ArrayList<Proposer>();
		List<Acceptor> acceptors = new ArrayList<Acceptor>();
		for(int i=1;i<=proposerNum;i++){
			proposers.add(new Proposer(i,View.randomView()));
		}
		for(int i=1;i<=acceptorNum;i++){
			acceptors.add(new Acceptor(i));
		}
		for(Proposer p:proposers){
			p.addAllAcceptors(acceptors);
		}
		for(Acceptor a:acceptors){
			a.addAllProposeres(proposers);
		}
	}
}
*/
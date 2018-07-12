/*package distributed.algorithm.paxos.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.activemq.transport.stomp.Stomp.Headers.Send;

import tools.ListUtils;
import distributed.algorithm.paxos.Contants;
import distributed.algorithm.paxos.View;
import distributed.algorithm.paxos.message.Message;
import distributed.algorithm.paxos.message.Proposal;
import distributed.algorithm.paxos.prosses.Process;
import distributed.algorithm.paxos.prosses.Recorder;

public class Proposer implements Runnable,Person{
	
	private int num;
	
	private View view;
	
	private List<Acceptor> allAcceptors = new ArrayList<Acceptor>();
	
	public Proposer(int num,View view){
		this.num = num;
		this.view = view;
	}

	@Override
	public void run() {
		while(true){
			Message msg = new Proposal(view);
			int majorNum = allAcceptors.size()/2 + 1;
			List<Acceptor> acceptors = ListUtils.randomSubList(allAcceptors,majorNum);
			for(Acceptor a:acceptors){
				Recorder.addProcess(new Process(this,System.nanoTime(),msg));
			}
			for(Acceptor d)
		}
	}
	
	private isSendMsg(0)

	public int getNum() {
		return num;
	}

	public View getView() {
		return view;
	}
	
	public void addAllAcceptors(List<Acceptor> list){
		allAcceptors.addAll(list);
	}

}
*/
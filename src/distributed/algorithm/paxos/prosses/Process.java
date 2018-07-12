package distributed.algorithm.paxos.prosses;

import distributed.algorithm.paxos.message.Message;
import distributed.algorithm.paxos.role.Person;

public class Process {
	
	private Person operator;
	
	private long time;
	
	private Message msg;
	
	public Process(Person o,long t,Message msg){
		this.operator = o;
		this.time = t;
		this.msg = msg;
	}

	public long getTime() {
		return time;
	}

	public Person getOperator() {
		return operator;
	}
	
	public Message getMsg() {
		return msg;
	}

}

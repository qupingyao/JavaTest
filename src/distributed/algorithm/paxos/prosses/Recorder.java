package distributed.algorithm.paxos.prosses;

import java.util.ArrayList;
import java.util.List;

public class Recorder {
	
	private static volatile List<Process> processList = new ArrayList<Process>();
	
	public static synchronized void addProcess(Process p){
		processList.add(p);
	}
	
	public static List<Process> getProcesses(){
		return processList;
	}

}

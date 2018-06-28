package distributed.byzantineProblem.verbalAgreeMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tools.ListUtils;

public class DoAlgorithm {

	public static void exec(int personNum, int traitorNum) {
		List<Person> pList = DoAlgorithm.initPData(personNum, traitorNum, 1);
		Person manager = ListUtils.searchSingleFromList(pList, "isLeader", new Boolean(true));
		Order finalOrder = Order.randomOrder();
//		Order finalOrder = Order.orderA;
		List<Process> processList = DoAlgorithm.sendOrder(pList, traitorNum, new ArrayList<Process>(), finalOrder,
				new Path(manager));
//		for(Process process:processList){
//			System.out.println(process);
//		}
		Set<Order> orderSet = new HashSet<Order>();
		for (Person person : pList) {
			if (!person.isLeader() && person.isLoyal()) {
				Order order = person.getJudgeOrder(traitorNum, pList, processList, new Path(manager));
//				System.out.println("personId:"+person.getId()+"  ,order:"+order);
				orderSet.add(order);
			}
		}
		if (orderSet.size() == 1) {
			if (manager.isLoyal()) {
				if (orderSet.contains(finalOrder)) {
//					System.out.println("loyal persons get the same order,and the order equals to finalOrder");
				} else {
					System.out.println("loyal persons get the same order,and the order don't equals to finalOrder");
				}
			} else {
				System.out.println("loyal persons get the same order");
			}
		} else {
			System.out.println("loyal persons don't get the same order");
		}
	}

	private static List<Process> sendOrder(List<Person> pList, int cycle, List<Process> processList, Order lastOrder,
			Path prePath) {
		if (cycle >= 0) {
			Person sender = prePath.top();
			List<Person> rest = ListUtils.subList(pList, prePath.getPathList());
			for (Person p : rest) {
				Order order = sender.prepareOrder(lastOrder);
				Process process = new Process(sender.getId(), p.getId(), order, new Path(prePath));
				processList.add(process);
				prePath.add(p);
				DoAlgorithm.sendOrder(pList, cycle - 1, processList, order, prePath);
				prePath.pop();
			}
		}
		return processList;
	}

	private static List<Person> initPData(int personNum, int traitorNum, int leaderNum) {
		List<Person> pList = new ArrayList<Person>();
		for (int i = 1; i <= personNum; i++) {
			Person p = new Person(i);
			if (i <= personNum - traitorNum) {
				p.setLoyal(true);
			}
			if (i <= leaderNum) {
				p.setLeader(true);
			}
			pList.add(p);
		}
		Collections.shuffle(pList);
		return pList;
	}

}

package distributed.byzantineProblem.verbalAgreeMode;

import java.util.ArrayList;
import java.util.List;
import tools.ListUtils;

public class Person {

	private int id;

	private boolean isLoyal;

	private boolean isLeader;

	public Person(int id) {
		this.id = id;
	}

	public Order getJudgeOrder(int cycle, List<Person> pList, List<Process> processList, Path prePath) {
		if (isLoyal) {
			List<Process> tList = ListUtils.searchFromList(processList, "path", prePath);
			Process process = ListUtils.searchSingleFromList(tList,"aimPId", id);
			if (cycle > 0) {
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(process.getOrder());
				List<Person> currentList = new ArrayList<Person>();
				currentList.add(this);
				currentList.addAll(prePath.getPathList());
				List<Person> restPList = ListUtils.subList(pList, currentList);
				for (Person p : restPList) {
					prePath.add(p);
					Order tOrder = this.getJudgeOrder(cycle - 1, pList, processList, prePath);
					prePath.pop();
					orderList.add(tOrder);
				}
				return Order.getMajorOrder(orderList);
			} else {
				return process.getOrder();
			}
		} else {
			return Order.randomOrder();
		}
	}

	public Order prepareOrder(Order order) {
		if (isLoyal) {
			return order;
		} else {
			return Order.randomOrder();
		}
	}

	public int getId() {
		return id;
	}

	public boolean isLoyal() {
		return isLoyal;
	}

	public void setLoyal(boolean isLoyal) {
		this.isLoyal = isLoyal;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	@Override
	public boolean equals(Object object) {
		if ((object == null) || !(object instanceof Person)) {
			return false;
		}
		Person person = (Person) object;
		return id == person.getId();
	}

	@Override
	public String toString() {
		return String.format("%d", id);
	}

}

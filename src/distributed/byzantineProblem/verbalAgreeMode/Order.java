package distributed.byzantineProblem.verbalAgreeMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import tools.NumberUtils;

public enum Order {

	orderA("a"), orderB("b"), orderC("c"), orderD("d"), defaultOrder("default");

	private String name;

	private Order(String name) {
		this.name = name;
	}

	public static List<Order> getNorOrderList() {
		List<Order> list = new ArrayList<Order>();
		for (Order order : Order.values()) {
			if (order != defaultOrder) {
				list.add(order);
			}
		}
		return list;
	}

	public static Order randomOrder() {
		List<Order> list = Order.getNorOrderList();
		return list.get(NumberUtils.randomInt(0, list.size() - 1));
	}

	// public static Order getMajorOrder(List<Order> list) {
	// if (list != null) {
	// Map<Order, Integer> map = new HashMap<Order, Integer>();
	// for (Order order : list) {
	// int count = map.getOrDefault(order, 0);
	// map.put(order, count + 1);
	// }
	// Order order = null;
	// int maxCount = -1;
	// int secondMaxCount = -1;
	// for (Entry<Order, Integer> entry : map.entrySet()) {
	// Order o = entry.getKey();
	// Integer count = entry.getValue();
	// if (count > secondMaxCount) {
	// secondMaxCount = Math.min(count, maxCount);
	// }
	// if (count > maxCount) {
	// maxCount = count;
	// order = o;
	// }
	// }
	// if (maxCount > 0 && maxCount > secondMaxCount) {
	// return order;
	// }
	// }
	// return defaultOrder;
	// }

	public static Order getMajorOrder(List<Order> list) {
		if (list != null) {
			Map<Order, Integer> map = new HashMap<Order, Integer>();
			for (Order order : list) {
				int count = map.getOrDefault(order, 0);
				map.put(order, count + 1);
			}
			Order order = null;
			int maxCount = -1;
			int totalCount = 0;
			for (Entry<Order, Integer> entry : map.entrySet()) {
				Order o = entry.getKey();
				Integer count = entry.getValue();
				totalCount = totalCount + count;
				if (count > maxCount) {
					maxCount = count;
					order = o;
				}
			}
			if (maxCount * 2 > totalCount) {
				return order;
			}
		}
		return defaultOrder;
	}

	@Override
	public String toString() {
		return name;
	}

}

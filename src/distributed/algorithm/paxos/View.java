package distributed.algorithm.paxos;

import java.util.ArrayList;
import java.util.List;
import tools.NumberUtils;

public enum View {

	A("A"), B("B"), C("C"), D("D"), E("E");

	private String name;

	private View(String name) {
		this.name = name;
	}

	public static View randomView() {
		List<View> list = new ArrayList<View>();
		for (View v : View.values()) {
			list.add(v);
		}
		return list.get(NumberUtils.randomInt(0, list.size() - 1));
	}

	@Override
	public String toString() {
		return name;
	}

}

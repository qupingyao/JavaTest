package Collection.Iterator;

import java.util.Iterator;
import java.util.List;

public class MyList implements Iterable<String> {

	private List<String> list;

	public MyList(List<String> list) {
		this.list = list;
	}

	public void add(String str) {
		list.add(str);
	}

	@Override
	public Iterator<String> iterator() {
		return new myListItr(list.size() - 1);
	}

	private class myListItr implements Iterator<String> {

		private int index;

		public myListItr(int index) {
			this.index = index;
		}

		@Override
		public boolean hasNext() {
			return (index >= 0 && index < list.size());
		}

		@Override
		public String next() {
			if (index >= 0 && index < list.size()) {
				return list.get(index--);
			}
			return null;
		}

	}

}
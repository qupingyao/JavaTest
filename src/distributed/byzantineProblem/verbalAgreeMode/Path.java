package distributed.byzantineProblem.verbalAgreeMode;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private List<Person> list;

	public Path(Person p) {
		list = new ArrayList<Person>();
		this.add(p);
	}
	
	public Path(Path path){
		list = new ArrayList<Person>();
		for(Person p:path.getPathList()){
			this.add(p);
		}
	}

	public void add(Person p) {
		list.add(p);
	}

	public void pop() {
		if (!list.isEmpty()) {
			list.remove(list.size() - 1);
		}
	}

	public Person top() {
		if (!list.isEmpty()) {
			return list.get(list.size() - 1);
		}
		return null;
	}

	public List<Person> getPathList() {
		return list;
	}

	@Override
	public boolean equals(Object object) {
		if ((object == null) || !(object instanceof Path)) {
			return false;
		}
		Path path = (Path) object;
		return list.containsAll(path.list) && path.list.containsAll(list);
	}
	
	@Override
	public String toString(){
		StringBuffer buff = new StringBuffer();
		for(Person p:list){
			buff.append(p);
		}
		return buff.toString();
	}

}

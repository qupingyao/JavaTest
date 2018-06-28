package tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	public static <E> List<E> searchFromList(List<E> list, String fieldName, Object value) {
		List<E> r = new ArrayList<E>();
		try {
			if (list != null && !list.isEmpty()) {
				Class<? extends Object> c = list.get(0).getClass();
				List<Field> fields = ClassUtils.getMembersFieldsByName(c, fieldName);
				if (!fields.isEmpty()) {
					List<E> t = list;
					for (Field f : fields) {
						t = searchFromList(t, f, value);
					}
					return t;
				} else {
					throw new Exception(fieldName + " is not a member field in class " + c.getName());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}

	public static <E> E searchSingleFromList(List<E> list, String fieldName, Object value) {
		List<E> r = ListUtils.searchFromList(list, fieldName, value);
		if (!r.isEmpty()) {
			return r.get(NumberUtils.randomInt(0, r.size() - 1));
		}
		return null;
	}

	public static <E> List<E> subList(List<E> list, List<E> subList) {
		List<E> r = new ArrayList<E>();
		if (list != null) {
			if (subList != null) {
				for (E e : list) {
					if (!subList.contains(e)) {
						r.add(e);
					}
				}
			} else {
				return list;
			}
		}
		return r;
	}

	private static <E> List<E> searchFromList(List<E> list, Field f, Object value) throws Exception {
		List<E> r = new ArrayList<E>();
		if (list != null && f != null) {
			for (E e : list) {
				f.setAccessible(true);
				if ((f.get(e) == null && value == null) || f.get(e).equals(value)) {
					r.add(e);
				}
			}
		}
		return r;
	}

}

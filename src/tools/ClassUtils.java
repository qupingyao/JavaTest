package tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {

	public static <T> List<Field> getMembersFieldsByName(Class<T> c, String name) {
		List<Field> r = new ArrayList<Field>();
		if (c != null) {
			Field[] arr = c.getDeclaredFields();
			for (Field f : arr) {
				if (f.getName().equals(name) && !Modifier.isStatic(f.getModifiers())) {
					r.add(f);
				}
			}
		}
		return r;
	}

}

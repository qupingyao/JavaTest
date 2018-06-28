package tools;

import java.util.Random;

public class NumberUtils {

	public static int randomInt(int start, int end) {
		if (end >= start && start >= 0) {
			Random r = new Random();
			return r.nextInt(end - start + 1) + start;
		}
		return -1;
	}

}

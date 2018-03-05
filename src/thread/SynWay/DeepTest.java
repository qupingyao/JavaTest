package thread.SynWay;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class DeepTest<T> {

	public static void main(String[] args) {

		SoftReference<Object> sr = new SoftReference<Object>(new Object());
		WeakReference<Object> wr = new WeakReference<Object>(new Object());
		System.out.println(sr.get());
		System.out.println(wr.get());
		System.gc();
		System.out.println(sr.get());
		System.out.println(wr.get());
	}

}

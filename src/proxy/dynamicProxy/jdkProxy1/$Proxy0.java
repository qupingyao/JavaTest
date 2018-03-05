package proxy.dynamicProxy.jdkProxy1;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import proxy.dynamicProxy.jdkProxy1.AInterface;

public final class $Proxy0 implements AInterface {
	private static Method m1;
	private static Method m4;
	private static Method m5;
	private static Method m2;
	private static Method m3;
	private static Method m0;
	public MyInvocationHandler h;

	public $Proxy0(MyInvocationHandler myInvocationHandler) {
		h = myInvocationHandler;
	}

	public final boolean equals(Object paramObject) {
		try {
			return ((Boolean) this.h.invoke(m1, new Object[] { paramObject })).booleanValue();
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final void cal(int paramInt) {
		try {
			this.h.invoke(m4, new Object[] { Integer.valueOf(paramInt) });
			return;
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final AInterface getObj() {
		try {
			return (AInterface) this.h.invoke(m5, null);
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final String toString() {
		try {
			return (String) this.h.invoke(m2, null);
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final void say(String paramString) {
		try {
			this.h.invoke(m3, new Object[] { paramString });
			return;
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final int hashCode() {
		try {
			return ((Integer) this.h.invoke(m0, null)).intValue();
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	static {
		try {
			m1 = Class.forName("java.lang.Object").getMethod("equals",
					new Class[] { Class.forName("java.lang.Object") });
			m4 = Class.forName("proxy.dynamicProxy.jdkProxy.AInterface").getMethod("cal", new Class[] { Integer.TYPE });
			m5 = Class.forName("proxy.dynamicProxy.jdkProxy.AInterface").getMethod("getObj", new Class[0]);
			m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
			m3 = Class.forName("proxy.dynamicProxy.jdkProxy.AInterface").getMethod("say",
					new Class[] { Class.forName("java.lang.String") });
			m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
//			return;
		} catch (NoSuchMethodException localNoSuchMethodException) {
			throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
		} catch (ClassNotFoundException localClassNotFoundException) {
			throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
		}
	}
}

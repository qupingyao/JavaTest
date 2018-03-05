//package orm.myBatis;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.lang.reflect.UndeclaredThrowableException;
//import orm.myBatis.dao.UserInfoDao;
//import orm.myBatis.domain.UserInfo;
//
//public final class $Proxy0
//  extends Proxy
//  implements UserInfoDao
//{
//  private static Method m1;
//  private static Method m2;
//  private static Method m3;
//  private static Method m0;
//  
//  public $Proxy0(InvocationHandler paramInvocationHandler)
//  {
//    super(paramInvocationHandler);
//  }
//  
//  public final boolean equals(Object paramObject)
//  {
//    try
//    {
//      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
//    }
//    catch (Error|RuntimeException localError)
//    {
//      throw localError;
//    }
//    catch (Throwable localThrowable)
//    {
//      throw new UndeclaredThrowableException(localThrowable);
//    }
//  }
//  
//  public final String toString()
//  {
//    try
//    {
//      return (String)this.h.invoke(this, m2, null);
//    }
//    catch (Error|RuntimeException localError)
//    {
//      throw localError;
//    }
//    catch (Throwable localThrowable)
//    {
//      throw new UndeclaredThrowableException(localThrowable);
//    }
//  }
//  
//  public final UserInfo getUserByUserCode(String paramString)
//  {
//    try
//    {
//      return (UserInfo)this.h.invoke(this, m3, new Object[] { paramString });
//    }
//    catch (Error|RuntimeException localError)
//    {
//      throw localError;
//    }
//    catch (Throwable localThrowable)
//    {
//      throw new UndeclaredThrowableException(localThrowable);
//    }
//  }
//  
//  public final int hashCode()
//  {
//    try
//    {
//      return ((Integer)this.h.invoke(this, m0, null)).intValue();
//    }
//    catch (Error|RuntimeException localError)
//    {
//      throw localError;
//    }
//    catch (Throwable localThrowable)
//    {
//      throw new UndeclaredThrowableException(localThrowable);
//    }
//  }
//  
//  static
//  {
//    try
//    {
//      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
//      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
//      m3 = Class.forName("orm.myBatis.dao.UserInfoDao").getMethod("getUserByUserCode", new Class[] { Class.forName("java.lang.String") });
//      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
////      return;
//    }
//    catch (NoSuchMethodException localNoSuchMethodException)
//    {
//      throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
//    }
//    catch (ClassNotFoundException localClassNotFoundException)
//    {
//      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
//    }
//  }
//}

package orm.myBatis;

import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Enumeration;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

import orm.myBatis.dao.UserInfoDao;
import orm.myBatis.domain.UserInfo;

public class Test1 {

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        String resource = "orm/myBatis/mybatis-config.xml";
        InputStream is = Test1.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sessionFactory.openSession(false);
        try{
	        UserInfo iUser1 = new UserInfo("张三", "zhangsan", 30, new Date(), new Date());
	        String statement = "orm.myBatis.dao.UserInfoDao.insertUser";
	        session.update(statement, iUser1);
//	        UserInfo user = session.selectOne(statement, "yaoquping");
//	        UserInfoDao userInfoDao = session.getMapper(UserInfoDao.class);//通过sqlSession获取对应注册接口  
//	        userInfoDao.insertUser(iUser1);
	        session.commit();
//	        UserInfo iUser2 = new UserInfo("李四", "lisi", 100, new Date(), new Date());
//	        userInfoDao.insertUser(iUser2);
//	        session.update(statement, iUser2);
//	        session.commit();
//	        session.close();
        }catch (Exception e) {
        	e.printStackTrace();
        	session.rollback();
        }
			// TODO: handle exception
//        session.commit(true);
//        session.close(); 
        
	}

}

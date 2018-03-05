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

public class Test {

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        String resource = "orm/myBatis/mybatis-config.xml";
        InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "orm.myBatis.dao.UserInfoDao.getUserByUserCode";
        UserInfo user = session.selectOne(statement, "yaoquping");
        System.out.println(user.getName());
        session.commit();
        UserInfo iUser = new UserInfo("张三", "zhangsan", 30, new Date(), new Date());
        UserInfoDao userInfoDao = session.getMapper(UserInfoDao.class);//通过sqlSession获取对应注册接口  
        UserInfo user1 = userInfoDao.getUserByUserCode("yaoquping");
        userInfoDao.insertUser(iUser);
        session.commit();
        session.close(); 
//        UserInfo user2 = userInfoDao.getUserByUserId(22);
//        System.out.println(user1.getName());
//        System.out.println(user2==null);
        
	}

}

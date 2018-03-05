package orm.myBatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMysqlConnect {

	private final static String driver = "com.mysql.jdbc.Driver";
	
	private final static String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf-8";
	
	private final static String user = "root";
	
	private final static String password = "";
	
	public static void main(String[] args) {
		try{
			Class.forName(driver);
//			testReadUncommitted();
//			testReadCommitted();
//			recoverData();
//			testRepeatableRead();
//			recoverData();
//			testRepeatableRead();
//			recoverData();
//			testRepeatableRead2();
//			recoverData2();
//			testSerializable();
//			recoverData2();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testReadUncommitted(){
		try{
			System.out.println("###################### TestReadUncommitted");
			Connection readCon = DriverManager.getConnection(url, user, password);
			Connection updateCon = DriverManager.getConnection(url, user, password);
			readCon.setAutoCommit(false);
			updateCon.setAutoCommit(false);
			readCon.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			Statement queryStatement = readCon.createStatement();
			String querySql = "select * from employee where id=1";
			ResultSet rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("before updateCon start update: age:"+ rs.getString("age"));
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "update employee set age=26 where id=1";
			updateStatement.executeUpdate(updateSql);
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update but not rollback: age:"+rs.getString("age"));
			updateCon.rollback();
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update and rollback: age:"+rs.getString("age"));
			rs.close();
			readCon.close();
			updateCon.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testReadCommitted(){
		try{
			System.out.println("###################### TestReadCommitted");
			Connection readCon = DriverManager.getConnection(url, user, password);
			Connection updateCon = DriverManager.getConnection(url, user, password);
			readCon.setAutoCommit(false);
			updateCon.setAutoCommit(false);
			readCon.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			Statement queryStatement = readCon.createStatement();
			String querySql = "select * from employee where id=1";
			ResultSet rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("before updateCon start update: age:"+ rs.getString("age"));
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "update employee set age=26 where id=1";
			updateStatement.executeUpdate(updateSql);
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update but not commit: age:"+rs.getString("age"));
			updateCon.commit();
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update and commit: age:"+rs.getString("age"));
			rs.close();
			readCon.close();
			updateCon.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testRepeatableRead(){
		try{
			System.out.println("###################### TestRepeatableRead");
			Connection readCon = DriverManager.getConnection(url, user, password);
			Connection updateCon = DriverManager.getConnection(url, user, password);
			readCon.setAutoCommit(false);
			updateCon.setAutoCommit(false);
			readCon.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			Statement queryStatement = readCon.createStatement();
			String querySql = "select * from employee where id=1";
			ResultSet rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("before updateCon start update: age:"+ rs.getString("age"));
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "update employee set age=26 where id=1";
			updateStatement.executeUpdate(updateSql);
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update but not commit: age:"+rs.getString("age"));
			updateCon.commit();
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update and commit: age:"+rs.getString("age"));
			rs.close();
			readCon.close();
			updateCon.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testRepeatableRead2(){
		try{
			System.out.println("###################### TestRepeatableRead2");
			Connection readCon = DriverManager.getConnection(url, user, password);
			Connection updateCon = DriverManager.getConnection(url, user, password);
			readCon.setAutoCommit(false);
			updateCon.setAutoCommit(false);
			readCon.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			Statement queryStatement = readCon.createStatement();
			String querySql = "select count(*) as count from employee where id=1";
			ResultSet rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("before updateCon start update: count:"+ rs.getString("count"));
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "insert into employee (name, userCode, age, creationTime,updateTime,yn) values ('苗嘉桂', 'bjmjgu', 35,'2018-01-02 09:56:02','2018-01-02 09:56:02',1)";
			updateStatement.executeUpdate(updateSql);
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update but not commit: count:"+rs.getString("count"));
			updateCon.commit();
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update and commit: count:"+rs.getString("count"));
			rs.close();
			readCon.close();
			updateCon.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testSerializable(){
		try{
			System.out.println("###################### TestSerializable");
			Connection readCon = DriverManager.getConnection(url, user, password);
			Connection updateCon = DriverManager.getConnection(url, user, password);
			readCon.setAutoCommit(false);
			updateCon.setAutoCommit(false);
			readCon.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			Statement queryStatement = readCon.createStatement();
			String querySql = "select count(*) as count from employee lock in share mode";
			ResultSet rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("before updateCon start update: count:"+ rs.getString("count"));
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "insert into employee (name, userCode, age, creationTime,updateTime,yn) values ('苗嘉桂', 'bjmjgu', 35,'2018-01-02 09:56:02','2018-01-02 09:56:02',1)";
			updateStatement.executeUpdate(updateSql);
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update but not commit: count:"+rs.getString("count"));
			updateCon.commit();
			rs = queryStatement.executeQuery(querySql);
			rs.absolute(1);
			System.out.println("after updateCon start update and commit: count:"+rs.getString("count"));
			rs.close();
			readCon.close();
			updateCon.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private static void recoverData(){
		Connection updateCon;
		try {
			updateCon = DriverManager.getConnection(url, user, password);
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "update employee set age=25 where id=1";
			updateStatement.executeUpdate(updateSql);
			updateCon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void recoverData2(){
		Connection updateCon;
		try {
			updateCon = DriverManager.getConnection(url, user, password);
			Statement updateStatement = updateCon.createStatement();
			String updateSql = "delete from employee where userCode='bjmjgu' ";
			updateStatement.executeUpdate(updateSql);
			updateCon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

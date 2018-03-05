package orm.myBatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestError {

	private final static String driver = "com.mysql.jdbc.Driver";
	
	private final static String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf-8";
	
	private final static String user = "root";
	
	private final static String password = "";
	
	public static void main(String[] args) {
		try{
			Class.forName(driver);
			test1();
			System.out.println("#########################");
			test2();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void test1(){
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement queryStatement = connection.createStatement();
			String userCode = "zhangsan";
//			String age = "1' or '1'='1";
			String age = "30";
			String column = "id";
			String querySql = "select * from employee where userCode='"+userCode+"' and age='"+age+"' order by "+column +" desc";
			System.out.println("querySql:"+querySql);
			ResultSet rs = queryStatement.executeQuery(querySql);
			while (rs.next()) {
	             System.out.println(rs.getInt(1) + "\t" + rs.getString(3));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test2(){
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String userCode = "zhangsan";
//			String age = "1' or '1'='1";
			String age = "30";
			String column = "id";
			String querySql = "select * from employee where userCode=? and age=? order by ? desc";
			System.out.println("querySql:"+querySql);
			PreparedStatement preparedStatement = connection.prepareStatement(querySql);
			preparedStatement.setString(1,userCode); 
			preparedStatement.setString(2,age); 
			preparedStatement.setString(3,column); 
			ResultSet  rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
	             System.out.println(rs.getInt(1) + "\t" + rs.getString(3));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

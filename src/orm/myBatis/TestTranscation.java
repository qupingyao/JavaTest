package orm.myBatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PRIVATE_MEMBER;

public class TestTranscation {

	private final static String driver = "com.mysql.jdbc.Driver";
	
	private final static String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf-8";
	
	private final static String user = "root";
	
	private final static String password = "";
	
	public static void main(String[] args) {
		try{
			Class.forName(driver);
			Connection con1 = DriverManager.getConnection(url, user, password);
			Connection con2 = DriverManager.getConnection(url, user, password);
//			con1.setAutoCommit(false);
			con2.setAutoCommit(false);
//////////////////////////////////////////////////
			Statement statement1 = con1.createStatement();
			String sql1 = "select * from employee where id=1";
			ResultSet rs1 = statement1.executeQuery(sql1);
			rs1.absolute(1);
			System.out.println(rs1.getString("age"));
//////////////////////////////////////////////////
			Statement statement2 = con2.createStatement();
			String sql2 = "update employee set age = 60 where id=1";
			statement2.executeUpdate(sql2);
//			con2.rollback();
			con2.close();
//			Statement statement3 = con2.createStatement();
//			String sql3 = "update employee set age = 45 where id=1";
//			statement3.executeUpdate(sql3);
//			con2.commit();
//			con2.commit();
//////////////////////////////////////////////////
			ResultSet rs3 = statement1.executeQuery(sql1);
			rs3.absolute(1);
			System.out.println(rs3.getString("age"));
			recoverData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void recoverData(){
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			String sql = "update employee set age = 15 where id=1";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

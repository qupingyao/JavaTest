package ORM.MyBatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMysqlConnect {

	private final static String driver = "com.mysql.jdbc.Driver";
	
	private final static String url = "jdbc:mysql://192.168.157.72:3358/mbp";
	
	private final static String user = "root";
	
	private final static String password = "123456";
	
	public static void main(String[] args) {
		try{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "select * from mail_apply where ERPNO = yangguang13";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("NAME"));
			}
			rs.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

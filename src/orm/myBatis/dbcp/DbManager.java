package orm.myBatis.dbcp;

import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import java.sql.SQLException; 
import javax.sql.DataSource;

public class DbManager {
	
	private static final String configFile = "dbcp.properties";
	private static DataSource dataSource;
	static {
		Properties properties = new Properties();
		try {
			ClassLoader classLoader = DbManager.class.getClassLoader();
			properties.load(classLoader.getResourceAsStream(configFile));
			dataSource = BasicDataSourceFactory.createDataSource(properties);
			Connection con = dataSource.getConnection();
			if (con != null) {
				System.out.println("connect success");
			}else{
				System.out.println("con is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		
	}

}

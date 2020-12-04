import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBManager {
	public DBManager() {
		
	}
	public Connection makeDB() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://www.solac.shop:3306";			
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("DB 연결중 ...");
		Connection con = DriverManager.getConnection(url, "공백", "공백");
		System.out.println("DB 연결완료^^");
		return con;
	}
}

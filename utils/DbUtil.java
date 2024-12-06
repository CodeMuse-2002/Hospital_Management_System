
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/hospital";
	private static final String USERNAME = "D2_87183_Chinmayee";
	private static final String PASSWORD = "manager";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}

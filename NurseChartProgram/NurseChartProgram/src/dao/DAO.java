package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {

	public Connection conn;

	public DAO() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("Driver Successfully Imported!");
			getConnection();
		} catch (Exception e) {
			System.out.println("Exception found in importing driver");
		}
	}

	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("DB Successfully Connected!");
			return conn;
		} catch (Exception e) {
			System.out.println("Exception found in connecting DB");
		}
		return null;
	}
}

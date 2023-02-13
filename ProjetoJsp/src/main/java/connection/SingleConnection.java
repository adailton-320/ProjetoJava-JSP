package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String url = "jdbc:postgresql://localhost:5432/dbProjetoJsp";
	private static String user = "postgres";
	private static String senha = "12345";
	private static Connection connection = null;

	static {
		conectar();
	}
	public SingleConnection() {
		conectar();
	}
	
	public static Connection getConection() {
		return connection;
	}

	public static void conectar() {
		try {
			if (connection == null) {

				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

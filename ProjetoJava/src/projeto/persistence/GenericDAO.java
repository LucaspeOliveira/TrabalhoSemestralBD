package projeto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO {
    private Connection con;
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		String dbName = "SistemaEstoque";
		String hostName = "localhost";
		String userName = "sa";
		String password = "123mudar";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		con = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
						hostName, dbName, userName, password)
				);
		return con;
	}
}

package components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import config.main;

public class DatabaseManager {
	public static Connection getConnection() throws SQLException{
		/*MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(main.DBUSERNAME);
		dataSource.setPassword(main.DBPASSWORD);
		dataSource.setServerName(String.format("%s/%s", main.DBURL,main.DBNAME));

		return dataSource.getConnection();*/
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection(String.format("%s/%s", main.DBURL,main.DBNAME), main.DBUSERNAME, main.DBPASSWORD);
	}
	public static void execute(String query) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement(); 
			statement.execute(query);
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(query);
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		
	}
	public static void executeQuery(DatabaseAction dbAct, String query) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement(); 
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				dbAct.doAction(rs);				
			}

			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(query);
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		
	}
}

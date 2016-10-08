package model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * This class allows the connection for the application to an online database
 * 
 * Singleton pattern
 * 
 * @author Gaetan FRANCOIS
 *
 */
public class Connect
{

	// Logger
	private static final Logger LOGGER = Logger.getLogger(Connect.class.getName());

	// Attributes
	private static Connect instance = null;
	private Properties properties;
	private FileInputStream fis;
	private MysqlDataSource dataSource;

	// Constructor
	private Connect()
	{
		properties = new Properties();
		fis = null;
		dataSource = null;

		try
		{
			fis = new FileInputStream("db.properties");
			properties.load(fis);
			dataSource = new MysqlDataSource();
			dataSource.setURL(properties.getProperty("DB_URL"));
			dataSource.setUser(properties.getProperty("DB_USERNAME"));
			dataSource.setPassword(properties.getProperty("DB_PASSWORD"));
		}
		catch(IOException e)
		{
			LOGGER.log(Level.SEVERE, "Cannot get information from file db.properties for database.", e);
		}
	}

	// getInstance
	public static synchronized Connect getInstance()
	{
		if(instance == null)
		{
			instance = new Connect();
		}
		return instance;
	}

	public Connection getConnection()
	{
		Connection connection = null;
		try
		{
			connection = this.dataSource.getConnection();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Connection to database failed.", e);
		}
		return connection;
	}
	
	public void closeConnection() {
		try {
			this.getConnection().close();
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Impossible to close connection to database.", e);
		}
	}
}

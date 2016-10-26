package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import model.business.Person;
import model.util.Connect;
import util.Passwords;
import util.exception.PolymazeException;

/**
 * This class manages links Person objects in the application with table Person
 * in the database
 * 
 * @author Gaetan FRANCOIS
 *
 */
public class PersonDaoImpl implements PersonDao
{

	// Logger
	private static final Logger LOGGER = Logger.getLogger(PersonDaoImpl.class.getName());

	// Queries
	private static final String queryGetPersonByName = "SELECT * FROM Person WHERE name = ?;";
	private static final String queryGetPersonById = "SELECT * FROM Person WHERE idPerson = ?;";
	private static final String queryCreatePerson = "INSERT INTO Person (name, password, salt) VALUES (?, ?, ?);";

	// Methods
	@Override
	public Person getPersonByLogin(String name, String password) throws PolymazeException
	{
		// Person to return
		Person person = null;

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetPersonByName);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				// Verifying the password
				if(Passwords.isExpectedPassword(password.toCharArray(), rs.getBytes(4), rs.getBytes(3)))
				{
					person = new Person(rs.getInt(1), rs.getString(2));
				}
				else
				{
					throw new PolymazeException("Wrong login combination.");
				}
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Person information from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return person;
	}

	@Override
	public Person getPersonByName(String name)
	{
		// Person to return
		Person person = null;

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetPersonByName);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				person = new Person(rs.getInt(1), rs.getString(2));
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Person information from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return person;
	}

	@Override
	public Person getPersonById(Integer id)
	{
		// Person to return
		Person person = null;

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetPersonById);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				person = new Person(rs.getInt(1), rs.getString(2));
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Person information from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return person;
	}

	@Override
	public Person createPerson(String name, String password) throws PolymazeException
	{
		// Person to return
		Person person = null;

		// Field returned by insertion
		Integer idReturned;

		// A PersonDaoImpl
		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		// Salt to mix with password
		byte[] salt = Passwords.getNextSalt();
		byte[] hashedPassword = Passwords.hash(password.toCharArray(), salt);

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			// Verifying if a Person using the name already exists
			if(personDaoImpl.getPersonByName(name) != null)
			{
				throw new PolymazeException("A Person with this name already exists.");
			}
			else
			{
				PreparedStatement statement = connection.prepareStatement(queryCreatePerson,
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, name);
				statement.setBytes(2, hashedPassword);
				statement.setBytes(3, salt);

				// Insert Person in database
				int affectedRows = statement.executeUpdate();

				if(affectedRows == 0)
				{
					throw new SQLException("Creating person failed, no rows affected.");
				}

				try(ResultSet generatedKeys = statement.getGeneratedKeys())
				{
					if(generatedKeys.next())
					{
						idReturned = generatedKeys.getInt(1);
					}
					else
					{
						throw new SQLException("Creating person failed, no ID obtained.");
					}
				}
				person = new Person(idReturned, name);
			}
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to create Person in database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return person;
	}

}

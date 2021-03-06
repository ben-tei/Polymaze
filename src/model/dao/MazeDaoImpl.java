package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import model.business.Maze;
import model.business.Person;
import model.util.Connect;
import util.exception.PolymazeException;
import util.exception.model.business.ContentToStringException;

/**
 * This class implements all the methods from MazeDao
 * 
 * @author Gaetan FRANCOIS
 *
 */
public class MazeDaoImpl implements MazeDao
{

	// Logger
	private static final Logger LOGGER = Logger.getLogger(MazeDaoImpl.class.getName());

	// Queries
	private static final String queryGetMazeById = "SELECT * FROM Maze WHERE idMaze = ?;";
	private static final String queryGetMazeByName = "SELECT * FROM Maze WHERE name = ?;";
	private static final String queryCreateMaze = "INSERT INTO Maze (name, length, width, startX, startY, endX, endY, content, creationDate, idPerson) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String queryDeleteMaze = "DELETE FROM Maze WHERE idMaze = ?;";
	private static final String queryGetMazesByCreator = "SELECT * FROM Maze WHERE idPerson = ?;";
	private static final String queryGetAllMazes = "SELECT * FROM Maze;";

	@Override
	public Maze getMazeById(Integer id)
	{
		// Maze to return
		Maze maze = null;

		// To retrieve the creator thanks to the Person's id
		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetMazeById);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				maze = new Maze(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDate(10),
						personDaoImpl.getPersonById(rs.getInt(11)));
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Maze information from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return maze;
	}

	@Override
	public Maze getMazeByName(String name)
	{
		// Maze to return
		Maze maze = null;

		// To retrieve the creator thanks to the Person's id
		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetMazeByName);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				maze = new Maze(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDate(10),
						personDaoImpl.getPersonById(rs.getInt(11)));
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Maze information from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return maze;
	}

	@Override
	public Maze createMaze(Maze maze) throws PolymazeException
	{
		// Maze to return
		Maze myMaze = null;

		// Id of the created maze
		Integer id = null;

		// A MazeDaoImpl
		MazeDaoImpl mazeDaoImpl = new MazeDaoImpl();

		// Content of the maze parsed into String
		String strContent = null;
		try
		{
			strContent = maze.contentToString();
		}
		catch(ContentToStringException e)
		{
			LOGGER.log(Level.SEVERE, "Maze's content is null.", e);
		}

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			// Verifying if a Maze with the same name already exists
			if(mazeDaoImpl.getMazeByName(maze.getName()) != null)
			{
				throw new PolymazeException("A Maze with this name already exists.");
			}
			else
			{
				PreparedStatement statement = connection.prepareStatement(queryCreateMaze,
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, maze.getName());
				statement.setInt(2, maze.getLength());
				statement.setInt(3, maze.getWidth());
				statement.setInt(4, maze.getStartX());
				statement.setInt(5, maze.getStartY());
				statement.setInt(6, maze.getEndX());
				statement.setInt(7, maze.getEndY());
				statement.setString(8, strContent);
				statement.setDate(9, maze.getCreationDate());
				statement.setInt(10, maze.getCreator().getId());

				// Insert Maze in database
				int affectedRows = statement.executeUpdate();

				if(affectedRows == 0)
				{
					throw new SQLException("Creating maze failed, no rows affected.");
				}

				try(ResultSet generatedKeys = statement.getGeneratedKeys())
				{
					if(generatedKeys.next())
					{
						id = generatedKeys.getInt(1);
					}
					else
					{
						throw new SQLException("Creating maze failed, no ID obtained.");
					}
				}

				myMaze = new Maze(id, maze.getName(), maze.getLength(), maze.getWidth(), maze.getStartX(),
						maze.getStartY(), maze.getEndX(), maze.getEndY(), strContent, maze.getCreationDate(),
						maze.getCreator());
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to create Maze in database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return myMaze;
	}

	@Override
	public boolean deleteMaze(Integer idMaze)
	{
		// Boolean to return
		boolean isDeleted = false;

		// Connection to database
		Connection connection = Connect.getInstance().getConnection();

		try
		{

			PreparedStatement statement = connection.prepareStatement(queryDeleteMaze);
			statement.setInt(1, idMaze);

			// Delete Maze from database
			int affectedRows = statement.executeUpdate();

			if(affectedRows == 0)
			{
				throw new SQLException("Deleting maze failed, no row affected.");
			}

			isDeleted = true;

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to delete Maze from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return isDeleted;
	}

	@Override
	public List<Maze> getMazesByCreator(Person person)
	{
		// List of mazes to return
		List<Maze> listMazes = new ArrayList<Maze>();

		// Connection to database
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetMazesByCreator);
			statement.setInt(1, person.getId());
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				listMazes.add(new Maze(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDate(10), person));
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Mazes from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return listMazes;
	}

	@Override
	public List<Maze> getAllMazes()
	{
		// List of mazes to return
		List<Maze> listMazes = new ArrayList<Maze>();

		// To retrieve the creator thanks to the Person's id
		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		// Connection to database
		Connection connection = Connect.getInstance().getConnection();

		try
		{
			PreparedStatement statement = connection.prepareCall(queryGetAllMazes);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				listMazes.add(new Maze(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDate(10),
						personDaoImpl.getPersonById(rs.getInt(11))));
			}

			connection.close();
		}
		catch(SQLException e)
		{
			LOGGER.log(Level.SEVERE, "Impossible to get Mazes from database.", e);
		}
		finally
		{
			Connect.getInstance().closeConnection();
		}

		return listMazes;
	}

}

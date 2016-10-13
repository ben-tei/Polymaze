package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import model.business.Maze;
import model.business.Person;
import model.util.Connect;
import util.PolymazeException;

public class MazeDaoImpl implements MazeDao {
	
	// Logger
	private static final Logger LOGGER = Logger.getLogger(Connect.class.getName());
	
	// Queries
	private static final String queryGetMazeByname = "SELECT * FROM Maze WHERE name = ?;";
	private static final String queryCreateMaze = "INSERT INTO Maze (name, length, width, content, creationDate, idPerson) VALUES (?, ?, ?, ?, ?, ?);";

	@Override
	public Maze getMazeByName(String name) {
		// Maze to return
		Maze maze = null;
		
		// To retrieve the creator thanks to the Person's id
		PersonDaoImpl personDaoImpl = new PersonDaoImpl();
		
		// Connection
		Connection connection = Connect.getInstance().getConnection();
		
		try {
			PreparedStatement statement = connection.prepareCall(queryGetMazeByname);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				maze = new Maze(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDate(6), personDaoImpl.getPersonById(rs.getInt(7)));
			}
			
			connection.close();
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Impossible to get Maze information from database.", e);
		}
		finally {
			Connect.getInstance().closeConnection();
		}
		
		return maze;
	}

	@Override
	public Maze createMaze(Maze maze) throws PolymazeException {
		// A MazeDaoImpl
		MazeDaoImpl mazeDaoImpl = new MazeDaoImpl();

		// Connection
		Connection connection = Connect.getInstance().getConnection();

		try {
			// Verifying if a Maze with the same name already exists
			if (mazeDaoImpl.getMazeByName(maze.getName()) != null) {
				throw new PolymazeException("A Maze with this name already exists.");
			}
			else {
				PreparedStatement statement = connection.prepareStatement(queryCreateMaze, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, maze.getName());
				statement.setInt(2, maze.getLength());
				statement.setInt(3, maze.getWidth());
				statement.setString(4, maze.getContent());
				statement.setDate(5, maze.getCreationDate());
				statement.setInt(6, maze.getCreator().getId());

				// Insert Maze in database
				int affectedRows = statement.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Creating maze failed, no rows affected.");
				}

				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedKeys.getInt(1);
					}
					else {
						throw new SQLException("Creating maze failed, no ID obtained.");
					}
				}
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Impossible to create Person in database.", e);
		}
		finally {
			Connect.getInstance().closeConnection();
		}

		return maze;
	}

	@Override
	public boolean deleteMaze(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Maze> getMazesByCreator(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Maze> getAllMazes() {
		// TODO Auto-generated method stub
		return null;
	}

}

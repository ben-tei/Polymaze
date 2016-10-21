package model.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.business.Maze;
import model.business.Person;
import util.exception.PolymazeException;

public final class DataBaseFacade
{
	// Logger
	private static final Logger LOGGER = Logger.getLogger(DataBaseFacade.class.getName());

	// Attributes
	private static MazeDao mazeDao = new MazeDaoImpl();
	private static PersonDao personDao = new PersonDaoImpl();

	// Constructor
	private DataBaseFacade()
	{
		super();
	}

	// Maze
	/**
	 * Gets a Maze by its id
	 * 
	 * @param id
	 *            the Maze's id
	 * @return the retrieved Maze
	 */
	public static Maze getMazeById(Integer id)
	{
		return mazeDao.getMazeById(id);
	}

	/**
	 * Gets a Maze by its name
	 * 
	 * @param name
	 *            the Maze's name
	 * @return the retrieved Maze
	 */
	public static Maze getMazeByName(String name)
	{
		return mazeDao.getMazeByName(name);
	}

	/**
	 * Creates a new Maze
	 * 
	 * @param maze
	 *            the Maze to create
	 * @throws PolymazeException
	 */
	public static Maze createMaze(Maze maze) throws PolymazeException
	{
		Maze myMaze = null;
		try
		{
			myMaze = mazeDao.createMaze(maze);
		}
		catch(PolymazeException e)
		{
			LOGGER.log(Level.SEVERE, "createMaze failed.", e);
		}
		return myMaze;
	}

	/**
	 * Deletes a Maze
	 * 
	 * @param idMaze
	 *            the id of the Maze to delete
	 * @return true if the Maze has been deleted, false otherwise
	 */
	public static boolean deleteMaze(Integer idMaze)
	{
		return mazeDao.deleteMaze(idMaze);
	}

	/**
	 * Gets all Mazes of a Person
	 * 
	 * @param creator
	 *            the creator of the Maze
	 * @return a list of Maze
	 */
	public static List<Maze> getMazesByCreator(Person creator)
	{
		return mazeDao.getMazesByCreator(creator);
	}

	/**
	 * Gets all the existing Mazes
	 * 
	 * @return a list of all the Mazes
	 */
	public static List<Maze> getAllMazes()
	{
		return mazeDao.getAllMazes();
	}

	// Person
	/**
	 * A Person tries to login
	 * 
	 * @param login
	 *            the Person's name
	 * @param password
	 *            the Person's password
	 * @return
	 */
	public static Person tryLogin(String login, String password)
	{
		Person myPerson = null;
		try
		{
			myPerson = personDao.getPersonByLogin(login, password);
		}
		catch(PolymazeException e)
		{
			LOGGER.log(Level.SEVERE, "tryLogin failed.", e);
		}
		return myPerson;
	}

	/**
	 * Get a person thanks to its name
	 * 
	 * @param name
	 *            the Person's name
	 * @return the retrieved Person
	 */
	public static Person getPersonByName(String name)
	{
		return personDao.getPersonByName(name);
	}

	/**
	 * Get a person thanks to its id
	 * 
	 * @param id
	 *            the Person's id
	 * @return the retrieved Person
	 */
	public static Person getPersonById(Integer id)
	{
		return personDao.getPersonById(id);
	}

	/**
	 * @param login
	 *            the Person's name
	 * @param password
	 *            the Person's password
	 * @return
	 */
	public static Person createPerson(String login, String password)
	{
		Person myPerson = null;
		try
		{
			myPerson = personDao.createPerson(login, password);
		}
		catch(PolymazeException e)
		{
			LOGGER.log(Level.SEVERE, "createUser failed.", e);
		}
		return myPerson;
	}

}

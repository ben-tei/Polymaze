
package model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
import model.factory.MazeFactory;
import util.exception.PolymazeException;

/**
 * This class manages a Maze Each method communicates with DataBaseFacade which
 * makes the link with the Database
 *
 */
public class MazeManager
{
	// Logger
	private static final Logger LOGGER = Logger.getLogger(MazeManager.class.getName());

	private Maze maze;
	private List<Maze> mazeList;
	private MazeFactory mazeFactory;
	// TODO: What to do with the attribute personManager?
	//private PersonManager personManager; // maze manager needs a reference to the user manager to instanciate mazes and to know current user

	/**
	 * Default constructor
	 */
	public MazeManager()
	{
		super();
		//this.personManager = personManager;
	}

	/**
	 * Method to create a Maze. This method creates a maze using the currently
	 * selected algorithm, add it to the list of existing mazes, and adds it to
	 * the DB.
	 * 
	 * @param name
	 *            the Maze's name
	 * @param length
	 *            the Maze's length
	 * @param width
	 *            the Maze's width
	 * @param creator
	 *            the Person who created the Maze
	 * @throws PolymazeException
	 */
	public void generateMaze(String name, Integer length, Integer width, Person creator) throws PolymazeException
	{
		Maze tempMaze = mazeFactory.generateMaze(name, length, width, creator);
		try
		{
			DataBaseFacade.createMaze(tempMaze); // this tries to add the maze to the BD
			mazeList.add(tempMaze);
		}
		catch(PolymazeException e)
		{
			LOGGER.log(Level.SEVERE, "generateMaze failed.", e);
		}
	}

	/**
	 * This method sets a Maze by its id
	 * 
	 * @param id
	 *            the Maze's id
	 * @return true if a Maze with this id exists, false otherwise
	 */
	public boolean setMazeById(Integer id)
	{
		boolean bool = false;
		Maze myMaze = DataBaseFacade.getMazeById(id);

		if(myMaze != null)
		{
			this.setMaze(myMaze);
			bool = true;
		}
		else
		{
			bool = false;
		}

		return bool;
	}

	/**
	 * This method sets a Maze by its name
	 * 
	 * @param name
	 *            the Maze's name
	 * @return true if a Maze with this name exists, false otherwise
	 */
	public boolean setMazeByName(String name)
	{
		boolean bool = false;
		Maze myMaze = DataBaseFacade.getMazeByName(name);

		if(myMaze != null)
		{
			this.setMaze(myMaze);
			bool = true;
		}
		else
		{
			bool = false;
		}

		return bool;
	}

	/**
	 * Deletes a Maze
	 * 
	 * @param id
	 *            the Maze's id
	 * @return true if the Maze has been deleted, false otherwise
	 */
	public boolean deleteMaze(Integer id)
	{
		return DataBaseFacade.deleteMaze(id);
	}

	/**
	 * Sets the List of Mazes of a Person
	 * 
	 * @param creator
	 */
	public void setMazesByCreator(Person creator)
	{
		this.setMazeList(DataBaseFacade.getMazesByCreator(creator));
	}

	/**
	 * Sets the List of all Mazes
	 */
	public void setAllMazes()
	{
		this.setMazeList(DataBaseFacade.getAllMazes());
	}

	// Getters
	public Maze getMaze()
	{
		return this.maze;
	}

	public List<Maze> getMazeList()
	{
		return this.mazeList;
	}

	public MazeFactory getMazeFactory()
	{
		return this.mazeFactory;
	}

	/*
	 * public PersonManager getPersonManager() { return personManager; }
	 */

	// Setters
	public void setMaze(Maze maze)
	{
		this.maze = maze;
	}

	public void setMazeList(List<Maze> list)
	{
		this.mazeList = list;
	}

	public void setMazeFactory(MazeFactory mazeFactory)
	{
		this.mazeFactory = mazeFactory;
	}

	/*
	 * public void setPersonManager(PersonManager personManager) {
	 * this.personManager = personManager; }
	 */

}

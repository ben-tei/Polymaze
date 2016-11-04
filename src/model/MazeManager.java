
package model;

import java.util.ArrayList;
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
	private List<Maze> allMazesList;
	private List<Maze> creatorMazesList;
	private MazeFactory mazeFactory;
	// TODO: What to do with the attribute personManager?
	//private PersonManager personManager; // maze manager needs a reference to the user manager to instanciate mazes and to know current user

	/**
	 * Default constructor
	 */
	public MazeManager()
	{
		super();
		this.mazeFactory = new MazeFactory();
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
			this.allMazesList.add(tempMaze);
			this.creatorMazesList.add(tempMaze);
		}
		catch(PolymazeException e)
		{
			LOGGER.log(Level.SEVERE, "generateMaze failed.", e);
			throw e;
		}
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
	 * @param startX
	 *            the Maze's starting point coordinate in X
	 * @param startY
	 *            the Maze's starting point coordinate in Y
	 * @param endX
	 *            the Maze's ending point coordinate in X
	 * @param endY
	 *            the Maze's ending point coordinate in Y
	 * @param creator
	 *            the Person who created the Maze
	 * @throws PolymazeException
	 */
	public void generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX,
			int endY, Person creator) throws PolymazeException
	{
		Maze tempMaze = mazeFactory.generateMazeWithStartEnd(name, length, width, startX, startY, endX, endY, creator);
		try
		{
			DataBaseFacade.createMaze(tempMaze); // this tries to add the maze to the BD
			this.allMazesList.add(tempMaze);
			this.creatorMazesList.add(tempMaze);
		}
		catch(PolymazeException e)
		{
			LOGGER.log(Level.SEVERE, "generateMaze failed.", e);
			throw e;
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
		int i = 0;
		boolean find = false;
		while(i < this.creatorMazesList.size() && !find)
		{
			if(this.creatorMazesList.get(i).getId() == id)
			{
				this.creatorMazesList.remove(i);
				find = true;
			}
			i++;
		}

		i = 0;
		find = false;
		while(i < this.allMazesList.size() && !find)
		{
			if(this.allMazesList.get(i).getId() == id)
			{
				this.allMazesList.remove(i);
				find = true;
			}
			i++;
		}

		return DataBaseFacade.deleteMaze(id);
	}

	/**
	 * Sets the List of Mazes of a Person
	 * 
	 * @param creator
	 */
	public void setMazesByCreator(Person creator)
	{
		this.creatorMazesList = new ArrayList<Maze>();

		for(int i = 0; i < this.allMazesList.size(); i++)
		{
			if(this.allMazesList.get(i).getCreator().getId() == creator.getId())
			{
				this.creatorMazesList.add(this.allMazesList.get(i));
			}
		}
	}

	/**
	 * Sets the List of all Mazes
	 */
	public void setAllMazes()
	{
		this.setAllMazesList(DataBaseFacade.getAllMazes());
	}

	// Getters
	public Maze getMaze()
	{
		return this.maze;
	}

	public List<Maze> getAllMazesList()
	{
		return this.allMazesList;
	}

	public List<Maze> getCreatorMazesList()
	{
		return this.creatorMazesList;
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

	public void setAllMazesList(List<Maze> list)
	{
		this.allMazesList = list;
	}

	public void setCreatorMazesList(List<Maze> list)
	{
		this.creatorMazesList = list;
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


package model;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
import model.factory.MazeFactory;
import util.exception.PolymazeException;

/**
 * This class manages a Maze
 * Each method communicates with DataBaseFacade which makes the link with the Database
 *
 */
public class MazeManager
{
	// Logger
	private static final Logger LOGGER = Logger.getLogger(MazeManager.class.getName());
	
	private Maze maze;
	private Vector<Maze> mazeList;
	private MazeFactory mazeFactory;
	//private PersonManager personManager; // maze manager needs a reference to the user manager to instanciate mazes and to know current user

	/**
	 * Default constructor
	 */
	public MazeManager(PersonManager personManager)
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
	 * This method gets a Maze by its id
	 * 
	 * @param id
	 * 			the Maze's id
	 * @return true if a Maze with this id exists, false otherwise
	 */
	public boolean getMazeById(Integer id) {
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
	 * This method gets a Maze by its name
	 * 
	 * @param name
	 * 			the Maze's name
	 * @return true if a Maze with this name exists, false otherwise
	 */
	public boolean getMazeByname(String name) {
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
	 * @param id
	 * 			the Maze's id
	 * @return true if the Maze has been deleted, false otherwise
	 */
	public boolean deleteMaze(Integer id) {
		return DataBaseFacade.deleteMaze(id);
	}
	
	/**
	 * Gets the List of Mazes of a Person
	 * @param creator
	 */
	public void getMazesByCreator(Person creator) {
		this.setMazeList((Vector<Maze>) DataBaseFacade.getMazesByCreator(creator));
	}
	
	/**
	 * Gets the List of all Mazes
	 */
	public void getAllMazes() {
		this.setMazeList((Vector<Maze>) DataBaseFacade.getAllMazes());
	}

	// Getters
	public Maze getMaze() {
		return maze;
	}
	
	public Vector<Maze> getMazeList() {
		return mazeList;
	}

	public MazeFactory getMazeFactory() {
		return mazeFactory;
	}

	/*public PersonManager getPersonManager() {
		return personManager;
	}*/

	// Setters
	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	public void setMazeList(Vector<Maze> mazeList) {
		this.mazeList = mazeList;
	}

	public void setMazeFactory(MazeFactory mazeFactory) {
		this.mazeFactory = mazeFactory;
	}

	/*public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}*/

}

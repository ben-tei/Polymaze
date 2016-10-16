package model;

import java.sql.Date;
import java.util.Vector;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
import model.factory.MazeFactory;
import util.PolymazeException;

public class MazeManager
{
	private Vector<Maze> mazeList_;
	private MazeFactory mazeFactory_;
	private UserManager userManager_; // maze manager needs a reference to the user manager to instanciate mazes and to know current user

	/**
	 * Default constructor
	 */
	public MazeManager(UserManager userManager)
	{
		super();
		userManager_ = userManager;
	}

	/**
	 * Method to create a Maze. This method creates a maze using the currently
	 * selected algorithm, add it to the list of existing mazes, and adds it to
	 * the DB.
	 * 
	 * @param name:
	 *            the Maze's name
	 * @param length:
	 *            the Maze's length
	 * @param width:
	 *            the Maze's width
	 * @param creator:
	 *            the Person who created the Maze
	 * @throws PolymazeException
	 */
	public void generateMaze(String name, Integer length, Integer width, Person creator) throws PolymazeException
	{
		Maze tempMaze = mazeFactory_.generateMaze(name, length, width, creator);
		try
		{
			DataBaseFacade.createMaze(tempMaze); // this tries to add the maze to the BD
			mazeList_.add(tempMaze);
		}
		catch(PolymazeException e)
		{
			// Do not add maze to the list and throw error for ui to handle it.
			throw e;
		}
	}

}

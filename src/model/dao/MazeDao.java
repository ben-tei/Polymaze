package model.dao;

import java.util.List;

import model.business.Maze;
import model.business.Person;
import util.exception.PolymazeException;

/**
 * Interface for a Maze Data Access Object
 * 
 * @author Gaetan FRANCOIS
 *
 */
public interface MazeDao
{
	/**
	 * This method retrieves a Maze in the database thanks to its id
	 * 
	 * @param id
	 *            the Maze's id
	 * @return the retrieved Maze
	 */
	public Maze getMazeById(Integer id);

	/**
	 * This method retrieves a Maze in the database thanks to its name
	 * 
	 * @param name
	 *            the Maze's name
	 * @return the retrieved Maze
	 */
	public Maze getMazeByName(String name);

	/**
	 * This method creates a Maze in the database
	 * 
	 * @param maze
	 *            the Maze to create
	 * @return the created Maze
	 */
	public Maze createMaze(Maze maze) throws PolymazeException;

	/**
	 * This method deletes a Maze in the database
	 * 
	 * @param idMaze
	 *            the id of the Maze to delete
	 * @return true if the deletion is successful, false otherwise
	 */
	public boolean deleteMaze(Integer idMaze);

	/**
	 * This method retrieves all mazes of a creator
	 * 
	 * @param person
	 *            the creator
	 * @return the list of retrieved mazes
	 */
	public List<Maze> getMazesByCreator(Person person);

	/**
	 * This method retrieves all mazes on the DB
	 * 
	 * @param person
	 *            the creator
	 * @return the list of retrieved mazes
	 */
	public List<Maze> getAllMazes();
}

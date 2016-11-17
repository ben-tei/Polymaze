package model.factory;

import model.business.Maze;
import model.business.Person;

/**
 * Strategy base class. It is the parent of all concrete strategies
 *         
 * @author Rodolphe
 */
public abstract class MazeFactoryStrategy
{
	protected MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Default;

	/**
	 * Method to get the name of the strategy, different for each concrete
	 * strategies
	 */
	public MazeFactoryStrategyName getName()
	{
		return NAME;
	}

	/**
	 * Method to create a Maze. Implementation is in child classes.
	 * 
	 * @param name
	 *            the Maze's name
	 * @param length
	 *            the Maze's length
	 * @param width
	 *            the Maze's width
	 * @param creator
	 *            the Person who created the Maze
	 */
	public abstract Maze generateMaze(String name, Integer length, Integer width, Person creator);

	/**
	 * Method to create a Maze. Implementation is in child classes.
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
	 */
	public abstract Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY,
			int endX, int endY, Person creator);
}

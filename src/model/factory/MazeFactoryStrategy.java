package model.factory;

import model.business.Maze;
import model.business.Person;

public abstract class MazeFactoryStrategy
{
	MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Default;

	/**
	 * Method to create a Maze. Implementation is in child classes.
	 * 
	 * @param name:
	 *            the Maze's name
	 * @param length:
	 *            the Maze's length
	 * @param width:
	 *            the Maze's width
	 * @param creator:
	 *            the Person who created the Maze
	 */
	public Maze generateMaze(String name, Integer length, Integer width, Person creator)
	{
		return null;
	}
}

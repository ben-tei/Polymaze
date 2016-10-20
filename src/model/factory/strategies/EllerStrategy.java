package model.factory.strategies;

import model.business.Maze;
import model.business.Person;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

public class EllerStrategy extends MazeFactoryStrategy
{
	MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Eller;

	/**
	 * Method to create a Maze using Eller's algorithm.
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
	@Override
	public Maze generateMaze(String name, Integer length, Integer width, Person creator) {
		// TODO EllerStrategy Auto-generated method stub
		return null;
	}
	
	/**
	 * Method to create a Maze using Eller's algorithm.
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
	@Override
	public Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX, int endY,
			Person creator) {
		// TODO EllerStrategy Auto-generated method stub
		return null;
	}
}

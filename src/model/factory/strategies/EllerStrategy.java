package model.factory.strategies;

import model.business.Maze;
import model.business.Person;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

public class EllerStrategy extends MazeFactoryStrategy {
	static MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Eller; 
	
	/**
	 * Method to create a Maze using Euler's algorithm.
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
	public Maze generateMaze(String name, Integer length, Integer width, Person creator) {
		// TODO
		return null;
	}
}

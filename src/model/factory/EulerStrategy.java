package model.factory;

import model.business.Maze;
import model.business.Person;

public class EulerStrategy extends MazeFactoryStrategy {
	static MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Euler; 
	
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

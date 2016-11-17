package model.factory;

import model.business.Maze;
import model.business.Person;
import model.factory.strategies.BacktrackStrategy;
import model.factory.strategies.EllerStrategy;
import model.factory.strategies.KruskalStrategy;
import model.factory.strategies.PrimStrategy;

/**
 * Factory class for creating maze.
 *         
 * @author Rodolphe
 */
public class MazeFactory
{
	private MazeFactoryStrategy currentStrategy;

	/**
	 * class that changes the strategy to be used during maze creation
	 * 
	 * @param name : the name of the strategy to use. The name is an enum of type MazeFactoryStrategyName 
	 */
	public void setStrategy(MazeFactoryStrategyName name)
	{
		switch(name)
		{
			case Backtrack:
				this.currentStrategy = new BacktrackStrategy();
				break;
			case Eller:
				this.currentStrategy = new EllerStrategy();
				break;
			case Kruskal:
				this.currentStrategy = new KruskalStrategy();
				break;
			case Prim:
				this.currentStrategy = new PrimStrategy();
				break;
			default:
				this.currentStrategy = new BacktrackStrategy(); // in case of bug, use backtrack
		}
	}

	/**
	 * getter for the strategy name
	 * 
	 * @return MazeFactoryStrategyName : the name of the current strategy
	 */
	public MazeFactoryStrategyName getStrategyName()
	{
		return currentStrategy.getName();
	}

	/**
	 * main method to generate mazes
	 * 
	 * @param name : the name of the maze that will be created
	 * @param length : the length (or height) in number of cells of the maze that will be created
	 * @param width : the width in number of cells of the maze that will be created
	 * @param creator : the Person object corresponding to the creator of the maze. It is the current user of the application
	 * 
	 * @return Maze : the maze generated
	 */
	public Maze generateMaze(String name, Integer length, Integer width, Person creator)
	{
		return currentStrategy.generateMaze(name, length, width, creator);
	}

	/**
	 * secondary method to generate mazes. It allows to specify a start and end point
	 * 
	 * @param name : the name of the maze that will be created
	 * @param length : the length (or height) in number of cells of the maze that will be created
	 * @param width : the width in number of cells of the maze that will be created
	 * @param creator : the Person object corresponding to the creator of the maze. It is the current user of the application
	 * @param startX : the x coordinate of the start point
	 * @param startY : the y coordinate of the start point
	 * @param endX : the x coordinate of the end point
	 * @param endY : the y coordinate of the end point
	 * 
	 * @return Maze : the maze generated
	 */
	public Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX,
			int endY, Person creator)
	{
		return currentStrategy.generateMazeWithStartEnd(name, length, width, startX, startY, endX, endY, creator);
	}
}

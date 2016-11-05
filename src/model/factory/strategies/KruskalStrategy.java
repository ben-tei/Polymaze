package model.factory.strategies;

import java.sql.Date;
import java.util.Calendar;

import model.business.Maze;
import model.business.Person;
import model.business.cell.KruskalCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

public class KruskalStrategy extends MazeFactoryStrategy
{
	private Maze maze;
	private KruskalCell[][] mazeArray;
	
	/**
	 * Default constructor
	 */
	public KruskalStrategy()
	{
		super();
		NAME = MazeFactoryStrategyName.Kruskal;
	}

	/**
	 * Method to create a Maze using Kruskal's algorithm.
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
	public Maze generateMaze(String name, Integer length, Integer width, Person creator)
	{
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		
		this.maze = new Maze(name, length, width, timeNow, creator);
		this.initializeMazeArray();
		
		return null;
	}

	/**
	 * Method to create a Maze using Kruskal's algorithm
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
	public Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX,
			int endY, Person creator)
	{
		// TODO KruskalStrategy Auto-generated method stub
		return null;
	}
	
	private void initializeMazeArray()
	{
		this.mazeArray = new KruskalCell[this.maze.getWidth()][this.maze.getLength()];

		for(int y = 0; y < this.maze.getLength(); y++)
		{
			for(int x = 0; x < this.maze.getWidth(); x++)
			{
				this.mazeArray[x][y] = new KruskalCell(x, y);
			}
		}
	}
}

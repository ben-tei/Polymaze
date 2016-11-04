package model.factory.strategies;

import java.sql.Date;
import java.util.Calendar;

import model.business.Maze;
import model.business.Person;
import model.business.cell.BacktrackCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

/**
 * @author Loic This class generate a Maze by using the recursive backtrack
 *         Strategy.
 * @see <a href=
 *      "http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking">
 *      Recursive BAcktracking</>
 */
public class BacktrackStrategy extends MazeFactoryStrategy
{

	// attributes
	private Maze maze;
	private BacktrackCell[][] mazeArray; // BacktrackCell[x][y] used locally to generate the maze.

	/**
	 * Default constructor
	 */
	public BacktrackStrategy()
	{
		super();
		NAME = MazeFactoryStrategyName.Backtrack;
	}

	/**
	 * Method to create a Maze using Backtrack method.
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
		// Get current date with format YYYY-MM-DD
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		
		Long timerStart = System.nanoTime();
		
		this.maze = new Maze(name, length, width, timeNow, creator);

		this.initializeMazeArray();
		this.exploreMaze(this.maze.getStartX(), this.maze.getStartY());
		this.maze.setContent(this.mazeArray);

		System.out.println("Maze of size " + width +"*" + length
				+ " generated in (ms) : " + (System.nanoTime() - timerStart)/1000000 
				+ " with " + MazeFactoryStrategyName.Backtrack.name());
		return this.maze;
	}

	/**
	 * Method to create a Maze using Backtrack method.
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
		// Get current date with format YYYY-MM-DD
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		
		Long timerStart = System.nanoTime();
		
		this.maze = new Maze(name, length, width, startX, startY, endX, endY, timeNow, creator);

		this.initializeMazeArray();
		this.exploreMaze(this.maze.getStartX(), this.maze.getStartY());
		this.maze.setContent(this.mazeArray);


		System.out.println("Maze of size " + width +"*" + length
				+ " generated in (ms) : " + (System.nanoTime() - timerStart)/1000000 
				+ " with " + MazeFactoryStrategyName.Backtrack.name());
		return this.maze;
	}

	/**
	 * Recursive method to "explore" all unvisited neighbour of the current
	 * Cell.
	 * 
	 * @param x
	 *            int of the current position
	 * @param Y
	 *            int of the current position
	 */
	private void exploreMaze(int x, int y)
	{
		this.mazeArray[x][y].setBtCellVisited(true);

		while(this.getNonVisitedNeighbour(this.mazeArray, x, y).length() > 0)
		{
			// prepare for the next step
			String currentNeighbour = this.getNonVisitedNeighbour(this.mazeArray, x, y);
			char direction = currentNeighbour.charAt((int) (Math.random() * currentNeighbour.length()));
			if(direction == 'N')
			{
				this.mazeArray[x][y].setWallNorth(false);
				this.mazeArray[x][y - 1].setWallSouth(false);
				this.exploreMaze(x, y - 1);
			}
			else if(direction == 'S')
			{
				this.mazeArray[x][y].setWallSouth(false);
				this.mazeArray[x][y + 1].setWallNorth(false);
				this.exploreMaze(x, y + 1);
			}
			else if(direction == 'E')
			{
				this.mazeArray[x][y].setWallEast(false);
				this.mazeArray[x + 1][y].setWallWest(false);
				this.exploreMaze(x + 1, y);
			}
			else if(direction == 'W')
			{
				this.mazeArray[x][y].setWallWest(false);
				this.mazeArray[x - 1][y].setWallEast(false);
				this.exploreMaze(x - 1, y);
			}
		}
	}

	/**
	 * @param mazeArray2
	 *            Cell[][] the array of the maze
	 * @param int
	 *            x current x position
	 * @param int
	 *            y current y position
	 * @return String All directions for possible non visited neighbour. order : N E S W
	 */
	private String getNonVisitedNeighbour(BacktrackCell[][] mazeArray2, int x, int y)
	{
		String result = "";
		boolean north = true, south = true, east = true, west = true;

		// if we are on the left or the left cell is already visited
		if(x == 0 || (mazeArray2[x - 1][y]).isBtCellVisited())
		{
			west = false;
		}
		// if we are on the right or the right cell is already visited
		if(x == this.maze.getWidth() - 1 || (mazeArray2[x + 1][y]).isBtCellVisited())
		{
			east = false;
		}
		// if we are on the top or the above cell is already visited
		if(y == 0 || (mazeArray2[x][y - 1]).isBtCellVisited())
		{
			north = false;
		}
		// if we are on the bottom or the under cell is already visited
		if(y == this.maze.getLength() - 1 || (mazeArray2[x][y + 1]).isBtCellVisited())
		{
			south = false;
		}

		if(north)
			result = result + "N";
		if(east)
			result = result + "E";
		if(south)
			result = result + "S";
		if(west)
			result = result + "W";

		return result;
	}

	/**
	 * Initialize the private attribute mazeArray of type :
	 * BacktrackCell[width][length]
	 */
	private void initializeMazeArray()
	{
		this.mazeArray = new BacktrackCell[this.maze.getWidth()][this.maze.getLength()];

		for(int y = 0; y < this.maze.getLength(); y++)
		{
			for(int x = 0; x < this.maze.getWidth(); x++)
			{
				this.mazeArray[x][y] = new BacktrackCell(x, y);
			}
		}
	}
}

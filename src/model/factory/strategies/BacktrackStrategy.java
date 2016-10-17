package model.factory.strategies;

import model.business.Maze;
import model.business.Person;
import model.business.cell.BacktrackCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

public class BacktrackStrategy extends MazeFactoryStrategy
{
	static MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Backtrack;

	// attribute
	private Maze maze;
	private BacktrackCell[][] mazeArray; // BacktrackCell[][] used locally to generate the maze.

	/**
	 * Method to create a Maze using Backtrack method.
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
		this.maze = new Maze(name, length, width, null, creator);

		this.initializeMazeArray();
		this.exploreMaze(this.maze.getStartX(), this.maze.getStartY());
		this.maze.setContent(this.mazeArray);

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
	public void exploreMaze(int x, int y)
	{
		this.mazeArray[x][y].setVisited(true);

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
	 * @return String All directions for possible non visited neighbour.
	 */
	private String getNonVisitedNeighbour(BacktrackCell[][] mazeArray2, int x, int y)
	{
		String result = "";
		boolean north = true, south = true, east = true, west = true;

		// if we are on the left or the left cell is already visited
		if(x == 0 || (mazeArray2[x - 1][y]).isVisited())
		{
			west = false;
		}
		// if we are on the right or the right cell is already visited
		if(x == this.maze.getWidth() - 1 || (mazeArray2[x + 1][y]).isVisited())
		{
			east = false;
		}
		// if we are on the top or the above cell is already visited
		if(y == 0 || (mazeArray2[x][y - 1]).isVisited())
		{
			north = false;
		}
		// if we are on the bottom or the under cell is already visited
		if(y == this.maze.getLength() - 1 || (mazeArray2[x][y + 1]).isVisited())
		{
			south = false;
		}

		if(north)
			result = result + "N";
		if(south)
			result = result + "S";
		if(east)
			result = result + "E";
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

	//	public void drawMaze() {
	//		int outputHeight = 2 * this.maze.getLength() + 1;
	//		int outputWidth = 2 * this.maze.getWidth() + 1;
	//
	//		char[][] outputMaze = new char[outputWidth][outputHeight];
	//
	//		for (int y = 0; y < outputHeight; y++) {
	//			for (int x = 0; x < outputWidth; x++) {
	//
	//				if (y == 0 || y == outputHeight - 1 || x == 0
	//						|| x == outputWidth - 1) {
	//					// border wall
	//					outputMaze[x][y] = 'M';
	//				} else if (y % 2 == 0 && x % 2 == 0) {
	//					// intern wall
	//					outputMaze[x][y] = 'M';
	//				}
	//
	//				if (y % 2 == 1 && x % 2 == 1) {
	//					outputMaze[x][y] = ' ';
	//					if (this.mazeArray[(x - 1) / 2][(y - 1) / 2].isWallEast()) {
	//						outputMaze[x + 1][y] = 'M';
	//					} else {
	//						outputMaze[x + 1][y] = ' ';
	//					}
	//
	//					if (this.mazeArray[(x - 1) / 2][(y - 1) / 2].isWallSouth()) {
	//						outputMaze[x][y + 1] = 'M';
	//					} else {
	//						outputMaze[x][y + 1] = ' ';
	//					}
	//				}
	//			}
	//		}
	//
	//		outputMaze[this.maze.getStartX() * 2 + 1][this.maze.getStartY() * 2 + 1] = 'S';
	//		outputMaze[this.maze.getEndX() * 2 + 1][this.maze.getEndY() * 2 + 1] = 'E';
	//
	//		// now we can print it.
	//		for (int y = 0; y < outputHeight; y++) {
	//			for (int x = 0; x < outputWidth; x++) {
	//				System.out.print(outputMaze[x][y]);
	//			}
	//			System.out.println("");
	//		}
	//
	//	}

}
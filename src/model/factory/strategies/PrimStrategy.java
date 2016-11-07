package model.factory.strategies;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import model.business.Maze;
import model.business.Person;
import model.business.cell.PrimCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

/**
 * @author Aurélien : This class generate a Maze by using the Prime Strategy.
 * @see <a href=
 *      "http://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm">
 *      Prime </>
 */

public class PrimStrategy extends MazeFactoryStrategy
{
	private Maze maze;
	private PrimCell[][] mazeArray;

	/**
	 * Default constructor
	 */
	public PrimStrategy()
	{
		super();
		NAME = MazeFactoryStrategyName.Prim;
	}

	/**
	 * Method to create a Maze using Prim's algorithm.
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
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());

		this.maze = new Maze(name, length, width, timeNow, creator);
		this.initializeMazeArray();
		this.launchPrim(this.maze.getStartX(), this.maze.getStartY());

		this.maze.setContent(this.mazeArray);

		return this.maze;
	}

	@Override
	public Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX,
			int endY, Person creator)
	{
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		
		this.maze = new Maze(name, length, width, startX, startY, endX, endY, timeNow, creator);
		this.initializeMazeArray();
		this.launchPrim(this.maze.getStartX(), this.maze.getStartY());
		
		this.maze.setContent(this.mazeArray);
		
		return this.maze;
	}

	private void launchPrim(Integer StartX, Integer StartY)
	{

		ArrayList<PrimCell> frontier = new ArrayList<PrimCell>();
		ArrayList<PrimCell> neighbors = new ArrayList<PrimCell>();

		Random rand = new Random();
		PrimCell selectedFrontier;
		PrimCell selectedNeighbor;
	
		this.markCell(StartX, StartY, this.mazeArray, frontier);

		while(!frontier.isEmpty())
		{
			int randomNum = rand.nextInt(frontier.size());
			selectedFrontier = frontier.get(randomNum);
			frontier.remove(randomNum);
			
			neighbors = this.neighbors(selectedFrontier, this.mazeArray);
			randomNum = rand.nextInt(neighbors.size());
			selectedNeighbor = neighbors.get(randomNum);
			
			this.direction(selectedFrontier, selectedNeighbor);

			this.markCell(selectedFrontier.getPositionX(), selectedFrontier.getPositionY(), mazeArray, frontier);
		}

	}

	private void markCell(Integer x, Integer y, PrimCell[][] mazeArray, ArrayList<PrimCell> frontier)
	{
		mazeArray[x][y].setVisited(true);

		this.add_frontier(x - 1, y, mazeArray, frontier);
		this.add_frontier(x + 1, y, mazeArray, frontier);
		this.add_frontier(x, y - 1, mazeArray, frontier);
		this.add_frontier(x, y + 1, mazeArray, frontier);

	}

	private void add_frontier(Integer x, Integer y, PrimCell[][] mazeArray, ArrayList<PrimCell> frontier)
	{
		if(x >= 0 && y >= 0 && x < this.maze.getWidth() && y < this.maze.getLength() && mazeArray[x][y].isVisited() == false && !frontier.contains(mazeArray[x][y]))
		{
			frontier.add(mazeArray[x][y]);
		}
	}

	private ArrayList<PrimCell> neighbors(PrimCell cell, PrimCell[][] mazeArray)
	{
		ArrayList<PrimCell> neighbors = new ArrayList<PrimCell>();
		int x = cell.getPositionX();
		int y = cell.getPositionY();

		if(x > 0 && mazeArray[x - 1][y].isVisited() == true)
		{
			neighbors.add(mazeArray[x - 1][y]);
		}

		if(x + 1 < this.maze.getWidth() && mazeArray[x + 1][y].isVisited() == true)
		{
			neighbors.add(mazeArray[x + 1][y]);
		}

		if(y > 0 && mazeArray[x][y - 1].isVisited() == true)
		{
			neighbors.add(mazeArray[x][y - 1]);
		}

		if(y + 1 < this.maze.getLength() && mazeArray[x][y + 1].isVisited() == true)
		{
			neighbors.add(mazeArray[x][y + 1]);
		}

		return neighbors;
	}

	private void direction(PrimCell selectedFrontier, PrimCell selectedNeighbor)
	{
		if(selectedFrontier.getPositionX() < selectedNeighbor.getPositionX())
		{
			//Going West
			this.mazeArray[selectedFrontier.getPositionX()][selectedFrontier.getPositionY()].setWallEast(false);
			this.mazeArray[selectedNeighbor.getPositionX()][selectedNeighbor.getPositionY()].setWallWest(false);
		}
		else if(selectedFrontier.getPositionX() > selectedNeighbor.getPositionX())
		{
			//Going East
			this.mazeArray[selectedFrontier.getPositionX()][selectedFrontier.getPositionY()].setWallWest(false);
			this.mazeArray[selectedNeighbor.getPositionX()][selectedNeighbor.getPositionY()].setWallEast(false);
		}
		else if(selectedFrontier.getPositionY() < selectedNeighbor.getPositionY())
		{
			//Going North
			this.mazeArray[selectedFrontier.getPositionX()][selectedFrontier.getPositionY()].setWallSouth(false);
			this.mazeArray[selectedNeighbor.getPositionX()][selectedNeighbor.getPositionY()].setWallNorth(false);
		}
		else if(selectedFrontier.getPositionY() > selectedNeighbor.getPositionY())
		{
			//Going South
			this.mazeArray[selectedFrontier.getPositionX()][selectedFrontier.getPositionY()].setWallNorth(false);
			this.mazeArray[selectedNeighbor.getPositionX()][selectedNeighbor.getPositionY()].setWallSouth(false);
		}
	}

	private void initializeMazeArray()
	{
		this.mazeArray = new PrimCell[this.maze.getWidth()][this.maze.getLength()];

		for(int y = 0; y < this.maze.getLength(); y++)
		{
			for(int x = 0; x < this.maze.getWidth(); x++)
			{
				this.mazeArray[x][y] = new PrimCell(x, y);
			}
		}
	}
}

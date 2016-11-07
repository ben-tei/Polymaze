package model.factory.strategies;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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
		this.launchKruskal(this.maze.getStartX(), this.maze.getStartY());
		
		this.maze.setContent(this.mazeArray);
		
		return this.maze;
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
	
	private void launchKruskal(Integer StartX, Integer StartY)
	{
		ArrayList<KruskalCell> edges = new ArrayList<KruskalCell>();
		
		Random rand = new Random();
		KruskalCell initEdgeNorth;
		KruskalCell initEdgeWest;
		KruskalCell selectedEdge;
		KruskalCell cellSelectedEdge;
		KruskalCell nextCell;
		String direction;
		
		
		//Initialization set of edges
		for(int y = 0; y < this.maze.getLength(); y++)
		{
			for(int x = 0; x < this.maze.getWidth(); x++)
			{
				initEdgeNorth = new KruskalCell(x, y, 0);
				initEdgeWest = new KruskalCell(x, y, 0);
				
				if(y > 0)
				{
					initEdgeNorth.setWallEast(false);
					initEdgeNorth.setWallSouth(false);
					initEdgeNorth.setWallWest(false);
					edges.add(initEdgeNorth);
				}
				if(x > 0)
				{
					initEdgeWest.setWallEast(false);
					initEdgeWest.setWallSouth(false);
					initEdgeWest.setWallNorth(false);
					edges.add(initEdgeWest);
				}
			}
		}
		
		while (!edges.isEmpty())
		{
			int randomNum = rand.nextInt(edges.size());
			selectedEdge = edges.get(randomNum);
			
			direction = this.getDirection(selectedEdge);
			
			cellSelectedEdge = this.mazeArray[selectedEdge.getPositionX()][selectedEdge.getPositionY()];
			
			if (direction == "N"){
				
				nextCell = this.mazeArray[selectedEdge.getPositionX()][selectedEdge.getPositionY() - 1];
				
				if(cellSelectedEdge.getSetID() != nextCell.getSetID())
				{
					if(cellSelectedEdge.getSetID() < nextCell.getSetID())
						this.changeSet(nextCell.getSetID(), cellSelectedEdge.getSetID());
					else
						this.changeSet(cellSelectedEdge.getSetID(), nextCell.getSetID());
					
					this.mazeArray[selectedEdge.getPositionX()][selectedEdge.getPositionY()].setWallNorth(false);
					this.mazeArray[selectedEdge.getPositionX()][selectedEdge.getPositionY() - 1].setWallSouth(false);
				}
				
			}else if (direction == "W"){
				
				nextCell = this.mazeArray[selectedEdge.getPositionX() - 1][selectedEdge.getPositionY()];
				
				if(cellSelectedEdge.getSetID() != nextCell.getSetID())
				{
					if(cellSelectedEdge.getSetID() < nextCell.getSetID())
						this.changeSet(nextCell.getSetID(), cellSelectedEdge.getSetID());
					else
						this.changeSet(cellSelectedEdge.getSetID(), nextCell.getSetID());
					
					this.mazeArray[selectedEdge.getPositionX()][selectedEdge.getPositionY()].setWallWest(false);
					this.mazeArray[selectedEdge.getPositionX() - 1][selectedEdge.getPositionY()].setWallEast(false);
				}
			}
			
			edges.remove(randomNum);
		}
	}
	
	private String getDirection(KruskalCell selectedEdge)
	{
		if(selectedEdge.isWallNorth())
			return "N";
		else if(selectedEdge.isWallWest())
			return "W";
		else
			return "";
	}
	
	private void changeSet(Integer toBeChanged, Integer newValue){
		for(int y = 0; y < this.maze.getLength(); y++)
		{
			for(int x = 0; x < this.maze.getWidth(); x++)
			{
				if (this.mazeArray[x][y].getSetID() == toBeChanged)
					this.mazeArray[x][y].setSetID(newValue);
			}
		}
	}
	
	
	
	private void initializeMazeArray()
	{
		this.mazeArray = new KruskalCell[this.maze.getWidth()][this.maze.getLength()];
		int setID = 0;
		
		for(int y = 0; y < this.maze.getLength(); y++)
		{
			for(int x = 0; x < this.maze.getWidth(); x++)
			{
				this.mazeArray[x][y] = new KruskalCell(x, y, setID);
				setID = setID + 1;
			}
		}
	}
}

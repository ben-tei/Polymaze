package model.factory.strategies;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import model.business.Maze;
import model.business.Person;
import model.business.cell.EllerCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

public class EllerStrategy extends MazeFactoryStrategy
{
	private EllerCell[][] mazeArray;

	/**
	 * Default constructor
	 */
	public EllerStrategy()
	{
		super();
		NAME = MazeFactoryStrategyName.Eller;
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
	 * @param creator
	 *            the Person who created the Maze
	 */
	@Override
	public Maze generateMaze(String name, Integer length, Integer width, Person creator)
	{
		Long timerStart = System.nanoTime();

		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		Maze maze = new Maze(name, length, width, timeNow, creator);

		initMazeArrayContent(length, width);
		for(int x = 0; x < width; ++x)
		{
			Map<Integer, ArrayList<EllerCell>> setsOfCells = new HashMap<Integer, ArrayList<EllerCell>>(); // create the sets map
			if(x < width - 1)
			{
				randomizeSetsOfCellsInColumn(mazeArray[x], setsOfCells);
				randomizeAdjacentColumnCellsLinks(mazeArray[x], mazeArray[x + 1], setsOfCells);
			}
			else
			{
				joinDisjointSetsInColumn(mazeArray[x]);
			}
		}
		maze.setContent(mazeArray);

		System.out.println("Maze of size " + width + "*" + length + " generated in (ms) : "
				+ (System.nanoTime() - timerStart) / 1000000 + " with " + MazeFactoryStrategyName.Eller.name());

		return maze;
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
	public Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX,
			int endY, Person creator)
	{
		Maze maze = generateMaze(name, length, width, creator);
		maze.setStartX(startX);
		maze.setStartY(startY);
		maze.setEndX(endX);
		maze.setEndY(endY);
		
		return maze;
	}

	private void initMazeArrayContent(Integer length, Integer width)
	{
		mazeArray = new EllerCell[width][length];
		Integer setID = 0;
		for(int y = 0; y < length; ++y)
		{
			for(int x = 0; x < width; ++x)
			{
				mazeArray[x][y] = new EllerCell(x, y, setID);
				mazeArray[x][y].setSetID(setID);
				setID++;
			}
		}
	}

	private void randomizeSetsOfCellsInColumn(EllerCell[] column, Map<Integer, ArrayList<EllerCell>> setsOfCells)
	{
		int startIndex;
		ArrayList<Integer> cellsSetsIDBeforeJoin = new ArrayList<Integer>();
		for(int i = 0; i < column.length; ++i)
		{
			startIndex = i;
			cellsSetsIDBeforeJoin.add(column[i].getSetID());
			while(shouldMakeVerticalOpening() && i < column.length - 1)
			{
				i++;
				if(getSmallestNumber(cellsSetsIDBeforeJoin) == column[i].getSetID())
				{
					i--;
					break;
				}
				cellsSetsIDBeforeJoin.add(column[i].getSetID());
			}
			// join the cells with smallest set ID.
			createCellsSetInColumn(startIndex, i, column, getSmallestNumber(cellsSetsIDBeforeJoin), setsOfCells);
			cellsSetsIDBeforeJoin.clear();
		}
	}

	private void createCellsSetInColumn(int startIndex, int endIndex, EllerCell[] column, Integer setNumber,
			Map<Integer, ArrayList<EllerCell>> setsToCells)
	{
		if(!setsToCells.containsKey(setNumber))
		{
			setsToCells.put(setNumber, new ArrayList<EllerCell>());
		}
		ArrayList<EllerCell> currentSet = setsToCells.get(setNumber);
		if(startIndex < endIndex)
		{
			for(int i = startIndex; i < endIndex; ++i)
			{
				column[i].setSetID(setNumber);
				column[i + 1].setSetID(setNumber);
				column[i].setWallSouth(false);
				column[i + 1].setWallNorth(false);
				if(i == startIndex)
				{
					currentSet.add(column[i]);
				}
				currentSet.add(column[i + 1]);
			}
		}
		else
		{
			currentSet.add(column[startIndex]);
		}
	}

	private void randomizeAdjacentColumnCellsLinks(EllerCell[] column1, EllerCell[] column2,
			Map<Integer, ArrayList<EllerCell>> setsToCells)
	{
		Iterator<Entry<Integer, ArrayList<EllerCell>>> it = setsToCells.entrySet().iterator();
		if(column1[0].getPositionX() == 1)
		{
		}
		while(it.hasNext())
		{
			Entry<Integer, ArrayList<EllerCell>> pair = it.next();

			ArrayList<EllerCell> cells = pair.getValue();
			boolean hasAtLeastOneOpening = false;
			for(int i = 0; i < cells.size(); ++i)
			{
				if(shouldMakeHorizontalOpening() || (!hasAtLeastOneOpening && i == cells.size() - 1))
				{
					hasAtLeastOneOpening = true;
					column1[cells.get(i).getPositionY()].setWallEast(false);
					column2[cells.get(i).getPositionY()].setWallWest(false);
					column2[cells.get(i).getPositionY()].setSetID(column1[cells.get(i).getPositionY()].getSetID());
				}
			}
		}
	}

	private void joinDisjointSetsInColumn(EllerCell[] column)
	{
		for(int i = 0; i < column.length; ++i)
		{
			if(i < column.length - 1 && column[i].getSetID() != column[i + 1].getSetID())
			{
				column[i].setWallSouth(false);
				column[i + 1].setWallNorth(false);

				ArrayList<Integer> tmpArray = new ArrayList<Integer>();
				tmpArray.add(column[i].getSetID());
				tmpArray.add(column[i + 1].getSetID());
				int tmpSet = getSmallestNumber(tmpArray);

				column[i].setSetID(tmpSet);
				column[i + 1].setSetID(tmpSet);
			}
		}
	}

	private Boolean shouldMakeVerticalOpening()
	{
		Double randomDoubleValueBetweenZeroAndOne = Math.random();
		if(randomDoubleValueBetweenZeroAndOne < 0.2)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	private Boolean shouldMakeHorizontalOpening()
	{
		Double randomDoubleValueBetweenZeroAndOne = Math.random();
		if(randomDoubleValueBetweenZeroAndOne < 0.8)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// utility method to get the smallest Integer in an Integer list
	private int getSmallestNumber(ArrayList<Integer> list)
	{
		Integer smallest = Integer.MAX_VALUE;
		for(int i = 0; i < list.size(); ++i)
		{
			if(list.get(i) < smallest)
			{
				smallest = list.get(i);
			}
		}
		return smallest;
	}

	/*
	 * public void drawMaze() { int outputHeight = 2 * this.maze.getLength() +
	 * 1; int outputWidth = 2 * this.maze.getWidth() + 1;
	 * 
	 * char[][] outputMaze = new char[outputWidth][outputHeight];
	 * 
	 * for(int y = 0; y < outputHeight; y++) { for(int x = 0; x < outputWidth;
	 * x++) {
	 * 
	 * if(y == 0 || y == outputHeight - 1 || x == 0 || x == outputWidth - 1) {
	 * // border wall outputMaze[x][y] = 'M'; } else if(y % 2 == 0 && x % 2 ==
	 * 0) { // intern wall outputMaze[x][y] = 'M'; }
	 * 
	 * if(y % 2 == 1 && x % 2 == 1) { outputMaze[x][y] = ' ';
	 * if(this.mazeArray[(x - 1) / 2][(y - 1) / 2].isWallEast()) { outputMaze[x
	 * + 1][y] = 'M'; } else { outputMaze[x + 1][y] = ' '; }
	 * 
	 * if(this.mazeArray[(x - 1) / 2][(y - 1) / 2].isWallSouth()) {
	 * outputMaze[x][y + 1] = 'M'; } else { outputMaze[x][y + 1] = ' '; } } } }
	 * 
	 * outputMaze[this.maze.getStartX() * 2 + 1][this.maze.getStartY() * 2 + 1]
	 * = 'S'; outputMaze[this.maze.getEndX() * 2 + 1][this.maze.getEndY() * 2 +
	 * 1] = 'E';
	 * 
	 * // now we can print it. for(int y = 0; y < outputHeight; y++) { for(int x
	 * = 0; x < outputWidth; x++) { System.out.print(outputMaze[x][y]); }
	 * System.out.println(""); }
	 * 
	 * }
	 */
}

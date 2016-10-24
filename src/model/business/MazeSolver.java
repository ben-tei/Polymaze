package model.business;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import util.exception.PolymazeException;

/**
 * This class is used to solve a maze by returning the path 
 * to follow to solve the maze.
 * This static class contain one public method : solveMaze(Maze)
 * This method return an ArrayList<Point> which equals to all 
 * position contained in the solution path
 * @author Loic
 *	
 */
public class MazeSolver {
	
	private static ArrayList<Point> pathSolution = new ArrayList<Point>();
	private static Cell[][] mazeArray;
	private static Integer endX;
	private static Integer endY;
	
	/**
	 * Main method of this class. Solve the Maze in parameter
	 * @param 	maze	Maze : The maze to resolve
	 * @return 	ArrayList<Point> : The list of all position from the starting 
	 * 				point to the ending point. 
	 * 				The first point is the starting point, the last the ending point.
	 * 				Point are like this : Point(x,y)
	 * @throws PolymazeException if it's impossible to solve the maze
	 */
	public static ArrayList<Point> solveMaze(Maze maze) throws PolymazeException
	{
		maze.setAllCellToNotVisited();
		mazeArray = maze.getContent();
		endX = maze.getEndX();
		endY = maze.getEndY();
		if ( resolve(maze.getStartX(), maze.getStartY()) == false )
		{	
			throw new PolymazeException("Impossible to solve the maze");
		}
		Collections.reverse(pathSolution);
		return pathSolution;
	}

	/**
	 * Recursive method used to solve a maze.
	 * @param x 	int : x position of the cell trying to resolve
	 * @param y		int : y position of the cell trying to resolve
	 * @return	boolean : <b>True</b> : if the current cell is on the solution path.
	 * 					(from that point, one of his recursive call have reached the end
	 * 					of the maze ) or this is directly the ending cell
	 * 					<b>False</b> : if there is not unvisited connected cell and the current 
	 * 					cell is not the exit of the maze.
	 */
	private static boolean resolve(int x, int y) 
	{
		Cell cell = mazeArray[x][y];
		cell.setVisited(true);
						
		//we are in the exit !!
		if (x == endX && y == endY)
		{
			pathSolution.add(new Point(x,y));
			
			return true;
		}
		else 
		{
			if (!cell.isWallNorth() && !mazeArray[x][y-1].isVisited() && resolve(x, y-1) )
			{
				pathSolution.add(new Point(x,y));
				return true;
			}

			if (!cell.isWallEast() && !mazeArray[x+1][y].isVisited() && resolve(x+1, y))
			{
				pathSolution.add(new Point(x,y));
				return true;
			}

			if (!cell.isWallSouth() && !mazeArray[x][y+1].isVisited() && resolve(x, y+1))
			{
				pathSolution.add(new Point(x,y));
				return true;
			}

			if (!cell.isWallWest() && !mazeArray[x-1][y].isVisited() && resolve(x-1, y))
			{
				pathSolution.add(new Point(x,y));
				return true;
			}
		}
		
		return false;
	}
	
	
}

package test.model.factory.strategies;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.business.cell.BacktrackCell;
import model.factory.strategies.BacktrackStrategy;
import util.exception.PolymazeException;

/**
 * @author Loic
 */
public class BacktrackStrategyTest
{
	// minimum size defined for the application
	@Test
	public void backtrackStrategy1_test() throws PolymazeException
	{
		BacktrackStrategy eller = new BacktrackStrategy();
		Maze maze = eller.generateMaze("maze", 3, 3, new Person(15, "Lama"));

		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(maze);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(maze);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);	}

	// max size
	@Test
	public void backtrackStrategy2_test() throws PolymazeException
	{
		BacktrackStrategy backtrack = new BacktrackStrategy();
		Maze maze = backtrack.generateMaze("maze", 100, 100, new Person(15, "Lama"));

		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(maze);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(maze);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
		
	}

	// normal maze with start and end
	@Test
	public void backtrackStrategy3_test() throws PolymazeException
	{
		BacktrackStrategy backtrack = new BacktrackStrategy();
		int startX = 0;
		int startY = 1;
		int endX = 50;
		int endY = 49;
		Person creator = new Person(42, "FRG");

		Maze maze = backtrack.generateMazeWithStartEnd("maze", 100, 70, startX, startY, endX, endY, creator);

		assertEquals("maze", maze.getName());
		assertEquals(100, (int) maze.getLength());
		assertEquals(70, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(1, maze.getStartY());
		assertEquals(50, maze.getEndX());
		assertEquals(49, maze.getEndY());
		assertEquals(creator, maze.getCreator());
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(maze);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(maze);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
	}

	@Test
	public void getNonVisitedNeighbour01_Test() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException
	{
		String result;
		BacktrackStrategy backtrackStrat = new BacktrackStrategy();
		backtrackStrat.generateMaze("test", (Integer) 2, (Integer) 3, new Person());

		BacktrackCell[][] maze = new BacktrackCell[3][2];
		maze[0][0] = new BacktrackCell(0, 0);
		maze[1][0] = new BacktrackCell(1, 0);
		maze[2][0] = new BacktrackCell(2, 0);
		maze[0][1] = new BacktrackCell(0, 1);
		maze[1][1] = new BacktrackCell(1, 1);
		maze[2][1] = new BacktrackCell(2, 1);

		Class<BacktrackStrategy> backtrackClass = BacktrackStrategy.class;

		Method method = backtrackClass.getDeclaredMethod("getNonVisitedNeighbour", BacktrackCell[][].class, int.class,
				int.class);

		method.setAccessible(true);

		result = (String) method.invoke(backtrackStrat, maze, 0, 0);
		assertEquals("ES", result);
		result = (String) method.invoke(backtrackStrat, maze, 1, 1);
		assertEquals("NEW", result);
	}
	
	/**
	 * 
	 * @param maze
	 * @return the solution path for a maze with reverse start/end point
	 * @throws PolymazeException
	 */
	private ArrayList<Point> getSolutionPathEndToBegin(Maze maze) throws PolymazeException{
		ArrayList<Point> pathEndToBegin;

		int newStartX = maze.getStartX();
		int newStartY = maze.getStartY();
		int newEndX = maze.getEndX();
		int newEndY = maze.getEndY();
		
		maze.setStartX(newStartX);
		maze.setStartY(newStartY);
		maze.setEndX(newEndX);
		maze.setEndY(newEndY);

		pathEndToBegin = MazeSolver.solveMaze(maze);
		Collections.reverse(pathEndToBegin);
		
		return pathEndToBegin;
	}
	

}

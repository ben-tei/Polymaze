package test.model.factory.strategies;

import org.junit.Test;

import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.factory.strategies.EllerStrategy;
import util.exception.PolymazeException;
import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Rodobros
 */
public class EllerStrategyTest
{
	// minimum size
	@Test
	public void testEllerStrategy1() throws PolymazeException
	{
		EllerStrategy eller = new EllerStrategy();
		Maze mazeToSolve = eller.generateMaze("Test Eller1", 3, 3, new Person(999, "rb"));

		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(mazeToSolve);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(mazeToSolve);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
	}

	// app-defined max size
	@Test
	public void testEllerStrategy2() throws PolymazeException
	{
		EllerStrategy eller = new EllerStrategy();
		Maze mazeToSolve = eller.generateMaze("Test Eller2", 100, 100, new Person(999, "rb"));
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(mazeToSolve);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(mazeToSolve);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
	}

	// very big maze
	@Test
	public void testEllerStrategy3() throws PolymazeException
	{
		EllerStrategy eller = new EllerStrategy();
		Maze mazeToSolve = eller.generateMaze("Test Eller3", 400, 400, new Person(999, "rb"));
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(mazeToSolve);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(mazeToSolve);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
	}

	// normal maze with start and end
	@Test
	public void testEllerStrategy4() throws PolymazeException
	{
		EllerStrategy eller = new EllerStrategy();
		int startX = 0;
		int startY = 23;
		int endX = 99;
		int endY = 88;
		Maze mazeToSolve = eller.generateMazeWithStartEnd("Test Eller4", 100, 100, startX, startY, endX, endY,
				new Person(999, "rb"));

		assertEquals(mazeToSolve.getStartX(), startX);
		assertEquals(mazeToSolve.getStartY(), startY);
		assertEquals(mazeToSolve.getEndX(), endX);
		assertEquals(mazeToSolve.getEndY(), endY);
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(mazeToSolve);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(mazeToSolve);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
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
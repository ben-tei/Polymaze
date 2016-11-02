package test.model.factory.strategies;

import org.junit.Test;

import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.factory.strategies.EllerStrategy;
import util.exception.PolymazeException;
import static org.junit.Assert.assertEquals;

/**
 * @author Rodobros
 */
public class EllerStrategyTest
{
	// minimum size
	@Test
	public void testEllerStrategy1()
	{
		EllerStrategy eller = new EllerStrategy();
		Maze mazeToSolve = eller.generateMaze("Test Eller1", 3, 3, new Person(999, "rb"));
		try
		{
			MazeSolver.solveMaze(mazeToSolve);
		}
		catch(PolymazeException e)
		{
			// if reach this, the test failed!
		}
	}

	// app-defined max size
	@Test
	public void testEllerStrategy2()
	{
		EllerStrategy eller = new EllerStrategy();
		Maze mazeToSolve = eller.generateMaze("Test Eller2", 100, 100, new Person(999, "rb"));
		try
		{
			MazeSolver.solveMaze(mazeToSolve);
		}
		catch(PolymazeException e)
		{
			// if reach this, the test failed!
		}
	}

	// very big maze
	@Test
	public void testEllerStrategy3()
	{
		EllerStrategy eller = new EllerStrategy();
		Maze mazeToSolve = eller.generateMaze("Test Eller3", 400, 400, new Person(999, "rb"));
		try
		{
			MazeSolver.solveMaze(mazeToSolve);
		}
		catch(PolymazeException e)
		{
			// if reach this, the test failed!
		}
	}

	// normal maze with start and end
	@Test
	public void testEllerStrategy4()
	{
		EllerStrategy eller = new EllerStrategy();
		int startX = 0;
		int startY = 23;
		int endX = 99;
		int endY = 88;
		Maze mazeToSolve = eller.generateMazeWithStartEnd("Test Eller4", 100, 100, startX, startY, endX, endY,
				new Person(999, "rb"));
		try
		{
			MazeSolver.solveMaze(mazeToSolve);
		}
		catch(PolymazeException e)
		{
			// if reach this, the test failed!
		}

		assertEquals(mazeToSolve.getStartX(), startX);
		assertEquals(mazeToSolve.getStartY(), startY);
		assertEquals(mazeToSolve.getEndX(), endX);
		assertEquals(mazeToSolve.getEndY(), endY);
	}

}
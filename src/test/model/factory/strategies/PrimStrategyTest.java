package test.model.factory.strategies;

import static org.junit.Assert.*;

import org.junit.Test;
import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.factory.strategies.PrimStrategy;
import util.exception.PolymazeException;

/**
 * @author Aurelien
 */
public class PrimStrategyTest
{

	//minimum size test with default start and end points
	@Test
	public void primStrategy1_test()
	{
		PrimStrategy prim = new PrimStrategy();
		Maze maze = prim.generateMaze("Test prim1", 3, 3, new Person(777, "Aurios"));

		try
		{
			MazeSolver.solveMaze(maze);
		}
		catch(PolymazeException p)
		{
			System.out.println(p);
		}

		assertEquals("Test prim1", maze.getName());
		assertEquals(3, (int) maze.getLength());
		assertEquals(3, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(0, maze.getStartY());
		assertEquals(2, maze.getEndX());
		assertEquals(2, maze.getEndY());
	}

	//maximum size test with default start and end points
	@Test
	public void primStrategy2_test()
	{
		PrimStrategy prim = new PrimStrategy();
		Maze maze = prim.generateMaze("Test prim2", 100, 100, new Person(778, "Aurios2"));

		try
		{
			MazeSolver.solveMaze(maze);
		}
		catch(PolymazeException p)
		{
			System.out.println(p);
		}

		assertEquals("Test prim2", maze.getName());
		assertEquals(100, (int) maze.getLength());
		assertEquals(100, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(0, maze.getStartY());
		assertEquals(99, maze.getEndX());
		assertEquals(99, maze.getEndY());
	}

	// normal maze with start and end
	@Test
	public void primStrategy3_test()
	{
		PrimStrategy prim = new PrimStrategy();
		int startX = 1;
		int startY = 1;
		int endX = 33;
		int endY = 35;
		Person creator = new Person(25, "Aurios3");

		Maze maze = prim.generateMazeWithStartEnd("Test prim3", 80, 90, startX, startY, endX, endY, creator);

		try
		{
			MazeSolver.solveMaze(maze);
		}
		catch(PolymazeException p)
		{
			//Cannot solve the maze
			System.out.println(p);
		}

		assertEquals("Test prim3", maze.getName());
		assertEquals(80, (int) maze.getLength());
		assertEquals(90, (int) maze.getWidth());
		assertEquals(1, maze.getStartX());
		assertEquals(1, maze.getStartY());
		assertEquals(33, maze.getEndX());
		assertEquals(35, maze.getEndY());
		assertEquals(creator, maze.getCreator());
	}

}

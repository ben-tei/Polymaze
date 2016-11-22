package test.model.factory.strategies;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

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
	public void primStrategy1_test() throws PolymazeException
	{
		PrimStrategy prim = new PrimStrategy();
		Maze maze = prim.generateMaze("Test prim1", 3, 3, new Person(777, "Aurios"));

		assertEquals("Test prim1", maze.getName());
		assertEquals(3, (int) maze.getLength());
		assertEquals(3, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(0, maze.getStartY());
		assertEquals(2, maze.getEndX());
		assertEquals(2, maze.getEndY());
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(maze);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(maze);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
	}

	//maximum size test with default start and end points
	@Test
	public void primStrategy2_test() throws PolymazeException
	{
		PrimStrategy prim = new PrimStrategy();
		Maze maze = prim.generateMaze("Test prim2", 100, 100, new Person(778, "Aurios2"));

		assertEquals("Test prim2", maze.getName());
		assertEquals(100, (int) maze.getLength());
		assertEquals(100, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(0, maze.getStartY());
		assertEquals(99, maze.getEndX());
		assertEquals(99, maze.getEndY());
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(maze);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(maze);
		
		assertEquals(pathBeginToEnd, pathEndToBegin);
	}

	// normal maze with start and end
	@Test
	public void primStrategy3_test() throws PolymazeException
	{
		PrimStrategy prim = new PrimStrategy();
		int startX = 1;
		int startY = 1;
		int endX = 33;
		int endY = 35;
		Person creator = new Person(25, "Aurios3");

		Maze maze = prim.generateMazeWithStartEnd("Test prim3", 80, 90, startX, startY, endX, endY, creator);

		assertEquals("Test prim3", maze.getName());
		assertEquals(80, (int) maze.getLength());
		assertEquals(90, (int) maze.getWidth());
		assertEquals(1, maze.getStartX());
		assertEquals(1, maze.getStartY());
		assertEquals(33, maze.getEndX());
		assertEquals(35, maze.getEndY());
		assertEquals(creator, maze.getCreator());
		
		ArrayList<Point> pathBeginToEnd = MazeSolver.solveMaze(maze);
		ArrayList<Point> pathEndToBegin = getSolutionPathEndToBegin(maze);
		
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

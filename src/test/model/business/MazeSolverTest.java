package test.model.business;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.business.Cell;
import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.factory.strategies.BacktrackStrategy;
import util.exception.PolymazeException;

/**
 * @author Loic Class for testing MazeSolver Method tested : - mazeSolver()
 */
public class MazeSolverTest
{

	@Test
	public void solveMazetest_00() throws PolymazeException
	{
		BacktrackStrategy backtrackStrategy = new BacktrackStrategy();
		Maze maze = backtrackStrategy.generateMaze("Test", 15, 15, new Person(15, "Lama"));
		ArrayList<Point> pathSolution = MazeSolver.solveMaze(maze);
		Iterator<Point> iterator = pathSolution.iterator();
		iterator.hasNext();
		assertEquals(new Point(0, 0), iterator.next());
	}

	@Test
	public void solveMazetest_01() throws PolymazeException
	{
		// Initialization of a maze
		//  _ _ _
		// |D   _|
		// |_ _ E|
		Integer width = 3;
		Integer length = 2;
		Maze maze = new Maze("test", length, width, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, false, true, true);
		content[1][0] = new Cell(1, 0, true, false, false, false);
		content[2][0] = new Cell(2, 0, true, true, true, false);
		content[0][1] = new Cell(0, 1, true, false, true, true);
		content[1][1] = new Cell(1, 1, false, false, true, false);
		content[2][1] = new Cell(2, 1, true, true, true, false);
		maze.setContent(content);

		ArrayList<Point> pathSolution = MazeSolver.solveMaze(maze);
		Iterator<Point> iterator = pathSolution.iterator();
		iterator.hasNext();
		assertEquals(new Point(0, 0), iterator.next());
		iterator.hasNext();
		assertEquals(new Point(1, 0), iterator.next());
		iterator.hasNext();
		assertEquals(new Point(1, 1), iterator.next());
		iterator.hasNext();
		assertEquals(new Point(2, 1), iterator.next());
	}

	@Test
	public void solveMazetest_02() throws PolymazeException
	{
		// Initialization of a maze
		//  _ _ _
		// |_ D _|
		// |E _ _|
		Integer width = 3;
		Integer length = 2;
		Maze maze = new Maze("test", length, width, 1, 0, 0, 1, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, false, true, true);
		content[1][0] = new Cell(1, 0, true, false, false, false);
		content[2][0] = new Cell(2, 0, true, true, true, false);
		content[0][1] = new Cell(0, 1, true, false, true, true);
		content[1][1] = new Cell(1, 1, false, false, true, false);
		content[2][1] = new Cell(2, 1, true, true, true, false);
		maze.setContent(content);

		ArrayList<Point> pathSolution = MazeSolver.solveMaze(maze);
		Iterator<Point> iterator = pathSolution.iterator();
		iterator.hasNext();
		assertEquals(new Point(1, 0), iterator.next());
		iterator.hasNext();
		assertEquals(new Point(1, 1), iterator.next());
		iterator.hasNext();
		assertEquals(new Point(0, 1), iterator.next());
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void solveMazetest_03() throws PolymazeException
	{
		// Initialization of a maze with unreachable exit
		//  _ _ _
		// |D   _|
		// |_ _|E|
		Integer width = 3;
		Integer length = 2;
		Maze maze = new Maze("test", length, width, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, false, true, true);
		content[1][0] = new Cell(1, 0, true, false, false, false);
		content[2][0] = new Cell(2, 0, true, true, true, false);
		content[0][1] = new Cell(0, 1, true, false, true, true);
		content[1][1] = new Cell(1, 1, false, true, true, false);
		content[2][1] = new Cell(2, 1, true, true, true, true);
		maze.setContent(content);

		exception.expect(PolymazeException.class);

		// Suppress pathSolution never used as it supposed to produce an Error.
		@SuppressWarnings("unused")
		ArrayList<Point> pathSolution = MazeSolver.solveMaze(maze);

	}

}

package test.model.factory.strategies;

import static org.junit.Assert.*;

import org.junit.Test;
import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.factory.strategies.KruskalStrategy;
import util.exception.PolymazeException;
/**
 * @author Aurelien
 */
public class KruskalStrategyTest {

	//minimum size test with default start and end points
	@Test
	public void kruskalStrategy1_test()
	{
		KruskalStrategy kruskal = new KruskalStrategy();
		Maze maze = kruskal.generateMaze("Test kruskal1", 3, 3, new Person(779, "Aurios4"));

		try			
		{
			MazeSolver.solveMaze(maze);
		}
		catch(PolymazeException p)
		{
			//Cannot solve the maze
			System.out.println(p);
		}
			
		assertEquals("Test kruskal1", maze.getName());
		assertEquals(3, (int) maze.getLength());
		assertEquals(3, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(0, maze.getStartY());
		assertEquals(2, maze.getEndX());
		assertEquals(2, maze.getEndY());
	}
	
	
	//maximum size test with default start and end points
	@Test
	public void kruskalStrategy2_test()
	{
		KruskalStrategy kruskal = new KruskalStrategy();
		Maze maze = kruskal.generateMaze("Test kruskal2", 100, 100, new Person(780, "Aurios5"));

		try			
		{
			MazeSolver.solveMaze(maze);
		}
		catch(PolymazeException p)
		{
			//Cannot solve the maze
			System.out.println(p);
		}
				
		assertEquals("Test kruskal2", maze.getName());
		assertEquals(100, (int) maze.getLength());
		assertEquals(100, (int) maze.getWidth());
		assertEquals(0, maze.getStartX());
		assertEquals(0, maze.getStartY());
		assertEquals(99, maze.getEndX());
		assertEquals(99, maze.getEndY());
	}
	
	// normal maze with start and end
	@Test
	public void kruskalStrategy3_test()
	{
		KruskalStrategy kruskal = new KruskalStrategy();
		int startX = 1;
		int startY = 1;
		int endX = 33;
		int endY = 35;
		Person creator = new Person(781, "Aurios6");

		Maze maze = kruskal.generateMazeWithStartEnd("Test kruskal3", 80, 90, startX, startY, endX, endY, creator);

		try
		{
			MazeSolver.solveMaze(maze);
		}
		catch(PolymazeException p)
		{
			//Cannot solve the maze
			System.out.println(p);
		}

		assertEquals("Test kruskal3", maze.getName());
		assertEquals(80, (int) maze.getLength());
		assertEquals(90, (int) maze.getWidth());
		assertEquals(1, maze.getStartX());
		assertEquals(1, maze.getStartY());
		assertEquals(33, maze.getEndX());
		assertEquals(35, maze.getEndY());
		assertEquals(creator, maze.getCreator());
	}

}

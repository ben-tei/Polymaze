package test.model.factory.strategies;

import org.junit.Test;

import model.business.Maze;
import model.business.MazeSolver;
import model.business.Person;
import model.factory.strategies.EllerStrategy;
import util.exception.PolymazeException;;

/**
 * @author Rodobros TODO
 */
public class EllerStrategyTest
{

	@Test
	public void testEllerStrategy1()
	{
		EllerStrategy eller = new EllerStrategy();
		MazeSolver solver = new MazeSolver();
		Maze mazeToSolve = eller.generateMaze("Test Eller1", 900, 900, new Person(999, "rb"));
		try {
			solver.solveMaze(mazeToSolve);
		} catch (PolymazeException e) {
			// test failed!
		}
	}

}
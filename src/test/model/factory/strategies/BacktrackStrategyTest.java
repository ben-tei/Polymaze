package test.model.factory.strategies;

import org.junit.Test;

import model.business.Person;
import model.factory.strategies.BacktrackStrategy;

/**
 * @author Loic TODO finish to do these tests.
 */
public class BacktrackStrategyTest
{

	@Test
	public void test()
	{
		BacktrackStrategy backtrackStrategy = new BacktrackStrategy();
		backtrackStrategy.generateMaze("Test", 15, 15, new Person(15, "Lama"));

	}

}

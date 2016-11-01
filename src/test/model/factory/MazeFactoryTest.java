package test.model.factory;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import model.factory.*;
import model.business.Maze;
import model.business.Person;

public class MazeFactoryTest {

	// test if the factory can changes its strategy correctly
	@Test
	public void mazeFactoryTest1() {
		MazeFactory factory = new MazeFactory();
		
		factory.setStrategy(MazeFactoryStrategyName.Backtrack);
		assertEquals(MazeFactoryStrategyName.Backtrack, factory.getStrategyName());
		
		factory.setStrategy(MazeFactoryStrategyName.Eller);
		assertEquals(MazeFactoryStrategyName.Eller, factory.getStrategyName());
		
		factory.setStrategy(MazeFactoryStrategyName.Kruskal);
		assertEquals(MazeFactoryStrategyName.Kruskal, factory.getStrategyName());
		
		factory.setStrategy(MazeFactoryStrategyName.Prim);
		assertEquals(MazeFactoryStrategyName.Prim, factory.getStrategyName());
	}
	
	// test if the method returns a maze with the specified parameter
	@Test
	public void mazeFactoryTest2() {
		MazeFactory factory = new MazeFactory();
		factory.setStrategy(MazeFactoryStrategyName.Backtrack);
		
		String nameTest = "Test";
		int lengthTest = 40;
		int widthTest = 40;
		Person creatorTest = new Person();
		Maze maze = factory.generateMaze(nameTest, lengthTest, widthTest, creatorTest);
		
		assertEquals(nameTest, maze.getName());
		assertEquals(Integer.valueOf(lengthTest), maze.getLength());
		assertEquals(Integer.valueOf(widthTest), maze.getWidth());
		assertEquals(creatorTest, maze.getCreator());
	}
}

package test.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
import model.factory.MazeFactory;
import model.factory.MazeFactoryStrategyName;
import util.exception.PolymazeException;

public class MazeDaoImplTest {

	/*@Test
	public void createMazeTest() throws PolymazeException {
		Person person = DataBaseFacade.getPersonByName("test");
		
		MazeFactory mazeFactory = new MazeFactory();
		mazeFactory.setStrategy(MazeFactoryStrategyName.Backtrack);
		Maze maze = mazeFactory.generateMaze("TestMaze", 20, 10, person);
		
		Maze createdMaze = DataBaseFacade.createMaze(maze);
		
		assertNotNull(createdMaze);
		assertEquals(createdMaze.getId(), Integer.valueOf(1));
		assertEquals(createdMaze.getName(), "TestMaze");
		assertEquals(createdMaze.getLength(), Integer.valueOf(20));
		assertEquals(createdMaze.getWidth(), Integer.valueOf(10));
		assertEquals(createdMaze.getCreationDate(), java.sql.Date.valueOf("2016-10-20"));
		assertEquals(createdMaze.getCreator().getId(), Integer.valueOf(4));
		assertEquals(createdMaze.getCreator().getName(), "test");
	}*/
	
	@Test
	public void getMazeByNameTest(){
		Maze maze = DataBaseFacade.getMazeByName("TestMaze");
		
		assertNotNull(maze);
		assertEquals(maze.getId(), Integer.valueOf(1));
		assertEquals(maze.getName(), "TestMaze");
		assertEquals(maze.getLength(), Integer.valueOf(20));
		assertEquals(maze.getWidth(), Integer.valueOf(10));
		assertEquals(maze.getCreationDate(), java.sql.Date.valueOf("2016-10-20"));
		assertEquals(maze.getCreator().getId(), Integer.valueOf(4));
		assertEquals(maze.getCreator().getName(), "test");
	}
}

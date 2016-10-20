package test.model.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
import model.factory.MazeFactory;
import model.factory.MazeFactoryStrategyName;
import util.exception.PolymazeException;

public class MazeDaoImplTest {

	@Test
	public void createMazeTest() throws PolymazeException {
		Person person = DataBaseFacade.getPersonByName("test");
		
		MazeFactory mazeFactory = new MazeFactory();
		mazeFactory.setStrategy(MazeFactoryStrategyName.Backtrack);
		Maze maze = mazeFactory.generateMaze("TestMaze", 15, 15, person);
		
		Maze createdMaze = DataBaseFacade.createMaze(maze);
		
		assertNotNull(createdMaze);
		assertEquals(createdMaze.getId(), Integer.valueOf(2));
		assertEquals(createdMaze.getName(), "MuscuMaze");
		assertEquals(createdMaze.getLength(), Integer.valueOf(45));
		assertEquals(createdMaze.getWidth(), Integer.valueOf(25));
		assertEquals(createdMaze.getCreationDate(), java.sql.Date.valueOf("2016-10-09"));
		assertEquals(createdMaze.getCreator().getId(), Integer.valueOf(4));
		assertEquals(createdMaze.getCreator().getName(), "test");
	}

}

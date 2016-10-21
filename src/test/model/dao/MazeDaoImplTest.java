package test.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
/*import model.factory.MazeFactory;
import model.factory.MazeFactoryStrategyName;
import util.exception.PolymazeException;*/

public class MazeDaoImplTest {
	
	@Test
	public void getMazeByIdTest(){
		Maze maze = DataBaseFacade.getMazeById(1);
		
		assertNotNull(maze);
		assertEquals(maze.getId(), Integer.valueOf(1));
		assertEquals(maze.getName(), "TestMaze");
		assertEquals(maze.getLength(), Integer.valueOf(20));
		assertEquals(maze.getWidth(), Integer.valueOf(10));
		assertEquals(maze.getCreationDate(), java.sql.Date.valueOf("2016-10-20"));
		assertEquals(maze.getCreator().getId(), Integer.valueOf(4));
		assertEquals(maze.getCreator().getName(), "test");
	}
	
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
	
	@Test
	public void getMazesByCreatorTest(){
		Person person = DataBaseFacade.getPersonByName("test");
		
		List<Maze> mazes = DataBaseFacade.getMazesByCreator(person);
		
		assertEquals(mazes.size(), 2);
		assertEquals(mazes.get(0).getId(), Integer.valueOf(1));
		assertEquals(mazes.get(0).getName(), "TestMaze");
		assertEquals(mazes.get(0).getCreator().getName(), "test");
		assertEquals(mazes.get(1).getId(), Integer.valueOf(2));
		assertEquals(mazes.get(1).getName(), "MySecondMaze");
		assertEquals(mazes.get(1).getCreationDate(), java.sql.Date.valueOf("2016-10-21"));
		assertEquals(mazes.get(1).getCreator().getName(), "test");
	}
	
	@Test
	public void getAllMazesTest(){		
		List<Maze> mazes = DataBaseFacade.getAllMazes();
		
		assertEquals(mazes.size(), 3);
		assertEquals(mazes.get(0).getId(), Integer.valueOf(1));
		assertEquals(mazes.get(0).getName(), "TestMaze");
		assertEquals(mazes.get(0).getCreator().getName(), "test");
		assertEquals(mazes.get(1).getId(), Integer.valueOf(2));
		assertEquals(mazes.get(1).getName(), "MySecondMaze");
		assertEquals(mazes.get(1).getCreationDate(), java.sql.Date.valueOf("2016-10-21"));
		assertEquals(mazes.get(1).getCreator().getName(), "test");
		assertEquals(mazes.get(2).getId(), Integer.valueOf(3));
		assertEquals(mazes.get(2).getName(), "MuscuMaze");
		assertEquals(mazes.get(2).getCreator().getName(), "Benji");
	}
	
	/*@Test
	public void deleteMazeTest() throws PolymazeException {
		boolean deleted = DataBaseFacade.deleteMaze(4);
		
		assertEquals(deleted, false);
	}
	
	@Test
	public void deleteMazeTest() throws PolymazeException {
		boolean deleted = DataBaseFacade.deleteMaze(4);
		
		assertEquals(deleted, true);
	}*/
	
	/*@Test
	public void createMazeTest() throws PolymazeException {
		Person person = DataBaseFacade.getPersonByName("Benji");
		
		MazeFactory mazeFactory = new MazeFactory();
		mazeFactory.setStrategy(MazeFactoryStrategyName.Backtrack);
		Maze maze = mazeFactory.generateMaze("MuscuMaze2", 12, 12, person);
		
		Maze createdMaze = DataBaseFacade.createMaze(maze);
		
		assertNotNull(createdMaze);
		assertEquals(createdMaze.getId(), Integer.valueOf(3));
		assertEquals(createdMaze.getName(), "MuscuMaze");
		assertEquals(createdMaze.getLength(), Integer.valueOf(12));
		assertEquals(createdMaze.getWidth(), Integer.valueOf(12));
		assertEquals(createdMaze.getCreationDate(), java.sql.Date.valueOf("2016-10-21"));
		assertEquals(createdMaze.getCreator().getId(), Integer.valueOf(1));
		assertEquals(createdMaze.getCreator().getName(), "Benji");
	}*/
	
}

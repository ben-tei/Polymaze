package test.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.business.Maze;
import model.business.Person;
import model.dao.DataBaseFacade;
import model.dao.MazeDao;
import model.factory.MazeFactory;
import model.factory.MazeFactoryStrategyName;
import util.exception.PolymazeException;
import model.dao.MazeDaoImpl;

public class MazeDaoImplTest
{
	
	@Test
	public void mazeDaoDriver00(){
		MazeDao m = new MazeDaoImpl();
		
		// create a maze;
		Person personTest = DataBaseFacade.getPersonById(3); 
		MazeFactory mazeFactory = new MazeFactory();
		mazeFactory.setStrategy(MazeFactoryStrategyName.Backtrack); 
		Maze testMaze = mazeFactory.generateMaze("mazeDaoDriverTest00", 12, 12, personTest); 
		Maze createdMaze = new Maze();
		try {
			createdMaze = m.createMaze(testMaze);
		} catch (PolymazeException e) {
			e.printStackTrace();
		}
		
		// test if it was created in database;
		Maze createdDBTestMaze = m.getMazeById(createdMaze.getId());
		assertNotNull(createdDBTestMaze);
		/*
		assertEquals(createdDBTestMaze.getId(),testMaze.getId());
		assertEquals(createdDBTestMaze.getName(), testMaze.getName());
		assertEquals(createdDBTestMaze.getLength(), testMaze.getLength());
		assertEquals(createdDBTestMaze.getWidth(), testMaze.getWidth());
		assertEquals(createdDBTestMaze.getCreationDate(), testMaze.getCreationDate());
		assertEquals(createdDBTestMaze.getCreator().getId(), testMaze.getCreator().getId());
		assertEquals(createdDBTestMaze.getCreator().getName(), testMaze.getCreator().getName());
		*/
		
		//test get by ID
		Maze testGetIdMaze = m.getMazeById(createdMaze.getId());
		assertNotNull(testGetIdMaze);
		
		//test get by name
		Maze testGetNameMaze = m.getMazeByName(testMaze.getName());
		assertNotNull(testGetNameMaze);
		
		//test get by creator
		List<Maze> testGetMazeByCreator = m.getMazesByCreator(personTest);
		assertNotNull(testGetMazeByCreator);
		assertNotEquals(Integer.valueOf(testGetMazeByCreator.size()), Integer.valueOf(0));
		
		//test getAllMaze
		List<Maze> testGetAllMaze = m.getAllMazes();
		assertNotNull(testGetAllMaze);
		assertNotEquals(Integer.valueOf(testGetAllMaze.size()), Integer.valueOf(0));
		
		// delete the created maze to cleanup:
		m.deleteMaze(createdMaze.getId());
		Maze deletedDBMaze = DataBaseFacade.getMazeById(createdMaze.getId());
		assertNull(deletedDBMaze);
	}
//
//	@Test
//	public void getMazeByIdTest()
//	{
//		Maze maze = DataBaseFacade.getMazeById(1);
//
//		assertNotNull(maze);
//		assertEquals(maze.getId(), Integer.valueOf(1));
//		assertEquals(maze.getName(), "TestMaze");
//		assertEquals(maze.getLength(), Integer.valueOf(20));
//		assertEquals(maze.getWidth(), Integer.valueOf(10));
//		assertEquals(maze.getCreationDate(), java.sql.Date.valueOf("2016-10-20"));
//		assertEquals(maze.getCreator().getId(), Integer.valueOf(4));
//		assertEquals(maze.getCreator().getName(), "test");
//	}
//
//	@Test
//	public void getMazeByNameTest()
//	{
//		Maze maze = DataBaseFacade.getMazeByName("TestMaze");
//
//		assertNotNull(maze);
//		assertEquals(maze.getId(), Integer.valueOf(1));
//		assertEquals(maze.getName(), "TestMaze");
//		assertEquals(maze.getLength(), Integer.valueOf(20));
//		assertEquals(maze.getWidth(), Integer.valueOf(10));
//		assertEquals(maze.getCreationDate(), java.sql.Date.valueOf("2016-10-20"));
//		assertEquals(maze.getCreator().getId(), Integer.valueOf(4));
//		assertEquals(maze.getCreator().getName(), "test");
//	}
//
//	@Test
//	public void getMazesByCreatorTest()
//	{
//		Person person = DataBaseFacade.getPersonByName("test");
//
//		List<Maze> mazes = DataBaseFacade.getMazesByCreator(person);
//
//		assertEquals(mazes.size(), 2);
//		assertEquals(mazes.get(0).getId(), Integer.valueOf(1));
//		assertEquals(mazes.get(0).getName(), "TestMaze");
//		assertEquals(mazes.get(0).getCreator().getName(), "test");
//		assertEquals(mazes.get(1).getId(), Integer.valueOf(2));
//		assertEquals(mazes.get(1).getName(), "MySecondMaze");
//		assertEquals(mazes.get(1).getCreationDate(), java.sql.Date.valueOf("2016-10-21"));
//		assertEquals(mazes.get(1).getCreator().getName(), "test");
//	}
//
//	@Test
//	public void getAllMazesTest()
//	{
//		List<Maze> mazes = DataBaseFacade.getAllMazes();
//
//		assertEquals(mazes.size(), 5);
//		assertEquals(mazes.get(0).getId(), Integer.valueOf(1));
//		assertEquals(mazes.get(0).getName(), "TestMaze");
//		assertEquals(mazes.get(0).getCreator().getName(), "test");
//		assertEquals(mazes.get(1).getId(), Integer.valueOf(2));
//		assertEquals(mazes.get(1).getName(), "MySecondMaze");
//		assertEquals(mazes.get(1).getCreationDate(), java.sql.Date.valueOf("2016-10-21"));
//		assertEquals(mazes.get(1).getCreator().getName(), "test");
//		assertEquals(mazes.get(2).getId(), Integer.valueOf(3));
//		assertEquals(mazes.get(2).getName(), "MuscuMaze");
//		assertEquals(mazes.get(2).getCreator().getName(), "Benji");
//	}

	/*
	 * @Test public void deleteMazeTest() throws PolymazeException { boolean
	 * deleted = DataBaseFacade.deleteMaze(4);
	 * 
	 * assertEquals(deleted, false); }
	 * 
	 * @Test public void deleteMazeTest() throws PolymazeException { boolean
	 * deleted = DataBaseFacade.deleteMaze(4);
	 * 
	 * assertEquals(deleted, true); }
	 */

	/*
	 * @Test public void createMazeTest() throws PolymazeException { Person
	 * person = DataBaseFacade.getPersonByName("Benji");
	 * 
	 * MazeFactory mazeFactory = new MazeFactory();
	 * mazeFactory.setStrategy(MazeFactoryStrategyName.Backtrack); Maze maze =
	 * mazeFactory.generateMaze("MuscuMaze2", 12, 12, person);
	 * 
	 * Maze createdMaze = DataBaseFacade.createMaze(maze);
	 * 
	 * assertNotNull(createdMaze); assertEquals(createdMaze.getId(),
	 * Integer.valueOf(3)); assertEquals(createdMaze.getName(), "MuscuMaze");
	 * assertEquals(createdMaze.getLength(), Integer.valueOf(12));
	 * assertEquals(createdMaze.getWidth(), Integer.valueOf(12));
	 * assertEquals(createdMaze.getCreationDate(),
	 * java.sql.Date.valueOf("2016-10-21"));
	 * assertEquals(createdMaze.getCreator().getId(), Integer.valueOf(1));
	 * assertEquals(createdMaze.getCreator().getName(), "Benji"); }
	 */

}

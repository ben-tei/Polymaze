package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import model.MazeManager;
import model.PersonManager;
import model.business.Maze;
import model.business.Person;
import model.factory.MazeFactoryStrategyName;
import util.exception.PolymazeException;

public class MazeManagerTest
{
	private Integer testPersonID = 3;

	@Test
	public void generateMaze_00()
	{
		MazeManager mazeManager = new MazeManager();
		PersonManager pm = new PersonManager();
		pm.setPersonById(testPersonID);
		Person personTest = pm.getCurrentPerson();
		try
		{
			mazeManager.getMazeFactory().setStrategy(MazeFactoryStrategyName.Backtrack);
			;
			mazeManager.generateMaze("generateMazeTest00", 10, 10, personTest);
		}
		catch(PolymazeException e)
		{
			e.printStackTrace();
		}

		// create maze test
		Maze m = mazeManager.getAllMazesList().get(mazeManager.getAllMazesList().size() - 1);
		assertEquals(m.getLength(), new Integer(10));
		assertEquals(m.getName(), "generateMazeTest00");
		assertEquals(m.getWidth(), new Integer(10));

		// set maze test
		mazeManager.setMazeById(m.getId());
		assertEquals(mazeManager.getMaze().getId(), m.getId());

		// delete maze test
		mazeManager.deleteMaze(m.getId());
		assertEquals(mazeManager.getAllMazesList().size(), 0);
	}

	@Test
	public void generateMaze_01()
	{
		MazeManager mazeManager = new MazeManager();
		PersonManager pm = new PersonManager();
		pm.setPersonById(testPersonID);
		Person personTest = pm.getCurrentPerson();
		try
		{
			mazeManager.getMazeFactory().setStrategy(MazeFactoryStrategyName.Backtrack);
			mazeManager.generateMazeWithStartEnd("generateMazeTest01", 10, 10, 0, 0, 1, 1, personTest);
		}
		catch(PolymazeException e)
		{
			e.printStackTrace();
		}

		// create maze test
		Maze m = mazeManager.getAllMazesList().get(mazeManager.getAllMazesList().size() - 1);
		assertEquals(m.getLength(), new Integer(10));
		assertEquals(m.getName(), "generateMazeTest01");
		assertEquals(m.getWidth(), new Integer(10));
		assertEquals(m.getStartX(), 0);
		assertEquals(m.getStartY(), 0);
		assertEquals(m.getEndX(), 1);
		assertEquals(m.getEndY(), 1);

		// set maze test
		mazeManager.setMazeById(m.getId());
		assertEquals(mazeManager.getMaze().getId(), m.getId());

		// delete maze test
		mazeManager.deleteMaze(m.getId());
		assertEquals(mazeManager.getAllMazesList().size(), 0);
	}

	@Test
	public void getMazeById_00()
	{
		MazeManager mazeManager = new MazeManager();
		PersonManager pm = new PersonManager();
		pm.setPersonById(testPersonID);
		Person personTest = pm.getCurrentPerson();
		try
		{
			mazeManager.getMazeFactory().setStrategy(MazeFactoryStrategyName.Backtrack);
			mazeManager.generateMaze("getMazeByIdTest00", 10, 10, personTest);
		}
		catch(PolymazeException e)
		{
			e.printStackTrace();
		}

		// create maze test
		Maze m = mazeManager.getAllMazesList().get(mazeManager.getAllMazesList().size() - 1);

		Integer id = m.getId();
		boolean bool = mazeManager.setMazeById(id);
		assertEquals(bool, true);

		assertEquals(mazeManager.getMaze().getId(), Integer.valueOf(m.getId()));
		assertEquals(mazeManager.getMaze().getName(), "getMazeByIdTest00");
		assertEquals(mazeManager.getMaze().getCreator().getId(), testPersonID);

		// delete maze test
		mazeManager.deleteMaze(m.getId());
		assertEquals(mazeManager.getAllMazesList().size(), 0);

	}

	@Test
	public void getMazeById_01()
	{
		Integer id = 151126;
		MazeManager mazeManager = new MazeManager();
		boolean bool = mazeManager.setMazeById(id);

		assertEquals(bool, false);
	}

	@Test
	public void getMazeByName_00()
	{

		MazeManager mazeManager = new MazeManager();
		PersonManager pm = new PersonManager();
		pm.setPersonById(testPersonID);
		Person personTest = pm.getCurrentPerson();
		try
		{
			mazeManager.getMazeFactory().setStrategy(MazeFactoryStrategyName.Backtrack);
			mazeManager.generateMaze("getMazeByNameTest00", 10, 10, personTest);
		}
		catch(PolymazeException e)
		{
			e.printStackTrace();
		}

		// create maze test
		Maze m = mazeManager.getAllMazesList().get(mazeManager.getAllMazesList().size() - 1);
		boolean bool = mazeManager.setMazeByName(m.getName());

		assertEquals(bool, true);
		assertEquals(mazeManager.getMaze().getId(), Integer.valueOf(m.getId()));
		assertEquals(mazeManager.getMaze().getName(), "getMazeByNameTest00");
		assertEquals(mazeManager.getMaze().getCreator().getId(), testPersonID);

		// delete maze test
		mazeManager.deleteMaze(m.getId());
		assertEquals(mazeManager.getAllMazesList().size(), 0);
	}

	@Test
	public void getMazeByName_01()
	{
		String name = "hellomaze";
		MazeManager mazeManager = new MazeManager();
		boolean bool = mazeManager.setMazeByName(name);

		assertEquals(bool, false);
	}

	@Test
	public void getMazesByCreator00()
	{
		MazeManager mazeManager = new MazeManager();
		PersonManager pm = new PersonManager();
		pm.setPersonById(testPersonID);
		Person personTest = pm.getCurrentPerson();
		try
		{
			mazeManager.getMazeFactory().setStrategy(MazeFactoryStrategyName.Backtrack);
			mazeManager.generateMaze("getMazesByCreatorTest1", 10, 10, personTest);
		}
		catch(PolymazeException e)
		{
		}

		mazeManager.setAllMazes();
		mazeManager.setMazesByCreator(personTest);

		// verify that there is some mazes
		assertNotEquals(Integer.valueOf(mazeManager.getCreatorMazesList().size()), Integer.valueOf(0));

		// clean up:
		List<Maze> mazesTest = mazeManager.getCreatorMazesList();
		for(int i = 0; i < mazesTest.size(); ++i)
		{
			mazeManager.deleteMaze(mazesTest.get(i).getId());
		}
	}

	@Test
	public void getAllMazes00()
	{
		MazeManager mazeManager = new MazeManager();
		mazeManager.setAllMazes();
		assertNotEquals(Integer.valueOf(mazeManager.getAllMazesList().size()), Integer.valueOf(0));
	}
}

package test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.MazeManager;
import model.PersonManager;
import model.business.Person;

public class MazeManagerTest
{
	@Test
	public void getMazeById_00() {
		Integer id = 1;
		MazeManager mazeManager = new MazeManager();
		boolean bool = mazeManager.getMazeById(id);
		
		assertEquals(bool, true);
		assertEquals(mazeManager.getMaze().getId(), Integer.valueOf(1));
		assertEquals(mazeManager.getMaze().getName(), "TestMaze");
		assertEquals(mazeManager.getMaze().getCreator().getName(), "test");
	}
	
	@Test
	public void getMazeById_01() {
		Integer id = 156;
		MazeManager mazeManager = new MazeManager();
		boolean bool = mazeManager.getMazeById(id);
		
		assertEquals(bool, false);
	}
	
	@Test
	public void getMazeByName_00() {
		String name = "TestMaze";
		MazeManager mazeManager = new MazeManager();
		boolean bool = mazeManager.getMazeByName(name);
		
		assertEquals(bool, true);
		assertEquals(mazeManager.getMaze().getId(), Integer.valueOf(1));
		assertEquals(mazeManager.getMaze().getName(), "TestMaze");
		assertEquals(mazeManager.getMaze().getCreator().getName(), "test");
	}
	
	@Test
	public void getMazeByName_01() {
		String name = "hellomaze";
		MazeManager mazeManager = new MazeManager();
		boolean bool = mazeManager.getMazeByName(name);
		
		assertEquals(bool, false);
	}
	
	@Test
	public void getMazesByCreator() {
		PersonManager personManager = new PersonManager();
		boolean bool = personManager.getPersonByName("test");
		Person person = null;
		if (bool) {
			person = personManager.getCurrentPerson();
		}
		MazeManager mazeManager = new MazeManager();
		mazeManager.getMazesByCreator(person);
		assertEquals(mazeManager.getMazeList().size(), 2);
		assertEquals(mazeManager.getMazeList().get(0).getName(), "TestMaze");
		assertEquals(mazeManager.getMazeList().get(1).getName(), "MySecondMaze");
		// Verifying the two Mazes have the same creator
		assertEquals(mazeManager.getMazeList().get(0).getCreator().getName(), mazeManager.getMazeList().get(1).getCreator().getName());
	}
	
	public void getAllMazes() {
		MazeManager mazeManager = new MazeManager();
		mazeManager.getAllMazes();
		assertEquals(mazeManager.getMazeList().size(), 3);
		assertEquals(mazeManager.getMazeList().get(0).getName(), "TestMaze");
		assertEquals(mazeManager.getMazeList().get(1).getName(), "MySecondMaze");
		assertEquals(mazeManager.getMazeList().get(2).getName(), "MuscuMaze");		
		assertEquals(mazeManager.getMazeList().get(0).getCreator().getName(), "test");
		assertEquals(mazeManager.getMazeList().get(2).getCreator().getName(), "Benji");
	}
}

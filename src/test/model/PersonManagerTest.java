package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PersonManager;

public class PersonManagerTest {

	@Test
	public void tryLoginTest_00() {
		String name = "test";
		String password = "test";
		PersonManager personManager = new PersonManager();
		boolean login = personManager.tryLogin(name, password);
		
		assertEquals(login, true);
		assertEquals(personManager.getCurrentPerson().getId(), Integer.valueOf(4));
		assertEquals(personManager.getCurrentPerson().getName(), "test");
	}
	
	@Test
	public void tryLoginTest_01() {
		String name = "test";
		String password = "TEST";
		PersonManager personManager = new PersonManager();
		boolean login = personManager.tryLogin(name, password);
		
		assertEquals(login, false);
	}
	
	@Test
	public void getPersonByNameTest_00() {
		String name = "test";
		PersonManager personManager = new PersonManager();
		boolean bool = personManager.getPersonByName(name);
		
		assertEquals(bool, true);
		assertEquals(personManager.getCurrentPerson().getId(), Integer.valueOf(4));
		assertEquals(personManager.getCurrentPerson().getName(), "test");
	}
	
	@Test
	public void getPersonByNameTest_01() {
		String name = "bonjour";
		PersonManager personManager = new PersonManager();
		boolean bool = personManager.getPersonByName(name);
		
		assertEquals(bool, false);
	}
	
	@Test
	public void getPersonByIdTest_00() {
		Integer id = 4;
		PersonManager personManager = new PersonManager();
		boolean bool = personManager.getPersonById(id);
		
		assertEquals(bool, true);
		assertEquals(personManager.getCurrentPerson().getId(), Integer.valueOf(4));
		assertEquals(personManager.getCurrentPerson().getName(), "test");
	}

	@Test
	public void getPersonByIdTest_01() {
		Integer id = 120;
		PersonManager personManager = new PersonManager();
		boolean bool = personManager.getPersonById(id);
		
		assertEquals(bool, false);
	}
	
	/*@Test
	public void createPersonTest() {
		String login = "Gaet";
		String password = "swag";
		PersonManager personManager = new PersonManager();
		boolean bool = personManager.createPerson(login, password);
		
		assertEquals(bool, true);
	}*/

}

package test.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import model.business.Person;
import model.dao.DataBaseFacade;

public class PersonDaoImplTest
{

	@Test
	public void getPersonByLoginTest()
	{
		String name = "test";
		String password = "test";
		Person person = DataBaseFacade.tryLogin(name, password);

		assertNotNull(person);
		assertEquals(person.getId(), Integer.valueOf(4));
		assertEquals(person.getName(), "test");
	}

	@Test
	public void getPersonByNameTest()
	{
		String name = "test";
		Person person = DataBaseFacade.getPersonByName(name);

		assertNotNull(person);
		assertEquals(person.getId(), Integer.valueOf(4));
		assertEquals(person.getName(), "test");
	}

	@Test
	public void getPersonByIdTest()
	{
		Integer id = 4;
		Person person = DataBaseFacade.getPersonById(id);

		assertNotNull(person);
		assertEquals(person.getId(), Integer.valueOf(4));
		assertEquals(person.getName(), "test");
	}

	/*
	 * @Test public void createPersonTest(){ String name = "newUser"; String
	 * password = "password"; Person person = DataBaseFacade.createPerson(name,
	 * password);
	 * 
	 * assertNotNull(person); assertEquals(person.getName(), "newUser"); }
	 */

}

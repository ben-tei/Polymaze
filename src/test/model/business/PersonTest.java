package test.model.business;

import static org.junit.Assert.*;

import org.junit.Test;

import model.business.Person;

/**
 * @author Loic Class for testing Person
 */
public class PersonTest {

	@Test
	public void PersonConstructor01_test() {
		Person person = new Person(10, "Lama");
		assertEquals((Integer) 10, person.getId());
		assertEquals("Lama", person.getName() );
	}
	
	@Test
	public void PersonConstructor02_test() {
		Person person = new Person();
		person.setId(10);
		assertEquals((Integer) 10, person.getId());
		person.setName("Lama");
		assertEquals("Lama", person.getName() );
	}

}

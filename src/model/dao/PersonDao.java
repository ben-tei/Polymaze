package model.dao;

import model.business.Person;
import util.exception.PolymazeException;

/**
 * Interface for a Person Data Access Object
 * 
 * @author Gaetan FRANCOIS
 *
 */
public interface PersonDao
{

	/**
	 * This method retrieves a Person in the database thanks to its login
	 * 
	 * @param name:
	 *            the Person's name
	 * @param password:
	 *            the Person's password
	 * @return the retrieved Person
	 */
	public Person getPersonByLogin(String name, String password) throws PolymazeException;

	/**
	 * This method retrieves a Person in the database thanks to its name
	 * 
	 * @param name:
	 *            the Person's name
	 * @return the retrieved Person
	 */
	public Person getPersonByName(String name);

	/**
	 * This method retrieves a Person in the database thanks to its id
	 * 
	 * @param id:
	 *            the Person's id
	 * @return the retrieved Person
	 */
	public Person getPersonById(Integer id);

	/**
	 * This method creates a Person in the database
	 * 
	 * @param name:
	 *            the Person's name
	 * @param password:
	 *            the Person's password
	 * @return the created Person
	 */
	public Person createPerson(String name, String password) throws PolymazeException;

}

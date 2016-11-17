package model;

import model.business.Person;
import model.dao.DataBaseFacade;

/**
 * This class manages a Person
 *
 * Each method calls the DataBaseFacade which is working with the Database
 */
public class PersonManager
{
	// Attributes
	private Person currentPerson;

	// Constructor
	public PersonManager()
	{
		super();
		this.currentPerson = null;
	}

	/**
	 * This method tries to connect a Person to the application
	 * 
	 * @param login
	 *            the Person's name
	 * @param password
	 *            the Person's password
	 * @return true if the login/password combination is good, false otherwise
	 */
	public boolean tryLogin(String login, String password)
	{
		boolean bool = false;
		Person myPerson = DataBaseFacade.tryLogin(login, password);

		if(myPerson != null)
		{
			this.setCurrentPerson(myPerson);
			bool = true;
		}
		else
		{
			bool = false;
		}

		return bool;
	}

	/**
	 * This method sets a Person by its name
	 * 
	 * @param name
	 *            the Person's name
	 * @return true if a Person with the name exists, false otherwise
	 */
	public boolean setPersonByName(String name)
	{
		boolean bool = false;
		Person myPerson = DataBaseFacade.getPersonByName(name);

		if(myPerson != null)
		{
			this.setCurrentPerson(myPerson);
			bool = true;
		}
		else
		{
			bool = false;
		}

		return bool;
	}

	/**
	 * This method sets a Person by its id
	 * 
	 * @param id
	 *            the Person's id
	 * @return true if a Person with the id exists, false otherwise
	 */
	public boolean setPersonById(Integer id)
	{
		boolean bool = false;
		Person myPerson = DataBaseFacade.getPersonById(id);

		if(myPerson != null)
		{
			this.setCurrentPerson(myPerson);
			bool = true;
		}
		else
		{
			bool = false;
		}

		return bool;
	}

	/**
	 * Creates a Person
	 * 
	 * @param login
	 *            the Person's login
	 * @param password
	 *            the Person's password
	 * @return true if success, false otherwise (profile already exists)
	 */
	public boolean createPerson(String login, String password)
	{
		boolean bool = false;
		Person myPerson = DataBaseFacade.createPerson(login, password);

		if(myPerson != null)
		{
			this.setCurrentPerson(myPerson);
			bool = true;
		}
		else
		{
			bool = false;
		}

		return bool;
	}

	// Getter
	public Person getCurrentPerson()
	{
		return this.currentPerson;
	}

	// Setter
	public void setCurrentPerson(Person person)
	{
		this.currentPerson = person;
	}

}

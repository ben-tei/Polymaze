package controller;

import model.*;

/**
 * This class is the bridge between the view and the model
 *
 * Each method calls methods from the model
 */
public class UIController
{

	private MazeManager myMazeManager;
	private PersonManager myUserManager;

	/**
	 * default constructor
	 */
	public UIController()
	{
		this.myUserManager = new PersonManager();
		this.myMazeManager = new MazeManager(); // maze manager needs a reference to the user manager to instanciate mazes and to know current user

	}

	/**
	 * getter for the mazeManager
	 * 
	 * @return MazeManager : the maze manager associated with this controller
	 */
	public MazeManager getMazeManager()
	{
		return this.myMazeManager;
	}

	/**
	 * getter for the personManager
	 * 
	 * @return PersonManager : the personManager associated with this controller
	 */
	public PersonManager getUserManager()
	{
		return this.myUserManager;
	}

	/**
	 * method used when the application user wants to sign in.
	 * 
	 * @param login
	 *            the Person's name
	 * @param password
	 *            the Person's password
	 * @return true if the login/password combination is good, false otherwise
	 */
	public boolean signIn(String login, String password)
	{
		return this.myUserManager.tryLogin(login, password);
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
	public boolean signUp(String login, String password)
	{
		return this.myUserManager.createPerson(login, password);
	}

}

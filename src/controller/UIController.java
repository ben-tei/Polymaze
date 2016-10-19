package controller;

import model.*;

public class UIController
{

	private MazeManager myMazeManager;
	private UserManager myUserManager;

	public UIController()
	{
		this.myUserManager = new UserManager();
		this.myMazeManager = new MazeManager(myUserManager); // maze manager needs a reference to the user manager to instanciate mazes and to know current user

	}

	public MazeManager getMazeManager()
	{
		return this.myMazeManager;
	}

	public UserManager getUserManager()
	{
		return this.myUserManager;
	}

	public boolean signIn(String login, String password)
	{
		return this.myUserManager.tryLogin(login, password);
	}

	public boolean signUp(String login, String password)
	{
		return this.myUserManager.createUser(login, password);
	}

}

package controller;

import model.*;

public class UIController
{

	private MazeManager myMazeManager;
	private UserManager myUserManager;

	public UIController()
	{
		myMazeManager = new MazeManager();
		myUserManager = new UserManager();
	}

	public MazeManager getMazeManager()
	{
		return myMazeManager;
	}

	public UserManager getUserManager()
	{
		return myUserManager;
	}

	public void signIn(String login, String password)
	{

	}

	public void signUp(String login, String password)
	{

	}

}

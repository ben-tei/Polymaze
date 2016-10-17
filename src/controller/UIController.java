package controller;

import model.*;

public class UIController
{

	private MazeManager myMazeManager_;
	private UserManager myUserManager_;

	public UIController()
	{
		myUserManager_ = new UserManager();
		myMazeManager_ = new MazeManager(myUserManager_);// maze manager needs a reference to the user manager to instanciate mazes and to know current user
		
	}

	public MazeManager getMazeManager()
	{
		return myMazeManager_;
	}

	public UserManager getUserManager()
	{
		return myUserManager_;
	}

	public boolean signIn(String login, String password)
	{
		return myUserManager_.tryLogin(login, password);
	}

	public boolean signUp(String login, String password)
	{
		return myUserManager_.createUser(login, password);
	}
	

}

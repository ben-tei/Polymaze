package model.dao;

import model.business.Person;
import util.PolymazeException;

import java.util.List;

import model.business.Maze;

public final class DataBaseFacade
{
	private static MazeDao mazeDao = new MazeDaoImpl();
	private static PersonDao userDao = new PersonDaoImpl();

	// Constructor
	private DataBaseFacade()
	{
		super();
	}

	public static List<Maze> getMazesByCreator(Person creator)
	{
		return mazeDao.getMazesByCreator(creator);
	}

	public static Maze getMazeByName(String name)
	{
		return mazeDao.getMazeByName(name);
	}

	public static void createMaze(Maze maze) throws PolymazeException
	{
		try
		{
			mazeDao.createMaze(maze);
		}
		catch(PolymazeException e)
		{
			throw e;
		}
	}
	
	// Person

	public static Person tryLogin(String login, String password)
	{
		try
		{
			Person tmp = userDao.getPersonByLogin(login, password);
			return tmp;
		}
		catch(PolymazeException e)
		{
			return null; // if the person doesn't exist, return null
		}
	}

	public static Person createUser(String login, String password)
	{
		try
		{
			Person tmp = userDao.createPerson(login, password);
			return tmp;
		}
		catch(PolymazeException e)
		{
			return null; // if the person was not created, return null
		}
	}
}

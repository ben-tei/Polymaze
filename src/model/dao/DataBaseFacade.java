package model.dao;

import model.business.Person;
import util.PolymazeException;

import java.util.List;

import model.business.Maze;

public final class DataBaseFacade {
	static private MazeDao mazeDao_ = new MazeDaoImpl();
	static private PersonDao userDao_ = new PersonDaoImpl();
	
	private DataBaseFacade () {
		
	}
	
	public static List<Maze> getMazesByCreator(Person creator){
		return mazeDao_.getMazesByCreator(creator);
	}
	
	public static Maze getMazeByName(String name){
		return mazeDao_.getMazeByName(name);
	}
	
	public static void createMaze(Maze maze) throws PolymazeException{
		try {
			mazeDao_.createMaze(maze);
		} catch (PolymazeException e) {
			throw e;
		}
	}
	
	public static Person tryLogin(String login, String password){
		try {
			Person tmp = userDao_.getPersonByLogin(login, password);
			return tmp;
		} catch (PolymazeException e) {
			return null; // if the person doesn't exist, return null
		}
	}
	
	public static Person createUser(String login, String password){
		try {
			Person tmp = userDao_.createPerson(login, password);
			return tmp;
		} catch (PolymazeException e) {
			return null; // if the person was not created, return null
		}
	}
}

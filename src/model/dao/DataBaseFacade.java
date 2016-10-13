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
}

package model.factory;

import model.business.Maze;
import model.business.Person;
import model.factory.strategies.BacktrackStrategy;
import model.factory.strategies.EllerStrategy;
import model.factory.strategies.KruskalStrategy;
import model.factory.strategies.PrimStrategy;

public class MazeFactory
{
	private MazeFactoryStrategy currentStrategy_;

	public void setStrategy(MazeFactoryStrategyName name)
	{
		switch(name)
		{
			case Backtrack:
				currentStrategy_ = new BacktrackStrategy();
				break;
			case Eller:
				currentStrategy_ = new EllerStrategy();
				break;
			case Kruskal:
				currentStrategy_ = new KruskalStrategy();
				break;
			case Prim:
				currentStrategy_ = new PrimStrategy();
				break;
			default:
				currentStrategy_ = new BacktrackStrategy(); // in case of bug, use backtrack
		}
	}

	public MazeFactoryStrategyName getStrategyName()
	{
		return currentStrategy_.NAME;
	}

	public Maze generateMaze(String name, Integer length, Integer width, Person creator)
	{
		return currentStrategy_.generateMaze(name, length, width, creator);
	}
}

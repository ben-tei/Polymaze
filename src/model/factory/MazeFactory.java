package model.factory;

import model.business.Maze;
import model.business.Person;
import model.factory.strategies.BacktrackStrategy;
import model.factory.strategies.EllerStrategy;
import model.factory.strategies.KruskalStrategy;
import model.factory.strategies.PrimStrategy;

public class MazeFactory
{
	private MazeFactoryStrategy currentStrategy;

	public void setStrategy(MazeFactoryStrategyName name)
	{
		switch(name)
		{
			case Backtrack:
				this.currentStrategy = new BacktrackStrategy();
				break;
			case Eller:
				this.currentStrategy = new EllerStrategy();
				break;
			case Kruskal:
				this.currentStrategy = new KruskalStrategy();
				break;
			case Prim:
				this.currentStrategy = new PrimStrategy();
				break;
			default:
				this.currentStrategy = new BacktrackStrategy(); // in case of bug, use backtrack
		}
	}

	public MazeFactoryStrategyName getStrategyName()
	{
		return currentStrategy.NAME;
	}

	public Maze generateMaze(String name, Integer length, Integer width, Person creator)
	{
		return currentStrategy.generateMaze(name, length, width, creator);
	}
}

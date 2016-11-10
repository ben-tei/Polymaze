package test.model.business.cell;

import static org.junit.Assert.*;

import org.junit.Test;

import model.business.cell.KruskalCell;

/**
 * @author Aurelien
 */
public class KruskalCellTest
{

	@Test
	public void constructor1_Test()
	{
		KruskalCell cell = new KruskalCell(4, 3, 1);

		assertEquals(4, cell.getPositionX());
		assertEquals(3, cell.getPositionY());

		assertEquals(true, cell.isWallNorth());
		cell.setWallNorth(false);
		assertEquals(false, cell.isWallNorth());

		assertEquals(true, cell.isWallEast());
		cell.setWallEast(false);
		assertEquals(false, cell.isWallEast());

		assertEquals(true, cell.isWallSouth());
		cell.setWallSouth(false);
		assertEquals(false, cell.isWallSouth());

		assertEquals(true, cell.isWallWest());
		cell.setWallWest(false);
		assertEquals(false, cell.isWallWest());

		assertEquals(false, cell.isVisited());

		cell.setVisited(true);
		assertEquals(true, cell.isVisited());

		assertEquals(new Integer(1), cell.getSetID());
		cell.setSetID(2);
		assertEquals(new Integer(2), cell.getSetID());
	}

	@Test
	public void constructor2_Test()
	{
		KruskalCell cell = new KruskalCell(4, 3, true, true, true, true);

		assertEquals(4, cell.getPositionX());
		assertEquals(3, cell.getPositionY());

		assertEquals(true, cell.isWallNorth());
		cell.setWallNorth(false);
		assertEquals(false, cell.isWallNorth());

		assertEquals(true, cell.isWallEast());
		cell.setWallEast(false);
		assertEquals(false, cell.isWallEast());

		assertEquals(true, cell.isWallSouth());
		cell.setWallSouth(false);
		assertEquals(false, cell.isWallSouth());

		assertEquals(true, cell.isWallWest());
		cell.setWallWest(false);
		assertEquals(false, cell.isWallWest());

		assertEquals(false, cell.isVisited());

		cell.setVisited(true);
		assertEquals(true, cell.isVisited());

		assertEquals(new Integer(0), cell.getSetID());
		cell.setSetID(1);
		assertEquals(new Integer(1), cell.getSetID());
	}
}

package test.model.business.cell;

import static org.junit.Assert.*;

import org.junit.Test;

import model.business.cell.BacktrackCell;

public class BacktrackCellTest
{

	@Test
	public void constructor1_Test()
	{
		BacktrackCell cell = new BacktrackCell(1, 2);

		assertEquals(1, cell.getPositionX());
		assertEquals(2, cell.getPositionY());
		assertEquals(false, cell.isBtCellVisited());
		assertEquals(true, cell.isWallNorth());
		assertEquals(true, cell.isWallEast());
		assertEquals(true, cell.isWallSouth());
		assertEquals(true, cell.isWallWest());

		cell.setBtCellVisited(true);
		assertEquals(true, cell.isBtCellVisited());

		cell.setBtCellVisited(false);
		assertEquals(false, cell.isBtCellVisited());
	}

	@Test
	public void constructor2_Test()
	{
		BacktrackCell cell = new BacktrackCell(1, 2, true, false, false, true);

		assertEquals(1, cell.getPositionX());
		assertEquals(2, cell.getPositionY());
		assertEquals(false, cell.isBtCellVisited());
		assertEquals(true, cell.isWallNorth());
		assertEquals(false, cell.isWallEast());
		assertEquals(false, cell.isWallSouth());
		assertEquals(true, cell.isWallWest());

		cell.setBtCellVisited(true);
		assertEquals(true, cell.isBtCellVisited());

		cell.setBtCellVisited(false);
		assertEquals(false, cell.isBtCellVisited());
	}

}

package test.model.business.cell;

import static org.junit.Assert.*;

import org.junit.Test;

import model.business.cell.PrimCell;

/**
 * @author Aurelien
 */
public class PrimCellTest
{

	@Test
	public void constructor1_Test()
	{
		PrimCell cell = new PrimCell(2, 3);

		assertEquals(2, cell.getPositionX());
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
	}

	@Test
	public void constructor2_Test()
	{
		PrimCell cell = new PrimCell(2, 3, true, true, true, true);

		assertEquals(2, cell.getPositionX());
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
	}
}

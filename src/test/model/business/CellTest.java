package test.model.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.business.Cell;
import util.exception.model.business.SetWallsFromStringNo0or1Exception;
import util.exception.model.business.SetWallsFromStringNot4CharException;

/**
 * test class for class Cell methods tested: - callToString() -
 * setWallsFromString()
 * 
 * @author Loic
 *
 */
public class CellTest
{

	@Test
	public void ConstructorTest_01() throws Exception
	{
		Cell cell = new Cell(0, 2);
		assertEquals(0, cell.getPositionX());
		assertEquals(2, cell.getPositionY());
		assertEquals(true, cell.isWallNorth());
		assertEquals(true, cell.isWallEast());
		assertEquals(true, cell.isWallSouth());
		assertEquals(true, cell.isWallWest());
		assertEquals(false, cell.isVisited());

		cell.setPositionX(15);
		cell.setPositionY(16);
		assertEquals(15, cell.getPositionX());
		assertEquals(16, cell.getPositionY());

		cell.setWallNorth(false);
		assertEquals(false, cell.isWallNorth());
		cell.setWallEast(false);
		assertEquals(false, cell.isWallEast());
		cell.setWallSouth(false);
		assertEquals(false, cell.isWallSouth());
		cell.setWallWest(false);
		assertEquals(false, cell.isWallWest());
		cell.setVisited(true);
		assertEquals(true, cell.isVisited());
	}

	@Test
	public void ConstructorTest_02() throws Exception
	{
		Cell cell = new Cell(0, 2, true, false, true, false);
		assertEquals(0, cell.getPositionX());
		assertEquals(2, cell.getPositionY());
		assertEquals(true, cell.isWallNorth());
		assertEquals(false, cell.isWallEast());
		assertEquals(true, cell.isWallSouth());
		assertEquals(false, cell.isWallWest());
		assertEquals(false, cell.isVisited());
	}

	@Test
	public void ConstructorTest_03() throws Exception
	{
		Cell cell = new Cell(0, 2, true, true, false, false);
		assertEquals(0, cell.getPositionX());
		assertEquals(2, cell.getPositionY());
		assertEquals(true, cell.isWallNorth());
		assertEquals(true, cell.isWallEast());
		assertEquals(false, cell.isWallSouth());
		assertEquals(false, cell.isWallWest());
		assertEquals(false, cell.isVisited());
	}

	@Test
	public void wallToStringTest_00()
	{
		Cell cell = new Cell(1, 1, true, true, true, true);
		assertEquals("1111", cell.wallToString());
	}

	@Test
	public void wallToStringTest_01()
	{
		Cell cell = new Cell(1, 1, true, true, true, false);
		assertEquals("1110", cell.wallToString());
	}

	@Test
	public void wallToStringTest_02()
	{
		Cell cell = new Cell(1, 1, true, true, false, true);
		assertEquals("1101", cell.wallToString());
	}

	@Test
	public void wallToStringTest_03()
	{
		Cell cell = new Cell(1, 1, true, true, false, false);
		assertEquals("1100", cell.wallToString());
	}

	@Test
	public void wallToStringTest_04()
	{
		Cell cell = new Cell(1, 1, true, false, true, true);
		assertEquals("1011", cell.wallToString());
	}

	@Test
	public void wallToStringTest_05()
	{
		Cell cell = new Cell(1, 1, true, false, true, false);
		assertEquals("1010", cell.wallToString());
	}

	@Test
	public void wallToStringTest_06()
	{
		Cell cell = new Cell(1, 1, true, false, false, true);
		assertEquals("1001", cell.wallToString());
	}

	@Test
	public void wallToStringTest_07()
	{
		Cell cell = new Cell(1, 1, true, false, false, false);
		assertEquals("1000", cell.wallToString());
	}

	@Test
	public void wallToStringTest_08()
	{
		Cell cell = new Cell(1, 1, false, true, true, true);
		assertEquals("0111", cell.wallToString());
	}

	@Test
	public void wallToStringTest_09()
	{
		Cell cell = new Cell(1, 1, false, true, true, false);
		assertEquals("0110", cell.wallToString());
	}

	@Test
	public void wallToStringTest_10()
	{
		Cell cell = new Cell(1, 1, false, true, false, true);
		assertEquals("0101", cell.wallToString());
	}

	@Test
	public void wallToStringTest_11()
	{
		Cell cell = new Cell(1, 1, false, true, false, false);
		assertEquals("0100", cell.wallToString());
	}

	@Test
	public void wallToStringTest_12()
	{
		Cell cell = new Cell(1, 1, false, false, true, true);
		assertEquals("0011", cell.wallToString());
	}

	@Test
	public void wallToStringTest_13()
	{
		Cell cell = new Cell(1, 1, false, false, true, false);
		assertEquals("0010", cell.wallToString());
	}

	@Test
	public void wallToStringTest_14()
	{
		Cell cell = new Cell(1, 1, false, false, false, true);
		assertEquals("0001", cell.wallToString());
	}

	@Test
	public void wallToStringTest_15()
	{
		Cell cell = new Cell(1, 1, false, false, false, false);
		assertEquals("0000", cell.wallToString());
	}
	// End tests wallToString()

	//start tests setWallsFromString
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void setWallsFromStringTest_00()
			throws SetWallsFromStringNot4CharException, SetWallsFromStringNo0or1Exception
	{
		Cell cell = new Cell(1, 1);
		exception.expect(SetWallsFromStringNot4CharException.class);
		cell.setWallsFromString("");
	}

	@Test
	public void setWallsFromStringTest_01() throws Exception
	{
		Cell cell = new Cell(1, 1);
		exception.expect(SetWallsFromStringNo0or1Exception.class);
		cell.setWallsFromString("a111");
	}

	@Test
	public void setWallsFromStringTest_02() throws Exception
	{
		Cell cell = new Cell(1, 1);
		exception.expect(SetWallsFromStringNo0or1Exception.class);
		cell.setWallsFromString("1211");
	}

	@Test
	public void setWallsFromStringTest_03() throws Exception
	{
		Cell cell = new Cell(1, 1);
		exception.expect(SetWallsFromStringNo0or1Exception.class);
		cell.setWallsFromString("11+5");
	}

	@Test
	public void setWallsFromStringTest_04() throws Exception
	{
		Cell cell = new Cell(1, 1);
		exception.expect(SetWallsFromStringNo0or1Exception.class);
		cell.setWallsFromString("111p");
	}

	@Test
	public void setWallsFromStringTest_05() throws Exception
	{
		Cell cell = new Cell(0, 2);
		cell.setWallsFromString("1111");
		assertTrue(cell.isWallNorth());
		assertTrue(cell.isWallEast());
		assertTrue(cell.isWallSouth());
		assertTrue(cell.isWallWest());
	}

	@Test
	public void setWallsFromStringTest_06() throws Exception
	{
		Cell cell = new Cell(0, 2);
		cell.setWallsFromString("1110");
		assertTrue(cell.isWallNorth());
		assertTrue(cell.isWallEast());
		assertTrue(cell.isWallSouth());
		assertFalse(cell.isWallWest());
	}

	@Test
	public void setWallsFromStringTest_07() throws Exception
	{
		Cell cell = new Cell(0, 2);
		cell.setWallsFromString("1010");
		assertTrue(cell.isWallNorth());
		assertFalse(cell.isWallEast());
		assertTrue(cell.isWallSouth());
		assertFalse(cell.isWallWest());
	}

	@Test
	public void setWallsFromStringTest_08() throws Exception
	{
		Cell cell = new Cell(0, 2);
		cell.setWallsFromString("1000");
		assertTrue(cell.isWallNorth());
		assertFalse(cell.isWallEast());
		assertFalse(cell.isWallSouth());
		assertFalse(cell.isWallWest());
	}

	@Test
	public void setWallsFromStringTest_09() throws Exception
	{
		Cell cell = new Cell(0, 2);
		cell.setWallsFromString("0000");
		assertFalse(cell.isWallNorth());
		assertFalse(cell.isWallEast());
		assertFalse(cell.isWallSouth());
		assertFalse(cell.isWallWest());
	}

}

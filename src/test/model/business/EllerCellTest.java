package test.model.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.business.cell.EllerCell;

public class EllerCellTest
{
	@Test
	public void constructorTest_00()
	{
		EllerCell cell = new EllerCell(1, 1, 1);
		assertEquals(new Integer(1), cell.getSetID());
	}

	@Test
	public void setSetIDTest_00()
	{
		EllerCell cell = new EllerCell(1, 1, 1);
		cell.setSetID(2);
		assertEquals(new Integer(2), cell.getSetID());
	}
}

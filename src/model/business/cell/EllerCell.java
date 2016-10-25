package model.business.cell;

import model.business.Cell;

public class EllerCell extends Cell
{
	private Integer setID;

	public void setSetID(Integer value)
	{
		this.setID = value;
	}

	public Integer getSetID()
	{
		return this.setID;
	}

	/**
	 * @param positionX:
	 *            int : x coordinate of the cell
	 * @param positionY:
	 *            int : y coordinate of the cell Set all walls of this Cell to
	 *            true.
	 * @param setID:
	 *            int : the set which this cell belongs currently to
	 * 
	 */
	public EllerCell(int positionX, int positionY, int setID)
	{
		super(positionX, positionY, true, true, true, true);
		this.setID = setID;
	}
}

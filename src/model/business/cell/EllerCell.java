package model.business.cell;

import model.business.Cell;
import util.exception.model.business.SetWallsFromStringNo0or1Exception;
import util.exception.model.business.SetWallsFromStringNot4CharException;

/**
 * A Eller Cell is used to generate a maze by the Eller
 *         Strategy.
 *         
 * @author Rodolphe
 */
public class EllerCell extends Cell
{
	private Integer setID;

	/**
	 * setter for the set ID number of Eller Cell. The set ID of a cell 
	 * can change during generation
	 * 
	 * @param value : the new value of the cell's set id number
	 */
	public void setSetID(Integer value)
	{
		this.setID = value;
	}

	/**
	 * getter for the set ID number of Eller Cell
	 * @return Interger : Return the cell's set id number. This is used
	 * during the maze generation for the Eller strategy
	 */
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

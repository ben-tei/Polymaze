package model.business.cell;

import model.business.Cell;

public class EllerCell extends Cell
{
	private Integer setID_;

	public void setSetID(Integer value){
		setID_ = value;
	}
	
	public Integer getSetID(){
		return setID_;
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
		setID_ = setID;
	}
}

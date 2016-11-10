package model.business.cell;

import model.business.Cell;

public class KruskalCell extends Cell
{

	private Integer setID;

	/**
	 * @param positionX:
	 *            int : x coordinate of the cell
	 * @param positionY:
	 *            int : y coordinate of the cell
	 * @param wallNorth:
	 *            boolean : true if there is a wall to the north
	 * @param wallEast:
	 *            boolean : true if there is a wall to the East
	 * @param wallSouth:
	 *            boolean : true if there is a wall to the South
	 * @param wallWest:
	 *            boolean : true if there is a wall to the West
	 */
	public KruskalCell(int positionX, int positionY, boolean wallNorth, boolean wallEast, boolean wallSouth,
			boolean wallWest)
	{
		super(positionX, positionY, wallNorth, wallEast, wallSouth, wallWest);
		this.setID = 0;
	}

	/**
	 * @param positionX:
	 *            int : x coordinate of the cell
	 * @param positionY:
	 *            int : y coordinate of the cell Set all walls of this Cell to
	 *            true.
	 */
	public KruskalCell(int positionX, int positionY, int setID)
	{
		super(positionX, positionY, true, true, true, true);
		this.setID = setID;
	}

	public void setSetID(Integer value)
	{
		this.setID = value;
	}

	public Integer getSetID()
	{
		return this.setID;
	}
}

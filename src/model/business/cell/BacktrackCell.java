package model.business.cell;

import model.business.Cell;

public class BacktrackCell extends Cell
{
	private boolean btCellVisited = false;

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
	public BacktrackCell(int positionX, int positionY, boolean wallNorth, boolean wallEast, boolean wallSouth,
			boolean wallWest)
	{
		super(positionX, positionY, wallNorth, wallEast, wallSouth, wallWest);
	}

	/**
	 * @param positionX:
	 *            int : x coordinate of the cell
	 * @param positionY:
	 *            int : y coordinate of the cell Set all walls of this Cell to
	 *            true.
	 */
	public BacktrackCell(int positionX, int positionY)
	{
		super(positionX, positionY, true, true, true, true);
	}
}

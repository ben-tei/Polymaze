package model.business;

import exception.model.business.ExceptionSetWallsFromStringNo0or1;
import exception.model.business.ExceptionSetWallsFromStringNot4Char;

public class Cell
{
	// x and y coordinate of the cell
	private int positionX;
	private int positionY;
	// True if there is a wall in that direction
	private boolean wallNorth;
	private boolean wallSouth;
	private boolean wallEast;
	private boolean wallWest;

	private boolean visited = false; // true if the cell have been visited, otherwise false

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
	public Cell(int positionX, int positionY, boolean wallNorth, boolean wallEast, boolean wallSouth, boolean wallWest)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.wallNorth = wallNorth;
		this.wallSouth = wallSouth;
		this.wallEast = wallEast;
		this.wallWest = wallWest;
	}

	/**
	 * @param positionX:
	 *            int : x coordinate of the cell
	 * @param positionY:
	 *            int : y coordinate of the cell Set all walls of this Cell to
	 *            true.
	 */
	public Cell(int positionX, int positionY)
	{
		this(positionX, positionY, true, true, true, true);
	}

	// Getters & setters
	public int getPositionX()
	{
		return this.positionX;
	}

	public void setPositionX(int coordX)
	{
		this.positionX = coordX;
	}

	public int getPositionY()
	{
		return this.positionY;
	}

	public void setPositionY(int corrdY)
	{
		this.positionY = corrdY;
	}

	public boolean isWallNorth()
	{
		return this.wallNorth;
	}

	public void setWallNorth(boolean wallNorth)
	{
		this.wallNorth = wallNorth;
	}

	public boolean isWallSouth()
	{
		return this.wallSouth;
	}

	public void setWallSouth(boolean wallSouth)
	{
		this.wallSouth = wallSouth;
	}

	public boolean isWallEast()
	{
		return this.wallEast;
	}

	public void setWallEast(boolean wallEast)
	{
		this.wallEast = wallEast;
	}

	public boolean isWallWest()
	{
		return this.wallWest;
	}

	public void setWallWest(boolean wallWest)
	{
		this.wallWest = wallWest;
	}

	public boolean isVisited()
	{
		return this.visited;
	}

	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	// End Getters & setters

	// Methods
	/**
	 * @return String : Return presence of walls as a string of 4 characters.
	 *         Each characters refer to a wall, if the character is 1, there is
	 *         a wall. If the Character is 0, there is no wall. Characters refer
	 *         to walls in this order : North East South West
	 */
	public String wallToString()
	{
		String strWalls = "";

		if(this.wallNorth)
		{
			strWalls = strWalls + "1";
		}
		else
		{
			strWalls = strWalls + "0";
		}
		if(this.wallEast)
		{
			strWalls = strWalls + "1";
		}
		else
		{
			strWalls = strWalls + "0";
		}
		if(this.wallSouth)
		{
			strWalls = strWalls + "1";
		}
		else
		{
			strWalls = strWalls + "0";
		}
		if(this.wallWest)
		{
			strWalls = strWalls + "1";
		}
		else
		{
			strWalls = strWalls + "0";
		}

		return strWalls;

	}

	/**
	 * Initialize walls of this cell from a string of 4 characters composed of 0
	 * and 1.
	 * 
	 * @param strWalls:
	 *            String of 4 characters. Each characters refer to a wall, if
	 *            the character is 1, there is a wall. If the Character is 0,
	 *            there is no wall. Characters refer to walls in this order :
	 *            North East South West
	 * @throws ExceptionSetWallsFromStringNot4Char
	 * @throws ExceptionSetWallsFromStringNo0or1
	 * @throws Exception:
	 *             If the length of strWalls != 4 or if a wall have a character
	 *             not equal to 1 or 0
	 */
	public void setWallsFromString(String strWalls)
			throws ExceptionSetWallsFromStringNot4Char, ExceptionSetWallsFromStringNo0or1
	{
		if(strWalls.length() != 4)
		{
			throw new ExceptionSetWallsFromStringNot4Char();
		}
		else
		{

			if(strWalls.charAt(0) == '1')
			{
				this.setWallNorth(true);
			}
			else if(strWalls.charAt(0) == '0')
			{
				this.setWallNorth(false);
			}
			else
			{
				throw new ExceptionSetWallsFromStringNo0or1(
						"North wall should be 1 (there's a wall) or 0 (there's no wall)");
			}
			if(strWalls.charAt(1) == '1')
			{
				this.setWallEast(true);
			}
			else if(strWalls.charAt(1) == '0')
			{
				this.setWallEast(false);
			}
			else
			{
				throw new ExceptionSetWallsFromStringNo0or1(
						"East wall should be 1 (there's a wall) or 0 (there's no wall)");
			}
			if(strWalls.charAt(2) == '1')
			{
				this.setWallSouth(true);
			}
			else if(strWalls.charAt(2) == '0')
			{
				this.setWallSouth(false);
			}
			else
			{
				throw new ExceptionSetWallsFromStringNo0or1(
						"South wall should be 1 (there's a wall) or 0 (there's no wall)");
			}
			if(strWalls.charAt(3) == '1')
			{
				this.setWallWest(true);
			}
			else if(strWalls.charAt(3) == '0')
			{
				this.setWallWest(false);
			}
			else
			{
				throw new ExceptionSetWallsFromStringNo0or1(
						"West wall should be 1 (there's a wall) or 0 (there's no wall)");
			}
		}
	}

}

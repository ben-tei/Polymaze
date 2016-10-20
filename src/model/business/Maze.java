package model.business;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.exception.model.business.ContentToStringException;
import util.exception.model.business.SetWallsFromStringNo0or1Exception;
import util.exception.model.business.SetWallsFromStringNot4CharException;

/**
 * Business class for a Maze
 * 
 * @author Gaetan FRANCOIS
 * @author Loic DULAS
 *
 */
public class Maze
{
	
	// Logger
	private static final Logger LOGGER = Logger.getLogger(Maze.class.getName());

	// Attributes
	private Integer id;
	private String name;
	private Integer length; // for y
	private Integer width; // for x
	private int startX; // coordinate start
	private int startY;
	private int endX; // coordinate end
	private int endY;
	private Cell[][] content = null; // content[x][y]
	private Date creationDate;
	private Person creator;

	// Constructors
	/**
	 * Default constructor
	 */
	public Maze()
	{
		super();
	}

	/**
	 * Constructor using 5 attributes
	 * To generate a Maze, when the user don't choose start and end point.
	 * 
	 * @param name
	 *            the Maze's name
	 * @param length
	 *            the Maze's length
	 * @param width
	 *            the Maze's width
	 * @param date
	 *            the Maze's date of creation
	 * @param creator
	 *            the Person who created the Maze
	 */
	public Maze(String name, Integer length, Integer width, Date creationDate, Person creator)
	{
		super();
		this.name = name;
		this.length = length;
		this.width = width;
		this.creationDate = creationDate;
		this.creator = creator;

		// To initialize start and end point as they are not identified.
		this.startX = 0;
		this.startY = 0;
		this.endX = this.width - 1;
		this.endY = this.length - 1;
	}

	/**
	 * Constructor using 9 attributes
	 * To generate a Maze, when the user choose start and end point.
	 * 
	 * @param name
	 *            the Maze's name
	 * @param length
	 *            the Maze's length
	 * @param width
	 *            the Maze's width
	 * @param startX
	 *            the Maze's starting point coordinate in X
	 * @param startY
	 *            the Maze's starting point coordinate in Y
	 * @param endX
	 *            the Maze's ending point coordinate in X
	 * @param endY
	 *            the Maze's ending point coordinate in Y
	 * @param date
	 *            the Maze's date of creation
	 * @param creator
	 *            the Person who created the Maze
	 */
	public Maze(String name, Integer length, Integer width, int startX, int startY, 
				int endX, int endY, Date creationDate, Person creator)
	{
		super();
		this.name = name;
		this.length = length;
		this.width = width;
		this.creationDate = creationDate;
		this.creator = creator;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}



	/**
	 * Constructor using all the attributes
	 * 
	 * @param id:
	 *            the Maze's id
	 * @param name:
	 *            the Maze's name
	 * @param length:
	 *            the Maze's length
	 * @param width:
	 *            the Maze's width
	 * @param startX:
	 *            the Maze's starting point coordinate in X
	 * @param startY:
	 *            the Maze's starting point coordinate in Y
	 * @param endX:
	 *            the Maze's ending point coordinate in X
	 * @param endY:
	 *            the Maze's ending point coordinate in Y
	 * @param content:
	 *            representation of the Maze using a String
	 * @param date:
	 *            the Maze's date of creation
	 * @param creator:
	 *            the Person who created the Maze
	 */
	public Maze(Integer id, String name, Integer length, Integer width, int startX, int startY, int endX, int endY,
			String content, Date creationDate, Person creator)
	{
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.width = width;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		try {
			this.content = this.contentFromString(content, width, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.creationDate = creationDate;
		this.creator = creator;
	}

	// Getters & setters
	// Getters
	public Integer getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public Integer getLength()
	{
		return this.length;
	}

	public Integer getWidth()
	{
		return this.width;
	}

	public Cell[][] getContent()
	{
		return this.content;
	}

	public Date getCreationDate()
	{
		return this.creationDate;
	}

	public Person getCreator()
	{
		return this.creator;
	}

	public int getStartX()
	{
		return this.startX;
	}

	public int getStartY()
	{
		return this.startY;
	}

	public int getEndX()
	{
		return this.endX;
	}

	public int getEndY()
	{
		return this.endY;
	}

	// Setters
	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setLength(Integer length)
	{
		this.length = length;
	}

	public void setWidth(Integer width)
	{
		this.width = width;
	}

	public void setContent(Cell[][] content)
	{
		this.content = content;
	}

	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public void setCreator(Person creator)
	{
		this.creator = creator;
	}

	public void setStartX(int startX)
	{
		this.startX = startX;
	}

	public void setEndX(int endX)
	{
		this.endX = endX;
	}

	public void setEndY(int endY)
	{
		this.endY = endY;
	}

	public void setStartY(int startY)
	{
		this.startY = startY;
	}

	// Methods
	/**
	 * Transforms the cell array maze into a string.
	 * 
	 * @return String of 0 and 1, with a length of 4*width*length organized by
	 *         line(width). Corresponding to a maze of size width*length. each
	 *         cell are represented by four 0 or 1 which each correspond to a
	 *         wall. In order : North East South West. 1 correspond to the
	 *         presence of a wall. 0 if there's no wall
	 * @throws ContentToStringException 
	 */
	public String contentToString() throws ContentToStringException
	{
		if (content == null ) 
		{
			throw new ContentToStringException();
		}
		
		String strContent = "";
		for(int y = 0; y < this.length; y++)
		{
			for(int x = 0; x < this.width; x++)
			{
				strContent = strContent + this.content[x][y].wallToString();
			}
		}
		return strContent;
	}

	/**
	 * Return a Cell[][] created from a String of 0 and 1.
	 * Be careful, this method do not check if the maze generated is a perfect maze.
	 * Or if walls are valid.
	 * @param strContent
	 *            String of 0 and 1, with a length of 4*width*length organized
	 *            by line(width). Corresponding to a maze of size width*length.
	 * @param width
	 *            int : width of a maze (correspond to x)
	 * @param length
	 *            int : length of a maze (correspond to y)
	 * @return Cell[width][length] Transform a string into a Cell array.
	 * @throws Exception 
	 * 			
	 */
	public Cell[][] contentFromString(String strContent, int width, int length) throws Exception
	{
		Cell[][] cellContent = new Cell[width][length];
		String currentStr = strContent;
				
		if(strContent.length() != width * length * 4 )
		{
			throw new Exception("Invalid number of characters in the string. Should be equal to 4*width*length");
		}
		if((width == 0 || length == 0) && strContent.length() != 0 )
		{
			throw new Exception("Invalid number of characters in the string. Should be equal to 0 as "
					+ " one dimension equal 0");
		}

		for(int y = 0; y < length; y++)
		{
			for(int x = 0; x < width; x++)
			{
				cellContent[x][y] = new Cell(x, y);
				try
				{
					// to initialize walls
					cellContent[x][y].setWallsFromString(currentStr.substring(0, 4));
				}
				catch(SetWallsFromStringNot4CharException e)
				{
					LOGGER.log(Level.SEVERE, "The length of strWalls != 4 ", e);
				}
				catch(SetWallsFromStringNo0or1Exception e)
				{
					LOGGER.log(Level.SEVERE, "A wall have a character not equal to 1 or 0", e);
				}
				//remove 4 first characters corresponding of the current cell.
				currentStr = currentStr.substring(4);
			}
		}
		return cellContent;
	}

}

package model.business;

import java.sql.Date;

/**
 * Business class for a Maze
 * 
 * @author Gaetan FRANCOIS
 *
 */
public class Maze
{

	// Attributes
	private Integer id;
	private String name;
	private Integer length; // for y
	private Integer width; // for x
	private int startX; // coordinate start
	private int startY;
	private int endX; // coordinate end
	private int endY;
	private Cell[][] content; // content[x][y]
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
	 * 
	 * @param name:
	 *            the Maze's name
	 * @param length:
	 *            the Maze's length
	 * @param width:
	 *            the Maze's width
	 * @param date:
	 *            the Maze's date of creation
	 * @param creator:
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

		// TODO add start and end point to constructor
		this.startX = 0;
		this.startY = 0;
		this.endX = this.width - 1;
		this.endY = this.length - 1;
	}

	/**
	 * Constructor using 6 attributes
	 * 
	 * @param name:
	 *            the Maze's name
	 * @param length:
	 *            the Maze's length
	 * @param width:
	 *            the Maze's width
	 * @param content:
	 *            representation of the Maze using a String
	 * @param date:
	 *            the Maze's date of creation
	 * @param creator:
	 *            the Person who created the Maze
	 */
	public Maze(String name, Integer length, Integer width, String content, Date creationDate, Person creator)
	{
		super();
		this.name = name;
		this.length = length;
		this.width = width;
		this.content = this.contentFromString(content, width, length);
		this.creationDate = creationDate;
		this.creator = creator;
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
		this.content = this.contentFromString(content, width, length);
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
	 */
	public String contentToString()
	{
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
	 * @param strContent:
	 *            String of 0 and 1, with a length of 4*width*length organized
	 *            by line(width). Corresponding to a maze of size width*length.
	 * @param width:
	 *            int : width of a maze (correspond to x)
	 * @param length:
	 *            int : length of a maze (correspond to y)
	 * @return Cell[width][length] Transformed string into a Cell array.
	 */
	public Cell[][] contentFromString(String strContent, int width, int length)
	{
		Cell[][] cellContent = new Cell[width][length];
		String currentStr = strContent;

		if(strContent.length() != width * length * 4)
		{
			try
			{
				throw new Exception("Invalid number of characters in the string. Should be equal to 4*width*length");
			}
			catch(Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			}
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
				catch(Exception e)
				{
					System.err.println(e);
					e.printStackTrace();
				}
				//remove 4 first characters corresponding of the current cell.
				currentStr = currentStr.substring(4);
			}
		}
		return cellContent;
	}

}

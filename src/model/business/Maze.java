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
	private Integer length;
	private Integer width;
	private String content;
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
	 * Constructor using all the attributes except the id
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
		this.content = content;
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
	 * @param content:
	 *            representation of the Maze using a String
	 * @param date:
	 *            the Maze's date of creation
	 * @param creator:
	 *            the Person who created the Maze
	 */
	public Maze(Integer id, String name, Integer length, Integer width, String content, Date creationDate, Person creator)
	{
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.width = width;
		this.content = content;
		this.creationDate = creationDate;
		this.creator = creator;
	}

	// Getters & setters
	// Getters
	public Integer getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Integer getLength()
	{
		return length;
	}

	public Integer getWidth()
	{
		return width;
	}

	public String getContent()
	{
		return content;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public Person getCreator()
	{
		return creator;
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

	public void setContent(String content)
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

}

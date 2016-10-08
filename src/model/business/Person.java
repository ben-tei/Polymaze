package model.business;

/**
 * Business class for a Person
 * 
 * @author Gaetan FRANCOIS
 *
 */
public class Person
{

	// Attributes
	private Integer id;
	private String name;

	// Constructors
	/**
	 * Default constructor
	 */
	public Person()
	{
		super();
	}

	/**
	 * Constructor using all the attributes
	 * 
	 * @param id:
	 *            the Person's id
	 * @param name:
	 *            the Person's name
	 *
	 */
	public Person(Integer id, String name)
	{
		super();
		this.id = id;
		this.name = name;
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
	
	// Setters
	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}

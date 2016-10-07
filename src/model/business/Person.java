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
	private String password;

	// Constructors
	/**
	 * Default constructor
	 */
	public Person()
	{
		super();
	}

	/**
	 * Constructor using all the attributes except the id
	 * 
	 * @param name:
	 *            the Person's name
	 * @param password:
	 *            the Person's password
	 */
	public Person(String name, String password)
	{
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * Constructor using all the attributes
	 * 
	 * @param id:
	 *            the Person's id
	 * @param name:
	 *            the Person's name
	 * @param password:
	 *            the Person's password
	 */
	public Person(Integer id, String name, String password)
	{
		super();
		this.id = id;
		this.name = name;
		this.password = password;
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

	public String getPassword()
	{
		return password;
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

	public void setPassword(String password)
	{
		this.password = password;
	}

}

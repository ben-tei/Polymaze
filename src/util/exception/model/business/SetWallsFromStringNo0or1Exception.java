package util.exception.model.business;

public class SetWallsFromStringNo0or1Exception extends Exception
{
	private static final long serialVersionUID = 1L;

	private String ErrorMessage;

	public SetWallsFromStringNo0or1Exception(String errorMessage)
	{
		super();
		this.ErrorMessage = errorMessage;
	}

	@Override
	public String getMessage()
	{
		return this.ErrorMessage;
	}
}

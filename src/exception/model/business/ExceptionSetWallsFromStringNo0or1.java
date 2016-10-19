package exception.model.business;

public class ExceptionSetWallsFromStringNo0or1 extends Exception
{
	private static final long serialVersionUID = 1L;

	private String ErrorMessage;

	public ExceptionSetWallsFromStringNo0or1(String errorMessage)
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

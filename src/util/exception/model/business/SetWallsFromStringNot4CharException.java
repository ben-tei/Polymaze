package util.exception.model.business;

public class SetWallsFromStringNot4CharException extends Exception
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage()
	{
		return "strWalls should be 4 characters for north east south west";
	}

}

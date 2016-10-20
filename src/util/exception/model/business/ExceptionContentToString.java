package util.exception.model.business;

/**
 * @author Loic
 * This exception is thrown when the content of a maze is not initialized.
 */
public class ExceptionContentToString extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage()
	{
		return "Impossible to convert the content of a maze, when content = null";
	}
}

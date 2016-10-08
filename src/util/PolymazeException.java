package util;

/**
 * This class handles the application errors
 * 
 * @author Gaetan FRANCOIS
 *
 */
public class PolymazeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PolymazeException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
}

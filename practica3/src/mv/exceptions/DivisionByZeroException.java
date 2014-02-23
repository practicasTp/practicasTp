package mv.exceptions;

public class DivisionByZeroException extends Error {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DivisionByZeroException (String message) {
		super(message);
	}
}

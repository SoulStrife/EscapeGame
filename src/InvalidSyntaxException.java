/**
 * This class is used as a flag to notify functions
 * that an invalid command has been passed.
 * 
 * @author Steven Bertolucci
 * @version 0.1 (incomplete)
 *
 */
public class InvalidSyntaxException extends Throwable {
		

	/**
	 * The version of the class for serializing purposes
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Explicit zero-argument call to superconstructor
	 */
	public InvalidSyntaxException() {
		super();
	}

	/**
	 * Explicit constructor for constructing an exception
	 * with a message.
	 * 
	 * @param message a more detailed error message
	 */
	public InvalidSyntaxException(String message) {
		super(message);
	}
		
}
/**
 * This class is used as a flag to notify functions
 * that a game exit command has occurred.
 * 
 * @author Steven Bertolucci
 * @version 0.1 (incomplete)
 *
 */
public class GameExitException extends Throwable {
		

	/**
	 * The version of the class for serializing purposes
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Explicit zero-argument call to superconstructor
	 */
	public GameExitException() {
		super();
	}

	/**
	 * Explicit constructor for constructing an exception
	 * with a message.
	 * 
	 * @param message a more detailed error message
	 */
	public GameExitException(String message) {
		super(message);
	}
		
}
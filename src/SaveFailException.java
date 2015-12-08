import java.io.IOException;

/**
 * Exception which indicates an error saving or loading
 * a GameWorld. Thrown by the methods in the TextUtils
 * class.
 * 
 * @author Steven Bertolucci
 * @version 1.0 December 6th, 2015
 *
 */
public class SaveFailException extends IOException {

	/**
	 * The version of the class for serializing purposes
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Explicit zero-argument call to superconstructor
	 */
	public SaveFailException() {
		super();
	}
	
	/**
	 * Explicit constructor for constructing an exception
	 * with a message.
	 * 
	 * @param message a more detailed error message
	 */
	public SaveFailException(String message) {
		super(message);
	}

}

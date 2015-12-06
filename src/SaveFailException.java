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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SaveFailException() {
		super();
	}
	
	public SaveFailException(String s) {
		super(s);
	}

}

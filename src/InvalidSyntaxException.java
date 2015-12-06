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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidSyntaxException() {
		super();
	}

	public InvalidSyntaxException(String s) {
		super(s);
	}
		
}
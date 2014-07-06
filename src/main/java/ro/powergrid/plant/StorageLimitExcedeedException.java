/**
 * 
 */
package ro.powergrid.plant;

/**
 * @author Catalin
 *
 */
public class StorageLimitExcedeedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5514754410708453318L;

	/**
	 * 
	 */
	public StorageLimitExcedeedException() {
	}

	/**
	 * @param message
	 */
	public StorageLimitExcedeedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public StorageLimitExcedeedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StorageLimitExcedeedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StorageLimitExcedeedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

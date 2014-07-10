package ro.powergrid;

public class InvalidPhaseActionException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPhaseActionException() {
	}

	public InvalidPhaseActionException(String arg0) {
		super(arg0);
	}

	public InvalidPhaseActionException(Throwable arg0) {
		super(arg0);
	}

	public InvalidPhaseActionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidPhaseActionException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}

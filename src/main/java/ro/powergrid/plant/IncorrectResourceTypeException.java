package ro.powergrid.plant;

public class IncorrectResourceTypeException extends Exception {

	private static final long serialVersionUID = -4000385842143358127L;

	public IncorrectResourceTypeException() {
	}

	public IncorrectResourceTypeException(String arg0) {
		super(arg0);
	}

	public IncorrectResourceTypeException(Throwable arg0) {
		super(arg0);
	}

	public IncorrectResourceTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IncorrectResourceTypeException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}

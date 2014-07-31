package ro.powergrid.city;

public class XYLocation implements Location {

	private static final long serialVersionUID = 1L;

	private final int x;
	private final int y;
	
	public XYLocation(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}

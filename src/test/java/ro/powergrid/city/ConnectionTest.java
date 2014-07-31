package ro.powergrid.city;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectionTest {

	@Test
	public void directConnectionsAreAdditive() throws Exception {
		assertEquals(2, new Connection(new DirectConnection(1), 
				new DirectConnection(1)).getDistance());
	}
}

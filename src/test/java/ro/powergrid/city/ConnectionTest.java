package ro.powergrid.city;

import static org.junit.Assert.*;

import org.junit.Test;

import ro.powergrid.city.connect.Connection;
import ro.powergrid.city.connect.DirectConnection;

public class ConnectionTest {

	@Test
	public void directConnectionsAreAdditive() throws Exception {
		assertEquals(2, onePlusOne().getDistance());
	}

	@Test
	public void connectionOf2IsGreaterThanConnectionOf1() throws Exception {
		assertTrue(onePlusOne().compareTo(one()) > 0);
	}

	public Connection onePlusOne() {
		return new Connection(one(), one());
	}

	public DirectConnection one() {
		return new DirectConnection(1);
	}
}

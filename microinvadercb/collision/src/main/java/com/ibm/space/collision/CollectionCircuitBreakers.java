package com.ibm.space.collision;


import java.util.Hashtable;

import com.ibm.space.collision.CircuitBreaker;

public class CollectionCircuitBreakers {
	
	private Hashtable<String, CircuitBreaker> vector = new Hashtable<String, CircuitBreaker>();
	private int tries;
	private long timeout;
	public CollectionCircuitBreakers(int tries, long timeout) {
		this.tries = tries;
		this.timeout = timeout;
	}

	public String callRest(String urlrest) {
		CircuitBreaker cb = vector.get(urlrest);
		if (cb == null)
		{
			 cb = new CircuitBreaker(tries, timeout, urlrest);
			vector.put(urlrest, cb);
		}
		return cb.callRest();
	}
}
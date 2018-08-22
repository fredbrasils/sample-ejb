package br.com.counterrequest;

public interface CounterRequestRemote {

	void increment() throws InterruptedException;
	
	int getCounter() throws InterruptedException;
	
}

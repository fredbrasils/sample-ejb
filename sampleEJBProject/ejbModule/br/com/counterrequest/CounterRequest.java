package br.com.counterrequest;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class CounterRequest
 */
@Singleton
@Startup
@Remote(value=CounterRequestRemote.class)
@LocalBean
@ConcurrencyManagement
public class CounterRequest implements CounterRequestRemote {

	private static final Logger log = Logger.getLogger(CounterRequest.class);			
	
	private int counter = 0;
	
    /**
     * Pos constructor. 
     */
	@PostConstruct
    public void started() {
		log.info("### Singleton: CounterRequest started!");
    }

	@Lock(LockType.WRITE)
	@Override	
	public void increment() throws InterruptedException {
		this.counter++;
		log.info("Counter: "+this.counter);
		Thread.sleep(4000);
	}
	
	@Lock(LockType.READ)
	@AccessTimeout(value=5,unit=java.util.concurrent.TimeUnit.SECONDS) 
	@Override	
	public int getCounter() throws InterruptedException {
		
		log.info("Request Counter: "+this.counter);
		Thread.sleep(5000);
		
		return this.counter;
	}

}

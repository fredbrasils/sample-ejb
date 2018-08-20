package br.com.entry;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name = "GetEntryValueEJB")
@LocalBean
public class GetEntryValue implements GetEntryValueRemote{

   private static final Logger log = Logger.getLogger(GetEntryValue.class.getName());
 
   private static final String ENV_ENTRY_MESSAGE_START = "messageStart";
   
   @Resource
   private SessionContext context;

   @Resource(name = ENV_ENTRY_MESSAGE_START)
   private String messageStart;
	   
   @PostConstruct
   public void initialize() {
      log.info("############# Initializing, part of " + PostConstruct.class.getName() + " lifecycle");
      
      log.info("############# Message Start: " + messageStart);
   }
   
	@Override
	public String returnEntry(String entryName) {
		
		if(ENV_ENTRY_MESSAGE_START.equals(entryName)) {
			return messageStart;
		}
		return getEnvironmentEntryAsString(entryName);
	}
	
    /**
    * Obtains the environment entry with the specified name, casting to a String,
    * and returning the result.  If the entry is not assignable 
    * to a String, an {@link IllegalStateException} will be raised.  In the event that the 
    * specified environment entry cannot be found, a warning message will be logged
    * and we'll return null.
    * 
    * @param envEntryName
    * @return
    * @throws IllegalStateException
    */
   private String getEnvironmentEntryAsString(final String envEntryName) throws IllegalStateException
   {
      // See if we have a SessionContext
      final SessionContext context = this.context;
      if (context == null)
      {
         log.warning("No SessionContext, bypassing request to obtain environment entry: " + envEntryName);
         return null;
      }

      // Lookup in the Private JNDI ENC via the injected SessionContext
      Object lookupValue = null;
      try
      {
         lookupValue = context.lookup(envEntryName);
         log.fine("Obtained environment entry \"" + envEntryName + "\": " + lookupValue);
      }
      catch (final IllegalArgumentException iae)
      {
         // Not found defined within this EJB's Component Environment, 
         // so return null and let the caller handle it
         log.warning("Could not find environment entry with name: " + envEntryName);
         return null;
      }

      // Cast
      String returnValue = null;
      try
      {
         returnValue = String.class.cast(lookupValue);
      }
      catch (final ClassCastException cce)
      {
         throw new IllegalStateException("The specified environment entry, " + lookupValue
               + ", was not able to be represented as a " + String.class.getName(), cce);
      }

      // Return
      return returnValue;
   }
	   
}

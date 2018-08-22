package br.com.counterrequest;

import java.util.Properties;

import javax.naming.InitialContext;

public class CounterRequest {

	public static void main(String[] args) {           
		try{   

			Properties props = new Properties();
	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	        InitialContext context = new InitialContext(props);
 
	        String appName = "";        	 
	        String moduleName = "sampleEJBProject";
	        String distinctName = "";        	 
	        String beanName = CounterRequest.class.getSimpleName();        	 
	        String interfaceName = CounterRequestRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
	        System.out.println(name);
	        
	        CounterRequestRemote bean1 = (CounterRequestRemote)context.lookup(name);
	        CounterRequestRemote bean2 = (CounterRequestRemote)context.lookup(name);
	        CounterRequestRemote bean3 = (CounterRequestRemote)context.lookup(name);
	        
	        bean1.increment();
	        System.out.println("Counter1 : "+bean1.getCounter()); 	
	        bean2.increment();
	        bean3.increment();
	        System.out.println("Counter2 : "+bean2.getCounter());
	        System.out.println("Counter3 : "+bean3.getCounter());
	        
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}

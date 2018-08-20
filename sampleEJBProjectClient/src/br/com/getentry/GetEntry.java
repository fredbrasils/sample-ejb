package br.com.getentry;

import java.util.Properties;

import javax.naming.InitialContext;

import br.com.entry.GetEntryValue;
import br.com.entry.GetEntryValueRemote;

public class GetEntry {

	public static void main(String[] args) {           
		try{   

			Properties props = new Properties();
	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	        InitialContext context = new InitialContext(props);
 
	        String appName = "";        	 
	        String moduleName = "sampleEJBProject";
	        String distinctName = "";        	 
	        String beanName = "GetEntryValueEJB";//GetEntryValue.class.getSimpleName();        	 
	        String interfaceName = GetEntryValueRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
	        System.out.println(name);
	        GetEntryValueRemote bean = (GetEntryValueRemote)context.lookup(name);
	        String result1=bean.returnEntry("messageStart");
	        String result2=bean.returnEntry("messageEnd");
	        
	        System.out.println("Result messageStart by EJB is: "+result1); 	
	        System.out.println("Result messageEnd by EJB is: "+result2);
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

}

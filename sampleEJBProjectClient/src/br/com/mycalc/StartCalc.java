package br.com.mycalc;

import java.util.Properties;

import javax.naming.InitialContext;

import br.com.firstejb.Calculator;
import br.com.firstejb.CalculatorRemote;

public class StartCalc {

	public static void main(String[] args) {           
		try{   
			System.out.println("Hello From Java!");
		    Properties props = new Properties();
	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	        InitialContext context = new InitialContext(props);
 
	        String appName = "";        	 
	        String moduleName = "sampleEJBProject";
	        String distinctName = "";        	 
	        String beanName = Calculator.class.getSimpleName();        	 
	        String interfaceName = CalculatorRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
	        System.out.println(name);
	        CalculatorRemote bean = (CalculatorRemote)context.lookup(name);
	        int result=bean.add(40,60);
	        System.out.println("Result computed by EJB is :"+result); 	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

}

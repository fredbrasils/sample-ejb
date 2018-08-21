package br.com.dialog;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class StartDialog {

	public static void main(String[] args) {           
		try{   
			
			System.out.println("#### Stateful dialog!");
			
		    Properties props = new Properties();
	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	        InitialContext context = new InitialContext(props);
	        String appName = "";        	 
	        String moduleName = "sampleEJBProject";
	        String distinctName = "";        	 
	        String beanName = Dialog.class.getSimpleName();        	 
	        String interfaceName = DialogRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName + "?stateful";
	        System.out.println(name);
	        DialogRemote bean = (DialogRemote)context.lookup(name);
	        
	        System.out.println("#### Stateless dialog!");
	        
	        Properties props1 = new Properties();
	        props1.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory"); 
	        props1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
	        InitialContext context1 = new InitialContext(props1);
	        String appName1 = "";        	 
	        String moduleName1 = "sampleEJBProject";
	        String distinctName1 = ""; 
	        String beanName1 = NoDialog.class.getSimpleName();        	 
	        String interfaceName1 = DialogBusiness.class.getName();
	        String name1 = "ejb:" + appName1 + "/" + moduleName1 + "/" +  distinctName1    + "/" + beanName1 + "!" + interfaceName1;
	        System.out.println(name1);
	        DialogBusiness bean2 = (DialogBusiness)context1.lookup(name1);

	        System.out.println("#### Stateless: " + bean2.helloMyNameIs("Thalita"));
	        System.out.println("#### Stateful: " + bean.helloMyNameIs("Fred"));
	        System.out.println("#### Stateful: " + bean.whatsMyName());
	        bean.finishDialog();
	        
	        try{
	        	System.out.println(bean.helloMyNameIs("João"));
	        	System.out.println(bean.whatsMyName());
	        }catch (Exception e) {
	        	System.out.println("#### Stateful: The dialog is finished.");
	        }
	        
	        System.out.println("#### Stateless: " + bean2.whatsMyName());
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

}

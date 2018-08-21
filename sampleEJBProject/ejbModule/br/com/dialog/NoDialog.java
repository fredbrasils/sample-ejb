package br.com.dialog;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(value=DialogBusiness.class)
@LocalBean
public class NoDialog implements DialogBusiness{
	
	private static final Logger log = Logger.getLogger(NoDialog.class.getName());
	   
	private String name;
	
	private final static String NICE_TO_MEET_YOU = "Nice to meet you ";
	private final static String YOUR_NAME_IS = "Your name is ";
	private final static String I_DONT_KNOW_YOUR_NAME = "I dont know your name.";
	
	@Override
	public String helloMyNameIs(String name) {
		log.info("###Stateless: Hello...");
		
		this.name = name;
		return NICE_TO_MEET_YOU + name.toLowerCase();
	}

	@Override
	public String whatsMyName() {
		log.info("###Stateless: Whats My Name...");
		
		if(this.name == null || this.name.isEmpty()) {
			return I_DONT_KNOW_YOUR_NAME;
		}
		
		return YOUR_NAME_IS + this.name.toLowerCase();
	}

}

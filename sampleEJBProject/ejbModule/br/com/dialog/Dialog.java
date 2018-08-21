package br.com.dialog;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@Remote(value=DialogRemote.class)
@LocalBean
public class Dialog implements DialogRemote, Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Dialog.class.getName());
	   
	private String name;
	
	private final static String NICE_TO_MEET_YOU = "Nice to meet you ";
	private final static String YOUR_NAME_IS = "Your name is ";
	private final static String I_DONT_KNOW_YOUR_NAME = "I dont know your name.";
	
	@Override
	public String helloMyNameIs(String name) {
		log.info("###Stateful: Hello...");
		
		this.name = name;
		return NICE_TO_MEET_YOU + name.toLowerCase();
	}

	@Override
	public String whatsMyName() {
		log.info("###Stateful: Whats My Name...");
		
		if(this.name == null || this.name.isEmpty()) {
			return I_DONT_KNOW_YOUR_NAME;
		}
		
		return YOUR_NAME_IS + this.name.toLowerCase();
	}
	
	@Override
	@Remove
	public void finishDialog() {
		log.info("###Stateful: Finish dialog...");
	}

	
}

package br.com.firstejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Calculator
 */
@Stateless
@LocalBean
public class Calculator implements CalculatorRemote {

    /**
     * Default constructor. 
     */
    public Calculator() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int add(int a, int b) {
		int r=a+b;
		return r;
	}

}

package br.com.firstejb;

import javax.ejb.Remote;

@Remote
public interface CalculatorRemote {

	public int add(int a , int b);
}

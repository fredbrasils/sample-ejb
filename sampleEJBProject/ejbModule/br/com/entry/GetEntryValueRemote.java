package br.com.entry;

import javax.ejb.Remote;

@Remote
public interface GetEntryValueRemote {

	String returnEntry(String entryName);
}

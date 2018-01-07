package com.aradim.raffle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class FileTicketRepository {

	private File baseDirectory;
	
	/**
	 * Creates a new Ticket Repository for the File System which reads from and stores into the given base directory.
	 * 
	 * @param baseDirectory - the directory into which the files are stored.
	 */
	public FileTicketRepository(File baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
	
	/**
	 * Loads the tickets from the file system stored in the given pot
	 * 
	 * @param potName - the name of the pot, the ticket of which are being retrieved
	 * @return the list of tickets for the given pot
	 */
	public List<Serializable> loadTicketList(String potName) {		
		File ticketFile = getTicketFile(potName);
		return this.loadTicketList(ticketFile);		
	}
	
	/**
	 * Stores the given list as the new list of tickets for the given pot
	 * 
	 * @param potName the name of the pot, for which the ticket list is to be stored
	 * @param ticketList the list of tickets that is persisted.
	 */
	public void saveTicketList(String potName, List<Serializable> ticketList) {
		File ticketFile = getTicketFile(potName);
		// TODO: add code for persisting the ticket file
	}
	
	private File getTicketFile(String potName) {
		return new File(this.baseDirectory, potName);
	}
	
	@SuppressWarnings("unchecked")
	private List<Serializable> loadTicketList(File ticketFile) {
		List<Serializable> result = new Vector<Serializable>();
		try {
			ObjectInputStream in = null;
			if (ticketFile.exists()) {
				try {
					in = new ObjectInputStream(new FileInputStream(ticketFile));				
					result = (List<Serializable>) in.readObject();
				} finally {
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// do Nothing
		}
		return result;
	}
}

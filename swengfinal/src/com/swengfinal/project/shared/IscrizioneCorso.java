package com.swengfinal.project.shared;

import java.io.Serializable;

public class IscrizioneCorso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public IscrizioneCorso() {}
	
	private int idCorso;
	private String mailStudente;
	
	public IscrizioneCorso(int idCorso, String mailStudente) {
		this.idCorso=idCorso;
		this.mailStudente=mailStudente;
	}
	
	public int getIdCorso() {
		return idCorso;
	}
	
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	
	public String getMailStudente() {
		return mailStudente;
	}
	
	public void setMailStudente(String mailStudente) {
		this.mailStudente = mailStudente;
	}

}

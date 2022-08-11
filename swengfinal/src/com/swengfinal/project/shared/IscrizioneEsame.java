package com.swengfinal.project.shared;

import java.io.Serializable;

public class IscrizioneEsame implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public IscrizioneEsame() {}
	
	private int idEsame;
	private String mailStudente;
	private int idIscrizione;
	
	public IscrizioneEsame(int idIscrizione, int idEsame, String mailStudente) {
		this.idEsame=idEsame;
		this.mailStudente=mailStudente;
		this.idIscrizione=idIscrizione;
	}
	
	public int getIdEsame() {
		return idEsame;
	}
	
	public void setIdCorso(int idEsame) {
		this.idEsame = idEsame;
	}
	
	public String getMailStudente() {
		return mailStudente;
	}
	
	public void setMailStudente(String mailStudente) {
		this.mailStudente = mailStudente;
	}
	
	public int getIdIscrizione() {
		return idIscrizione;
	}
	
	public void setIdEsame(int idEsame) {
		this.idEsame = idEsame;
	}
	
	public void setIdIscrizione(int idIscrizione) {
		this.idIscrizione = idIscrizione;
	}

}

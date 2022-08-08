package com.swengfinal.project.shared;

import java.io.Serializable;

public class Studente extends Utente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Studente() {}
	
	private String matricola;
	
	public Studente(String email, String password, String nome, String cognome,
			String luogoNascita, String dataNascita, String matricola) {
		
		super(email, password, nome, cognome, luogoNascita, dataNascita);
		this.matricola=matricola;
	}
	
	public String getMatricola() {
		return matricola;
	}
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

}

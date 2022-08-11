package com.swengfinal.project.shared;

import java.io.Serializable;

public class Docente extends Utente implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	public Docente() {}
	
	public Docente(String email, String password, String nome,
			String cognome, String luogoNascita, String dataNascita) {
		super(email, password, nome, cognome, luogoNascita, dataNascita);
	}
	

}

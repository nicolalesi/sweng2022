package com.swengfinal.project.shared;

import java.util.ArrayList;
import java.io.Serializable;

public class Esame implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private  int idEsame;
	private  int idCorso; 
	private  String data; 
	private  String ora; 
	private  String aula;
	private  String cfu;
	private  String nomeEsame;
	private  String emailDocente;
	private ArrayList<String> listaUtenti; 
	
	public Esame() {
	}

	
	public Esame(int idEsame, int idCorso, String data, String ora, String aula, String cfu, String nomeEsame, String emailDocente) {
		this.idEsame = idEsame;
		this.idCorso = idCorso;
		this.data = data;
		this.ora = ora;
		this.aula = aula;
		this.cfu=cfu;
		this.nomeEsame=nomeEsame;
		this.emailDocente=emailDocente;
		
		listaUtenti = new ArrayList<>();
	}
	
	public ArrayList<String> getListaUtenti() {
		return listaUtenti;
	}
	
	public  int getIdEsame() {
		return this.idEsame;
	}
	public  void setIdEsame(int idEsame) {
		this.idEsame = idEsame;
	}
	
	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public  String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
	
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	
	public  String getEmailDocente() {
		return emailDocente;
		
	}
	public void setEmailDocente(String emailDocente) {
		this.emailDocente=emailDocente;
	}
	public String getCfu() {
		return cfu;
	}
	public void setCfu(String cfu) {
		this.cfu = cfu;
	}
	
	public String getNomeEsame() {
		return nomeEsame;
	}
	
	public void setNomeEsame(String nomeEsame) {
		this.nomeEsame = nomeEsame;
	}
}

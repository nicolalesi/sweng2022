package com.swengfinal.project.shared;

import java.io.Serializable;

public class Voto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeEsame;
	private String matricola;
	private String voto;
	private Integer idVoto;
	private boolean pubblicato;
	
	public Voto() {}
	
	public Voto(Integer idVoto, String nomeEsame, String matricola, String voto, boolean pubblicato) {
		this.idVoto=idVoto;
		this.nomeEsame=nomeEsame;
		this.matricola=matricola;
		this.voto=voto;
		this.pubblicato=pubblicato;
	}
	
	public String getMatricola() {
		return matricola;
	}
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public String getNomeEsame() {
		return nomeEsame;
	}
	
	public void setNomeEsame(String nomeEsame) {
		this.nomeEsame = nomeEsame;
	}
	
	public String getVoto() {
		return voto;
	}
	
	public void setVoto(String voto) {
		this.voto = voto;
	}
	
	public Integer getIdVoto() {
		return idVoto;
	}
	
	public void setIdVoto(Integer idVoto) {
		this.idVoto = idVoto;
	}
	
	public boolean getPubblicato() {
		return pubblicato;
	}
	
	public void setPubblicato(boolean pubblicato) {
		this.pubblicato = pubblicato;
	}
}

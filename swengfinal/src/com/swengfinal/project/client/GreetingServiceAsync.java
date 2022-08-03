package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.swengfinal.project.shared.Corso;
import com.swengfinal.project.shared.Docente;
import com.swengfinal.project.shared.Esame;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;
import com.swengfinal.project.shared.Voto;

public interface GreetingServiceAsync {
	
	/**
	 * Metodi dell'utente 
	 */
	void registrazioneStudente(ArrayList<String> dati, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void registrazioneSegreteria(ArrayList<String> dati, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void registrazioneAdmin(ArrayList<String> dati, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void registrazioneDocente(ArrayList<String> dati, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void login(String username, String password, AsyncCallback<Utente> callback) throws IllegalArgumentException;
	
	void getInfoUtente(String email, AsyncCallback<String> callback)throws IllegalArgumentException;

	void getUtente(String email, AsyncCallback<Utente> callback)throws IllegalArgumentException;
	
	void getStudenti(AsyncCallback<ArrayList<Studente>> callback) throws IllegalArgumentException;
	
	void getDocenti(AsyncCallback<ArrayList<Docente>> callback) throws IllegalArgumentException;
	
	void getUtentiAll(AsyncCallback<ArrayList<Utente>> callback)throws IllegalArgumentException;
	
	void deleteUtente(String email, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void updateUtente(ArrayList<String> datiUtente,String email, AsyncCallback<String> callback)throws IllegalArgumentException;

	/**
	 * Metodi del corso
	 */
	void creazioneCorso(ArrayList<String> dati, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void updateCorso(ArrayList<String> datiCorso,String nomeCorso, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void iscrizioneCorso(String email, int idCorso, AsyncCallback<String>callback) throws IllegalArgumentException;
	
	void getAllCorso(String email, AsyncCallback<ArrayList<Corso>>callback) throws IllegalArgumentException;

	void getCorsoStudente(String email, AsyncCallback<ArrayList<Integer>> callback) throws IllegalArgumentException;
	
	void deleteCorso(String nomeCorso, AsyncCallback<String>callback) throws IllegalArgumentException;
	
	void  getCorsi(AsyncCallback<ArrayList<Corso>> callback) throws IllegalArgumentException;
	
	/**
	 * Metodi esame
	 */
	void creazioneEsame(ArrayList<String> dati, int idCorso,AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void updateEsame(ArrayList<String> esameUpdate, int idCorso, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void iscrizioneEsame(String email, int idCorso, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void getAllEsame(String email, AsyncCallback<ArrayList<Esame>> callback) throws IllegalArgumentException;
	
	void getEsameStudente(String email, AsyncCallback<ArrayList<Integer>> callback) throws IllegalArgumentException;
	
	void deleteEsame(int idCorso, AsyncCallback<String>callback) throws IllegalArgumentException;
	
	void  getEsami(AsyncCallback<ArrayList<Esame>> callback) throws IllegalArgumentException;
	
	void getIscrizioniEsame(Integer idEsame,AsyncCallback<ArrayList<String>> callback) throws IllegalArgumentException; 
	
	/**
	 * Metodi voti
	 */
	void addVoto(ArrayList<String> dati, AsyncCallback<String>callback) throws IllegalArgumentException;
	
	void getVoto(String matricola, AsyncCallback<ArrayList<Voto>> callback) throws IllegalArgumentException;
	
	void getVotoAll(AsyncCallback<ArrayList<Voto>> callback) throws IllegalArgumentException;

	void getDatabase(AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void votoPubblicato(ArrayList<String> dati, AsyncCallback<String>callback) throws IllegalArgumentException;

	void eliminaVoto(Voto voto, AsyncCallback<String> callback)  throws IllegalArgumentException;
}

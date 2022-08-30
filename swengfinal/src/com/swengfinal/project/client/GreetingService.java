package com.swengfinal.project.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.swengfinal.project.shared.Corso;
import com.swengfinal.project.shared.Docente;
import com.swengfinal.project.shared.Esame;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;
import com.swengfinal.project.shared.Voto;

@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	// METODI UTENTE
	
	/**
	 * Registrazione dello studente,input un ArrayList con i dati dello studente
	 * e restituisce un messaggio di riuscita dell'operazione
	 **/
	String registrazioneStudente(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Registrazione della segreteria,input un ArrayList con i dati
	 *  e restituisce un messaggio di riuscita dell'operazione
	 **/
	String registrazioneSegreteria(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Registrazione dell'admin,input un ArrayList con i dati
	 *  e restituisce un messaggio di riuscita dell'operazione
	 **/
	String registrazioneAdmin(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Registrazione del docente,input un ArrayList con i dati
	 *  e restituisce un messaggio di riuscita dell'operazione
	 **/
	String registrazioneDocente(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Gestione del login, prende in input la mail e la password scritti nel form
	 * e restituisce un oggetto di tipo utente se l'operazione è riuscita
	 **/
	Utente login(String username, String password) throws IllegalArgumentException;

	/**
	 * Restituisce le informazioni dell'utente,restituisce 
	 * i dati dell'utente se l'operazione è riuscita
	 **/
	String getInfoUtente(String email)throws IllegalArgumentException;

	/**
	 * Restituisce un utente con la stessa mail passata come parametro
	 **/
	Utente getUtente(String email) throws IllegalArgumentException;

	/**
	 * Restituice i dati dell'utente se l'operazione è andata a buon fine
	 **/
	ArrayList<Studente> getStudenti() throws IllegalArgumentException;

	/**
	 * Restituisce lista docenti registrati alla piattaforma
	 **/
	ArrayList<Docente> getDocenti() throws IllegalArgumentException;

	/**
	 * Restituisce lista di tutti gli utenti registrati
	 **/
	ArrayList<Utente> getUtentiAll() throws IllegalArgumentException;

	/**
	 * Elimina utente con una determinata mail
	 **/
	String deleteUtente(String email) throws IllegalArgumentException;

	/**
	 * Modifica utente prendendo in input mail e dati da aggiornare
	 **/
	String updateUtente(ArrayList<String> utenteUpdate, String email) throws IllegalArgumentException;


	// METODI CORSO

	/* Crea un corso e lo inserisce nel db, prende in input i dati del corso 
	 */
	String creazioneCorso(ArrayList<String> dati) throws IllegalArgumentException;

	/* Modifica i dati del corso prende in input i dati da aggiornare ed il nome del corso, riaggiunge il corso 
	 * con i dati aggiornati
	 */
	String updateCorso(ArrayList<String> corsoUpdate, String nomeCorso) throws IllegalArgumentException;

	/* Iscrive utente ad un corso, input mail studente
	 * l'id del corso  */
	String iscrizioneCorso(String email, int idCorso) throws IllegalArgumentException;

	/* Restituisce tutti i corsi a cui uno studento è iscritto, input  mail */
	ArrayList<Corso> getAllCorso(String email) throws IllegalArgumentException;

	/* Restituisce tutti i corsi a cui uno studente è iscritto, input 
	 * mail dello studente, restituisce un arraylist con l'id dei corsi a cui è iscritto*/
	ArrayList<Integer> getCorsoStudente(String email) throws IllegalArgumentException;

	/* Rimuove un corso, input nome del corso*/
	String deleteCorso(String nomeCorso) throws IllegalArgumentException;

	/*Restituisce corsi presenti nel database */
	ArrayList<Corso> getCorsi() throws IllegalArgumentException;


	 // METODI ESAME
	
	/* Crea un esame, input dati dell'esame e id del corso corrispondente,*/
	String creazioneEsame(ArrayList<String> dati, int idCorso) throws IllegalArgumentException;

	/* Modifica i dati dell'esame prende in input i dati da aggiornare ed id del corso, riaggiunge esame
	 * con i dati aggiornati
	 */
	String updateEsame(ArrayList<String> esameUpdate, int idCorso) throws IllegalArgumentException;

	/* Iscrive uno studente ad un esame,input l'id esame e mail 
	 * studente */
	String iscrizioneEsame(String email, int idCorso) throws IllegalArgumentException;

	/* Restituisce tutti gli esami tenuti da un docente, input mail docente, restituisce tutti 
	 * gli esami che ha creato */
	ArrayList<Esame> getAllEsame(String email) throws IllegalArgumentException;

	/* Restituisce tutti gli esami presenti nel database */
	ArrayList<Esame> getEsami() throws IllegalArgumentException;

	/* Restituisce  id degli esami a cui uno studente è iscritto, 
	 * input mail studente */
	ArrayList<Integer> getEsameStudente(String email) throws IllegalArgumentException;

	/* Elimina un esame, input l'id del corso */
	String deleteEsame(int idCorso) throws IllegalArgumentException;

	/* Restituisce mail degli studenti iscritti ad un esame, input
	 * id dell'esame */
	ArrayList<String> getIscrizioniEsame(Integer idEsame) throws IllegalArgumentException;

	// METODI VOTO
	
	/* Aggiunge un voto al database, input un'arraylist con i dati del voto e restituisce un messaggio 
	 */
	String addVoto(ArrayList<String> dati) throws IllegalArgumentException;

	/* Restituisce tutti i voti assegnati ad uno studente,input la sua matricola */
	ArrayList<Voto> getVoto(String matricola) throws IllegalArgumentException;

	/* Restituisce tutti i voti nel database */
	ArrayList<Voto> getVotoAll() throws IllegalArgumentException;

	/* Pubblica un voto e lo rende disponibile agli studenti,input i valori del voto */
	String votoPubblicato(ArrayList<String> dati) throws IllegalArgumentException;

	/* Elimina un voto,input voto e lo rimuove dal database*/
	String eliminaVoto(Voto voto)  throws IllegalArgumentException;
	
	// TEST
	String getDatabase() throws IllegalArgumentException;
}



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
	 * Metodo per la registrazione dello studente, prende in input un ArrayList con i dati dello studente
	 * e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	String registrazioneStudente(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Metodo per la registrazione della segretaria, prende in input un ArrayList con i dati
	 * dell'account della segreteria e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	String registrazioneSegreteria(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Metodo per la registrazione dell'admin, prende in input un ArrayList con i dati dell'admin
	 * e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	String registrazioneAdmin(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Metodo per la registrazione del docente, prende in input un ArrayList con i dati del docente
	 * e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	String registrazioneDocente(ArrayList<String> dati) throws IllegalArgumentException;

	/**
	 * Metodo per la gestione del login, prende in input la mail e la password scritti nel form
	 * e restituisce un oggetto di tipo utente se l'operazione è andata a buon fine o meno
	 **/
	Utente login(String username, String password) throws IllegalArgumentException;

	/**
	 * Metodo che restituisce le informazioni dell'utente, verificando la mail passata in input restituisce 
	 * i dati dell'utente se l'operazione è andata a buon fine
	 **/
	String getInfoUtente(String email)throws IllegalArgumentException;

	/**
	 * Metodo che restituisce un utente la cui mail corrisponde a quella passata in input
	 **/
	Utente getUtente(String email) throws IllegalArgumentException;

	/**
	 * Metodo che restituisce le informazioni dell'utente, verificando la mail passata in input restituisce 
	 * i dati dell'utente se l'operazione è andata a buon fine
	 **/
	ArrayList<Studente> getStudenti() throws IllegalArgumentException;

	/**
	 * Metodo che restituisce una lista con tutti i docenti registrati
	 **/
	ArrayList<Docente> getDocenti() throws IllegalArgumentException;

	/**
	 * Metodo che restituisce una lista con tutti i docenti registrati
	 **/
	ArrayList<Utente> getUtentiAll() throws IllegalArgumentException;

	/**
	 * Metodo che elimina l'utente, la cui mail corrisponde a quella passata input
	 **/
	String deleteUtente(String email) throws IllegalArgumentException;

	/**
	 * Metodo si occupa di modificare l'utente, la cui mail corrisponde a quella passata input e insieme ai dati da aggiornare
	 **/
	String updateUtente(ArrayList<String> utenteUpdate, String email) throws IllegalArgumentException;


	// METODI CORSO

	/* Metodo per creare un corso e inserirlo nel database, prende in input i dati del corso e 
	 * restituisce una stringa se l'operazione è andata a buon fine
	 */
	String creazioneCorso(ArrayList<String> dati) throws IllegalArgumentException;

	/* Metodo per modificare i dati del corso, prende in input i nuovi dati e il nome del corso, rimuove dal
	 *  database il corso e lo riaggiunge con i nuovi dati aggiornati */
	String updateCorso(ArrayList<String> corsoUpdate, String nomeCorso) throws IllegalArgumentException;

	/* Metodo che permette ad un utente di iscriversi ad un corso, prende in input la mail dello studente
	 * e l'id del corso per poi aggiungere l'iscrizione al database */
	String iscrizioneCorso(String email, int idCorso) throws IllegalArgumentException;

	/* Metodo che restituisce tutti i corsi a cui uno studento è iscritto, prendendo in input la sua mail */
	ArrayList<Corso> getAllCorso(String email) throws IllegalArgumentException;

	/* Metodo per ottenere tutti i corsi a cui uno studente è iscritto, prende in input la 
	 * mail dello studente e restituisce un arraylist con l'id dei corsi a cui è iscritto*/
	ArrayList<Integer> getCorsoStudente(String email) throws IllegalArgumentException;

	/* Metodo per rimuovere un corso, prende in input il nome del corso e, se è presente nel database, lo rimuove */
	String deleteCorso(String nomeCorso) throws IllegalArgumentException;

	/* Metodo per ottenere tutti i corsi presenti nel database */
	ArrayList<Corso> getCorsi() throws IllegalArgumentException;


	 // METODI ESAME
	
	/* Metodo per creare un esame e inserirlo nel database, prende in input i dati dell'esame e l'id del corso corrispondente, restituisce una stringa se
	 * l'operazione è andata a buon fine o meno */
	String creazioneEsame(ArrayList<String> dati, int idCorso) throws IllegalArgumentException;

	/* Metodo per modificare i dati di un esame, prende in input i dati modificati e l'id del corso corrispondente,
	 * rimuove dal database l'esame e lo riaggiunge con i nuovi valori modificati */
	String updateEsame(ArrayList<String> esameUpdate, int idCorso) throws IllegalArgumentException;

	/* Metodo per iscrivere uno studente ad un esame, prende in input l'id dell'esame e la mail dello
	 * studente, inserendo nel database l'iscrizione */
	String iscrizioneEsame(String email, int idCorso) throws IllegalArgumentException;

	/* Metodo per restituire tutti gli esami tenuti da un docente, prende in input la sua mail e restituisce tutti 
	 * gli esami che egli ha creato */
	ArrayList<Esame> getAllEsame(String email) throws IllegalArgumentException;

	/* Metodo per ottenere tutti gli esami presenti nel database */
	ArrayList<Esame> getEsami() throws IllegalArgumentException;

	/* Metodo che restituisce i vari id degli esami a cui uno studente è iscritto, prendendo
	 * in input la mail dello studente */
	ArrayList<Integer> getEsameStudente(String email) throws IllegalArgumentException;

	/* Metodo per eliminare un esame, prende in input l'id del corso corrispondente e rimuove l'esame con corrispondente id corso*/
	String deleteEsame(int idCorso) throws IllegalArgumentException;

	/* Metodo che restituisce le mail degli studenti iscritti ad un esame, prendendo in input
	 * l'id dell'esame */
	ArrayList<String> getIscrizioniEsame(Integer idEsame) throws IllegalArgumentException;

	// METODI VOTO
	
	/* Metodo per aggiungere un voto al database, prende in input un'arraylist con i dati del voto e restituisce un messaggio 
	 * dicendoci se l'operazione è andata a buon fine o no */
	String addVoto(ArrayList<String> dati) throws IllegalArgumentException;

	/* Metodo per ottenere tutti i voti assegnati ad uno studente, prendendo in input la sua matricola */
	ArrayList<Voto> getVoto(String matricola) throws IllegalArgumentException;

	/* Metodo per ottenere tutti i voti nel database */
	ArrayList<Voto> getVotoAll() throws IllegalArgumentException;

	/* Metodo per pubblicare un voto e renderlo disponibile agli studenti, prende in input i valori del voto e restituisce una messaggio
	 * se l'operazione è andata a buon fine o meno */
	String votoPubblicato(ArrayList<String> dati) throws IllegalArgumentException;

	/* Metodo per eliminare un voto, prende in input il voto e lo rimuove dal database*/
	String eliminaVoto(Voto voto)  throws IllegalArgumentException;
	
	// TEST
	String getDatabase() throws IllegalArgumentException;
}



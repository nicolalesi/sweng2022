package com.swengfinal.project.server;


import com.swengfinal.project.shared.Amministratore;
import com.swengfinal.project.shared.Docente;
import com.swengfinal.project.shared.Segreteria;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;


/* db.getAll().clear()	per pulire il db */

public class dbUtenti {

	/* Metodo per creare un nuovo database per gli utenti */
	private static DB getDB() {
		DB db = DBMaker.newFileDB(new File("dbUtenti")).make();
		return db;
	}

	private static boolean checkMail(String email) {
		boolean find = false;
		DB db = getDB();
		BTreeMap<String,Utente> Users = db.getTreeMap("UtentiMap");
		for(Entry<String,Utente> test : Users.entrySet()) {
			if(test.getValue().getEmail().equalsIgnoreCase(email)) {
				find = true;
			}
		} return find;
	}
	/*
	private static boolean checkMatricola(String matricola) {
		boolean find = false;
		DB db = getDB();
		BTreeMap<String,Utente> Users = db.getTreeMap("UtentiMap");
		ArrayList<Studente> studenti = new ArrayList<Studente>();
		for(Entry<String,Utente> test : Users.entrySet()) {
			if(test.getValue() instanceof Studente) {
				studenti.add((Studente) test.getValue());
			}
		}
		for(int i=0; i<studenti.size(); i++) {
			if(studenti.get(i).equals(matricola)) {
				find = true;
			}
		}
		return find;
	}*/

	/**
	 * Metodo per la registrazione dello studente, prende in input un ArrayList con i dati dello studente
	 * e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	public static String registrazioneStudente(ArrayList<String> dati) {
		DB db = getDB();
		BTreeMap<String,Studente> Users;

		if(!checkMail(dati.get(0)) ) {
			Users = db.getTreeMap("UtentiMap");

			Studente user = new Studente(dati.get(0),dati.get(1),dati.get(2),dati.get(3),
					dati.get(4),
					dati.get(5),
					dati.get(6));


			Users.put(user.getEmail(),user);
			db.commit();
			db.close();
			//Alert gg = new Alert("Utente registrato con mail: " + user.getEmail() + " e password: " + user.getPw());
			return "Registrazione completata";
		}
		else return "Errore";
	}

	/**
	 * Metodo per la registrazione del docente, prende in input un ArrayList con i dati del docente
	 * e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	public static String registrazioneDocente(ArrayList<String> dati) {
		DB db = getDB();
		BTreeMap<String,Utente> Users;

		if(!checkMail(dati.get(0))) {
			Users = db.getTreeMap("UtentiMap");

			Docente user = new Docente(dati.get(0),dati.get(1),dati.get(2),dati.get(3),
					dati.get(4),
					dati.get(5));

			Users.put(user.getEmail(),user);
			db.commit();
			db.close();
			//Alert gg = new Alert("Utente registrato con mail: " + user.getEmail() + " e password: " + user.getPw());
			return "Registrazione completata";
		}
		else return "Errore";
	}

	/**
	 * Metodo per la registrazione dell'admin, prende in input un ArrayList con i dati dell'admin
	 * e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	public static String registrazioneAdmin(ArrayList<String> dati) {
		DB db = getDB();
		BTreeMap<String,Utente> Users;

		if(!checkMail(dati.get(0))) {
			Users = db.getTreeMap("UtentiMap");

			Amministratore user = new Amministratore(dati.get(0),dati.get(1),dati.get(2),dati.get(3),
					dati.get(4),
					dati.get(5));

			Users.put(user.getEmail(),user);
			db.commit();
			db.close();
			//Alert gg = new Alert("Utente registrato con mail: " + user.getEmail() + " e password: " + user.getPw());
			return "Registrazione completata";
		}
		else return "Errore";
	}

	/**
	 * Metodo per la registrazione della segretaria, prende in input un ArrayList con i dati
	 * dell'account della segreteria e restituisce un messaggio se l'operazione è andata a buon fine o meno
	 **/
	public static String registrazioneSegreteria(ArrayList<String> dati) {
		DB db = getDB();
		BTreeMap<String, Utente> Users;

		if(!checkMail(dati.get(0))) {
			Users = db.getTreeMap("UtentiMap");

			Segreteria user = new Segreteria(dati.get(0),dati.get(1),dati.get(2),dati.get(3),
					dati.get(4),
					dati.get(5));

			Users.put(user.getEmail(),user);
			db.commit();
			db.close();
			//Alert gg = new Alert("Utente registrato con mail: " + user.getEmail() + " e password: " + user.getPw());
			return "Registrazione completata";
		}
		else return "Errore";
	}

	/**
	 * Metodo per la gestione del login, prende in input la mail e la password scritti nel form
	 * e restituisce un oggetto di tipo utente se l'operazione è andata a buon fine o meno
	 **/
	public static Utente login(String email, String password) throws IllegalArgumentException{

		DB db = getDB();
		BTreeMap<String,Utente> Users = db.getTreeMap("UtentiMap");
		if(checkMail(email)) {
			Utente user = Users.get(email);
			if(user.getPw().equals(password)) {
				return user;
			}else return null;
		}else return null;
	}

	/**
	 * Metodo che elimina l'utente, la cui mail corrisponde a quella passata input
	 **/
	public static String deleteUtente(String email) {
		DB db = getDB();
		BTreeMap<Integer, Utente> utenti = db.getTreeMap("UtentiMap");

		utenti.remove(email);
		db.commit();
		db.close();

		return "Successo";
	}

	/**
	 * Metodo che restituisce le informazioni dell'utente, verificando la mail passata in input restituisce 
	 * i dati dell'utente se l'operazione è andata a buon fine
	 **/
	public static String getInfoUtente(String email) {
		DB db = getDB();
		BTreeMap<String, Utente> Users = db.getTreeMap("UtentiMap");

		Utente user = Users.get(email);

		String all = "Email :" + user.getEmail() + "\nPassword : " + user.getPw() + "\nUsername : " + 
				"\nMatricola : " + "\nNome : " + user.getNome() + "\nCognome : " + user.getCognome()
				+ "\nData Nascita : " + user.getDataNascita()
				+ "\nLuogo Nascita : " + user.getLuogoNascita() + "\nTipologia";
		return all;
	}

	/**
	 * Metodo che restituisce un utente la cui mail corrisponde a quella passata in input
	 **/
	public static Utente getUtente(String email) {
		DB db = getDB();
		BTreeMap<String, Utente> Users = db.getTreeMap("UtentiMap");
		Utente user = Users.get(email);
		return user;

	}

	/**
	 * Metodo che restituisce le informazioni dell'utente, verificando la mail passata in input restituisce 
	 * i dati dell'utente se l'operazione è andata a buon fine
	 **/
	public static ArrayList<Studente> getStudenti(){
		DB db = getDB();
		Map<String, Utente> Users = db.getTreeMap("UtentiMap");
		ArrayList<Studente> output = new ArrayList<Studente>();
		for(Entry<String,Utente> test : Users.entrySet()) {
			if(test.getValue() instanceof Studente) {
				output.add((Studente) test.getValue());
			}
		}
		return output;
	}

	/**
	 * Metodo che restituisce una lista con tutti i docenti registrati
	 **/
	public static ArrayList<Docente> getDocenti(){
		DB db = getDB();

		BTreeMap<String, Utente> Users = db.getTreeMap("UtentiMap");
		ArrayList<Docente> output = new ArrayList<Docente>();
		for(Entry<String,Utente> test : Users.entrySet()) {
			if(test.getValue() instanceof Docente) {
				output.add((Docente) test.getValue());
			}
		}
		return output;
	}

	/**
	 * Metodo che restituisce una lista con tutti i docenti registrati
	 **/
	public static ArrayList<Utente> getUtentiAll(){
		DB db = getDB();
		BTreeMap<String, Utente> Users = db.getTreeMap("UtentiMap");
		ArrayList<Utente> output = new ArrayList<Utente>();
		for(Entry<String,Utente> test : Users.entrySet()) {
			output.add(test.getValue());
		}
		return output;
	}
	/* Metodo usato in fase di testing per ottenere i valori del db */
	public static String getDatabase() {
		DB db = getDB();
		String s = "";
		for(Entry<String,Object> test : db.getAll().entrySet()) {
			s += test.getValue().toString() + " | ";
		}

		return s;
	}

	/**
	 * Metodo si occupa di modificare l'utente, la cui mail corrisponde a quella passata input e insieme ai dati da aggiornare
	 **/
	public static String modificaUtente(ArrayList<String> dati, String email) {
		DB db = getDB();
		BTreeMap<String, Utente> utenti = db.getTreeMap("UtentiMap");


		Utente utente = utenti.get(email);
		utenti.remove(email);
		if(dati.get(0).length()>=1)
		{
			utente.setPassword(dati.get(0));
		}
		if(dati.get(1).length()>=1)
		{
			utente.setCognome(dati.get(1));
		}
		if(dati.get(2).length()>=1)
		{
			utente.setNome(dati.get(2));
		}
		if(dati.get(3).length()>=1)
		{
			utente.setDataNascita(dati.get(3));
		}
		if(dati.get(4).length()>=1)
		{
			utente.setLuogoNascita(dati.get(4));
		}

		utenti.put(email, utente);
		db.commit();
		db.close();

		return "Successo";

	}

	// TESTING JUNIT

	public static void tryUser() {
		DB db = getDB();
		BTreeMap<String, Utente> utenti = db.getTreeMap("UtentiMap");

		Amministratore admin = new Amministratore("admi","admi","admi","admi","admi","admi");
		utenti.put(admin.getEmail(), admin);
		db.commit();
		db.close();

	}

}

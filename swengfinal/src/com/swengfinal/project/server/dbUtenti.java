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


/* db.getAll().clear()	pulire il db */

public class dbUtenti {

	/*Crea db utenti */
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

	/**
	 * Registrazione dello studente, input ArrayList con i dati dello studente
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
	 * Registrazione docente, input un ArrayList con i dati del docente
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
	 *Registrazione admin, prende in input un ArrayList con i dati dell'admin
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
			return "Registrazione completata";
		}
		else return "Errore";
	}

	/**
	 * Registrazione della segretaria, input un ArrayList con i dati
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
	 * Gestione del login, in input la mail e la password scritti nel form
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
	 * Elimina l'utente, la cui mail corrisponde a quella passata input
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
	 * Restituisce le informazioni dell'utente, verificando la mail passata in input 
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
	 * Restituisce un utente con mail in input
	 **/
	public static Utente getUtente(String email) {
		DB db = getDB();
		BTreeMap<String, Utente> Users = db.getTreeMap("UtentiMap");
		Utente user = Users.get(email);
		return user;

	}

	/**
	 * Restituisce le informazioni dell'utente, verificando la mail passata in input 
	 *
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
	 * Restituisce una lista con tutti i docenti registrati
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
	 * Restituisce una lista con tutti i docenti registrati
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
	/* Usato in fase di testing per ottenere i valori del db */
	public static String getDatabase() {
		DB db = getDB();
		String s = "";
		for(Entry<String,Object> test : db.getAll().entrySet()) {
			s += test.getValue().toString() + " | ";
		}

		return s;
	}

	/**
	 * Modificare l'utente, la cui mail corrisponde a quella passata input
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

package com.swengfinal.project.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.swengfinal.project.shared.IscrizioneCorso;

public class dbIscrizioneCorso {

	private static DB getDB() {
		DB db = DBMaker.newFileDB(new File("dbIscrizioneCorso")).make();
		return db;
	}

	/* Permette ad un utente di iscriversi ad un corso, input la mail dello studente
	 * e l'id del corso */
	
	public static String iscrizioneCorso(String email, Integer idCorso) { 
		DB db = getDB();
		BTreeMap<Integer, IscrizioneCorso> iscrizioni = db.getTreeMap("IscrizioniCorso");

		IscrizioneCorso iscrizione = new IscrizioneCorso(idCorso, email);
		boolean found = false;


		for(Entry<Integer, IscrizioneCorso> test : iscrizioni.entrySet()) {
			if(idCorso == test.getValue().getIdCorso() && email.equals(test.getValue().getMailStudente())) {
				found = true;
			}
		}

		if(!found) {
			iscrizioni.put(iscrizioni.size(), iscrizione);
			db.commit();
			db.close();
			return "Successo";

		} else {
			db.commit();
			db.close();
			return "Errore";
		}
	}

	/*Restituisce tutti i corsi a cui uno studente è iscritto,  input la 
	 * mail dello studente */
	public static ArrayList<Integer> getCorsoStudente(String email){
		DB db = getDB();
		BTreeMap<Integer, IscrizioneCorso> iscrizioni = db.getTreeMap("IscrizioniCorso");
		ArrayList<Integer> corsiOutput = new ArrayList<Integer>();

		for(Entry<Integer, IscrizioneCorso> test : iscrizioni.entrySet()) {
			if(email.equals(test.getValue().getMailStudente())) {
				corsiOutput.add(test.getValue().getIdCorso());
			}
		}
		return corsiOutput;
	}
}
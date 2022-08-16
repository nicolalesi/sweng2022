package com.swengfinal.project.server;

import com.swengfinal.project.shared.Corso;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;


public class dbCorso {

	/* Metodo per creare il database dei corsi */
	private static DB getDB() {
		DB db = DBMaker.newFileDB(new File("dbCorso")).make();
		return db;
	}

	/* Metodo per ottenere l'id del corso se presente nel database, prendendo in input il nome del corso */
	private static int posizioneCorso(String nomeCorso) {
		DB db = getDB();
		BTreeMap<Integer, Corso> corsi = db.getTreeMap("CorsiMap");
		for(Entry<Integer, Corso> test : corsi.entrySet()) {
			if(test.getValue().getNomeCorso().equals(nomeCorso)) {
				return test.getValue().getIdCorso(); //ritorna id del Corso
			}
		}
		return 0;
	}

	/* Metodo per creare un corso e inserirlo nel database, prende in input i dati del corso e restituisce una stringa
	 * se l'operazione è andata a buon fine
	 */
	public static String creazioneCorso(ArrayList<String> dati) { 
		DB db = getDB();
		BTreeMap<Integer, Corso> corsi = db.getTreeMap("CorsiMap");

		Corso corso = new Corso(
				corsi.size(), 			// idCorso
				dati.get(1), 			// mailDocente
				dati.get(2),            // nomeCorso
				dati.get(3),            // Descrizione Corso
				dati.get(4),            // data inizio corso
				dati.get(5),            // data fine corso 
				dati.get(6)
				);
		corsi.put(corso.getIdCorso(), corso);
		db.commit();
		db.close();

		return "Successo";			
	}

	/* Metodo per modificare i dati del corso, prende in input i nuovi dati e il nome del corso, rimuove dal database il corso e lo
	 * riaggiunge con i nuovi dati aggiornati */
	public static String modificaCorso(ArrayList<String> dati, String nomeCorso) {
		DB db = getDB();
		BTreeMap<Integer, Corso> corsi = db.getTreeMap("CorsiMap");

		int idCorso=posizioneCorso(nomeCorso);
		Corso corso = corsi.get(idCorso);
		corsi.remove(idCorso);
		if(dati.get(0)!="")
		{
			corso.setDescrizione(dati.get(0));
		}
		if(dati.get(1)!="")
		{
			corso.setDataInizio(dati.get(1));

		}
		if(dati.get(2)!="")
		{
			corso.setDataFine(dati.get(2));
		}
		if(dati.get(3)!="")
		{
			corso.setCoDocente(dati.get(3));
		}

		corsi.put(idCorso, corso);
		db.commit();
		db.close();
		return "Successo";

	}

	/* Metodo per rimuovere un corso, prende in input il nome del corso e, se è presente nel database, lo rimuove */
	public static String deleteCorso(String nomeCorso) {
		DB db = getDB();
		BTreeMap<Integer, Corso> corsi = db.getTreeMap("CorsiMap");

		int idCorso=posizioneCorso(nomeCorso);
		corsi.remove(idCorso);
		db.commit();
		db.close();

		return "Successo";
	}
	
	/* Metodo che restituisce tutti i corsi a cui uno studento è iscritto, prendendo in input la sua mail */
	public static ArrayList<Corso> getAllCorso(String email) {
		DB db = getDB();
		BTreeMap<Integer, Corso> corsi = db.getTreeMap("CorsiMap");
		ArrayList<Corso> corsiOutput = new ArrayList<Corso>();

		for(Entry<Integer, Corso> test : corsi.entrySet()) {
			if(email.equals(test.getValue().getEmailDocente())) {
				corsiOutput.add(test.getValue());
			}
		}
		return corsiOutput;
	}

	/* Metodo per ottenere tutti i corsi presenti nel database */
	public static ArrayList<Corso> getCorsi(){
		DB db = getDB();
		BTreeMap<Integer, Corso> corsi = db.getTreeMap("CorsiMap");
		ArrayList<Corso> corsiAll = new ArrayList<Corso>();
		
		for(Entry<Integer, Corso> test : corsi.entrySet()) {
			corsiAll.add(test.getValue());
		}
		return corsiAll;
	}
}

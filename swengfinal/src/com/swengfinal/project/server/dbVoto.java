package com.swengfinal.project.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.swengfinal.project.shared.Voto;

public class dbVoto {

	public static DB getDB() {

		/* Metodo per creare il database dei voti */
		DB db = DBMaker.newFileDB(new File("dbVoto")).make();
		return db;
	}

	/* Metodo per aggiungere un voto al database, prende in input un'arraylist con i dati del voto e restituisce un messaggio 
	 * dicendoci se l'operazione è andata a buon fine o no */
	public static String addVoto(ArrayList<String> dati) { 
		DB db = getDB();
		BTreeMap<Integer, Voto> voti = db.getTreeMap("VotiMap");

		boolean found = false;

		Voto voto = new Voto(
				voti.size(),
				dati.get(1), 	// nomeEsame
				dati.get(2),    // matricola
				dati.get(3),    // voto
				false);

		for(Entry<Integer, Voto> test : voti.entrySet()) {
			if(test.getValue().getMatricola().equals(voto.getMatricola()) && test.getValue().getNomeEsame().equals(voto.getNomeEsame())) {
				found=true; //ritorna id del Corso
			}
		}
		if(dati.get(1).length()>=1 && dati.get(2).length()>=1 && dati.get(3).length()>=1 && !found) {
			voti.put(voto.getIdVoto(), voto);
			db.commit();
			db.close();
			return "Successo";
		}else return "Errore";
	}

	/* Metodo per ottenere tutti i voti assegnati ad uno studente, prendendo in input la sua matricola */
	public static ArrayList<Voto> getVoto(String matricola){
		DB db = getDB();
		BTreeMap<Integer, Voto> voti = db.getTreeMap("VotiMap");
		ArrayList<Voto> votiOutput = new ArrayList<Voto>();
		for(Entry<Integer, Voto> test : voti.entrySet()) {
			if(matricola.equals(test.getValue().getMatricola())) {
				if(test.getValue().getPubblicato()) {
					votiOutput.add(test.getValue());
				}	
			}
		}
		return votiOutput;
	}

	/* Metodo per ottenere tutti i voti nel database */
	public static ArrayList<Voto> getVotiAll(){
		DB db = getDB();
		BTreeMap<Integer, Voto> voti = db.getTreeMap("VotiMap");
		ArrayList<Voto> output = new ArrayList<Voto>();
		for(Entry<Integer,Voto> test : voti.entrySet()) {
			if(!test.getValue().getPubblicato()) {
				output.add(test.getValue());
			}
		}
		return output;
	}

	/* Metodo per pubblicare un voto e renderlo disponibile agli studenti, prende in input i valori del voto e restituisce una messaggio
	 * se l'operazione è andata a buon fine o meno */
	public static String votoPubblicato(ArrayList<String> dati) {
		DB db = getDB();
		BTreeMap<Integer, Voto> voti = db.getTreeMap("VotiMap");

		Voto voto=new Voto();
		int id=0;
		for(Entry<Integer,Voto> test : voti.entrySet()) {
			if(dati.get(0).equals(test.getValue().getMatricola()) && dati.get(1).equals(test.getValue().getNomeEsame())) {
				voto=test.getValue();
				voto.setPubblicato(true);
				id=test.getValue().getIdVoto();
				voti.remove(id);
			}
		}
		voti.put(id, voto);
		db.commit();
		db.close();
		return "Successo";
	}

	/* Metodo per eliminare un voto, prende in input il voto e lo rimuove dal database*/
	public static String eliminaVoto(Voto voto) {
		DB db = getDB();
		BTreeMap<Integer, Voto> voti = db.getTreeMap("VotiMap");
		for(Entry<Integer,Voto> test : voti.entrySet()) {
			if(voto==test.getValue()) {
				voti.remove(test.getKey());

			}
		}return("Successo");
	}

	/* Metodo per ripulire il database e cancellare tutti i voti presenti */
	public static void clearDBVoti() {
		DB db = getDB();
		BTreeMap<Integer, Voto> voti = db.getTreeMap("VotiMap");
		voti.clear();
		db.commit();
		db.close();
	}



}

package com.swengfinal.project.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.swengfinal.project.shared.Esame;


public class dbEsame {
	
	/*Crea db esami */
	private static DB getDB() {
		DB db = DBMaker.newFileDB(new File("dbEsame")).make();
		return db;
	}
	
	/*Creare un esame, parametro dati dell'esame e l'id del corso corrispondente */
	public static String creazioneEsame(ArrayList<String> dati, int idCorso) { 
		DB db = getDB();
		BTreeMap<Integer, Esame> esami = db.getTreeMap("EsamiMap");
		boolean found = false;
			Esame esame = new Esame(
					esami.size(), 					// idEsame
					idCorso,						// idCorso
					dati.get(2), 				   // data
					dati.get(3),                   // ora
					dati.get(4), 					// aula 
					dati.get(5),					//cfu
					dati.get(6),					//nome esame
					dati.get(7)                   // email docente
					);
			
			for(Entry<Integer, Esame> test : esami.entrySet()) {
	            if(test.getValue().getIdCorso() == idCorso) {
	            	found=true;
	            }
			}
	            
			if(!found) {
				esami.put(esame.getIdEsame(), esame);
				db.commit();
				db.close();
				return "Successo";
			}else return "Errore";
		}

	/* Modifica i dati dell'esame, prende in input i nuovi dati e id del corso*/
	
	public static String modificaEsame(ArrayList<String> dati, int idCorso) {
		DB db = getDB();
		Esame esame = new Esame();
		BTreeMap<Integer, Esame> esami = db.getTreeMap("EsamiMap");
		for(Entry<Integer, Esame> test : esami.entrySet()) {
            if(test.getValue().getIdCorso() == idCorso) {
                esame = test.getValue() ;
                esami.remove(test.getKey());
            }
        }
		if(dati.get(0).length()>=1) {
			esame.setData(dati.get(0));
		}
		if(dati.get(1).length()>=1) {
			esame.setCfu(dati.get(1));
		}
		if(dati.get(2).length()>=1) {
			esame.setAula(dati.get(2));
		}
		if(dati.get(3).length()>=1) {
			esame.setOra(dati.get(3));
		}
		
		esami.put(esame.getIdEsame(), esame);
		db.commit();
		db.close();
		return "Successo";
		
	}
	
	/* Rimuove un esame, prende in input id del corso e rimuove il relativo esame  */
	public static String deleteEsame(int idCorso) {
		DB db = getDB();

		BTreeMap<Integer, Esame> esami = db.getTreeMap("EsamiMap");
		Esame esame = null;
		for(Entry<Integer, Esame> test : esami.entrySet()) {
            if(test.getValue().getIdCorso() == idCorso) {
                esame = test.getValue() ;
                esami.remove(test.getKey());
            }
        }
		if(esami.size()==0) {
			return "Errore";
		}
		else {
			esami.remove(esame.getIdEsame());
			db.commit();
			db.close();
			
			return "Successo";
		}
	}
	
	/* Restituisce tutti gli esami tenuti da un docente, input mail e restituisce tutti 
	 * gli esami che ha creato */
	public static ArrayList<Esame> getAllEsame(String email)
	{
		DB db = getDB();
		BTreeMap<Integer, Esame> esami = db.getTreeMap("EsamiMap");
		ArrayList<Esame> esamiOutput = new ArrayList<Esame>();
		
		for(Entry<Integer, Esame> test : esami.entrySet()) {
			if(email.equals(test.getValue().getEmailDocente())) {
					esamiOutput.add(test.getValue());
			}
		}
		return esamiOutput;
	}
	
	/* Restituisce tutti gli esami presenti nel database */
	public static ArrayList<Esame> getEsami(){
		DB db = getDB();
		BTreeMap<Integer, Esame> esami = db.getTreeMap("EsamiMap");
		ArrayList<Esame> esamiAll = new ArrayList<Esame>();
		for(Entry<Integer, Esame> test : esami.entrySet()) {
			esamiAll.add(test.getValue());
		}
		
		return esamiAll;
	}
	
	/* Cancella tutti gli esami presenti */
	public static void clearDBEsami() {
		DB db = getDB();
		BTreeMap<Integer, Esame> esami = db.getTreeMap("EsamiMap");
		esami.clear();
		db.commit();
		db.close();
	}
	
	
}

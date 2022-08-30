package com.swengfinal.project.server;
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.swengfinal.project.shared.IscrizioneEsame;


public class dbIscrizioneEsame {

	/*Crea il db con iscrizione degli studenti agli esami */
	private static DB getDB() {
		DB db = DBMaker.newFileDB(new File("dbIscrizioneEsame")).make();
		return db;
	}

	/* Iscrive studente ad esame, input l'id dell'esame e la mail dello
	 * studente */
	public static String iscrizioneEsame(Integer idEsame, String email) { 
		DB db = getDB();
		BTreeMap<Integer, IscrizioneEsame> iscrizioniEsami = db.getTreeMap("IscrizioniEsame");

		IscrizioneEsame iscrizione = new IscrizioneEsame(iscrizioniEsami.size(),idEsame, email);
		boolean found = false;

		for(Entry<Integer, IscrizioneEsame> test : iscrizioniEsami.entrySet()) {
			if(idEsame == test.getValue().getIdEsame() && email.equals(test.getValue().getMailStudente())) {
				found = true;
			}
		}

		if(!found) {

			iscrizioniEsami.put(iscrizioniEsami.size(), iscrizione); // Aggiunta mail studente al relativo corso
			db.commit();
			return "Successo";

		}else 
			db.commit();
		return "Errore";
	}

	/* Restituisce id degli esami a cui uno studente è iscritto, 
	 * parametro la mail dello studente*/
	public static ArrayList<Integer> getEsamiStudente(String email){
		DB db = getDB();
		BTreeMap<Integer, IscrizioneEsame> iscrizioniEsami = db.getTreeMap("IscrizioniEsame");
		ArrayList<Integer> esamiOutput = new ArrayList<Integer>();

		for(Entry<Integer, IscrizioneEsame> test : iscrizioniEsami.entrySet()) {
			if(email.equals(test.getValue().getMailStudente())) {
				esamiOutput.add(test.getValue().getIdEsame());
			}
		}
		return esamiOutput;
	}

	/* Restituisce mail degli studenti iscritti ad un esame, input
	 * l'id dell'esame
	 */
	public static ArrayList<String> getIscrizioniEsame(Integer idEsame){
		DB db = getDB();
		BTreeMap<Integer, IscrizioneEsame> iscrizioni = db.getTreeMap("IscrizioniEsame");
		ArrayList<String> mailIscritti = new ArrayList<String>();
		for(Entry<Integer,IscrizioneEsame> test : iscrizioni.entrySet()) {
			if(test.getValue().getIdEsame()==idEsame) {
				mailIscritti.add(test.getValue().getMailStudente());
			}
		}
		return mailIscritti;
	}

}

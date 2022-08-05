package com.swengfinal.project.server;

import com.swengfinal.project.client.GreetingService;
import com.swengfinal.project.shared.Corso;
import com.swengfinal.project.shared.Docente;
import com.swengfinal.project.shared.Esame;

import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;
import com.swengfinal.project.shared.Voto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	// metodi utenti
	@Override
	public String registrazioneStudente(ArrayList<String> dati) throws IllegalArgumentException {
		return dbUtenti.registrazioneStudente(dati);
	}

	@Override
	public String registrazioneDocente(ArrayList<String> dati) throws IllegalArgumentException {
		return dbUtenti.registrazioneDocente(dati);
	}

	@Override
	public String registrazioneSegreteria(ArrayList<String> dati) throws IllegalArgumentException {
		return dbUtenti.registrazioneSegreteria(dati);
	}

	@Override
	public String registrazioneAdmin(ArrayList<String> dati) throws IllegalArgumentException {
		return dbUtenti.registrazioneAdmin(dati);
	}

	@Override
	public Utente login(String username, String password) {
		return dbUtenti.login(username, password);
	}

	@Override
	public String getInfoUtente(String email) throws IllegalArgumentException{
		return dbUtenti.getInfoUtente(email);
	}

	@Override
	public Utente getUtente(String email) {
		return dbUtenti.getUtente(email);
	}

	@Override
	public ArrayList<Studente> getStudenti(){
		return dbUtenti.getStudenti();
	}

	@Override
	public ArrayList<Docente> getDocenti(){
		return dbUtenti.getDocenti();
	}

	@Override
	public ArrayList<Utente> getUtentiAll(){
		return dbUtenti.getUtentiAll();
	}

	@Override
	public String deleteUtente(String email){
		return dbUtenti.deleteUtente(email);
	}

	@Override
	public String updateUtente(ArrayList<String> utenteUpdate, String email) throws IllegalArgumentException {
		return dbUtenti.modificaUtente(utenteUpdate, email);
	}


	// metodi corso

	@Override
	public String creazioneCorso(ArrayList<String> dati) throws IllegalArgumentException {
		return dbCorso.creazioneCorso(dati);
	}
	@Override
	public String updateCorso(ArrayList<String> corsoUpdate, String nomeCorso) throws IllegalArgumentException {
		return dbCorso.modificaCorso(corsoUpdate, nomeCorso);
	}

	@Override
	public String iscrizioneCorso(String email, int idCorso) throws IllegalArgumentException {
		return dbIscrizioneCorso.iscrizioneCorso(email, idCorso);
	}

	@Override
	public ArrayList<Integer> getCorsoStudente(String email) throws IllegalArgumentException {
		return dbIscrizioneCorso.getCorsoStudente(email);
	}

	@Override
	public ArrayList<Corso> getAllCorso(String email) throws IllegalArgumentException {
		return dbCorso.getAllCorso(email);
	}

	@Override
	public String deleteCorso(String nomeCorso) throws IllegalArgumentException
	{
		return dbCorso.deleteCorso(nomeCorso);
	}

	public ArrayList<Corso> getCorsi() throws IllegalArgumentException{
		return dbCorso.getCorsi();
	}

	// metodi esame

	@Override
	public String creazioneEsame(ArrayList<String> dati, int idCorso) throws IllegalArgumentException {
		return dbEsame.creazioneEsame(dati, idCorso);
	}
	@Override
	public String updateEsame(ArrayList<String> esameUpdate, int idCorso) throws IllegalArgumentException {
		return dbEsame.modificaEsame(esameUpdate, idCorso);
	}

	@Override
	public String iscrizioneEsame(String email, int idCorso) throws IllegalArgumentException {
		return dbIscrizioneEsame.iscrizioneEsame(idCorso, email);
	}

	@Override
	public ArrayList<Integer> getEsameStudente(String email) throws IllegalArgumentException {
		return dbIscrizioneEsame.getEsamiStudente(email);
	}

	@Override
	public ArrayList<Esame> getAllEsame(String email) throws IllegalArgumentException
	{
		return dbEsame.getAllEsame(email);
	}

	@Override
	public String deleteEsame(int idCorso) throws IllegalArgumentException
	{
		return dbEsame.deleteEsame(idCorso);
	}

	@Override
	public ArrayList<Esame> getEsami() throws IllegalArgumentException
	{
		return dbEsame.getEsami();
	}

	@Override
	public  ArrayList<String> getIscrizioniEsame(Integer idEsame) throws IllegalArgumentException{
		return dbIscrizioneEsame.getIscrizioniEsame(idEsame);
	}

	//metodo invio voto

	@Override
	public String addVoto(ArrayList<String> dati ) throws IllegalArgumentException {
		return dbVoto.addVoto(dati);
	}

	@Override
	public ArrayList<Voto> getVoto(String matricola) throws IllegalArgumentException {
		return dbVoto.getVoto(matricola);
	}

	@Override
	public ArrayList<Voto> getVotoAll() throws IllegalArgumentException {
		return dbVoto.getVotiAll();
	}

	@Override
	public String getDatabase() throws IllegalArgumentException {
		return dbUtenti.getDatabase();
	}

	@Override
	public String votoPubblicato(ArrayList<String> dati) throws IllegalArgumentException {
		return dbVoto.votoPubblicato(dati);
	}

	public String eliminaVoto(Voto voto) throws IllegalArgumentException {
		return dbVoto.eliminaVoto(voto);
	}

	//testing junit

	private GreetingService mock = null;

	public GreetingService getMock() {
		return mock;
	}

	public void setMock(GreetingService mock) {
		this.mock = mock;
	}

	public void tryUser() {
		dbUtenti.tryUser();
	}

	public void clearDBVoti() {
		dbVoto.clearDBVoti();
	}

	public void clearDBEsami() {
		dbEsame.clearDBEsami();
	}

}

package swengfinal;

import com.swengfinal.project.client.GreetingService;
import com.swengfinal.project.server.GreetingServiceImpl;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;
import com.swengfinal.project.shared.Studente;

/*
 * Ogni volta che eseguiamo i test, resettiamo i database per lavorare su nuovi dati.
 * Quindi se si avvia questa pagina, si perdereranno tutti i dati memorizzati nel database. 
 * 
 */


public class test {
	
	static GreetingServiceImpl tryTest;
	static GreetingServiceImpl clear;
	
	
	@BeforeAll
	public static void ready() {
		GreetingService i = mock(GreetingService.class);
		tryTest = new GreetingServiceImpl();
		tryTest.setMock(i);
	}
	
	
	// Test per creazione di un utente (amministratore)
	@Test
	@DisplayName("Test creazione utente")
	public void testStart() {
		tryTest.tryUser();
	}
	
	@Test
	@DisplayName("Test registrazione studente")
	public void testRegistrazioneStudente() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("email123");
		info.add("password");
		info.add("Nome");
		info.add("Cognome");
		info.add("Luogo Nascita");
		info.add("Data Nascita");
		info.add("Matricola");
		tryTest.deleteUtente(info.get(0));
		assertEquals("Registrazione completata", tryTest.registrazioneStudente(info));
		
	}
	
	@Test
	@DisplayName("Test login studente")
	public void testLogin() {
		Studente u = new Studente("email123","password","Nome","Cognome",
				"Luogo Nascita","Data Nascita","Matricola");
		assertEquals(u.getEmail(), tryTest.login("email123", "password").getEmail());
		

	}
	
	@Test
	@DisplayName("Test registrazione docente")
	public void testRegistrazioneDocente() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("emailDocente");
		info.add("password");
		info.add("Nome");
		info.add("Cognome");
		info.add("Luogo Nascita");
		info.add("Data Nascita");
		
		assertEquals("Registrazione completata", tryTest.registrazioneDocente(info));
		tryTest.deleteUtente(info.get(0));
	}
	
	@Test
	@DisplayName("Test registrazione amministratore")
	public void testRegistrazioneAmmministrazione() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("emailAdmin");
		info.add("password");
		info.add("Nome");
		info.add("Cognome");
		info.add("Luogo Nascita");
		info.add("Data Nascita");
		
		assertEquals("Registrazione completata", tryTest.registrazioneAdmin(info));
		tryTest.deleteUtente(info.get(0));
	}
	
	@Test
	@DisplayName("Test registrazione segreteria")
	public void testRegistrazioneSegweteria() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("emailSegreteria");
		info.add("password");
		info.add("Nome");
		info.add("Cognome");
		info.add("Luogo Nascita");
		info.add("Data Nascita");
		assertEquals("Registrazione completata", tryTest.registrazioneSegreteria(info));
		tryTest.deleteUtente(info.get(0));
	}
	
	@Test
	@DisplayName("Test creazione e modifica corso")
	public void testCreazioneModificaCorso() {
		
		ArrayList<String> corso = new ArrayList<String>();
		corso.add(0,"");
		corso.add(1,"mailDocente");
		corso.add(2,"nomeCorso");
		corso.add(3,"descrizioneCorso");
		corso.add(4,"dataInizioCorso");
		corso.add(5,"dataFineCorso");
		corso.add(6,"coDocente");
		
		assertEquals("Successo",tryTest.creazioneCorso(corso));
		
		corso.set(3, "nuovaDescrizione");
		
		assertEquals("Successo",tryTest.updateCorso(corso, corso.get(3)));
		tryTest.deleteCorso(corso.get(2));
		

	}

	
	@Test
	@DisplayName("Test creazione e modifica esame")
	public void testCreazioneModificaEsame() {
		
		int idCorso = 0;
		ArrayList<String> esame = new ArrayList<String>();
		esame.add(0,"");
		esame.add(1,"");
		esame.add(2,"data");
		esame.add(3,"ora");
		esame.add(4,"aula");
		esame.add(5,"cfu");
		esame.add(6,"nomeEsame");
		esame.add(7,"emailDocente");

		assertEquals("Successo",tryTest.creazioneEsame(esame,idCorso));

		esame.set(2, "nuovaData");
		
		assertEquals("Successo",tryTest.updateEsame(esame, idCorso));
		
	}

	@Test
	@DisplayName("Test invio e pubblicazione voto")
	public void testInvioPubblicaVoto() {
		testRegistrazioneStudente();
		ArrayList<String> voto = new ArrayList<String>();
		voto.add("");
		voto.add("nomeEsame");
		voto.add("Matricola");
		voto.add("22");
		
		assertEquals("Successo",tryTest.addVoto(voto));
		assertEquals("Successo",tryTest.votoPubblicato(voto));
	}

	
	@AfterAll
	public static void puliziaDB() {
		tryTest.clearDBVoti();
		tryTest.clearDBEsami();
	}
}

	
	


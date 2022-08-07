package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;

public class PageCreazioneAccount extends Composite {

	private static PageCreazioneAccountUiBinder uiBinder = GWT.create(PageCreazioneAccountUiBinder.class);

	private static final ArrayList<Utente> listaUtente = new ArrayList<Utente>();
	private static final ArrayList<Studente> listaStudenti = new ArrayList<Studente>();

	interface PageCreazioneAccountUiBinder extends UiBinder<Widget, PageCreazioneAccount> {
	}

	public PageCreazioneAccount() {
		initWidget(uiBinder.createAndBindUi(this));

		getUtenti();
		getStudenti();

		btnHome.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnHome.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnHome.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnCreazione.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnCreazione.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnCreazione.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnModifica.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnModifica.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnModifica.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnLogout.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnLogout.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnLogout.getElement().getStyle().setMarginLeft(870.0, Unit.PX);

		addTipologia();
	}

	public void getUtenti() {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.getUtentiAll(new AsyncCallback<ArrayList<Utente>>() {
			public void onFailure(Throwable caught) {
				Alert a = new Alert("Errore stampa utente");
				System.out.println(a);
			}
			@Override
			public void onSuccess(ArrayList<Utente> output) {
				listaUtente.clear();
				for(int i=0;i<output.size();i++) {
					listaUtente.add(output.get(i));

					//tmp += output.get(i).getEmail();
				}
				//System.out.println(a);
			}	
		});
	}


	public void getStudenti() {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.getStudenti(new AsyncCallback<ArrayList<Studente>>() {
			public void onFailure(Throwable caught) {
				Alert a = new Alert("Errore getStudenti");
				System.out.println(a);
			}
			@Override
			public void onSuccess(ArrayList<Studente> output) {
				listaStudenti.clear();
				for(int i=0;i<output.size();i++) {					
					listaStudenti.add(output.get(i));


				}
			}	
		});
	}


	@UiHandler("btnHome")
	void doClickSubmit(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePageAdmin());
	}

	@UiHandler("btnCreazione")
	void doClickDip(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageCreazioneAccount());

	}

	@UiHandler("btnModifica")
	void doClickContatti(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageModificaAccount());
	}


	@UiHandler("btnLogout")
	void doClickLogout(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());
	}

	@UiHandler("btnCreazioneAccount")
	void doClickCreaAccount(ClickEvent event) {
		switch(menuTipo.getSelectedValue()) {
		case "Studente":
			if(!studentePresente(txtMatricola.getText())) {
				creazioneStudente();
			}
			else {
				Alert a = new Alert("Matricola gia assegnata ad un altro studente");
				System.out.println(a);
			}
			break;
		case "Docente":
			creazioneDocente();
			break;
		case "Segreteria":
			creazioneSegreteria();
			break;
		}
	}

	public boolean studentePresente(String matricola) {
		boolean presente=false;
		for(int i=0; i<listaStudenti.size(); i++) {
			if(listaStudenti.get(i).getMatricola().equals(matricola)) {
				presente=true;
				return presente;
			}
		}
		return presente;
	}


	public void creazioneStudente() {
		ArrayList<String> datiUtenti = new ArrayList<String>();
		datiUtenti.add(txtEmail.getText());
		datiUtenti.add(txtPassword.getText());
		datiUtenti.add(txtNome.getText());
		datiUtenti.add(txtCognome.getText());
		datiUtenti.add(txtLuogo.getText());
		datiUtenti.add(txtData.getText());
		datiUtenti.add(txtMatricola.getText());



		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


		greetingService.registrazioneStudente(datiUtenti, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore!");
				System.out.println(a);
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Registrazione completata")) {
					RootPanel.get("container").clear();
					Alert a = new Alert("Studente creato con successo!");
					System.out.println(a);

					RootPanel.get("container").add(new PageCreazioneAccount());
				}else {
					Alert a = new Alert("Errore! Utente già presente");
					System.out.println(a);
				} 	

			}
		});
	}

	public void creazioneDocente() {

		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		ArrayList<String> datiUtenti = new ArrayList<String>();
		datiUtenti.add(txtEmail.getText());
		datiUtenti.add(txtPassword.getText());
		datiUtenti.add(txtNome.getText());
		datiUtenti.add(txtCognome.getText());
		datiUtenti.add(txtLuogo.getText());
		datiUtenti.add(txtData.getText());

		greetingService.registrazioneDocente(datiUtenti, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore!");
				System.out.println(a);
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Registrazione completata")) {
					RootPanel.get("container").clear();
					Alert a = new Alert("Docente creato con successo!");
					System.out.println(a);

					RootPanel.get("container").add(new PageCreazioneAccount());
				}else {
					Alert a = new Alert("Errore! Utente già presente");
					System.out.println(a);
				} 	

			}
		});
	}

	public void creazioneSegreteria() {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		ArrayList<String> datiUtenti = new ArrayList<String>();
		datiUtenti.add(txtEmail.getText());
		datiUtenti.add(txtPassword.getText());
		datiUtenti.add(txtNome.getText());
		datiUtenti.add(txtCognome.getText());
		datiUtenti.add(txtLuogo.getText());
		datiUtenti.add(txtData.getText());

		greetingService.registrazioneSegreteria(datiUtenti, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore!");
				System.out.println(a);
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Registrazione completata")) {
					RootPanel.get("container").clear();
					Alert a = new Alert("Account segreteria creato con successo!");
					System.out.println(a);

					RootPanel.get("container").add(new PageCreazioneAccount());
				}else {
					Alert a = new Alert("Errore! Utente già presente");
					System.out.println(a);
				} 	

			}
		});
	}

	void addTipologia(){
		menuTipo.addItem("Studente");
		menuTipo.addItem("Docente");
		menuTipo.addItem("Segreteria");
	}

	@UiHandler("menuTipo")
	void doClickTipo(ClickEvent event) {
		if(menuTipo.getSelectedValue()!="Studente") {
			txtMatricola.setVisible(false);
			lblMatricola.setVisible(false);
		}
		else {
			txtMatricola.setVisible(true);
			lblMatricola.setVisible(true);
		}
	}



	@UiField
	Button btnCreazione;

	@UiField
	Button btnHome;

	@UiField
	Button btnModifica;

	@UiField
	Button btnLogout;

	@UiField
	Button btnCreazioneAccount;


	@UiField
	TextBox txtPassword;

	@UiField
	TextBox txtEmail;

	@UiField
	TextBox txtMatricola;

	@UiField
	TextBox txtNome;

	@UiField
	TextBox txtCognome;

	@UiField
	TextBox txtLuogo;

	@UiField
	TextBox txtData;

	@UiField
	Label lblMatricola;


	@UiField
	ListBox menuTipo;

}

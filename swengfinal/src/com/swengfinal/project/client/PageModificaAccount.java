package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Amministratore;
import com.swengfinal.project.shared.Utente;

public class PageModificaAccount extends Composite {

	private static PageModificaAccountUiBinder uiBinder = GWT.create(PageModificaAccountUiBinder.class);

	private static Utente utente;

	interface PageModificaAccountUiBinder extends UiBinder<Widget, PageModificaAccount> {
	}

	public PageModificaAccount() {
		initWidget(uiBinder.createAndBindUi(this));

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
		txtPassword.getElement().getStyle().setMarginLeft(27.0, Unit.PX);
		listUtenti.getElement().getStyle().setMarginLeft(80.0, Unit.PX);
		txtNome.getElement().getStyle().setMarginLeft(15.0, Unit.PX);
		txtCognome.getElement().getStyle().setMarginLeft(58.0, Unit.PX);
		txtLuogo.getElement().getStyle().setMarginLeft(15.0, Unit.PX);
		txtData.getElement().getStyle().setMarginLeft(40.0, Unit.PX);
		btnModificaAccount.getElement().getStyle().setMargin(10.0, Unit.PX);
		btnEliminaAccount.getElement().getStyle().setMargin(10.0, Unit.PX);

		addUtenti();

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

	@UiHandler("btnModificaAccount")
	void doClickCreaAccount(ClickEvent event) {
		ArrayList<String>dati = new ArrayList<String>();
		String email=listUtenti.getSelectedValue();
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.getUtente(email, new AsyncCallback<Utente>() {
			public void onFailure(Throwable caught) {
				Window.alert("ERRORE!");
			}
			@Override
			public void onSuccess(Utente user) {
				utente = user;	 
			}				
		});

		if(txtPassword.getText()!="") {
			dati.add(0, txtPassword.getText());
		}else {
			dati.add(0, utente.getPw());
		}
		if(txtCognome.getText()!="") {
			dati.add(1, txtCognome.getText());
		}else {
			dati.add(1, utente.getCognome());
		}
		if(txtNome.getText()!="") {
			dati.add(2, txtNome.getText());
		}else {
			dati.add(2, utente.getNome());
		}
		if(txtData.getText()!="") {
			dati.add(3, txtData.getText());
		}else {
			dati.add(3, utente.getDataNascita());
		}
		if(txtLuogo.getText()!="") {
			dati.add(4, txtLuogo.getText());
		}else {
			dati.add(4, utente.getLuogoNascita());
		}
/*
		dati.add(0,txtPassword.getText());
		dati.add(1, txtCognome.getText());
		dati.add(2, txtNome.getText());
		dati.add(3, txtData.getText());
		dati.add(4, txtLuogo.getText());*/
		//Window.alert("Corso modificato");



		final GreetingServiceAsync greetingService2 = GWT.create(GreetingService.class);

		greetingService2.updateUtente(dati, email, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore:" + c);
				System.out.println(a);
				RootPanel.get("container").clear();
				RootPanel.get("container").add(new PageModificaAccount());
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Successo")) {
					RootPanel.get("container").clear();
					Alert a = new Alert("Utente modificato con successo!");
					System.out.println(a);

					RootPanel.get("container").add(new PageModificaAccount());
				}else {
					Alert a = new Alert("Errore!");
					System.out.println(a);
				} 	

			}
		}); 
	}

	@UiHandler("btnEliminaAccount")
	void doClickElimnaAccount(ClickEvent event) {
		String email=menuUtenti.getSelectedValue();

		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.deleteUtente(email, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore:" + c);
				System.out.println(a);
				RootPanel.get("container").clear();
				RootPanel.get("container").add(new PageModificaAccount());
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Successo")) {
					RootPanel.get("container").clear();
					Alert a = new Alert("Utente eliminato con successo!");
					System.out.println(a);

					RootPanel.get("container").add(new PageModificaAccount());
				}else {
					Alert a = new Alert("Errore!");
					System.out.println(a);
				} 	

			}
		}); 
	}


	public void addUtenti() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getUtentiAll(new AsyncCallback<ArrayList<Utente>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

				}
				@Override
				public void onSuccess(ArrayList<Utente> utenti) {
					menuUtenti.clear();
					listUtenti.clear();
					for(int i=0;i<utenti.size();i++) {
						if(!(utenti.get(i) instanceof Amministratore)) {
							menuUtenti.addItem(utenti.get(i).getEmail());
							listUtenti.addItem(utenti.get(i).getEmail());
						}

					}
				}
			});
		}catch(Error e){
		};
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
	Button btnModificaAccount;

	@UiField
	Button btnEliminaAccount;

	@UiField
	ListBox menuUtenti;

	@UiField
	TextBox txtPassword;

	@UiField
	ListBox listUtenti;

	@UiField
	TextBox txtNome;

	@UiField
	TextBox txtCognome;

	@UiField
	TextBox txtLuogo;

	@UiField
	TextBox txtData;



}

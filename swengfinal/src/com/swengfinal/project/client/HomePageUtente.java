package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Corso;
import com.swengfinal.project.shared.Esame;
import com.swengfinal.project.shared.Utente;
import com.swengfinal.project.shared.Voto;

public class HomePageUtente extends Composite {

	private static HomePageUtenteUiBinder uiBinder = GWT.create(HomePageUtenteUiBinder.class);

	private static final ArrayList<Esame> esamiStudenti = new ArrayList<Esame>();
	private static  ArrayList<Voto> votiFinal = new ArrayList<Voto>();
	private static final ArrayList<Corso> corsiFinal = new ArrayList<Corso>();


	@UiTemplate("HomePageUtente.ui.xml")
	interface HomePageUtenteUiBinder extends UiBinder<Widget, HomePageUtente> {
	}



	public HomePageUtente() {
		initWidget(uiBinder.createAndBindUi(this));
		getUtente();
		getEsami();
		getCorsiDisponibili();
		getVoti();
		getCorsoStudente();
		getEsamiStudente();

		btnHome.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnHome.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnHome.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnIscrizione.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnIscrizione.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnIscrizione.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnRegistrazione.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnRegistrazione.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnRegistrazione.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnVoti.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnVoti.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnLogout.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnLogout.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnLogout.getElement().getStyle().setMarginLeft(820.0, Unit.PX);


		//getCorsiStudente();

	}

	/* Prende tutti i corsi disponibili per lo studente */
	public void getCorsiDisponibili() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getCorsi(new AsyncCallback<ArrayList<Corso>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE! ");

				}
				@Override
				public void onSuccess(ArrayList<Corso> corsi) {

					corsiFinal.clear();
					for(int i=0;i<corsi.size();i++) {
						corsiFinal.add(corsi.get(i));
						//txtNomeCorso.addItem(corsi.get(i).getNomeCorso());
					}
				}
			});
		}catch(Error e){
		};
	}

	/* Prende i voti associati ad uno studente */
	public void getVoti() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getVoto(Account.matricola, new AsyncCallback<ArrayList<Voto>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

				}
				@Override
				public void onSuccess(ArrayList<Voto> output) {
					votiFinal.clear();
					for(int i=0;i<output.size();i++) {
						votiFinal.add(output.get(i));
					}
				}
			});
		}catch(Error e){
		};
	}


	/* Prende una lista di esami */
	public void getEsami() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getEsami(new AsyncCallback<ArrayList<Esame>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE! ");

				}
				@Override
				public void onSuccess(ArrayList<Esame> esami) {
					esamiStudenti.clear();
					for(int i=0;i<esami.size();i++) {
						esamiStudenti.add(esami.get(i));

					}
				}
			});
		}catch(Error e){
		};
	}


	/* Prende i dati dell'utente e mostra nella homepage Nome,Cognome e Mail*/
	public void getUtente() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getUtente(Account.email, new AsyncCallback<Utente>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE! ");

				}

				@Override
				public void onSuccess(Utente user) {
					lblNome.setText(user.getNome());
					lblCognome.setText(user.getCognome());
					lblMail.setText(user.getEmail());
				}


			});
		}catch(Error e){
		};
	}

	/* Prende i corsi a cui l'utente è iscritto */
	public void getCorsoStudente() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getCorsoStudente(Account.email, new AsyncCallback<ArrayList<Integer>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE! ");

				}
				@Override
				public void onSuccess(ArrayList<Integer> idCorsi) {
					String test = "";
					for(int i=0;i<corsiFinal.size();i++) {
						for(int j=0;j<idCorsi.size();j++) {
							if(idCorsi.get(j)==corsiFinal.get(i).getIdCorso()) {
								test += corsiFinal.get(i).getNomeCorso() + " | ";
							}
						}
					}
					lblCorsi.setText(test);

				}
			});
		}catch(Error e){
		};
	}

	public void getEsamiStudente() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getEsameStudente(Account.email, new AsyncCallback<ArrayList<Integer>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE! corsi");

				}
				@Override
				public void onSuccess(ArrayList<Integer> idCorsi) {
					String test = "";
					for(int i=0;i<esamiStudenti.size();i++) {
						for(int j=0;j<idCorsi.size();j++) {

							if(idCorsi.get(j)==esamiStudenti.get(i).getIdCorso()) {
								test += esamiStudenti.get(i).getNomeEsame() + " | ";
							}
						}
					}

					lblEsami.setText(test);

				}
			});
		}catch(Error e){
		};
	}



	@UiHandler("btnHome")
	void doClickSubmit(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePageUtente());
	}

	@UiHandler("btnIscrizione")
	void doClickDip(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageCorsiDisponibili(corsiFinal));
	}

	@UiHandler("btnRegistrazione")
	void doClickContatti(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new RegistrazioneEsame());
	}

	@UiHandler("btnVoti")
	void doClickHome(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageVoti(votiFinal));
	}

	@UiHandler("btnLogout")
	void doClickLogout(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());
	}

	@UiField
	Button btnIscrizione;

	@UiField
	Button btnHome;

	@UiField
	Button btnRegistrazione;

	@UiField
	Button btnVoti;

	@UiField
	Button btnLogout;

	@UiField
	Label lblNome;

	@UiField
	Label lblCognome;

	@UiField
	Label lblMail;

	@UiField
	Label lblCorsi;

	@UiField
	Label lblEsami;



}

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Corso;
import com.swengfinal.project.shared.Esame;
import com.swengfinal.project.shared.Voto;

public class RegistrazioneEsame extends Composite {

	private static RegistrazioneEsameUiBinder uiBinder = GWT.create(RegistrazioneEsameUiBinder.class);

	private static ArrayList<Corso> corsiFinal = new ArrayList<Corso>();

	@UiTemplate("RegistrazioneEsame.ui.xml")
	interface RegistrazioneEsameUiBinder extends UiBinder<Widget, RegistrazioneEsame> {
	}

	private static  ArrayList<Voto> votiFinal = new ArrayList<Voto>();

	private static final ArrayList<Integer> corsiStudenti = new ArrayList<Integer>();

	private static final ArrayList<Esame> esamiStudenti = new ArrayList<Esame>();

	//private static final ArrayList<Integer> idCorsiDisponibileEsame = new ArrayList<Integer>();

	public RegistrazioneEsame() {
		initWidget(uiBinder.createAndBindUi(this));

		menuCorsi.clear();
		getVoti();
		getCorsiStudente();
		getCorsiDisponibili();
		getEsami();
		getEsamiDisponibili();
		//addOptionMenuEsami();

		//corsiFinal.clear();

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




	}
	/* Ritorna tutti i corsi disponibili */
	public void getCorsiDisponibili() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getCorsi(new AsyncCallback<ArrayList<Corso>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

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


	/* Ritorna tutti i corsi a cui l'utente è iscritto */
	public void getCorsiStudente() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getCorsoStudente(Account.email,new AsyncCallback<ArrayList<Integer>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

				}
				@Override
				public void onSuccess(ArrayList<Integer> corsi) {
					corsiStudenti.clear();
					for(int i=0;i<corsi.size();i++) {
						corsiStudenti.add(corsi.get(i));
					}
				}
			});
		}catch(Error e){
		};
	}

	/* Metodo per prendere una lista con tutti gli esami disponibili */
	public void getEsami() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getEsami(new AsyncCallback<ArrayList<Esame>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

				}
				@Override
				public void onSuccess(ArrayList<Esame> esami) {
					esamiStudenti.clear();
					for(int i=0;i<esami.size();i++) {
						esamiStudenti.add(esami.get(i));
						//menuCorsi.addItem(esami.get(i).getNomeEsame());	
					}
				}
			});
		}catch(Error e){
		};
	}

	public void getEsamiDisponibili() {
		menuCorsi.clear();
		for(int i=0;i<corsiStudenti.size();i++) { // Integer
			for(int j=0;j<esamiStudenti.size();j++) { // Esame
				if(corsiStudenti.get(i)==esamiStudenti.get(j).getIdCorso()) {
					menuCorsi.addItem(esamiStudenti.get(j).getNomeEsame());

				}
			}
		}
	}

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

	@UiHandler("btnHome")
	void doClickSubmit(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePageUtente());
	}

	@UiHandler("btnIscrizione")
	void doClickIscrizioneEsame(ClickEvent event) {
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

	@UiHandler("btnEsame")
	void doClickEsame(ClickEvent event) {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
		Integer id = 0;
		String nomeCorso = menuCorsi.getSelectedValue();
		for(int i=0;i<esamiStudenti.size();i++) {
			if(esamiStudenti.get(i).getNomeEsame().equals(nomeCorso)) {
				id = esamiStudenti.get(i).getIdEsame();
			}
		}
		/*Alert nice = new Alert("Id:" + id + txtNomeCorso.getSelectedValue());
		System.out.println(nice);*/
		greetingService.iscrizioneEsame(Account.email, id , new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {

			}
			@Override
			public void onSuccess(String result) {
				if(result=="Successo") {
					Alert nice = new Alert("Iscrizione all'esame effettuata!");
					System.out.println(nice);
				}else if(result=="Errore") {
					Alert e = new Alert("Sei gia iscritto a questo esame!");
					System.out.println(e);
				}
			}

		});
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new RegistrazioneEsame());
	}

	@UiHandler("menuCorsi")
	void doClickCorsi(ClickEvent event) {
		final String voce=menuCorsi.getSelectedValue();

		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getEsami(new AsyncCallback<ArrayList<Esame>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

				}
				@Override
				public void onSuccess(ArrayList<Esame> esami) {

					for(int i=0;i<esami.size();i++) {
						for(int a=0; a<corsiStudenti.size(); a++) {
							if(esami.get(i).getIdCorso() == corsiStudenti.get(a)) {
								if(esami.get(i).getNomeEsame().equals(voce)) {
									dataAppello.setText(esami.get(i).getData());
								}
							}
						}
					}
				}
			});
		}catch(Error e){
		};
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
	Button btnEsame;

	@UiField
	ListBox menuCorsi;

	@UiField
	Label dataAppello;



}

package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Voto;

public class SegreteriaPubblicaVoto extends Composite {

	private static SegreteriaPubblicaVotoUiBinder uiBinder = GWT.create(SegreteriaPubblicaVotoUiBinder.class);

	interface SegreteriaPubblicaVotoUiBinder extends UiBinder<Widget, SegreteriaPubblicaVoto> {
	}

	private static final ArrayList<Studente> listaStudenti = new ArrayList<Studente>();

	private static ArrayList<Voto> listaVoti = new ArrayList<Voto>();

	public SegreteriaPubblicaVoto(ArrayList<Voto> list) {
		initWidget(uiBinder.createAndBindUi(this));
		btnHomeSegreteria.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnHomeSegreteria.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnHomeSegreteria.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnPubblicaVoto.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnPubblicaVoto.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnPubblicaVoto.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnLogout.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnLogout.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnLogout.getElement().getStyle().setMarginLeft(870.0, Unit.PX);
		cellVotiUsers.getElement().getStyle().setFontSize(24.0, Unit.PX);
		cellVotiUsers.getElement().getStyle().setMarginTop(35.0, Unit.PX);

		listaVoti=list;

		getStudenti();
		newTableVoti();
		getVoti();

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

	public void newTableVoti() {
		TextColumn<Voto> matricolaColumn = new TextColumn<Voto>() {
			@Override
			public String getValue(Voto object) {
				return object.getMatricola();
			}
		};
		cellVotiUsers.addColumn(matricolaColumn, "Matricola Studente");

		TextColumn<Voto> esameColumn = new TextColumn<Voto>() {
			@Override
			public String getValue(Voto object) {
				return object.getNomeEsame();
			}
		};

		cellVotiUsers.addColumn(esameColumn, "Esame Sostenuto");

		TextColumn<Voto> votoColumn = new TextColumn<Voto>() {
			@Override
			public String getValue(Voto object) {
				return object.getVoto();
			}
		};

		cellVotiUsers.addColumn(votoColumn, "Voto");

		cellVotiUsers.setRowCount(listaVoti.size(), true);

		cellVotiUsers.setRowData(0, listaVoti);
	}
	@UiField
	CellTable<Voto> cellVotiUsers;

	@UiHandler("btnHomeSegreteria")
	void doClickHome(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePageSegreteria(listaStudenti));
	}


	@UiHandler("btnPubblicaVoto")
	void doClickPubblicVoto(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new SegreteriaPubblicaVoto(listaVoti));

	}

	@UiHandler("btnPubblicaVotoStudente")
	void doClickPubblicVotoStudente(ClickEvent event) {


		ArrayList<String>dati = new ArrayList<String>();
		dati.clear();

		/* Controllo se il voto da pubblicare esiste già nel database dei voti inviati dal docente */
		for(int i=0;i<listaVoti.size();i++) {
			if(txtMatricola.getText()==listaVoti.get(i).getMatricola() && txtNomeEsame.getText()==listaVoti.get(i).getNomeEsame() && txtVoto.getText()==listaVoti.get(i).getVoto()) {
				dati.add(0,txtMatricola.getText());
				dati.add(1, txtNomeEsame.getText());
				dati.add(2, txtVoto.getText());
			}
		}

		if(!dati.isEmpty()) {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.votoPubblicato(dati, new AsyncCallback<String>() {

				public void onFailure(Throwable c) {
					Alert a = new Alert("Errore:" + c);
					System.out.println(a);
					RootPanel.get("container").clear();
					RootPanel.get("container").add(new SegreteriaPubblicaVoto(listaVoti));
				}

				@Override
				public void onSuccess(String result) {
					if(result.equals("Successo")) {

						RootPanel.get("container").clear();
						Alert a = new Alert("Voto pubblicato con successo!");
						System.out.println(a);

						RootPanel.get("container").add(new SegreteriaPubblicaVoto(listaVoti));
					}else {
						Alert a = new Alert("Errore!");
						System.out.println(a);
					} 	

				}
			}); 
		}else {
			Alert error = new Alert("Errore! I campi inseriti non sono corretti");
			System.out.println(error);
		}


	}

	public void getVoti() {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.getVotoAll(new AsyncCallback<ArrayList<Voto>>() {
			public void onFailure(Throwable caught) {
				Alert a = new Alert("Errore getVoti");
				System.out.println(a);
			}
			@Override
			public void onSuccess(ArrayList<Voto> output) {
				listaVoti.clear();
				for(int i=0;i<output.size();i++) {					
					listaVoti.add(output.get(i));


				}
			}	
		});
	}

	@UiHandler("btnLogout")
	void doClickLogout(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());
	}


	@UiField
	Button btnLogout;

	@UiField
	Button btnHomeSegreteria;

	@UiField
	Button btnPubblicaVoto;


	@UiField
	Button btnPubblicaVotoStudente;	

	@UiField
	TextBox txtMatricola;

	@UiField
	TextBox txtNomeEsame;

	@UiField
	TextBox txtVoto;
}

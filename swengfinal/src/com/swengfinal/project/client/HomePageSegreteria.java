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
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Voto;

public class HomePageSegreteria extends Composite {

	private static HomePageSegreteriaUiBinder uiBinder = GWT.create(HomePageSegreteriaUiBinder.class);
	private static ArrayList<Studente> listaStudenti = new ArrayList<Studente>();

	private static ArrayList<Voto> listaVoti = new ArrayList<Voto>();

	interface HomePageSegreteriaUiBinder extends UiBinder<Widget, HomePageSegreteria> {
	}

	public HomePageSegreteria(ArrayList<Studente> list) {
		initWidget(uiBinder.createAndBindUi(this));

		getVoti();

		btnHomeSegreteria.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnHomeSegreteria.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnHomeSegreteria.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnPubblicaVoto.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnPubblicaVoto.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnPubblicaVoto.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnLogout.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnLogout.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnLogout.getElement().getStyle().setMarginLeft(870.0, Unit.PX);


		cellTable.getElement().getStyle().setFontSize(24.0, Unit.PX);
		cellTable.getElement().getStyle().setMarginTop(35.0, Unit.PX);

		listaStudenti=list;

		//getStudenti();
		newTableStudente();


	}


	public void getVoti() {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.getVotoAll(new AsyncCallback<ArrayList<Voto>>() {
			public void onFailure(Throwable caught) {
				Alert a = new Alert("Errore getStudenti");
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

	public void newTableStudente() {


		TextColumn<Studente> emailColumn=new TextColumn<Studente>() {
			@Override
			public String getValue(Studente obj) {
				return obj.getEmail();
			}
		};
		cellTable.addColumn(emailColumn, "Email Studente");

		TextColumn<Studente> matricolaColumn=new TextColumn<Studente>() {

			public String getValue(Studente obj) {
				return obj.getMatricola();
			}
		};

		cellTable.addColumn(matricolaColumn, "Matricola");

		TextColumn<Studente> nomeColumn=new TextColumn<Studente>() {
			@Override
			public String getValue(Studente obj) {
				return obj.getNome();
			}
		};
		cellTable.addColumn(nomeColumn, "Nome");

		TextColumn<Studente> cognomeColumn=new TextColumn<Studente>() {
			@Override
			public String getValue(Studente obj) {
				return obj.getCognome();
			}
		};
		cellTable.addColumn(cognomeColumn, "Cognome");

		TextColumn<Studente> luogoColumn=new TextColumn<Studente>() {
			@Override
			public String getValue(Studente obj) {
				return obj.getLuogoNascita();
			}
		};
		cellTable.addColumn(luogoColumn, "Luogo di Nascita");

		TextColumn<Studente> dataColumn=new TextColumn<Studente>() {
			@Override
			public String getValue(Studente obj) {
				return obj.getDataNascita();
			}
		};
		cellTable.addColumn(dataColumn, "Data di Nascita");

		cellTable.setRowCount(listaStudenti.size(), true);

		cellTable.setRowData(0, listaStudenti);
	}
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

	@UiHandler("btnLogout")
	void doClickLogout(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());
	}

	@UiField
	CellTable<Studente> cellTable;

	@UiField
	Button btnLogout;

	@UiField
	Button btnHomeSegreteria;

	@UiField
	Button btnPubblicaVoto;

}

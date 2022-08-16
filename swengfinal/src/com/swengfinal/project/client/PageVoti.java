package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Corso;
import com.swengfinal.project.shared.Voto;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;


public class PageVoti extends Composite {

	private static PageVotiUiBinder uiBinder = GWT.create(PageVotiUiBinder.class);
	private static  ArrayList<Voto> votiFinal = new ArrayList<Voto>();
	public static Studente studente = new Studente();
	private static ArrayList<Corso> corsiFinal = new ArrayList<Corso>();

	@UiTemplate("PageVoti.ui.xml")
	interface PageVotiUiBinder extends UiBinder<Widget, PageVoti> {
	}

	public PageVoti(ArrayList<Voto> list) {

		initWidget(uiBinder.createAndBindUi(this));

		corsiFinal.clear();

		getUtente();
		votiFinal=list;
		addTable();

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
		cellTable.getElement().getStyle().setFontSize(24.0, Unit.PX);
		cellTable.getElement().getStyle().setMarginTop(35.0, Unit.PX);
		cellTable.getElement().getStyle().setMarginLeft(450.0, Unit.PX);

		getCorsiDisponibili();

	}



	public void getUtente() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getUtente(Account.email, new AsyncCallback<Utente>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");
				}
				@Override
				public void onSuccess(Utente user) {
					studente = (Studente) user;
				}		
			});
		}catch(Error e){
		};
	}
	/*
	public void getVoti() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getVoto(Account.matricola, new AsyncCallback<ArrayList<Voto>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");
				}
				@Override
				public void onSuccess(ArrayList<Voto> votiOutput) {
					votiFinal.clear();
					for(int i=0;i<votiOutput.size();i++) {
						votiFinal.add(votiOutput.get(i));
					}
				}		
			});
		}catch(Error e){
			};
	}*/

	public void addTable() {

		TextColumn<Voto> esameColumn=new TextColumn<Voto>() {
			@Override
			public String getValue(Voto obj) {
				return obj.getNomeEsame();
			}
		};
		cellTable.addColumn(esameColumn, "Esame");

		TextColumn<Voto> votoColumn=new TextColumn<Voto>() {
			@Override
			public String getValue(Voto obj) {
				return obj.getVoto();
			}
		};
		cellTable.addColumn(votoColumn, "Voto");


		//cellTable.

		cellTable.setRowCount(votiFinal.size(), true);

		cellTable.setRowData(0, votiFinal);


	}

	public void getCorsiDisponibili() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getCorsi(new AsyncCallback<ArrayList<Corso>>() {
				public void onFailure(Throwable caught) {
					Window.alert("ERRORE!");

				}
				@Override
				public void onSuccess(ArrayList<Corso> corsi) {

					for(int i=0;i<corsi.size();i++) {
						corsiFinal.add(corsi.get(i));
						//txtNomeCorso.addItem(corsi.get(i).getNomeCorso());
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
	CellTable<Voto> cellTable;

}

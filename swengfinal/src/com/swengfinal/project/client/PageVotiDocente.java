package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Esame;

public class PageVotiDocente extends Composite {

	private static PageVotiDocenteUiBinder uiBinder = GWT.create(PageVotiDocenteUiBinder.class);
	//private static final ArrayList<String> iscrizioni = new ArrayList<String>();

	@UiTemplate("PageVotiDocente.ui.xml")
	interface PageVotiDocenteUiBinder extends UiBinder<Widget, PageVotiDocente> {
	}

	public PageVotiDocente() {
		initWidget(uiBinder.createAndBindUi(this));

		btnHome.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnHome.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnHome.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnCorso.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnCorso.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnCorso.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnEsame.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnEsame.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnEsame.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnVoti.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnVoti.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnLogout.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnLogout.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnLogout.getElement().getStyle().setMarginLeft(800.0, Unit.PX);
		menuEsame.getElement().getStyle().setMarginLeft(30.0, Unit.PX);
		txtVoto.getElement().getStyle().setMarginLeft(30.0, Unit.PX);
		txtMatricola.getElement().getStyle().setMarginLeft(26.0, Unit.PX);
		btnInvioVoto.getElement().getStyle().setMargin(10, Unit.PX);

		fillistbox();
	}

	void fillistbox() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getAllEsame(Account.email, new AsyncCallback<ArrayList<Esame>>() {
				public void onFailure(Throwable caught) {
					Alert a = new Alert("Errore prendere corso");
					System.out.println(a);
				}	

				@Override
				public void onSuccess(ArrayList<Esame> esami) {
					for(int i=0;i<esami.size();i++)
					{
						menuEsame.addItem(esami.get(i).getNomeEsame());
					}					
				}					
			});
		}catch(Error e){
		};
	}
	/*
	 * BISOGNA PRENDERE L'ID DELL'ESAME DA METTERE IN INPUT
	 * 
	void getMailIscrittiEsame() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getIscrizioniEsame(id, new AsyncCallback<ArrayList<String>>() {
				public void onFailure(Throwable caught) {
					Alert a = new Alert("Errore prendere iscrizioni");
					System.out.println(a);
				}

				@Override
				public void onSuccess(ArrayList<String> iscritti) {
					for(int i=0;i<iscritti.size();i++) {
						iscrizioni.add(iscritti.get(i));
					}
				}
			});
		}catch(Error e){
			};
	}*/

	@UiHandler("btnHome")
	void doClickSubmit(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePageDocente());
	}

	@UiHandler("btnCorso")
	void doClickDip(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageCorsoDocente());
	}

	@UiHandler("btnEsame")
	void doClickContatti(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageEsameDocente());
	}

	@UiHandler("btnVoti")
	void doClickHome(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new PageVotiDocente());
	}

	@UiHandler("btnLogout")
	void doClickLogout(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());
	}

	@UiHandler("btnInvioVoto")
	void doClickSendVoto(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());

		ArrayList<String>dati = new ArrayList<String>();
		dati.add(0, "");
		dati.add(1, menuEsame.getSelectedValue());
		dati.add(2, txtMatricola.getText());
		dati.add(3, txtVoto.getText());


		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		greetingService.addVoto(dati, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore:" + c);
				System.out.println(a);
				RootPanel.get("container").clear();
				RootPanel.get("container").add(new HomePageDocente());
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Successo")) {
					RootPanel.get("container").clear();
					Alert a = new Alert("Voto inviato alla segreteria!");
					System.out.println(a);

					RootPanel.get("container").add(new PageVotiDocente());
				}else if(result.equals("Errore")){
					RootPanel.get("container").clear();
					Alert a = new Alert("Errore nell'invio del voto, ricontrolla i parametri");
					System.out.println(a);
					RootPanel.get("container").add(new PageVotiDocente());
				}   	

			}  
		});

	}

	@UiField
	Button btnCorso;

	@UiField
	Button btnHome;

	@UiField
	Button btnEsame;

	@UiField
	Button btnVoti;

	@UiField
	Button btnLogout;

	@UiField
	Button btnInvioVoto;

	@UiField
	ListBox menuEsame;

	@UiField
	TextBox txtVoto;

	@UiField
	TextBox txtMatricola;

}

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

public class HomePageDocente extends Composite {

	private static HomePageDocenteUiBinder uiBinder = GWT.create(HomePageDocenteUiBinder.class);

	@UiTemplate("HomePageDocente.ui.xml")
	interface HomePageDocenteUiBinder extends UiBinder<Widget, HomePageDocente> {
	}

	public HomePageDocente() {
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

		getUtente();
		getCorso();
		getEsame();


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
					lblNome.setText(user.getNome());
					lblCognome.setText(user.getCognome());
					lblMail.setText(user.getEmail());


				}


			});
		}catch(Error e){
		};
	}

	public void getCorso() {
		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getAllCorso(Account.email, new AsyncCallback<ArrayList<Corso>>() {
				public void onFailure(Throwable caught) {
					Alert a = new Alert("Errore prendere corso");
					System.out.println(a);

				}

				@Override
				public void onSuccess(ArrayList<Corso> corsi) {
					String tmp = "";
					for(int i=0;i<corsi.size();i++)
					{

						tmp += corsi.get(i).getNomeCorso() + ", ";


					}
					lblCorsi.setText(tmp);


				}


			});
		}catch(Error e){
		};
	}

	public void getEsame() {

		try {
			final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

			greetingService.getAllEsame(Account.email, new AsyncCallback<ArrayList<Esame>>() {
				public void onFailure(Throwable caught) {
					Alert a = new Alert("Errore prendere esame");
					System.out.println(a);

				}

				@Override
				public void onSuccess(ArrayList<Esame> esami) {
					String tmp = "";
					for(int i=0;i<esami.size();i++)
					{

						tmp += esami.get(i).getNomeEsame() + ", ";


					}
					lblEsami.setText(tmp);


				} 


			});
		}catch(Error e){
		};
	}

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

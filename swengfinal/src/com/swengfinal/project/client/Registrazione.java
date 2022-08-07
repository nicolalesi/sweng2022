package com.swengfinal.project.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;

public class Registrazione extends Composite {

	private static RegistrazioneUiBinder uiBinder = GWT.create(RegistrazioneUiBinder.class);

	@UiTemplate("Registrazione.ui.xml")

	interface RegistrazioneUiBinder extends UiBinder<Widget, Registrazione> {
	} 


	public Registrazione() {



		initWidget(uiBinder.createAndBindUi(this));
		menu.addItem("Studente");
		menu.addItem("Docente");
		menu.addItem("Segreteria");
		menu.addItem("Amministratore");

		btnHome.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnHome.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnHome.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnDip.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnDip.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnDip.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnContatti.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnContatti.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnContatti.getElement().getStyle().setMarginRight(10, Unit.PX);
		btnLogin.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnLogin.getElement().getStyle().setWidth(70.0, Unit.PX);
		btnRegistrazione.getElement().getStyle().setHeight(50.0, Unit.PX);
		btnRegistrazione.getElement().getStyle().setWidth(90.0, Unit.PX);
		btnRegistrazione.getElement().getStyle().setMarginLeft(10.0, Unit.PX);

	}

	@UiHandler("menu")
	void doClickType(ClickEvent event) {
		if(menu.getSelectedValue().equals("Studente")) {
			RootPanel.get("form").clear();
			RootPanel.get("form").add(new FormStudente());
		}
		else {
			RootPanel.get("form").clear();
			RootPanel.get("form").add(new FormOtherUser(menu.getSelectedValue()));
		}

	}

	@UiHandler("btnLogin")
	void doClickSubmit(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Login());
	}

	@UiHandler("btnDip")
	void doClickDip(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Dipartimenti());
	}

	@UiHandler("btnContatti")
	void doClickContatti(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Contatti());
	}

	@UiHandler("btnHome")
	void doClickHome(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new HomePage());
	}
	@UiHandler("btnRegistrazione")
	void doClickRegistrazione(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Registrazione());
	}



	@UiField
	Button btnLogin;

	@UiField
	Button btnHome;

	@UiField
	Button btnDip;

	@UiField
	Button btnContatti;

	@UiField
	Button btnRegistrazione;

	@UiField
	ListBox menu;
}



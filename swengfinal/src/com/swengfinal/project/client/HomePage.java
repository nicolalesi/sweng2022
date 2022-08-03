package com.swengfinal.project.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiField;

public class HomePage extends Composite {

	private static HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

	@UiTemplate("HomePage.ui.xml")
	interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
	}



	public HomePage() {

		initWidget(uiBinder.createAndBindUi(this));
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

	@UiField
	Button btnLogin;

	@UiField
	Button btnHome;

	@UiField
	Button btnDip;

	@UiField
	Button btnContatti;






}

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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.swengfinal.project.shared.Amministratore;
import com.swengfinal.project.shared.Docente;
import com.swengfinal.project.shared.Segreteria;
import com.swengfinal.project.shared.Studente;
import com.swengfinal.project.shared.Utente;

public class Login extends Composite {
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

	private static final ArrayList<Studente> listaStudenti = new ArrayList<Studente>();

	@UiTemplate("Login.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, Login> {


	}

	public Login() {
		initWidget(uiBinder.createAndBindUi(this));

		getStudenti();

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
		txtMail.getElement().getStyle().setMarginLeft(10.0, Unit.PX);
		txtPassword.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		btnSubmit.getElement().getStyle().setHeight(40.0, Unit.PX);
		btnSubmit.getElement().getStyle().setWidth(70.0, Unit.PX);

	}

	//final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

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

	@UiHandler("btnSubmit")
	void doClickLogin(ClickEvent event) {
		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
		//Window.alert(txtPassword.getText() + " " + txtMail.getText());
		greetingService.login(txtMail.getText(), txtPassword.getText(), new AsyncCallback<Utente>() {



			public void onFailure(Throwable c) {

				RootPanel.get("container").clear();
				RootPanel.get("container").add(new HomePage());
			}

			@Override
			public void onSuccess(Utente user) {
				Account.email = txtMail.getText();

				RootPanel.get("container").clear();
				if(user instanceof Studente) {
					Account.matricola=((Studente) user).getMatricola();
					RootPanel.get("container").add(new HomePageUtente());

				}else if(user instanceof Docente) {
					RootPanel.get("container").add(new HomePageDocente());
				}else if(user instanceof Amministratore) {
					RootPanel.get("container").add(new HomePageAdmin());
				}else if(user instanceof Segreteria) {
					RootPanel.get("container").add(new HomePageSegreteria(listaStudenti));
				}

				else {
					RootPanel.get("container").add(new HomePage());
				}


			}
		});



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
	TextBox txtMail;

	@UiField
	PasswordTextBox txtPassword;

	@UiField
	Button btnSubmit;
}

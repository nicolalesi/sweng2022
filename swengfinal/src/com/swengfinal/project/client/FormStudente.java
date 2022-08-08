package com.swengfinal.project.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FormStudente extends Composite {

	private static FormStudenteUiBinder uiBinder = GWT.create(FormStudenteUiBinder.class);

	interface FormStudenteUiBinder extends UiBinder<Widget, FormStudente> {


	}

	public FormStudente() {
		initWidget(uiBinder.createAndBindUi(this));

		txtMail.getElement().getStyle().setMarginLeft(10.0, Unit.PX);
		txtPassword.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		txtMatricola.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		txtNome.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		txtCognome.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		txtLuogoNascita.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		txtDataNascita.getElement().getStyle().setMarginLeft(35.0, Unit.PX);
		btnSubmit.getElement().getStyle().setHeight(40.0, Unit.PX);
		btnSubmit.getElement().getStyle().setWidth(70.0, Unit.PX);
	}


	@UiHandler("btnSubmit")
	void doClickSubmit2(ClickEvent event) {


		ArrayList<String> datiUtenti = new ArrayList<String>();
		datiUtenti.add(txtMail.getText());
		datiUtenti.add(txtPassword.getText());
		datiUtenti.add(txtNome.getText());
		datiUtenti.add(txtCognome.getText());
		datiUtenti.add(txtLuogoNascita.getText());
		datiUtenti.add(txtDataNascita.getText());
		datiUtenti.add(txtMatricola.getText());



		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


		greetingService.registrazioneStudente(datiUtenti, new AsyncCallback<String>() {

			public void onFailure(Throwable c) {
				Alert a = new Alert("Errore!");
				System.out.println(a);
			}

			@Override
			public void onSuccess(String result) {
				if(result.equals("Registrazione completata")) {
					RootPanel.get("container").clear();
					//Account.email = txtMail.getText();
					//Account.tipoAccount = 1;

					RootPanel.get("container").add(new Login());
				}else {
					Alert a = new Alert("Errore! Registrazione");
					System.out.println(a);
				} 	

			}
		});

	}

	@UiField
	TextBox txtMail;

	@UiField
	TextBox txtPassword;


	@UiField
	TextBox txtMatricola;

	@UiField
	TextBox txtNome;

	@UiField
	TextBox txtCognome;

	@UiField
	TextBox txtLuogoNascita;

	@UiField
	TextBox txtDataNascita;


	@UiField
	Button btnSubmit;
}

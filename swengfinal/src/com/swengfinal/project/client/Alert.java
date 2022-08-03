package com.swengfinal.project.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Alert extends DialogBox  {

	private DialogBox messaggio = null;

	public Alert(String testo){

		this.messaggio = new DialogBox(false, true);
		this.messaggio.setText("Attenzione!");

		Label content = new Label(testo.replaceAll(" +", "\u00a0").replaceAll("\n", " "));
		content.setWidth("1%");

		if (this.messaggio.isAutoHideEnabled())  {
			this.messaggio.setWidget(content);
		} else {
			VerticalPanel vPanel = new VerticalPanel();
			vPanel.setSpacing(2);
			vPanel.add(content);vPanel.add(new Label("\n"));

			final Button close = new Button("Close");			
			close.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					messaggio.hide();
				}
			});
			close.getElement().setAttribute("align", "center");

			vPanel.add(close);
			this.messaggio.setWidget(vPanel);
		}
		this.messaggio.center();
		this.messaggio.show();

	}
}

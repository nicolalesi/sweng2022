package com.swengfinal.project.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

//classe che fa partire il tutto

public class Swengfinal implements EntryPoint {
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


	public void onModuleLoad() {
		RootPanel.get("container").add(new HomePage()); 
	}	
}

 
package com.opcoach.training.rental.ui.e4.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class HelloWorldHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell s, IEventBroker ieb) {
		MessageDialog.openInformation(s, "Hello world", " Hello world message");
		ieb.send("eventTest", "Hello");
	}
		
}
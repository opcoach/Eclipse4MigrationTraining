package com.opcoach.training.rental.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.RentalCoreActivator;

public class RentalAddon {

	@PostConstruct
	public void initializeRental(IEclipseContext ctx) {
		
		   ctx.set(RentalAgency.class, RentalCoreActivator.getAgency());
	}
	
	
	@Inject @Optional
	public void receiveevent(@UIEventTopic("eventTest") String z)
	{
		System.out.println(" I received this event : " + z);
	}
}

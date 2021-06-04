package com.opcoach.training.rental.ui.e4.handlers;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ToolControlSample {

	@PostConstruct
	public void test(@Optional Composite parent)
	{
		
		System.out.println("Enter in tool control with this parent : " + parent);
		
		
		Label l = new Label(parent, SWT.BORDER);
		l.setText("XXXXXXXXXXXXXXXXX");
	}
}

package com.opcoach.training.rental.ui.e4.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.ui.Messages;
import com.opcoach.training.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.ui.RentalUIConstants;

public class RentalPropertyView   {
	public static final String VIEW_ID = "com.opcoach.rental.ui.views.rentalView"; //$NON-NLS-1$

	private Label rentedObjectLabel;
	private Label customerNameLabel;
	private Label startDateLabel;
	private Label endDateLabel;


	public RentalPropertyView() {
	}

	@PostConstruct  
	public void createPartControl( Composite parent, RentalAgency a) {
		parent.setLayout(new GridLayout(1, false));
		

		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText(Messages.RentalPropertyView_Information);
		infoGroup.setLayout(new GridLayout(2, false));

		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);

		DragSource ds = new DragSource(rentedObjectLabel, DND.DROP_COPY);
		ds.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		ds.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = rentedObjectLabel.getText();
				}
			}

			@Override
			public void dragStart(DragSourceEvent event) {
				event.image = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIConstants.IMG_AGENCY);
			}

		});

		Label customerTitle = new Label(infoGroup, SWT.NONE);
		customerTitle.setText(Messages.RentalPropertyView_RentedBy);
		customerNameLabel = new Label(infoGroup, SWT.NONE);

		Group dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		dateGroup.setText(Messages.RentalPropertyView_RentalDateTitle);
		dateGroup.setLayout(new GridLayout(2, false));

		Label startDateTitle = new Label(dateGroup, SWT.NONE);
		startDateTitle.setText(Messages.RentalPropertyView_From);
		startDateLabel = new Label(dateGroup, SWT.NONE);

		Label endDateTitle = new Label(dateGroup, SWT.NONE);
		endDateTitle.setText(Messages.RentalPropertyView_To);
		endDateLabel = new Label(dateGroup, SWT.NONE);
		endDateLabel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));

		// Fill with sample
		//Rental a = RentalCoreActivator.getAgency();
		setRental(a.getRentals().get(0));


	}

	public void setRental(Rental r) {

		if (rentedObjectLabel == null) return;
		
		if (r == null) {
			rentedObjectLabel.setText(" ");
			customerNameLabel.setText(" ");
			startDateLabel.setText(" ");
			endDateLabel.setText(" ");
		} else {
			DateFormat df = new SimpleDateFormat();
			rentedObjectLabel.setText(r.getRentedObject().getName());
			customerNameLabel.setText(r.getCustomer().getDisplayName());
			startDateLabel.setText(df.format(r.getStartDate()));
			endDateLabel.setText(df.format(r.getEndDate()));
		}

	}

	

	@Focus
	public void setFocus() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	
	
	
	@Inject @Optional
	public void reactOnSelect(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		
			setRental(r);

		}



}

package cz.muni.fi.cdii.plugin.ui;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import cz.muni.fi.cdii.plugin.model.IInspection;

public class InspectorPart {
	
	public static final String ID = "cz.muni.fi.cdii.plugin.InspectorPartDescriptor";
	
	@Inject
	private Logger log;

	private Label inspectionPartLabel;
	private Text outputText;
	private GraphViewer graphViewer;

	public InspectorPart() {
		System.out.println("Inspector part init()");
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, true));
		
		this.inspectionPartLabel = new Label(parent, SWT.NONE);
		this.inspectionPartLabel.setText("Inspector part");
		
		outputText = new Text(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		GridData gd_outputText = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_outputText.heightHint = 30;
		outputText.setLayoutData(gd_outputText);
		if (this.log != null) {
			log.info("log injected into inspection part");
		}
		this.graphViewer = new GraphViewer(parent, SWT.BORDER);
		this.graphViewer.getGraphControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.graphViewer.setContentProvider(new GraphContentProvider());
		this.graphViewer.setLabelProvider(new GraphLabelProvider());
		this.graphViewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));

		
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		this.graphViewer.getGraphControl().setFocus();
	}
	
	public void inspect(IInspection inspection) {
		log.info("inspectionPart.inspect() called");
		this.outputText.setText(inspection.toString());
		

		this.graphViewer.setInput(inspection.getBeans().toArray());
		this.graphViewer.applyLayout();
		this.setFocus();
	}

}

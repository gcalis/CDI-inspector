package cz.muni.fi.cdii.plugin.ui.e3compat;

import org.eclipse.jface.action.Action;

import cz.muni.fi.cdii.plugin.Activator;


public class ZoomInAction extends Action {

    public ZoomInAction() {
        super();
        this.setImageDescriptor(Activator.getImageDescriptor("icons/eclipse/add_correction.gif"));
        this.setToolTipText("Zoom in");
    }
    
    @Override
    public void run() {
        System.out.println("ahoj");
    }
}
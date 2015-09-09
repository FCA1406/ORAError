package br.com.decade.oraerror.client;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class MainBean {
    private String findError;
    private String makeFavorite;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public MainBean() {
    }

    public void setFindError(String findError) {
        String oldFindError = this.findError;
        this.findError = findError;
        propertyChangeSupport.firePropertyChange("findError", oldFindError, findError);
    }

    public String getFindError() {
        return findError;
    }

    public void setMakeFavorite(String makeFavorite) {
        String oldMakeFavorite = this.makeFavorite;
        this.makeFavorite = makeFavorite;
        propertyChangeSupport.firePropertyChange("makeFavorite", oldMakeFavorite, makeFavorite);
    }

    public String getMakeFavorite() {
        return makeFavorite;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}

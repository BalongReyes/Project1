
package FrameSystem.SAbstractComponents;

import EventSystem.EventSLayer.SLayerBeforeShowEvent;
import EventSystem.EventSLayer.SLayerBeforeShowListener;
import EventSystem.EventSLayer.SLayerEventMulticaster;
import EventSystem.EventSLayer.SLayerHideEvent;
import EventSystem.EventSLayer.SLayerHideListener;
import EventSystem.EventSLayer.SLayerKeyPressedListener;
import EventSystem.EventSLayer.SLayerShowEvent;
import EventSystem.EventSLayer.SLayerShowListener;
import EventSystem.Listeners.MousePressedAdaptor;
import FrameSystem.SComponents.SPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import java.util.EventListener;

@JavaBean(description = "A component built for JLayeredPane")
public abstract class SLayer extends SPanel{
    
// Abstract Methods ==========================================================================================
    
    protected abstract void showLayeredPanel();
    
// Setters and Getters =======================================================================================
    
    private SLayerButton button = null;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The button that will show this panel")
    public void setLayerButton(SLayerButton button){
        this.button = button;
        button.addLayeredPanelMouseListener((MousePressedAdaptor) evt -> {
            showLayeredPanel();
        });
    }
    
    public SLayerButton getLayerButton(){
        return button;
    }
    
// Event =====================================================================================================
    
    transient SLayerShowListener showListener;

    public synchronized void addLayeredPanelShowListener(SLayerShowListener l) {
        if(l == null) return;
        showListener = SLayerEventMulticaster.add(showListener, l);
    }

    public synchronized void removeLayeredPanelShowListener(SLayerShowListener l) {
        if(l == null) return;
        showListener = SLayerEventMulticaster.remove(showListener, l);
    }
    
    public synchronized SLayerShowListener[] getLayeredPanelShowListener(){
        return getSLayerListeners(SLayerShowListener.class);
    }
    
    public synchronized void fireLayeredPanelShowListener(boolean showing, String recent){
        if(showListener == null) return;
        showListener.layeredPanelShow(new SLayerShowEvent(this, showing, recent));
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    transient SLayerHideListener hideListener;
    
    public synchronized void addLayeredPanelHideListener(SLayerHideListener l) {
        if(l == null) return;
        hideListener = SLayerEventMulticaster.add(hideListener, l);
    }

    public synchronized void removeLayeredPanelHideListener(SLayerHideListener l) {
        if(l == null) return;
        hideListener = SLayerEventMulticaster.remove(hideListener, l);
    }
    
    public synchronized SLayerHideListener[] getLayeredPanelHideListener(){
        return getSLayerListeners(SLayerHideListener.class);
    }
    
    public synchronized boolean fireLayeredPanelHideListener(String next){
        if(hideListener == null) return true;
        return hideListener.layeredPanelHide(new SLayerHideEvent(this, next));
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    transient SLayerBeforeShowListener beforeShowListener;
    
    public synchronized void addLayeredPanelBeforeShowListener(SLayerBeforeShowListener l) {
        if(l == null) return;
        beforeShowListener = SLayerEventMulticaster.add(beforeShowListener, l);
    }

    public synchronized void removeLayeredPanelBeforeShowListener(SLayerBeforeShowListener l) {
        if(l == null) return;
        beforeShowListener = SLayerEventMulticaster.remove(beforeShowListener, l);
    }
    
    public synchronized SLayerBeforeShowListener[] getLayeredPanelBeforeShowListener(){
        return getSLayerListeners(SLayerBeforeShowListener.class);
    }
    
    public synchronized boolean fireLayeredPanelBeforeShowListener(boolean showing, String recent){
        if(beforeShowListener == null) return true;
        return beforeShowListener.layeredPanelBeforeShow(new SLayerBeforeShowEvent(this, showing, recent));
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    transient SLayerKeyPressedListener keyPressedListener;
    
    public synchronized void addLayeredPanelKeyPressedListener(SLayerKeyPressedListener l) {
        if(l == null) return;
        keyPressedListener = SLayerEventMulticaster.add(keyPressedListener, l);
    }

    public synchronized void removeLayeredPanelKeyPressedListener(SLayerKeyPressedListener l) {
        if(l == null) return;
        keyPressedListener = SLayerEventMulticaster.remove(keyPressedListener, l);
    }
    
    public synchronized SLayerKeyPressedListener[] getLayeredPanelKeyPressedListener(){
        return getSLayerListeners(SLayerKeyPressedListener.class);
    }
    
    public synchronized void fireLayeredPanelKeyPressedListener(KeyEvent evt){
        if(keyPressedListener == null) return;
        keyPressedListener.layeredPanelKeyPressed(evt);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    public synchronized <T extends EventListener> T[] getSLayerListeners(Class<T> listenerType) {
        EventListener l = null;
        if(listenerType == SLayerShowListener.class){
            l = showListener;
        }
        if(listenerType == SLayerHideListener.class){
            l = hideListener;
        }
        return SLayerEventMulticaster.getListeners(l, listenerType);
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void paintOverrideAll(Graphics g){
        super.paintOverrideAll(g);
    }
    
    @Override
    public void paintOverride(Graphics g, int n){
        if(n > 0){
            n--;
            if(n > 0){
                super.paintOverride(g, n);
            }else{
                super.paintOverride(g);
            }
        }else{
            super.paint(g);
        }
    }
    
    @Override
    public void paintOverride(Graphics g){
        super.paint(g);
    }
    
}

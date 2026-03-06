
package FrameSystem.SAbstractComponents;

import FrameSystem.SComponents.SPanelActivatableHover;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.beans.JavaBean;

@JavaBean(description = "A component that displays a jpanel acts as a button for layered panel")
public abstract class SLayerButton extends SPanelActivatableHover{

    public SLayerButton(){
        super();
    }
    
// Abstract Methods ==========================================================================================
    
    public abstract void addLayeredPanelMouseListener(MouseListener m);
    public abstract void setActiveButton(boolean active);
    
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

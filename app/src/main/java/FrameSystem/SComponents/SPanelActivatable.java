
package FrameSystem.SComponents;

import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A SPanel that is activatable")
public class SPanelActivatable extends SPanel{

    public SPanelActivatable(){
        super();
    }
    
// Getters and Setters =======================================================================================
    
    protected boolean active = false;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "If panel is active")
    public void setActive(boolean active){
        if(this.active == active) return;
        this.active = active;
        repaint();
    }

    public boolean isActive(){
        return active;
    }

// -----------------------------------------------------------------------------------------------------------
    
    protected Color activeBackgroundColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The active background color")
    public void setActiveBackgroundColor(Color activeBackgroundColor){
        this.activeBackgroundColor = activeBackgroundColor;
    }

    public Color getActiveBackgroundColor(){
        return activeBackgroundColor;
    }
    
    protected Color inactiveBackgroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The inactive background color")
    public void setInactiveBackgroundColor(Color inactiveBackgroundColor){
        this.inactiveBackgroundColor = inactiveBackgroundColor;
    }

    public Color getInactiveBackgroundColor(){
        return inactiveBackgroundColor;
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
    
// -----------------------------------------------------------------------------------------------------------
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        if(active){
            g2.setColor(activeBackgroundColor);
        }else{
            g2.setColor(inactiveBackgroundColor);
        }
        g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
        
        if(!cornerUL){
            g2.fillRect(0, 0, radius, radius);
        }
        if(!cornerUR){
            g2.fillRect(s.width - radius, 0, s.width, radius);
        }
        if(!cornerDL){
            g2.fillRect(0, s.height - radius, radius, s.height);
        }
        if(!cornerDR){
            g2.fillRect(s.width - radius, s.height - radius, s.width, s.height);
        }
        
        super.paintOverride(g);
    }
    
}

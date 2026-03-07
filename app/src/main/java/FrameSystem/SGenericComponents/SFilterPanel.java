
package FrameSystem.SGenericComponents;

import FrameSystem.SComponents.SPanelActivatable;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A SPanel for filters")
public class SFilterPanel extends SPanelActivatable{

    public SFilterPanel(){
        super();
    }

// Setters and Getters =======================================================================================

    private Color activeLineColor = new Color(255, 255, 255);

    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setActiveLineColor(Color activeLineColor){
        this.activeLineColor = activeLineColor;
    }

    public Color getActiveLineColor(){
        return activeLineColor;
    }
    
    private Color inactiveLineColor = new Color(255, 255, 255);

    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setInactiveLineColor(Color inactiveLineColor){
        this.inactiveLineColor = inactiveLineColor;
    }

    public Color getInactiveLineColor(){
        return inactiveLineColor;
    }
    
// Overrided Methods =========================================================================================
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();

        super.paint(g);
        
        if(active){
            g2.setColor(activeLineColor);
        }else{
            g2.setColor(inactiveLineColor);
        }
        g2.fillRoundRect(s.width - 10, 9, 4, (s.height - 18), 4, 4);
    }
    
}

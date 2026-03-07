
package FrameSystem.HeroGroup.Components;

import FrameSystem.SComponents.SPanel;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A component that displays a jpanel in hero menu bar")
public class HeroMenuPanel extends SPanel{

    public HeroMenuPanel(){
        setOpaque(false);
    }
    
// ===========================================================================================================
    
    private int borderLine = 0;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The border line width")
    public void setBorderLine(int borderLine){
        this.borderLine = borderLine;
    }
    
    public int getBorderLine(){
        return borderLine;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color borderColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The border color")
    public void setBorderColor(Color borderColor){
        this.borderColor = borderColor;
    }

    public Color getBorderColor(){
        return borderColor;
    }
    
// Overrided Methods =========================================================================================
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        g2.setColor(getBackground());
        g2.fillRect(0, 0, s.width, s.height);
        
        g2.setColor(borderColor);
        g2.fillRect(s.width - borderLine, 0, borderLine, s.height);
        
        super.paintOverrideAll(g);
    }

}

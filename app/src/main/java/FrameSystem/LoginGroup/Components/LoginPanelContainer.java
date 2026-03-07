
package FrameSystem.LoginGroup.Components;

import FrameSystem.SComponents.SPanel;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A component that displays the login panel")
public class LoginPanelContainer extends SPanel{

// Constructor ===============================================================================================
    
    public LoginPanelContainer(){
    }
    
// Setters and Getters =======================================================================================
    
    private Color line = new Color(255, 255, 255);

    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setLine(Color line){
        this.line = line;
    }

    public Color getLine(){
        return line;
    }
    
// Overrided Methods =========================================================================================
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        g2.setColor(line);
        g2.fillRoundRect(0, 20, 4, (s.height - 40), 4, 4);
        
        super.paintOverride(g);
    }

}

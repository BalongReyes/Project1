
package FrameSystem.SComponents;

import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A component that displays a jlabel that change color when hovered")
public class SPanelActivatableHover extends SPanelActivatable{

    protected MouseListener hoverListener;
    
// Constructor ===============================================================================================

    public SPanelActivatableHover(){
        super();
        
        this.setBorder(null);
        hoverListener = (MouseListener)(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt){
                setHovering(true);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                setHovering(false);
            }
        });
        super.addMouseListener(hoverListener);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
// Methods ===================================================================================================

    protected boolean hovering = false;
    
    public void setHovering(boolean hovering){
        this.hovering = hovering;
        repaint();
    }
    
    public boolean isHovering(){
        return hovering;
    }
    
// Setters and Getters =======================================================================================

    protected Color hoverBackgroundColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The hover color")
    public void setHoverBackgroundColor(Color hoverBackgroundColor){
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getHoverBackgroundColor(){
        return hoverBackgroundColor;
    }

// Overrided Methods =========================================================================================

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
    public final void setCursor(Cursor cursor){
        super.setCursor(cursor);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        if(active){
            g2.setColor(activeBackgroundColor);
        }else if(hovering){
            g2.setColor(hoverBackgroundColor);
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
        
        super.paintOverrideAll(g);
    }

}

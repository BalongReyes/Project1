
package FrameSystem.SComponents;

import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A custom panel with highlighted sides and rounded corners")
public class SPanelHighlight extends SPanel{

    private boolean hovering = false;
    
// Constructor ===============================================================================================
    
    public SPanelHighlight(){
        setOpaque(false);
        
        addMouseListener(hoverListener);
    }
    
    private MouseAdapter hoverListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent evt){
            hovering = true;
            repaint();
        }
        @Override
        public void mouseExited(MouseEvent evt){
            hovering = false;
            repaint();
        }
    };

// Main Methods ==============================================================================================

    public void applyHoverInnerListener(){
        addInnerListeners(hoverListener);
    }
    
// Setters and Getters =======================================================================================
    
    private boolean active = false;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "Set panel active")
    public void setActive(boolean active){
        if(this.active == active) return;
        this.active = active;
        repaint();
    }

    public boolean isActive(){
        return active;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private boolean danger = false;

    @BeanProperty(preferred = true, visualUpdate = true, description = "Set panel danger")
    public void setDanger(boolean danger){
        if(this.danger == danger) return;
        this.danger = danger;
        repaint();
    }

    public boolean isDanger(){
        return danger;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color dangerBackgroundColor = Color.white;
    
    @BeanProperty(preferred = true, description = "danger background color")
    public void setDangerBackgroundColor(Color dangerBackgroundColor){
        this.dangerBackgroundColor = dangerBackgroundColor;
    }

    public Color getDangerBackgroundColor(){
        return dangerBackgroundColor;
    }
    
    private Color hoverBackgroundColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The hover background color")
    public void setHoverBackgroundColor(Color hoverBackgroundColor){
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getHoverBackgroundColor(){
        return hoverBackgroundColor;
    }

    private Color activeBackgroundColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The active background color")
    public void setActiveBackgroundColor(Color activeBackgroundColor){
        this.activeBackgroundColor = activeBackgroundColor;
    }

    public Color getActiveBackgroundColor(){
        return activeBackgroundColor;
    }
    
    private Color inactiveBackgroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The inactive background color")
    public void setInactiveBackgroundColor(Color inactiveBackgroundColor){
        this.inactiveBackgroundColor = inactiveBackgroundColor;
    }

    public Color getInactiveBackgroundColor(){
        return inactiveBackgroundColor;
    }
    
    @Override
    @BeanProperty(hidden = true)
    public void setBackground(Color bg){
        super.setBackground(bg);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color dangerForegroundColor = Color.white;

    @BeanProperty(preferred = true, description = "danger foreground color")
    public void setDangerForegroundColor(Color dangerForegroundColor){
        this.dangerForegroundColor = dangerForegroundColor;
    }

    public Color getDangerForegroundColor(){
        return dangerForegroundColor;
    }
    
    private Color hoverForegroundColor = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The hover foreground color")
    public void setHoverForegroundColor(Color hoverForegroundColor){
        this.hoverForegroundColor = hoverForegroundColor;
    }

    public Color getHoverForegroundColor(){
        return hoverForegroundColor;
    }
    
    private Color activeForegroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The active foreground color")
    public void setActiveForegroundColor(Color activeForegroundColor){
        this.activeForegroundColor = activeForegroundColor;
        if(active){
            setForeground(activeForegroundColor);
        }
    }

    public Color getActiveForegroundColor(){
        return activeForegroundColor;
    }

    private Color inactiveForegroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The inactive foreground color")
    public void setInactiveForegroundColor(Color inactiveForegroundColor){
        this.inactiveForegroundColor = inactiveForegroundColor;
        if(!active){
            setForeground(inactiveForegroundColor);
        }
    }

    public Color getInactiveForegroundColor(){
        return inactiveForegroundColor;
    }
    
    @Override
    @BeanProperty(hidden = true)
    public void setForeground(Color fg){
        super.setForeground(fg);
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();

        if(danger){
            g2.setColor(dangerForegroundColor);
        }else if(active){
            g2.setColor(activeForegroundColor);
        }else if(hovering && hoverForegroundColor != null){
            g2.setColor(hoverForegroundColor);
        }else{
            g2.setColor(inactiveForegroundColor);
        }
        g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
        
        if(danger){
            g2.setColor(dangerBackgroundColor);
        }else if(active){
            g2.setColor(activeBackgroundColor);
        }else if(hovering){
            g2.setColor(hoverBackgroundColor);
        }else{
            g2.setColor(inactiveBackgroundColor);
        }
        g2.fillRoundRect(1, 1, s.width - 2, s.height - 2, radius - 1, radius - 1);
        
        super.paintOverrideAll(g);
    }

}

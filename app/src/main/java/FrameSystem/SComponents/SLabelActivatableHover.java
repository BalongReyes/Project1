
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

public class SLabelActivatableHover extends SLabelActivatable{

    protected MouseListener hoverListener;
    
// Constructor ===============================================================================================

    public SLabelActivatableHover(){
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
        if(!hoverable) return;
        this.hovering = hovering;
        repaint();
    }
    
    public boolean isHovering(){
        return hovering;
    }
    
    private boolean hoverable = true;

    public void setHoverable(boolean hoverable){
        this.hoverable = hoverable;
        if(!hoverable){
            setHovering(false);
        }
    }

    public boolean isHoverable(){
        return hoverable;
    }
    
    @Override
    public void setEnabled(boolean enabled){
        if(!enabled){
            setHovering(false);
        }
        super.setEnabled(enabled);
    }
    
// Setters and Getters =======================================================================================

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
    
// -----------------------------------------------------------------------------------------------------------
    
    protected Color hoverBackgroundColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The hover color")
    public void setHoverBackgroundColor(Color hoverBackgroundColor){
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getHoverBackgroundColor(){
        return hoverBackgroundColor;
    }

    protected int radius = 0;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The background radius")
    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
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
        
        super.paintOverrideAll(g);
    }
    
}

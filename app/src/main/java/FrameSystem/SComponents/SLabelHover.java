
package FrameSystem.SComponents;

import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanProperty;
import java.beans.JavaBean;

@JavaBean(description = "A component that displays a jlabel that change color when hovered")
public class SLabelHover extends SLabel{

// Constructor ===============================================================================================

    public SLabelHover(){
        super();
        
        this.setBorder(null);
        this.setOpaque(false);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt){
                setHovering(true);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                setHovering(false);
            }
        });
        
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
// Methods ===================================================================================================

    private boolean hovering = false;
    
    public void setHovering(boolean hovering){
        if(!isEnabled()) return;
        this.hovering = hovering;
        repaint();
    }
    
    public boolean isHovering(){
        return hovering;
    }
    
    @Override
    public void setEnabled(boolean enabled){
        if(!enabled){
            setHovering(false);
        }
        super.setEnabled(enabled);
    }
    
// Setters and Getters =======================================================================================

    private Color defaultColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The default color")
    public void setDefaultColor(Color defaultColor){
        this.defaultColor = defaultColor;
        setBackground(defaultColor);
    }

    public Color getDefaultColor(){
        return defaultColor;
    }
    
    private Color hoverColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The hover color")
    public void setHoverColor(Color hoverColor){
        this.hoverColor = hoverColor;
    }

    public Color getHoverColor(){
        return hoverColor;
    }

    private int radius = 0;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The panel radius")
    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }

    @Override
    @BeanProperty(hidden = true)
    public void setBackground(Color bg){
        super.setBackground(bg);
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
    public final void setCursor(Cursor cursor){
        super.setCursor(cursor);
    }
    
    public Color overrideColor = null;
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();

        if(overrideColor != null){
            g2.setColor(overrideColor);
        }else if(hovering){
            g2.setColor(hoverColor);
        }else{
            g2.setColor(defaultColor);
        }
        g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
        
        super.paint(g);
    }

}

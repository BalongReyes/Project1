
package FrameSystem.SComponents;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.beans.BeanProperty;
import java.beans.JavaBean;

import javax.swing.JPanel;
import javax.swing.border.Border;

import EventSystem.Interface.InnerListener;
import MainSystem.CustomGraphics;

@JavaBean(description = "A component that displays a jpanel")
public class SPanel extends JPanel implements InnerListener{

    public SPanel(){
        super();
        super.setBorder(null);
    }
    
// Setters and Getters =======================================================================================
    
    protected int radius = 0;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The corner radius")
    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }
    
    public int getRadius(int add){
        int output = radius + add;
        return output < 0 ? 0 : output;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    protected boolean rounded = false;

    @BeanProperty(preferred = true, visualUpdate = true, description = "If the panel is rounded")
    public void setRounded(boolean rounded){
        this.rounded = rounded;
        setOpaque(!rounded);
    }

    public boolean isRounded(){
        return rounded;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    protected boolean cornerUL = true, cornerUR = true, cornerDL = true, cornerDR = true;

    @BeanProperty(visualUpdate = true, description = "The UL corner")
    public void setCornerUL(boolean cornerUL){
        this.cornerUL = cornerUL;
    }

    public boolean isCornerUL(){
        return cornerUL;
    }

    @BeanProperty(visualUpdate = true, description = "The UR corner")
    public void setCornerUR(boolean cornerUR){
        this.cornerUR = cornerUR;
    }

    public boolean isCornerUR(){
        return cornerUR;
    }

    @BeanProperty(visualUpdate = true, description = "The DL corner")
    public void setCornerDL(boolean cornerDL){
        this.cornerDL = cornerDL;
    }

    public boolean isCornerDL(){
        return cornerDL;
    }

    @BeanProperty(visualUpdate = true, description = "The DR corner")
    public void setCornerDR(boolean cornerDR){
        this.cornerDR = cornerDR;
    }

    public boolean isCornerDR(){
        return cornerDR;
    }
    
// -----------------------------------------------------------------------------------------------------------

    @Override
    @BeanProperty(hidden = true)
    public void setToolTipText(String text){
        super.setToolTipText(text);
    }

    @Override
    @BeanProperty(hidden = true)
    public void setBorder(Border border){
        super.setBorder(border);
    }
    
// Overrided Methods =========================================================================================

    public void paintOverrideAll(Graphics g){
        super.paint(g);
    }
    
    public void paintOverride(Graphics g, int n){
        super.paint(g);
    }
    
    public void paintOverride(Graphics g){
        super.paint(g);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    @Override
    public void setCursor(Cursor cursor){
        super.setCursor(cursor);
    }
    
    @Override
    public void addInnerListeners(MouseListener listener){
        super.addMouseListener(listener);
        synchronized(getTreeLock()){
            int i = getComponentCount() - 1;
            if(i < 0) return;
            for(; i >= 0; i--){
                Component c = getComponent(i);
                if(c == null) continue;
                if(c instanceof InnerListener innerListener){
                    innerListener.addInnerListeners(listener);
                }else{
                    c.addMouseListener(listener);
                }
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        if(!rounded){
            super.paint(g);
            return;
        }
        
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        g2.setColor(getBackground());
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
        
        super.paint(g);
    }

}

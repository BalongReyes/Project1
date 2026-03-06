
package FrameSystem.SComponents;

import EventSystem.Interface.InnerListener;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicScrollBarUI;

@JavaBean(description = "A component with a custom jscrollbar")
public class SScrollPane extends JScrollPane{

    private boolean hovering = false;
    
// Constructor ===============================================================================================
    
    public SScrollPane(){
        this.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        setBorder(null);
        
        JScrollBar sb = getVerticalScrollBar();
        sb.setUnitIncrement(10);
        sb.setBackground(scrollbarBackgroundColor);
        sb.setUI(scrollbarUI);
        sb.addMouseListener(listener);
        addMouseListener(listener);
    }
    
// Main Methods ==============================================================================================

    public void applyInnerListeners(){
        JViewport viewPort = getViewport();
        viewPort.addMouseListener(listener);
        synchronized(viewPort.getTreeLock()){
            int i = viewPort.getComponentCount() - 1;
            if(i < 0) return;
            for(; i >= 0; i--){
                Component c = viewPort.getComponent(i);
                if(c == null) continue;
                if(c instanceof InnerListener innerListener){
                    innerListener.addInnerListeners(listener);
                }else{
                    c.addMouseListener(listener);
                }
            }
        }
    }
    
    public void addInnerListeners(InnerListener n){
        n.addInnerListeners(listener);
    }

    public MouseAdapter getListener(){
        return listener;
    }
    
    private MouseAdapter listener = new MouseAdapter() {
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
    
    private ScrollBarUI scrollbarUI = new ScrollBarUI(){
        @Override
        protected void configureScrollBarColors() {
            this.scrollBarWidth = 7;
        }

        @Override
        public void paint(Graphics g, JComponent c){
            paintTrack(g, c, getTrackBounds());
            Rectangle thumbBounds = getThumbBounds();
            if (thumbBounds.intersects(g.getClipBounds())) {
                paintThumb(g, c, thumbBounds);
            }
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
            if(thumbBounds.isEmpty() || !scrollbar.isEnabled()){
                return;
            }

            int w = thumbBounds.width;
            int h = thumbBounds.height;

            g.translate(thumbBounds.x, thumbBounds.y);

            Graphics2D g2 = CustomGraphics.getGraphics2D(g);

            if(hovering){
                g2.setColor(hoverColor);
            }else{
                g2.setColor(defaultColor);
            }
            g2.fillRoundRect(2, 0, w - 1 - 2, h - 2, 3, 3);

            g.translate(-thumbBounds.x, -thumbBounds.y);
        }
    };

// Scroll Key ================================================================================================
    
    private int scrollBarUpperCap = 0, scrollBarLowerCap = 0;
    private int scrollBarVisibleCount = 0, scrollBarMod = 0;
    
    public void scrollToObjectContent(int n){
        int scrollbarValue = -1;
        if(n < scrollBarUpperCap){
            scrollbarValue = ((n - 1) * 60);
        }else if(n > scrollBarLowerCap){
            scrollbarValue = ((n - scrollBarVisibleCount) * 60) - scrollBarMod;
        }

        if(scrollbarValue != -1){
            this.getVerticalScrollBar().setValue(scrollbarValue);
        }
    }
    
// Setters and Getters =======================================================================================
    
    public void setObjectContentHeight(int objectContentHeight){
        this.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            if(objectContentHeight == -1) return;
        
            double v1 = (double)this.getVerticalScrollBar().getValue();
            scrollBarUpperCap = (int)Math.ceil(v1 / objectContentHeight);
            scrollBarUpperCap += 1;

            int scrollBarHeight = this.getSize().height;
            scrollBarMod = scrollBarHeight % objectContentHeight;
            scrollBarVisibleCount = (int)Math.floor(scrollBarHeight / objectContentHeight);

            double v2 = v1 + this.getSize().height;
            scrollBarLowerCap = (int)Math.floor(v2 / objectContentHeight);
        });
        
        // To prevent scrolling by arrow key
        AbstractAction nullAction = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){}
        };
        this.getActionMap().put("unitScrollUp", nullAction);
        this.getActionMap().put("unitScrollDown", nullAction);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color defaultColor = Color.white;
    
    @Override
    public void setBackground(Color bg){
        super.setBackground(bg);
        defaultColor = bg;
    }

// -----------------------------------------------------------------------------------------------------------
    
    private Color hoverColor = Color.white;
    
    @BeanProperty(preferred = true, description = "The hover color")
    public void setHoverColor(Color hoverColor){
        this.hoverColor = hoverColor;
    }

    public Color getHoverColor(){
        return hoverColor;
    }

// -----------------------------------------------------------------------------------------------------------
    
    private Color scrollbarBackgroundColor = new Color(31, 32, 41);
    
    @BeanProperty(preferred = true, description = "background color of scrollbar")
    public void setScrollbarBackgroundColor(Color scrollbarBackgroundColor){
        this.scrollbarBackgroundColor = scrollbarBackgroundColor;
        getVerticalScrollBar().setBackground(scrollbarBackgroundColor);
    }

    public Color getScrollbarBackgroundColor(){
        return scrollbarBackgroundColor;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private boolean scrollbarRTL = false;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "Set panel active")
    public void setScrollbarRTL(boolean scrollbarRTL){
        this.scrollbarRTL = scrollbarRTL;
        if(scrollbarRTL){
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }else{
            setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
    }

    public boolean isScrollbarRTL(){
        return scrollbarRTL;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    @Override
    @BeanProperty(hidden = true)
    public void setToolTipText(String text){
        super.setToolTipText(text);
    }
    
}

// Classes ===================================================================================================

class ScrollBarUI extends BasicScrollBarUI{

    @Override 
    protected JButton createDecreaseButton(int orientation){
        return new ZeroSizeButton();
    }

    @Override 
    protected JButton createIncreaseButton(int orientation){
        return new ZeroSizeButton();
    }

}

class ZeroSizeButton extends JButton{

    public ZeroSizeButton(){
        setFocusable(false);
    }
    
    @Override 
    public Dimension getPreferredSize() {
        return new Dimension();
    }
    
}
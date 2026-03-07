package FrameSystem.SideBarGroup.Components;

import FrameSystem.SAbstractComponents.SLayerButton;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@JavaBean(description = "A component that displays a jpanel as a layered panel button")
public class SideBarButton extends SLayerButton{

    public SideBarButton(){
        super();
        super.setRounded(true);
        
        initComponents();
        sLabel1.addMouseListener(hoverListener);
    }
    
// Setters and Getters =======================================================================================

    @Override
    public void setActive(boolean active){
        if(this.active == active) return;
        if(active){
            sLabel1.setForeground(activeForegroundColor);
            sLabel1.setIcon(activeIcon);
        }else{
            sLabel1.setForeground(inactiveForegroundColor);
            sLabel1.setIcon(inactiveIcon);
        }
        super.setActive(active);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color activeLineColor = new Color(255, 255, 255);

    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setActiveLineColor(Color activeLineColor){
        this.activeLineColor = activeLineColor;
    }

    public Color getActiveLineColor(){
        return activeLineColor;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color activeForegroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The active foreground color")
    public void setActiveForegroundColor(Color activeForegroundColor){
        this.activeForegroundColor = activeForegroundColor;
        if(active){
            setForeground(activeForegroundColor);
            sLabel1.setForeground(activeForegroundColor);
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
            sLabel1.setForeground(inactiveForegroundColor);
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
    
// -----------------------------------------------------------------------------------------------------------
    
    private Icon activeIcon = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The active icon")
    public void setActiveIcon(ImageIcon activeIcon){
        Image resizedActiveIcon = activeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        this.activeIcon = new ImageIcon(resizedActiveIcon);
        if(active){
            sLabel1.setIcon(this.activeIcon);
        }
    }

    public Icon getActiveIcon(){
        return activeIcon;
    }
    
    private Icon inactiveIcon = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The inactive icon")
    public void setInactiveIcon(ImageIcon inactiveIcon){
        Image resizedInactiveIcon = inactiveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        this.inactiveIcon = new ImageIcon(resizedInactiveIcon);
        if(!active){
            sLabel1.setIcon(this.inactiveIcon);
        }
    }

    public Icon getInactiveIcon(){
        return inactiveIcon;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    public String text = "";

    @BeanProperty(preferred = true, visualUpdate = true, description = "The text of panel")
    public void setText(String text){
        this.text = text;
        sLabel1.setText(text);
    }

    public String getText(){
        return text;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private SideBarMinButton minButton = null;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The minimize button of itself")
    public void setSideBarMinButton(SideBarMinButton minButton){
        this.minButton = minButton;
        if(layerPanelMouseListener != null){
            minButton.addLayeredPanelMouseListener(layerPanelMouseListener);
        }
    }
    
    public SideBarMinButton getSideBarMinButton(){
        return minButton;
    }
    
// -----------------------------------------------------------------------------------------------------------

    @Override
    @BeanProperty(hidden = true)
    public void setRounded(boolean rounded){
        super.setRounded(rounded);
    }
    
// ===========================================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sLabel1 = new FrameSystem.SComponents.SLabel();

        setMinimumSize(new java.awt.Dimension(0, 45));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(200, 40));

        sLabel1.setForeground(new java.awt.Color(255, 255, 255));
        sLabel1.setText(text);
        sLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sLabel1.setIconTextGap(18);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

// Overrided Methods =========================================================================================
    
    private MouseListener layerPanelMouseListener = null;
    
    @Override
    public void addLayeredPanelMouseListener(MouseListener m){
        this.layerPanelMouseListener = m;
        if(minButton != null){
            minButton.addLayeredPanelMouseListener(m);
        }
        
        addMouseListener(m);
        sLabel1.addMouseListener(m);
    }

    @Override
    public void setActiveButton(boolean active){
        setActive(active);
        if(minButton != null) minButton.setActiveButton(active);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();

        super.paint(g);
        
        if(active){
            g2.setColor(activeLineColor);
            g2.fillRoundRect(s.width - 10, 9, 4, (s.height - 18), 4, 4);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabel sLabel1;
    // End of variables declaration//GEN-END:variables

}

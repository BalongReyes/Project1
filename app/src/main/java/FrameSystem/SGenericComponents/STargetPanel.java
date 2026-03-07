package FrameSystem.SGenericComponents;

import FrameSystem.SComponents.SPanelHover;
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
import javax.swing.JPanel;

@JavaBean(description = "A SPanel that targets a SPanel for hovering")
public class STargetPanel extends SPanelHover{

    public STargetPanel(){
        super();
        
        initComponents();
        sLabel1.addMouseListener(hoverListener);
    }

// Setters and Getters =======================================================================================
    
    protected Color inactiveBackgroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The inactive background color")
    public void setInactiveBackgroundColor(Color inactiveBackgroundColor){
        this.inactiveBackgroundColor = inactiveBackgroundColor;
    }

    public Color getInactiveBackgroundColor(){
        return inactiveBackgroundColor;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private JPanel targetPanel;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The target panel")
    public void setTargetPanel(JPanel targetPanel){
        this.targetPanel = targetPanel;
        targetPanel.addMouseListener(hoverListener);
    }

    public JPanel getTargetPanel(){
        return targetPanel;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private ImageIcon activeIcon = null;
    private ImageIcon scaledActiveIcon = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The active icon")
    public void setActiveIcon(ImageIcon activeIcon){
        this.activeIcon = activeIcon;
        scaledActiveIcon = activeIcon;
        if(iconSize > 0){
            scaledActiveIcon = new ImageIcon(activeIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        }
        if(hovering) sLabel1.setIcon(scaledActiveIcon);
    }

    public Icon getActiveIcon(){
        return activeIcon;
    }
    
    private ImageIcon inactiveIcon = null;
    private ImageIcon scaledInactiveIcon = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The inactive icon")
    public void setInactiveIcon(ImageIcon inactiveIcon){
        this.inactiveIcon = inactiveIcon;
        scaledInactiveIcon = inactiveIcon;
        if(iconSize > 0){
            scaledInactiveIcon = new ImageIcon(inactiveIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        }
        if(!hovering) sLabel1.setIcon(scaledInactiveIcon);
    }

    public Icon getInactiveIcon(){
        return inactiveIcon;
    }
    
    private int iconSize = 16;

    @BeanProperty(preferred = true, visualUpdate = true, description = "Size of scaled icon")
    public void setIconSize(int iconSize){
        this.iconSize = iconSize;
        if(activeIcon != null){
            scaledActiveIcon = activeIcon;
            if(iconSize > 0){
                scaledActiveIcon = new ImageIcon(activeIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            }
            if(hovering) sLabel1.setIcon(scaledActiveIcon);
        }
        if(inactiveIcon != null){
            scaledInactiveIcon = inactiveIcon;
            if(iconSize > 0){
                scaledInactiveIcon = new ImageIcon(inactiveIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            }
            if(!hovering) sLabel1.setIcon(scaledInactiveIcon);
        }
    }

    public int getIconSize(){
        return iconSize;
    }
    
// -----------------------------------------------------------------------------------------------------------

    @Override
    public synchronized void addMouseListener(MouseListener l){
        sLabel1.addMouseListener(l);
        if(targetPanel != null) targetPanel.addMouseListener(l);
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void setHovering(boolean hovering){
        if(this.hovering == hovering) return;
        if(hovering){
            sLabel1.setIcon(scaledActiveIcon);
        }else{
            sLabel1.setIcon(scaledInactiveIcon);
        }
        super.setHovering(hovering);
    }

// -----------------------------------------------------------------------------------------------------------
    
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
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        if(hovering){
            g2.setColor(hoverBackgroundColor);
        }else{
            g2.setColor(inactiveBackgroundColor);
        }
        g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
        
        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, s.width - 2, s.height - 2, getRadius(-1), getRadius(-1));
        
        super.paintOverrideAll(g);
    }
    
// Generated =================================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sLabel1 = new FrameSystem.SComponents.SLabel();

        sLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel1.setPreferredSize(new java.awt.Dimension(12, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabel sLabel1;
    // End of variables declaration//GEN-END:variables
}

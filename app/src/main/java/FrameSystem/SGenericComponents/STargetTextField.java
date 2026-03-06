package FrameSystem.SGenericComponents;

import EventSystem.Listeners.MouseClickedAdaptor;
import FrameSystem.SComponents.SPanelActivatable;
import MainSystem.CustomGraphics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.beans.BeanProperty;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class STargetTextField extends SPanelActivatable{

    public FocusListener focusListener;
    public MouseListener mouseListener;
    
    public STargetTextField(){
        super();
        
        initComponents();
        setOpaque(false);
        
        addMouseListener((MouseClickedAdaptor) evt -> {
            if(targetTextField != null) targetTextField.requestFocus();
        });
        
        focusListener = new FocusListener(){
            @Override
            public void focusGained(FocusEvent evt){
                setActive(true);
            }
            @Override
            public void focusLost(FocusEvent evt){
                setActive(false);
            }
        };
    }

// Setters and Getters =======================================================================================
    
    @Override
    @BeanProperty(preferred = true, visualUpdate = true, description = "If panel is active")
    public void setActive(boolean active){
        if(this.active == active) return;
        if(active){
            if(scaledActiveIcon != null) sLabel1.setIcon(scaledActiveIcon);
        }else{
            if(scaledInactiveIcon != null) sLabel1.setIcon(scaledInactiveIcon);
        }
        super.setActive(active);
    }

    private JTextField targetTextField = null;

    @BeanProperty(preferred = true, description = "The target textfield")
    public void setTargetTextField(JTextField targetTextField){
        if(this.targetTextField != null || targetTextField == null) return;
        this.targetTextField = targetTextField;
        this.targetTextField.addFocusListener(focusListener);
    }

    public JTextField getTargetTextField(){
        return targetTextField;
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
        if(active) sLabel1.setIcon(scaledActiveIcon);
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
        if(!active) sLabel1.setIcon(scaledInactiveIcon);
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
            if(active) sLabel1.setIcon(scaledActiveIcon);
        }
        if(inactiveIcon != null){
            scaledInactiveIcon = inactiveIcon;
            if(iconSize > 0){
                scaledInactiveIcon = new ImageIcon(inactiveIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            }
            if(!active) sLabel1.setIcon(scaledInactiveIcon);
        }
    }

    public int getIconSize(){
        return iconSize;
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        if(active){
            g2.setColor(activeBackgroundColor);
        }else{
            g2.setColor(inactiveBackgroundColor);
        }
        g2.fillRoundRect(0, 0, s.width, s.height, getRadius(), getRadius());
        
        g2.setColor(getBackground());
        g2.fillRoundRect(1, 1, s.width - 2, s.height - 2, getRadius() - 1, getRadius() - 1);
        
        super.paintOverrideAll(g);
    }

// Generated =================================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sLabel1 = new FrameSystem.SComponents.SLabel();

        sLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabel sLabel1;
    // End of variables declaration//GEN-END:variables
}

package FrameSystem.SideBarGroup.Components;

import FrameSystem.SComponents.SPanelActivatableHover;
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
public class SideBarSwitch extends SPanelActivatableHover{

    public SideBarSwitch(){
        super();
        super.setRounded(true);
        
        initComponents();
        sLabel1.addMouseListener(hoverListener);
    }
    
// Setters and Getters =======================================================================================

    private Color foregroundColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The foreground color")
    public void setForegroundColor(Color foregroundColor){
        this.foregroundColor = foregroundColor;
        setForeground(foregroundColor);
    }

    public Color getForegroundColor(){
        return foregroundColor;
    }

    @Override
    @BeanProperty(hidden = true)
    public void setForeground(Color fg){
        super.setForeground(fg);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color switchInactiveColor = Color.white;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The switch inactive color")
    public void setSwitchInactiveColor(Color switchInactiveColor){
        this.switchInactiveColor = switchInactiveColor;
    }

    public Color getSwitchInactiveColor(){
        return switchInactiveColor;
    }
    
    private Color switchActiveColor = Color.white;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The switch active color")
    public void setSwitchActiveColor(Color switchActiveColor){
        this.switchActiveColor = switchActiveColor;
    }

    public Color getSwitchActiveColor(){
        return switchActiveColor;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Icon icon = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "The icon")
    public void setIcon(ImageIcon icon){
        Image resizedIcon = icon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        this.icon = new ImageIcon(resizedIcon);
        sLabel1.setIcon(this.icon);
    }

    public Icon getIcon(){
        return icon;
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

// Overrided Methods =========================================================================================
    
    @Override
    public final void addMouseListener(MouseListener l){
        super.addMouseListener(l);
        sLabel1.addMouseListener(l);
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        super.paint(g);
        
        if(active){
            g2.setColor(switchActiveColor);
            g2.fillRoundRect(s.width - 40, (s.height / 2) - 7, 25, 14, 14, 14);
            g2.setColor(Color.white);
            g2.fillRoundRect(s.width - 26, (s.height / 2) - 4, 8, 8, 8, 8);
        }else{
            g2.setColor(switchInactiveColor);
            g2.fillRoundRect(s.width - 40, (s.height / 2) - 7, 25, 14, 14, 14);
            g2.setColor(Color.white);
            g2.fillRoundRect(s.width - 37, (s.height / 2) - 4, 8, 8, 8, 8);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabel sLabel1;
    // End of variables declaration//GEN-END:variables
}

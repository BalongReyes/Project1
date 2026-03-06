package FrameSystem.SGenericComponents;

import FrameSystem.SComponents.SPanelHover;
import java.awt.event.MouseListener;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.ImageIcon;

@JavaBean(description = "")
public class SFilterTitlePanel extends SPanelHover{

    public SFilterTitlePanel(){
        super();
        initComponents();
        
        sLabel1.addMouseListener(hoverListener);
        sLabel2.addMouseListener(hoverListener);
    }
    
// Setters and Getters =======================================================================================

    private String text = "";

    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setText(String text){
        this.text = text;
        sLabel1.setText(text);
    }

    public String getText(){
        return text;
    }
    
    private int arrowDirection = -1;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setArrowDirection(int n){
        arrowDirection = n;
        switch(n){
            case 1 -> { // down
                sLabel2.setScaledIcon(new ImageIcon(getClass().getResource("/Icons/arrowDown.png")));
            }
            case 2 -> { // up
                sLabel2.setScaledIcon(new ImageIcon(getClass().getResource("/Icons/arrowUp.png")));
            }
            default -> {
                sLabel2.setScaledIcon(new ImageIcon(getClass().getResource("/Icons/arrowNeutral.png")));
                arrowDirection = -1;
            }
        }
    }

    public int getArrowDirection(){
        return arrowDirection;
    }
    
    public int setNextArrowDirection(){
        switch(arrowDirection){
            case 1 -> {
                setArrowDirection(2);
                return 2;
            }
            case 2 -> {
                setArrowDirection(-1);
                return -1;
            }
            default -> {
                setArrowDirection(1);
                return 1;
            }
        }
    }
    
// Overrided Methods =========================================================================================

    @Override
    public synchronized void addMouseListener(MouseListener l){
        addInnerListeners(l);
    }
    
// Generated =================================================================================================

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sLabel1 = new FrameSystem.SComponents.SLabel();
        sLabel2 = new FrameSystem.SComponents.SLabel();

        sLabel1.setForeground(new java.awt.Color(255, 255, 255));
        sLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sLabel1.setText("test");
        sLabel1.setPreferredSize(new java.awt.Dimension(0, 30));

        sLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel2.setIconSize(20);
        sLabel2.setScaledIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/arrowNeutral.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabel sLabel1;
    private FrameSystem.SComponents.SLabel sLabel2;
    // End of variables declaration//GEN-END:variables
}

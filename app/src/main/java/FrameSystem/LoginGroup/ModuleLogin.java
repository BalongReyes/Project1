package FrameSystem.LoginGroup;

import FrameSystem.SComponents.SPanel;

public class ModuleLogin extends SPanel{

    public ModuleLogin(){
        initComponents();
    }
    
// Generated =================================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrameSystem.SComponents.SPanel sPanelRIght = new FrameSystem.SComponents.SPanel();
        FrameSystem.SComponents.SPanel sPanelLeft = new FrameSystem.SComponents.SPanel();
        FrameSystem.SComponents.SLabel sLabel25 = new FrameSystem.SComponents.SLabel();

        setBackground(new java.awt.Color(255, 102, 102));
        setRadius(20);
        setRounded(true);
        setMinimumSize(new java.awt.Dimension(820, 530));
        setPreferredSize(new java.awt.Dimension(820, 530));

        sPanelRIght.setBackground(new java.awt.Color(255, 255, 255));
        sPanelRIght.setRadius(20);
        sPanelRIght.setRounded(true);

        javax.swing.GroupLayout sPanelRIghtLayout = new javax.swing.GroupLayout(sPanelRIght);
        sPanelRIght.setLayout(sPanelRIghtLayout);
        sPanelRIghtLayout.setHorizontalGroup(
            sPanelRIghtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );
        sPanelRIghtLayout.setVerticalGroup(
            sPanelRIghtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        sPanelLeft.setOpaque(false);

        sLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/mainSmallLogo.png"))); // NOI18N

        javax.swing.GroupLayout sPanelLeftLayout = new javax.swing.GroupLayout(sPanelLeft);
        sPanelLeft.setLayout(sPanelLeftLayout);
        sPanelLeftLayout.setHorizontalGroup(
            sPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanelLeftLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sPanelLeftLayout.setVerticalGroup(
            sPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanelLeftLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(sPanelRIght, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sPanelRIght, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

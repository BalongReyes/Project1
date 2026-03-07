package FrameSystem.HeroGroup.Dialogs;

import FrameSystem.SComponents.SDialog;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class HeroDialogDiscard extends SDialog{

// Constructor ===============================================================================================
    
    public HeroDialogDiscard(JFrame parent){
        super(parent);
        initComponents();
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent evt) -> {
            if(evt.getID() == KeyEvent.KEY_PRESSED) switch(evt.getKeyCode()){
                case KeyEvent.VK_ESCAPE, KeyEvent.VK_HOME -> {
                    buttonCancelMousePressed(null);
                }
            }
            return false;
        });
        
        setVisible();
    }

// Generated =================================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sPanel1 = new FrameSystem.SComponents.SPanel();
        sLabel1 = new FrameSystem.SComponents.SLabel();
        sLabel2 = new FrameSystem.SComponents.SLabel();
        buttonCancel = new FrameSystem.SComponents.SLabelHover();
        buttonConfirm = new FrameSystem.SComponents.SLabelHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Discard Items");
        setResizable(false);

        sPanel1.setBackground(new java.awt.Color(24, 29, 37));

        sLabel1.setForeground(new java.awt.Color(255, 255, 255));
        sLabel1.setText("Confirm Discard Items");
        sLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        sLabel2.setForeground(new java.awt.Color(255, 255, 255));
        sLabel2.setText("Are you sure you want to discard all items");
        sLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        sLabel2.setPreferredSize(new java.awt.Dimension(227, 27));

        buttonCancel.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonCancel.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonCancel.setRadius(15);
        buttonCancel.setText("Cancel");
        buttonCancel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonCancel.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonCancelMousePressed(evt);
            }
        });

        buttonConfirm.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonConfirm.setForeground(new java.awt.Color(255, 255, 255));
        buttonConfirm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonConfirm.setHoverColor(new java.awt.Color(173, 0, 0));
        buttonConfirm.setRadius(15);
        buttonConfirm.setText("Confirm");
        buttonConfirm.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonConfirm.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonConfirmMousePressed(evt);
            }
        });

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sPanel1Layout.createSequentialGroup()
                                .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE))
                            .addComponent(sLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMousePressed
        confirmed = false;
        this.dispose();
    }//GEN-LAST:event_buttonCancelMousePressed

    private void buttonConfirmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConfirmMousePressed
        confirmed = true;
        this.dispose();
    }//GEN-LAST:event_buttonConfirmMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabelHover buttonCancel;
    private FrameSystem.SComponents.SLabelHover buttonConfirm;
    private FrameSystem.SComponents.SLabel sLabel1;
    private FrameSystem.SComponents.SLabel sLabel2;
    private FrameSystem.SComponents.SPanel sPanel1;
    // End of variables declaration//GEN-END:variables
}

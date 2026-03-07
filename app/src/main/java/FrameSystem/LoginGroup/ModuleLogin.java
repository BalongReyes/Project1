package FrameSystem.LoginGroup;

import FrameSystem.LoginGroup.Components.LoginPanelContainer;
import java.awt.Color;
import java.beans.BeanProperty;


public class ModuleLogin extends LoginPanelContainer {

    public ModuleLogin() {
        initComponents();
    }

// Overrided Methods =========================================================================================
    @Override
    @BeanProperty(hidden = true)
    public void setBackground(Color color) {
        super.setBackground(color);
    }

// Generated =================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrameSystem.SComponents.SPanel sPanelLeft = new FrameSystem.SComponents.SPanel();
        FrameSystem.SComponents.SLabel sLabel25 = new FrameSystem.SComponents.SLabel();
        FrameSystem.LoginGroup.Components.LoginPanel sPanelRight = new FrameSystem.LoginGroup.Components.LoginPanel();
        FrameSystem.SComponents.SLabel sLabel1 = new FrameSystem.SComponents.SLabel();
        FrameSystem.SComponents.SLabel sLabel2 = new FrameSystem.SComponents.SLabel();
        FrameSystem.SComponents.SLabel loginError1 = new FrameSystem.SComponents.SLabel();
        FrameSystem.LoginGroup.Components.LoginTextFieldContainer loginTextFieldContainer1 = new FrameSystem.LoginGroup.Components.LoginTextFieldContainer();
        loginUsernameField = new FrameSystem.SComponents.STextField();
        FrameSystem.SComponents.SLabel sLabel4 = new FrameSystem.SComponents.SLabel();
        loginError2 = new FrameSystem.SComponents.SLabel();
        loginTextFieldContainer2 = new FrameSystem.LoginGroup.Components.LoginTextFieldContainer();
        loginPasswordField = new FrameSystem.SComponents.SPasswordField();
        FrameSystem.SGenericComponents.STogglePassword sTogglePassword1 = new FrameSystem.SGenericComponents.STogglePassword();
        loginError3 = new FrameSystem.SComponents.SLabel();
        loginButton = new FrameSystem.SComponents.SLabelHover();

        setBackground(new java.awt.Color(255, 209, 209));
        setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/Icons/wave.png"))); // NOI18N
        setRadius(30);
        setRounded(true);
        setMinimumSize(new java.awt.Dimension(820, 530));
        setPreferredSize(new java.awt.Dimension(820, 530));

        sPanelLeft.setOpaque(false);

        sLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/mainSmallLogo.png"))); // NOI18N

        javax.swing.GroupLayout sPanelLeftLayout = new javax.swing.GroupLayout(sPanelLeft);
        sPanelLeft.setLayout(sPanelLeftLayout);
        sPanelLeftLayout.setHorizontalGroup(
            sPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanelLeftLayout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addComponent(sLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        sPanelLeftLayout.setVerticalGroup(
            sPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanelLeftLayout.createSequentialGroup()
                .addGap(0, 197, Short.MAX_VALUE)
                .addComponent(sLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 270, Short.MAX_VALUE))
        );

        sPanelRight.setBackground(new java.awt.Color(255, 255, 255));
        sPanelRight.setLine(new java.awt.Color(205, 64, 68));
        sPanelRight.setRadius(30);
        sPanelRight.setRounded(true);

        sLabel1.setForeground(new java.awt.Color(0, 0, 0));
        sLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel1.setText("Login to your account");
        sLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        sLabel2.setForeground(new java.awt.Color(56, 56, 56));
        sLabel2.setText("Username");
        sLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        loginError1.setForeground(new java.awt.Color(255, 102, 102));
        loginError1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loginError1.setText("*required");
        loginError1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        loginTextFieldContainer1.setBackground(new java.awt.Color(238, 238, 238));
        loginTextFieldContainer1.setFocusedColor(new java.awt.Color(255, 127, 127));
        loginTextFieldContainer1.setForeground(new java.awt.Color(0, 0, 0));
        loginTextFieldContainer1.setRadius(20);
        loginTextFieldContainer1.setTextFieldChild(loginUsernameField);

        loginUsernameField.setBackground(new java.awt.Color(153, 153, 153));
        loginUsernameField.setForeground(new java.awt.Color(0, 0, 0));
        loginUsernameField.setHint("Enter Username");
        loginUsernameField.setHintColor(new java.awt.Color(102, 102, 102));
        loginUsernameField.setHintOffest(26);
        loginUsernameField.setCaretColor(new java.awt.Color(0, 0, 0));
        loginUsernameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout loginTextFieldContainer1Layout = new javax.swing.GroupLayout(loginTextFieldContainer1);
        loginTextFieldContainer1.setLayout(loginTextFieldContainer1Layout);
        loginTextFieldContainer1Layout.setHorizontalGroup(
            loginTextFieldContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginTextFieldContainer1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(loginUsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        loginTextFieldContainer1Layout.setVerticalGroup(
            loginTextFieldContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginTextFieldContainer1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(loginUsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        sLabel4.setForeground(new java.awt.Color(56, 56, 56));
        sLabel4.setText("Password");
        sLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        loginError2.setForeground(new java.awt.Color(255, 102, 102));
        loginError2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loginError2.setText("*required");
        loginError2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        loginTextFieldContainer2.setBackground(new java.awt.Color(238, 238, 238));
        loginTextFieldContainer2.setFocusedColor(new java.awt.Color(255, 127, 127));
        loginTextFieldContainer2.setForeground(new java.awt.Color(0, 0, 0));
        loginTextFieldContainer2.setRadius(20);
        loginTextFieldContainer2.setTextFieldChild(loginPasswordField);

        loginPasswordField.setEditable(false);
        loginPasswordField.setBackground(new java.awt.Color(153, 153, 153));
        loginPasswordField.setForeground(new java.awt.Color(0, 0, 0));
        loginPasswordField.setHint("Enter Password");
        loginPasswordField.setHintColor(new java.awt.Color(102, 102, 102));
        loginPasswordField.setCaretColor(new java.awt.Color(0, 0, 0));
        loginPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        sTogglePassword1.setDefaultColor(new java.awt.Color(46, 46, 46));
        sTogglePassword1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sTogglePassword1.setHoverColor(new java.awt.Color(54, 54, 54));
        sTogglePassword1.setRadius(31);
        sTogglePassword1.setPreferredSize(new java.awt.Dimension(31, 31));

        javax.swing.GroupLayout loginTextFieldContainer2Layout = new javax.swing.GroupLayout(loginTextFieldContainer2);
        loginTextFieldContainer2.setLayout(loginTextFieldContainer2Layout);
        loginTextFieldContainer2Layout.setHorizontalGroup(
            loginTextFieldContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginTextFieldContainer2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(loginPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sTogglePassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        loginTextFieldContainer2Layout.setVerticalGroup(
            loginTextFieldContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginTextFieldContainer2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(loginPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginTextFieldContainer2Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(sTogglePassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        loginError3.setForeground(new java.awt.Color(255, 102, 102));
        loginError3.setText("Incorrect Password or Username");
        loginError3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        loginButton.setDefaultColor(new java.awt.Color(44, 44, 44));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginButton.setHoverColor(new java.awt.Color(75, 75, 75));
        loginButton.setRadius(10);
        loginButton.setText("Login");
        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        loginButton.setPreferredSize(new java.awt.Dimension(87, 38));

        javax.swing.GroupLayout sPanelRightLayout = new javax.swing.GroupLayout(sPanelRight);
        sPanelRight.setLayout(sPanelRightLayout);
        sPanelRightLayout.setHorizontalGroup(
            sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanelRightLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sPanelRightLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(loginError3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanelRightLayout.createSequentialGroup()
                        .addGroup(sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(sPanelRightLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(loginTextFieldContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(sLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loginTextFieldContainer2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sPanelRightLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(sPanelRightLayout.createSequentialGroup()
                                        .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                                        .addComponent(loginError1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(sPanelRightLayout.createSequentialGroup()
                                        .addComponent(sLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(loginError2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)))
                        .addGap(60, 60, 60))))
        );
        sPanelRightLayout.setVerticalGroup(
            sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sPanelRightLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginError1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(loginTextFieldContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(sPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginError2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(loginTextFieldContainer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(loginError3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(sPanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sPanelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sPanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    FrameSystem.SComponents.SLabelHover loginButton;
    FrameSystem.SComponents.SLabel loginError2;
    FrameSystem.SComponents.SLabel loginError3;
    FrameSystem.SComponents.SPasswordField loginPasswordField;
    FrameSystem.LoginGroup.Components.LoginTextFieldContainer loginTextFieldContainer2;
    FrameSystem.SComponents.STextField loginUsernameField;
    // End of variables declaration//GEN-END:variables
}

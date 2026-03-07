
package MainSystem;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import DatabaseSystem.AccountsData.AccountsDataHandler;
import DatabaseSystem.Database;
import DatabaseSystem.SettingsData.SettingsDataHandler;
import EventSystem.Interface.ReconnectExecute;
import FrameSystem.HeroGroup.Components.HeroLayer;
import FrameSystem.LoginGroup.Managers.ManagerLogin;
import FrameSystem.SComponents.SDialog;
import java.awt.AWTEvent;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class SFrame extends JFrame {

    public SFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Icons/main.png")).getImage());
        
        setLocationRelativeTo(null);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setListeners();
        
        heroMenuPanel.setVisible(false);
        heroMenuPanel1.setVisible(false);
    }

    public void initShowDefaultLayer(){
        try{
            if(Database.getConnection() == null || Database.getConnection().isClosed()){
                offlineMode();
            }else{
                refreshDefaultData();
                HeroLayer.showLayer(heroLayer_Login);
            }
        }catch(SQLException e){
            Console.errorOut("Initialize show default layer", e);
        }
    }
    
    private Timer refreshTimer;
    
    public final void offlineMode(){
//        HeroLayer.showLayer(heroLayer_Offline);
//        if(refreshTimer != null && refreshTimer.isRunning()) return;
//        refreshTimer = new Timer(5000, (evt) -> { // refreshing connection every 5 seconds
//            try{
//                Database.openConnection();
//                if(Database.getConnection() != null && !Database.getConnection().isClosed()){
//                    refreshDefaultData();
//                    HeroLayer.showLayer(heroLayer_Login);
//                    ((Timer)evt.getSource()).stop();
//                }else if(HeroLayer.getCurrentLayeredPanel() != heroLayer_Offline){
//                    HeroLayer.showLayer(heroLayer_Offline);
//                }
//            }catch(SQLException e){
//                Console.errorOut("Offline mode error", e);
//            }
//        });
//        refreshTimer.start();
    }
    
    private volatile ReconnectExecute currentReconnectExecute;
    
    public void reconnectMode(String caller, ReconnectExecute reconnectExecute){
        currentReconnectExecute = reconnectExecute;
        
        if(refreshTimer != null && refreshTimer.isRunning()) return;
        refreshTimer = new Timer(5000, (evt) -> { // refreshing connection every 5 seconds
            try{
                Database.openConnection();
                if(Database.getConnection() != null && !Database.getConnection().isClosed()){
                    if(Main.debugDataHandlerRefresh) Console.line().out("RECONNECTION OF " + caller, ConsoleColors.GREEN);
                    currentReconnectExecute.reconnect();
                    ((Timer)evt.getSource()).stop();
                }
            }catch(SQLException e){
                Console.errorOut("Reconnecting failed", e);
            }
        });
        refreshTimer.start();
    }
    
// Auto Logout -----------------------------------------------------------------------------------------------
    
    private Timer autoLogoutTimer;
    private Integer autoLogoutMinute = null;
    private Integer oldAutoLogoutMinute = null;
    
    public void updateAutoLogout(){
        if(autoLogoutTimer != null && autoLogoutTimer.isRunning()){
            if(oldAutoLogoutMinute.equals(autoLogoutMinute)){
                autoLogoutTimer.restart();
                return;
            }else{
                autoLogoutTimer.stop();
                autoLogoutTimer = null;
            }
        }
        if(autoLogoutMinute == null) return;
        oldAutoLogoutMinute = autoLogoutMinute;
        autoLogoutTimer = new Timer(autoLogoutMinute, (evt) -> {
            ManagerLogin.logoutAccount();
            SDialog.closeByAutoLogout();
            ((Timer)evt.getSource()).stop();
        });
        autoLogoutTimer.start();
    }
    
    public void updateAutoLogout(int minutes){
        autoLogoutMinute = minutes * 60 * 1000;
        updateAutoLogout();
    }
    
    public void stopAutoLogout(){
        if(autoLogoutTimer == null) return;
        
        if(autoLogoutTimer.isRunning()) autoLogoutTimer.stop();
        autoLogoutTimer = null;
        autoLogoutMinute = null;
    }
    
// Methods ===================================================================================================
    
    private void refreshDefaultData(){
        try{
            SettingsDataHandler.refreshData();
        }catch(SQLException e){
            Console.errorOut("Refreshing settings error", e);
        }
        try{
            AccountsDataHandler.refreshData();
        }catch(SQLException e){
            Console.errorOut("Refreshing accounts error", e);
        }
    }
    
    private void setListeners(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent evt) -> {
            if(SFrame.getKeyLock()) return false;
            if(evt.getID() == KeyEvent.KEY_PRESSED){
                HeroLayer.keyPressed(evt);
                switch(evt.getKeyCode()){
                    case KeyEvent.VK_F11 -> {
                        toggleFullscreen();
                    }
                }
            }
            return false;
        });
        
        long eventMask = AWTEvent.MOUSE_EVENT_MASK;
        Toolkit.getDefaultToolkit().addAWTEventListener((evt) -> {
            updateAutoLogout();
        }, eventMask);
    }

    private boolean fullscreen = false;
    
    public void toggleFullscreen(){
        fullscreen = !fullscreen;
        
        dispose();
        setUndecorated(fullscreen);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        repaint();
    }
    
// Static Methods ============================================================================================

    private static boolean keyLock = false;
    
    public static void setKeyLock(boolean keyLock){
        SFrame.keyLock = keyLock;
    }
    
    public static boolean getKeyLock(){
        return keyLock;
    }
    
// Generated =================================================================================================
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heroLayeredPane = new javax.swing.JLayeredPane();
        heroLayer_Login = new FrameSystem.HeroGroup.Components.HeroLayer();
        moduleLogin = new FrameSystem.LoginGroup.ModuleLogin();
        heroMenuPanel = new FrameSystem.HeroGroup.Components.HeroMenuPanel();
        heroMenuPanel1 = new FrameSystem.HeroGroup.Components.HeroMenuPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventory Master");
        setBackground(new java.awt.Color(9, 12, 16));
        setIconImages(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        heroLayeredPane.setLayout(new java.awt.CardLayout());

        heroLayer_Login.setBackground(new java.awt.Color(254, 241, 241));
        heroLayer_Login.setName("Login"); // NOI18N

        moduleLogin.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout heroLayer_LoginLayout = new javax.swing.GroupLayout(heroLayer_Login);
        heroLayer_Login.setLayout(heroLayer_LoginLayout);
        heroLayer_LoginLayout.setHorizontalGroup(
            heroLayer_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroLayer_LoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(moduleLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        heroLayer_LoginLayout.setVerticalGroup(
            heroLayer_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroLayer_LoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(moduleLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        heroLayeredPane.add(heroLayer_Login, "card2");

        heroMenuPanel.setBackground(new java.awt.Color(24, 29, 37));
        heroMenuPanel.setBorderColor(new java.awt.Color(30, 36, 46));
        heroMenuPanel.setBorderLine(2);

        javax.swing.GroupLayout heroMenuPanelLayout = new javax.swing.GroupLayout(heroMenuPanel);
        heroMenuPanel.setLayout(heroMenuPanelLayout);
        heroMenuPanelLayout.setHorizontalGroup(
            heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );
        heroMenuPanelLayout.setVerticalGroup(
            heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
        );

        heroMenuPanel1.setBackground(new java.awt.Color(24, 29, 37));
        heroMenuPanel1.setBorderColor(new java.awt.Color(30, 36, 46));
        heroMenuPanel1.setBorderLine(2);
        heroMenuPanel1.setPreferredSize(new java.awt.Dimension(80, 0));

        javax.swing.GroupLayout heroMenuPanel1Layout = new javax.swing.GroupLayout(heroMenuPanel1);
        heroMenuPanel1.setLayout(heroMenuPanel1Layout);
        heroMenuPanel1Layout.setHorizontalGroup(
            heroMenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        heroMenuPanel1Layout.setVerticalGroup(
            heroMenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(heroMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(heroMenuPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(heroLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1271, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heroLayeredPane)
            .addComponent(heroMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(heroMenuPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.closeConnection();
        ExecutorDriver.closeExecutor();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public FrameSystem.HeroGroup.Components.HeroLayer heroLayer_Login;
    private javax.swing.JLayeredPane heroLayeredPane;
    public FrameSystem.HeroGroup.Components.HeroMenuPanel heroMenuPanel;
    public FrameSystem.HeroGroup.Components.HeroMenuPanel heroMenuPanel1;
    private FrameSystem.LoginGroup.ModuleLogin moduleLogin;
    // End of variables declaration//GEN-END:variables

}

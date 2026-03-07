
package MainSystem;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import DatabaseSystem.Database;
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
        HeroLayer.showLayer(heroLayer_Offline);
        if(refreshTimer != null && refreshTimer.isRunning()) return;
        refreshTimer = new Timer(5000, (evt) -> { // refreshing connection every 5 seconds
            try{
                Database.openConnection();
                if(Database.getConnection() != null && !Database.getConnection().isClosed()){
                    refreshDefaultData();
                    HeroLayer.showLayer(heroLayer_Login);
                    ((Timer)evt.getSource()).stop();
                }else if(HeroLayer.getCurrentLayeredPanel() != heroLayer_Offline){
                    HeroLayer.showLayer(heroLayer_Offline);
                }
            }catch(SQLException e){
                Console.errorOut("Offline mode error", e);
            }
        });
        refreshTimer.start();
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

        sideBarSwitch1.setActive(fullscreen);
        sideBarMinSwitch1.setActive(fullscreen);
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
        heroLayer_Offline = new FrameSystem.HeroGroup.Components.HeroLayer();
        mainOfflinePanelContainer1 = new FrameSystem.MainOfflineGroup.Components.MainOfflinePanelContainer();
        mainOfflinePanel1 = new FrameSystem.MainOfflineGroup.Components.MainOfflinePanel();
        sLabel3 = new FrameSystem.SComponents.SLabel();
        jPanel13 = new javax.swing.JPanel();
        sLabel25 = new FrameSystem.SComponents.SLabel();
        jPanel14 = new javax.swing.JPanel();
        sLabel26 = new FrameSystem.SComponents.SLabel();
        sLabel27 = new FrameSystem.SComponents.SLabel();
        heroLayer_Login = new FrameSystem.HeroGroup.Components.HeroLayer();
        loginPanelContainer1 = new FrameSystem.LoginGroup.Components.LoginPanelContainer();
        loginPanel1 = new FrameSystem.LoginGroup.Components.LoginPanel();
        sLabel1 = new FrameSystem.SComponents.SLabel();
        sLabel2 = new FrameSystem.SComponents.SLabel();
        loginError1 = new FrameSystem.SComponents.SLabel();
        loginTextFieldContainer1 = new FrameSystem.LoginGroup.Components.LoginTextFieldContainer();
        loginUsernameField = new FrameSystem.SComponents.STextField();
        sLabel4 = new FrameSystem.SComponents.SLabel();
        loginError2 = new FrameSystem.SComponents.SLabel();
        loginTextFieldContainer2 = new FrameSystem.LoginGroup.Components.LoginTextFieldContainer();
        loginPasswordField = new FrameSystem.SComponents.SPasswordField();
        sTogglePassword1 = new FrameSystem.SGenericComponents.STogglePassword();
        loginError3 = new FrameSystem.SComponents.SLabel();
        loginButton = new FrameSystem.SComponents.SLabelHover();
        jPanel1 = new javax.swing.JPanel();
        sLabel7 = new FrameSystem.SComponents.SLabel();
        jPanel2 = new javax.swing.JPanel();
        sLabel8 = new FrameSystem.SComponents.SLabel();
        sLabel9 = new FrameSystem.SComponents.SLabel();
        heroMenuPanel = new FrameSystem.HeroGroup.Components.HeroMenuPanel();
        sLabel5 = new FrameSystem.SComponents.SLabel();
        sLabel6 = new FrameSystem.SComponents.SLabel();
        sLabel10 = new FrameSystem.SComponents.SLabel();
        sLabel11 = new FrameSystem.SComponents.SLabel();
        sPanel1 = new FrameSystem.SComponents.SPanel();
        sScrollPane1 = new FrameSystem.SComponents.SScrollPane();
        sScrollPane1Container = new FrameSystem.SComponents.SPanel();
        sScrollPane1Wrapper = new FrameSystem.SComponents.SPanel();
        sLabel28 = new FrameSystem.SComponents.SLabel();
        sideBarButton2 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sLabel14 = new FrameSystem.SComponents.SLabel();
        sideBarButton3 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sideBarButton4 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sideBarButton5 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sLabel20 = new FrameSystem.SComponents.SLabel();
        sideBarButton6 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sideBarButton7 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sLabel22 = new FrameSystem.SComponents.SLabel();
        sideBarButton9 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sideBarButton11 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sideBarButton8 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sPanel3 = new FrameSystem.SComponents.SPanel();
        sLabel12 = new FrameSystem.SComponents.SLabel();
        sPanel2 = new FrameSystem.SComponents.SPanel();
        logoutButton = new FrameSystem.SComponents.SLabelHover();
        userName = new FrameSystem.SComponents.SLabel();
        userRole = new FrameSystem.SComponents.SLabel();
        sLabelHover1 = new FrameSystem.SComponents.SLabelHover();
        sideBarButton10 = new FrameSystem.SideBarGroup.Components.SideBarButton();
        sideBarSwitch1 = new FrameSystem.SideBarGroup.Components.SideBarSwitch();
        heroMenuPanel1 = new FrameSystem.HeroGroup.Components.HeroMenuPanel();
        sLabel29 = new FrameSystem.SComponents.SLabel();
        sPanel14 = new FrameSystem.SComponents.SPanel();
        sScrollPane2 = new FrameSystem.SComponents.SScrollPane();
        sScrollPane2Container = new FrameSystem.SComponents.SPanel();
        sScrollPane2Wrapper = new FrameSystem.SComponents.SPanel();
        sPanel23 = new FrameSystem.SComponents.SPanel();
        sPanel24 = new FrameSystem.SComponents.SPanel();
        sPanel27 = new FrameSystem.SComponents.SPanel();
        sideBarMinButton2 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton3 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton4 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton5 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton6 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton7 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton9 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton11 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinButton8 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sPanel21 = new FrameSystem.SComponents.SPanel();
        sPanel20 = new FrameSystem.SComponents.SPanel();
        logoutButton1 = new FrameSystem.SComponents.SLabelHover();
        sLabelHover2 = new FrameSystem.SComponents.SLabelHover();
        sideBarMinButton10 = new FrameSystem.SideBarGroup.Components.SideBarMinButton();
        sideBarMinSwitch1 = new FrameSystem.SideBarGroup.Components.SideBarMinSwitch();

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

        heroLayer_Offline.setBackground(new java.awt.Color(9, 12, 16));
        heroLayer_Offline.setName("Offline"); // NOI18N

        mainOfflinePanelContainer1.setBackground(new java.awt.Color(24, 29, 37));
        mainOfflinePanelContainer1.setLine(new java.awt.Color(168, 232, 254));
        mainOfflinePanelContainer1.setRadius(20);
        mainOfflinePanelContainer1.setRounded(true);
        mainOfflinePanelContainer1.setPreferredSize(new java.awt.Dimension(820, 530));

        mainOfflinePanel1.setBackground(new java.awt.Color(24, 29, 37));
        mainOfflinePanel1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/Icons/wave.png"))); // NOI18N
        mainOfflinePanel1.setRadius(20);
        mainOfflinePanel1.setRounded(true);
        mainOfflinePanel1.setPreferredSize(new java.awt.Dimension(502, 530));

        sLabel3.setForeground(new java.awt.Color(255, 255, 255));
        sLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel3.setText("Database is offline");
        sLabel3.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N

        javax.swing.GroupLayout mainOfflinePanel1Layout = new javax.swing.GroupLayout(mainOfflinePanel1);
        mainOfflinePanel1.setLayout(mainOfflinePanel1Layout);
        mainOfflinePanel1Layout.setHorizontalGroup(
            mainOfflinePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainOfflinePanel1Layout.createSequentialGroup()
                .addGap(0, 67, Short.MAX_VALUE)
                .addComponent(sLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addGap(0, 69, Short.MAX_VALUE))
        );
        mainOfflinePanel1Layout.setVerticalGroup(
            mainOfflinePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainOfflinePanel1Layout.createSequentialGroup()
                .addGap(0, 245, Short.MAX_VALUE)
                .addComponent(sLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 245, Short.MAX_VALUE))
        );

        jPanel13.setOpaque(false);
        jPanel13.setRequestFocusEnabled(false);

        sLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/main2.png"))); // NOI18N

        jPanel14.setOpaque(false);

        sLabel26.setForeground(new java.awt.Color(106, 213, 249));
        sLabel26.setText("Inventory");
        sLabel26.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        sLabel27.setForeground(new java.awt.Color(255, 255, 255));
        sLabel27.setText("Master");
        sLabel27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(sLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout mainOfflinePanelContainer1Layout = new javax.swing.GroupLayout(mainOfflinePanelContainer1);
        mainOfflinePanelContainer1.setLayout(mainOfflinePanelContainer1Layout);
        mainOfflinePanelContainer1Layout.setHorizontalGroup(
            mainOfflinePanelContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainOfflinePanelContainer1Layout.createSequentialGroup()
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(mainOfflinePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        mainOfflinePanelContainer1Layout.setVerticalGroup(
            mainOfflinePanelContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainOfflinePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(mainOfflinePanelContainer1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout heroLayer_OfflineLayout = new javax.swing.GroupLayout(heroLayer_Offline);
        heroLayer_Offline.setLayout(heroLayer_OfflineLayout);
        heroLayer_OfflineLayout.setHorizontalGroup(
            heroLayer_OfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroLayer_OfflineLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainOfflinePanelContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        heroLayer_OfflineLayout.setVerticalGroup(
            heroLayer_OfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroLayer_OfflineLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainOfflinePanelContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        heroLayeredPane.add(heroLayer_Offline, "card1");

        heroLayer_Login.setBackground(new java.awt.Color(9, 12, 16));
        heroLayer_Login.setName("Login"); // NOI18N

        loginPanelContainer1.setBackground(new java.awt.Color(24, 29, 37));
        loginPanelContainer1.setLine(new java.awt.Color(168, 232, 254));
        loginPanelContainer1.setRadius(20);
        loginPanelContainer1.setRounded(true);
        loginPanelContainer1.setPreferredSize(new java.awt.Dimension(820, 530));

        loginPanel1.setBackground(new java.awt.Color(24, 29, 37));
        loginPanel1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/Icons/wave.png"))); // NOI18N
        loginPanel1.setRadius(20);
        loginPanel1.setRounded(true);
        loginPanel1.setPreferredSize(new java.awt.Dimension(502, 530));

        sLabel1.setForeground(new java.awt.Color(255, 255, 255));
        sLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel1.setText("Login to your account");
        sLabel1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N

        sLabel2.setForeground(new java.awt.Color(255, 255, 255));
        sLabel2.setText("Username or ID");
        sLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        loginError1.setForeground(new java.awt.Color(255, 102, 102));
        loginError1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loginError1.setText("*required");
        loginError1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        loginTextFieldContainer1.setBackground(new java.awt.Color(30, 35, 46));
        loginTextFieldContainer1.setFocusedColor(new java.awt.Color(168, 232, 254));
        loginTextFieldContainer1.setRadius(20);

        loginUsernameField.setForeground(new java.awt.Color(255, 255, 255));
        loginUsernameField.setHint("Enter Username or ID");
        loginUsernameField.setHintColor(new java.awt.Color(102, 102, 102));
        loginUsernameField.setHintOffest(26);
        loginUsernameField.setCaretColor(new java.awt.Color(255, 255, 255));
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

        sLabel4.setForeground(new java.awt.Color(255, 255, 255));
        sLabel4.setText("Password");
        sLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        loginError2.setForeground(new java.awt.Color(255, 102, 102));
        loginError2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loginError2.setText("*required");
        loginError2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        loginTextFieldContainer2.setBackground(new java.awt.Color(30, 35, 46));
        loginTextFieldContainer2.setFocusedColor(new java.awt.Color(168, 232, 254));
        loginTextFieldContainer2.setRadius(20);

        loginPasswordField.setForeground(new java.awt.Color(255, 255, 255));
        loginPasswordField.setHint("Enter Password");
        loginPasswordField.setHintColor(new java.awt.Color(102, 102, 102));
        loginPasswordField.setCaretColor(new java.awt.Color(255, 255, 255));
        loginPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        sTogglePassword1.setDefaultColor(new java.awt.Color(46, 46, 60));
        sTogglePassword1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sTogglePassword1.setHoverColor(new java.awt.Color(54, 54, 72));
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
        loginError3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        loginButton.setDefaultColor(new java.awt.Color(44, 44, 57));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginButton.setHoverColor(new java.awt.Color(75, 75, 90));
        loginButton.setRadius(10);
        loginButton.setText("Login");
        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        loginButton.setPreferredSize(new java.awt.Dimension(87, 38));

        javax.swing.GroupLayout loginPanel1Layout = new javax.swing.GroupLayout(loginPanel1);
        loginPanel1.setLayout(loginPanel1Layout);
        loginPanel1Layout.setHorizontalGroup(
            loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(loginError3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanel1Layout.createSequentialGroup()
                        .addGroup(loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(loginPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(loginTextFieldContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(sLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loginTextFieldContainer2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(loginPanel1Layout.createSequentialGroup()
                                        .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                                        .addComponent(loginError1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(loginPanel1Layout.createSequentialGroup()
                                        .addComponent(sLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(loginError2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)))
                        .addGap(60, 60, 60))))
        );
        loginPanel1Layout.setVerticalGroup(
            loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginError1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(loginTextFieldContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(loginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        jPanel1.setOpaque(false);
        jPanel1.setRequestFocusEnabled(false);

        sLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/main2.png"))); // NOI18N

        jPanel2.setOpaque(false);

        sLabel8.setForeground(new java.awt.Color(106, 213, 249));
        sLabel8.setText("Inventory");
        sLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        sLabel9.setForeground(new java.awt.Color(255, 255, 255));
        sLabel9.setText("Master");
        sLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(sLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout loginPanelContainer1Layout = new javax.swing.GroupLayout(loginPanelContainer1);
        loginPanelContainer1.setLayout(loginPanelContainer1Layout);
        loginPanelContainer1Layout.setHorizontalGroup(
            loginPanelContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelContainer1Layout.createSequentialGroup()
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(loginPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        loginPanelContainer1Layout.setVerticalGroup(
            loginPanelContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelContainer1Layout.createSequentialGroup()
                .addComponent(loginPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelContainer1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout heroLayer_LoginLayout = new javax.swing.GroupLayout(heroLayer_Login);
        heroLayer_Login.setLayout(heroLayer_LoginLayout);
        heroLayer_LoginLayout.setHorizontalGroup(
            heroLayer_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroLayer_LoginLayout.createSequentialGroup()
                .addGap(0, 363, Short.MAX_VALUE)
                .addComponent(loginPanelContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 363, Short.MAX_VALUE))
        );
        heroLayer_LoginLayout.setVerticalGroup(
            heroLayer_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroLayer_LoginLayout.createSequentialGroup()
                .addGap(0, 189, Short.MAX_VALUE)
                .addComponent(loginPanelContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 189, Short.MAX_VALUE))
        );

        heroLayeredPane.add(heroLayer_Login, "card2");

        heroMenuPanel.setBackground(new java.awt.Color(24, 29, 37));
        heroMenuPanel.setBorderColor(new java.awt.Color(30, 36, 46));
        heroMenuPanel.setBorderLine(2);

        sLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/main.png"))); // NOI18N
        sLabel5.setPreferredSize(new java.awt.Dimension(35, 33));

        sLabel6.setForeground(new java.awt.Color(106, 213, 249));
        sLabel6.setText("Inventory");
        sLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        sLabel10.setForeground(new java.awt.Color(255, 255, 255));
        sLabel10.setText("Master");
        sLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        sLabel11.setForeground(new java.awt.Color(153, 153, 153));
        sLabel11.setText("Point Of Sale System");
        sLabel11.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N

        sPanel1.setBackground(new java.awt.Color(36, 43, 55));
        sPanel1.setPreferredSize(new java.awt.Dimension(257, 2));

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sScrollPane1.setBackground(new java.awt.Color(24, 29, 37));
        sScrollPane1.setHoverColor(new java.awt.Color(34, 41, 52));
        sScrollPane1.setScrollbarBackgroundColor(new java.awt.Color(24, 29, 37));
        sScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sScrollPane1Container.setBackground(new java.awt.Color(24, 29, 37));
        sScrollPane1Container.setPreferredSize(new java.awt.Dimension(237, 526));

        sScrollPane1Wrapper.setBackground(new java.awt.Color(24, 29, 37));

        sLabel28.setBackground(new java.awt.Color(24, 29, 37));
        sLabel28.setForeground(new java.awt.Color(153, 166, 188));
        sLabel28.setText("Terminal");
        sLabel28.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        sideBarButton2.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton2.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton2.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemOutActive.png"))); // NOI18N
        sideBarButton2.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton2.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton2.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton2.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton2.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemOut.png"))); // NOI18N
        sideBarButton2.setRadius(25);
        sideBarButton2.setText("Item Out");

        sLabel14.setBackground(new java.awt.Color(24, 29, 37));
        sLabel14.setForeground(new java.awt.Color(153, 166, 188));
        sLabel14.setText("Inventory");
        sLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        sideBarButton3.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton3.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton3.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemDetailsActive.png"))); // NOI18N
        sideBarButton3.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton3.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton3.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton3.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton3.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemDetails.png"))); // NOI18N
        sideBarButton3.setRadius(25);
        sideBarButton3.setText("Item Details");

        sideBarButton4.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton4.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton4.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemPacksActive.png"))); // NOI18N
        sideBarButton4.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton4.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton4.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton4.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton4.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemPacks.png"))); // NOI18N
        sideBarButton4.setRadius(25);
        sideBarButton4.setText("Item Packs");

        sideBarButton5.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton5.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton5.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/stockActive.png"))); // NOI18N
        sideBarButton5.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton5.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton5.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton5.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton5.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/stock.png"))); // NOI18N
        sideBarButton5.setRadius(25);
        sideBarButton5.setText("Stock");

        sLabel20.setBackground(new java.awt.Color(24, 29, 37));
        sLabel20.setForeground(new java.awt.Color(153, 166, 188));
        sLabel20.setText("Transactions");
        sLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        sideBarButton6.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton6.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton6.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/revenueActive.png"))); // NOI18N
        sideBarButton6.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton6.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton6.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton6.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton6.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/revenue.png"))); // NOI18N
        sideBarButton6.setRadius(25);
        sideBarButton6.setText("Sales Revenue");

        sideBarButton7.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton7.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton7.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/historyActive.png"))); // NOI18N
        sideBarButton7.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton7.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton7.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton7.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton7.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/history.png"))); // NOI18N
        sideBarButton7.setRadius(25);
        sideBarButton7.setText("History");

        sLabel22.setBackground(new java.awt.Color(24, 29, 37));
        sLabel22.setForeground(new java.awt.Color(153, 166, 188));
        sLabel22.setText("Admin Controls");
        sLabel22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        sideBarButton9.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton9.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton9.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/accountsActive.png"))); // NOI18N
        sideBarButton9.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton9.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton9.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton9.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton9.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/accounts.png"))); // NOI18N
        sideBarButton9.setRadius(25);
        sideBarButton9.setText("Accounts");

        sideBarButton11.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton11.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton11.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditAccountsActive.png"))); // NOI18N
        sideBarButton11.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton11.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton11.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton11.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton11.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditAccounts.png"))); // NOI18N
        sideBarButton11.setRadius(25);
        sideBarButton11.setText("Credit Accounts");

        sideBarButton8.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton8.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton8.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditPaymentActive.PNG"))); // NOI18N
        sideBarButton8.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton8.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton8.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton8.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton8.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditPayment.PNG"))); // NOI18N
        sideBarButton8.setRadius(25);
        sideBarButton8.setText("Credit Payment");

        javax.swing.GroupLayout sScrollPane1WrapperLayout = new javax.swing.GroupLayout(sScrollPane1Wrapper);
        sScrollPane1Wrapper.setLayout(sScrollPane1WrapperLayout);
        sScrollPane1WrapperLayout.setHorizontalGroup(
            sScrollPane1WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sScrollPane1WrapperLayout.createSequentialGroup()
                .addGroup(sScrollPane1WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sideBarButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sideBarButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(sideBarButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sideBarButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sideBarButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sideBarButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sScrollPane1WrapperLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(sScrollPane1WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(sideBarButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sideBarButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sScrollPane1WrapperLayout.createSequentialGroup()
                .addComponent(sideBarButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        sScrollPane1WrapperLayout.setVerticalGroup(
            sScrollPane1WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane1WrapperLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout sScrollPane1ContainerLayout = new javax.swing.GroupLayout(sScrollPane1Container);
        sScrollPane1Container.setLayout(sScrollPane1ContainerLayout);
        sScrollPane1ContainerLayout.setHorizontalGroup(
            sScrollPane1ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane1ContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sScrollPane1Wrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        sScrollPane1ContainerLayout.setVerticalGroup(
            sScrollPane1ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane1ContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sScrollPane1Wrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sScrollPane1.setViewportView(sScrollPane1Container);

        sPanel3.setBackground(new java.awt.Color(36, 43, 55));
        sPanel3.setPreferredSize(new java.awt.Dimension(0, 2));

        javax.swing.GroupLayout sPanel3Layout = new javax.swing.GroupLayout(sPanel3);
        sPanel3.setLayout(sPanel3Layout);
        sPanel3Layout.setHorizontalGroup(
            sPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel3Layout.setVerticalGroup(
            sPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sLabel12.setBackground(new java.awt.Color(24, 29, 37));
        sLabel12.setForeground(new java.awt.Color(153, 166, 188));
        sLabel12.setText("Preferences");
        sLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        sPanel2.setBackground(new java.awt.Color(36, 43, 55));
        sPanel2.setPreferredSize(new java.awt.Dimension(257, 2));

        javax.swing.GroupLayout sPanel2Layout = new javax.swing.GroupLayout(sPanel2);
        sPanel2.setLayout(sPanel2Layout);
        sPanel2Layout.setHorizontalGroup(
            sPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel2Layout.setVerticalGroup(
            sPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        logoutButton.setDefaultColor(new java.awt.Color(32, 37, 49));
        logoutButton.setForeground(new java.awt.Color(250, 250, 250));
        logoutButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutButton.setHoverColor(new java.awt.Color(40, 48, 61));
        logoutButton.setIconSize(13);
        logoutButton.setRadius(25);
        logoutButton.setText("Logout");
        logoutButton.setPreferredSize(new java.awt.Dimension(89, 35));

        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setText("Name");
        userName.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        userRole.setForeground(new java.awt.Color(255, 255, 255));
        userRole.setText("Role");
        userRole.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        sLabelHover1.setDefaultColor(new java.awt.Color(24, 29, 37));
        sLabelHover1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabelHover1.setHoverColor(new java.awt.Color(30, 34, 46));
        sLabelHover1.setIconSize(12);
        sLabelHover1.setRadius(20);
        sLabelHover1.setScaledIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/collapse.png"))); // NOI18N
        sLabelHover1.setPreferredSize(new java.awt.Dimension(25, 25));

        sideBarButton10.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarButton10.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarButton10.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/settingsActive.png"))); // NOI18N
        sideBarButton10.setActiveLineColor(new java.awt.Color(86, 158, 240));
        sideBarButton10.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarButton10.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarButton10.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarButton10.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/settings.png"))); // NOI18N
        sideBarButton10.setRadius(25);
        sideBarButton10.setText("Settings");

        sideBarSwitch1.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarSwitch1.setForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarSwitch1.setHoverBackgroundColor(new java.awt.Color(40, 48, 61));
        sideBarSwitch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/fullscreen.png"))); // NOI18N
        sideBarSwitch1.setInactiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarSwitch1.setRadius(25);
        sideBarSwitch1.setSwitchActiveColor(new java.awt.Color(86, 158, 240));
        sideBarSwitch1.setSwitchInactiveColor(new java.awt.Color(50, 60, 76));
        sideBarSwitch1.setText("Fullscreen");

        javax.swing.GroupLayout heroMenuPanelLayout = new javax.swing.GroupLayout(heroMenuPanel);
        heroMenuPanel.setLayout(heroMenuPanelLayout);
        heroMenuPanelLayout.setHorizontalGroup(
            heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroMenuPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(sLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(heroMenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(heroMenuPanelLayout.createSequentialGroup()
                        .addComponent(sScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, heroMenuPanelLayout.createSequentialGroup()
                        .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sideBarSwitch1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sideBarButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(sPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(sPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, heroMenuPanelLayout.createSequentialGroup()
                                .addComponent(sLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(heroMenuPanelLayout.createSequentialGroup()
                                        .addComponent(sLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sLabelHover1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(heroMenuPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(userRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        heroMenuPanelLayout.setVerticalGroup(
            heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroMenuPanelLayout.createSequentialGroup()
                .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(heroMenuPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(sLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(heroMenuPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(sLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, heroMenuPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(sLabelHover1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarSwitch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(sPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(heroMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(heroMenuPanelLayout.createSequentialGroup()
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(userRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        heroMenuPanel1.setBackground(new java.awt.Color(24, 29, 37));
        heroMenuPanel1.setBorderColor(new java.awt.Color(30, 36, 46));
        heroMenuPanel1.setBorderLine(2);
        heroMenuPanel1.setPreferredSize(new java.awt.Dimension(80, 0));

        sLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/main.png"))); // NOI18N
        sLabel29.setPreferredSize(new java.awt.Dimension(35, 33));

        sPanel14.setBackground(new java.awt.Color(36, 43, 55));
        sPanel14.setPreferredSize(new java.awt.Dimension(74, 2));

        javax.swing.GroupLayout sPanel14Layout = new javax.swing.GroupLayout(sPanel14);
        sPanel14.setLayout(sPanel14Layout);
        sPanel14Layout.setHorizontalGroup(
            sPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel14Layout.setVerticalGroup(
            sPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sScrollPane2.setBackground(new java.awt.Color(24, 29, 37));
        sScrollPane2.setHoverColor(new java.awt.Color(34, 41, 52));
        sScrollPane2.setScrollbarBackgroundColor(new java.awt.Color(24, 29, 37));
        sScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sScrollPane2Container.setBackground(new java.awt.Color(24, 29, 37));
        sScrollPane2Container.setPreferredSize(new java.awt.Dimension(48, 480));

        sScrollPane2Wrapper.setBackground(new java.awt.Color(24, 29, 37));
        sScrollPane2Wrapper.setPreferredSize(new java.awt.Dimension(53, 480));

        sPanel23.setBackground(new java.awt.Color(36, 43, 55));
        sPanel23.setPreferredSize(new java.awt.Dimension(50, 2));

        javax.swing.GroupLayout sPanel23Layout = new javax.swing.GroupLayout(sPanel23);
        sPanel23.setLayout(sPanel23Layout);
        sPanel23Layout.setHorizontalGroup(
            sPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        sPanel23Layout.setVerticalGroup(
            sPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sPanel24.setBackground(new java.awt.Color(36, 43, 55));
        sPanel24.setPreferredSize(new java.awt.Dimension(50, 2));

        javax.swing.GroupLayout sPanel24Layout = new javax.swing.GroupLayout(sPanel24);
        sPanel24.setLayout(sPanel24Layout);
        sPanel24Layout.setHorizontalGroup(
            sPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel24Layout.setVerticalGroup(
            sPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sPanel27.setBackground(new java.awt.Color(36, 43, 55));
        sPanel27.setPreferredSize(new java.awt.Dimension(50, 2));

        javax.swing.GroupLayout sPanel27Layout = new javax.swing.GroupLayout(sPanel27);
        sPanel27.setLayout(sPanel27Layout);
        sPanel27Layout.setHorizontalGroup(
            sPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        sPanel27Layout.setVerticalGroup(
            sPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sideBarMinButton2.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton2.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton2.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemOutActive.png"))); // NOI18N
        sideBarMinButton2.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton2.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton2.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton2.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemOut.png"))); // NOI18N
        sideBarMinButton2.setRadius(25);
        sideBarMinButton2.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton3.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton3.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton3.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemDetailsActive.png"))); // NOI18N
        sideBarMinButton3.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton3.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton3.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton3.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemDetails.png"))); // NOI18N
        sideBarMinButton3.setRadius(25);
        sideBarMinButton3.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton4.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton4.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton4.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemPacksActive.png"))); // NOI18N
        sideBarMinButton4.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton4.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton4.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton4.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/itemPacks.png"))); // NOI18N
        sideBarMinButton4.setRadius(25);
        sideBarMinButton4.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton5.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton5.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton5.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/stockActive.png"))); // NOI18N
        sideBarMinButton5.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton5.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton5.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton5.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/stock.png"))); // NOI18N
        sideBarMinButton5.setRadius(25);
        sideBarMinButton5.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton6.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton6.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton6.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/revenueActive.png"))); // NOI18N
        sideBarMinButton6.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton6.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton6.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton6.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/revenue.png"))); // NOI18N
        sideBarMinButton6.setRadius(25);
        sideBarMinButton6.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton7.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton7.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton7.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/historyActive.png"))); // NOI18N
        sideBarMinButton7.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton7.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton7.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton7.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/history.png"))); // NOI18N
        sideBarMinButton7.setRadius(25);
        sideBarMinButton7.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton9.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton9.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton9.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/accountsActive.png"))); // NOI18N
        sideBarMinButton9.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton9.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton9.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton9.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/accounts.png"))); // NOI18N
        sideBarMinButton9.setRadius(25);
        sideBarMinButton9.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton11.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton11.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton11.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditAccountsActive.png"))); // NOI18N
        sideBarMinButton11.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton11.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton11.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton11.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditAccounts.png"))); // NOI18N
        sideBarMinButton11.setRadius(25);
        sideBarMinButton11.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinButton8.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton8.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton8.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditPaymentActive.PNG"))); // NOI18N
        sideBarMinButton8.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton8.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton8.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton8.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/creditPayment.PNG"))); // NOI18N
        sideBarMinButton8.setRadius(25);
        sideBarMinButton8.setPreferredSize(new java.awt.Dimension(50, 40));

        javax.swing.GroupLayout sScrollPane2WrapperLayout = new javax.swing.GroupLayout(sScrollPane2Wrapper);
        sScrollPane2Wrapper.setLayout(sScrollPane2WrapperLayout);
        sScrollPane2WrapperLayout.setHorizontalGroup(
            sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane2WrapperLayout.createSequentialGroup()
                .addGroup(sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(sideBarMinButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(sideBarMinButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sideBarMinButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sideBarMinButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sideBarMinButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sideBarMinButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sideBarMinButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sideBarMinButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sideBarMinButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
        sScrollPane2WrapperLayout.setVerticalGroup(
            sScrollPane2WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane2WrapperLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sideBarMinButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarMinButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sideBarMinButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarMinButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarMinButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(sPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sideBarMinButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarMinButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(sPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sideBarMinButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarMinButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sScrollPane2ContainerLayout = new javax.swing.GroupLayout(sScrollPane2Container);
        sScrollPane2Container.setLayout(sScrollPane2ContainerLayout);
        sScrollPane2ContainerLayout.setHorizontalGroup(
            sScrollPane2ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane2ContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sScrollPane2Wrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        sScrollPane2ContainerLayout.setVerticalGroup(
            sScrollPane2ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sScrollPane2ContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sScrollPane2Wrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        sScrollPane2.setViewportView(sScrollPane2Container);

        sPanel21.setBackground(new java.awt.Color(36, 43, 55));
        sPanel21.setPreferredSize(new java.awt.Dimension(74, 2));

        javax.swing.GroupLayout sPanel21Layout = new javax.swing.GroupLayout(sPanel21);
        sPanel21.setLayout(sPanel21Layout);
        sPanel21Layout.setHorizontalGroup(
            sPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel21Layout.setVerticalGroup(
            sPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        sPanel20.setBackground(new java.awt.Color(36, 43, 55));
        sPanel20.setPreferredSize(new java.awt.Dimension(74, 2));

        javax.swing.GroupLayout sPanel20Layout = new javax.swing.GroupLayout(sPanel20);
        sPanel20.setLayout(sPanel20Layout);
        sPanel20Layout.setHorizontalGroup(
            sPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sPanel20Layout.setVerticalGroup(
            sPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        logoutButton1.setDefaultColor(new java.awt.Color(32, 37, 49));
        logoutButton1.setForeground(new java.awt.Color(250, 250, 250));
        logoutButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutButton1.setHoverColor(new java.awt.Color(40, 48, 61));
        logoutButton1.setIconSize(13);
        logoutButton1.setRadius(25);
        logoutButton1.setScaledIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/logout.png"))); // NOI18N
        logoutButton1.setPreferredSize(new java.awt.Dimension(50, 35));

        sLabelHover2.setDefaultColor(new java.awt.Color(24, 29, 37));
        sLabelHover2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabelHover2.setHoverColor(new java.awt.Color(30, 34, 46));
        sLabelHover2.setIconSize(12);
        sLabelHover2.setRadius(20);
        sLabelHover2.setScaledIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/expand.png"))); // NOI18N
        sLabelHover2.setPreferredSize(new java.awt.Dimension(25, 25));

        sideBarMinButton10.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinButton10.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinButton10.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/settingsActive.png"))); // NOI18N
        sideBarMinButton10.setHoverBackgroundColor(new java.awt.Color(30, 34, 46));
        sideBarMinButton10.setInactiveBackgroundColor(new java.awt.Color(24, 29, 37));
        sideBarMinButton10.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinButton10.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/settings.png"))); // NOI18N
        sideBarMinButton10.setRadius(25);
        sideBarMinButton10.setPreferredSize(new java.awt.Dimension(50, 40));

        sideBarMinSwitch1.setActiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinSwitch1.setActiveForegroundColor(new java.awt.Color(86, 158, 240));
        sideBarMinSwitch1.setActiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/fullscreenActive.png"))); // NOI18N
        sideBarMinSwitch1.setHoverBackgroundColor(new java.awt.Color(40, 48, 61));
        sideBarMinSwitch1.setIconSize(15);
        sideBarMinSwitch1.setInactiveBackgroundColor(new java.awt.Color(32, 37, 49));
        sideBarMinSwitch1.setInactiveForegroundColor(new java.awt.Color(250, 250, 250));
        sideBarMinSwitch1.setInactiveIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sidebar/fullscreen.png"))); // NOI18N
        sideBarMinSwitch1.setRadius(25);
        sideBarMinSwitch1.setPreferredSize(new java.awt.Dimension(50, 40));

        javax.swing.GroupLayout heroMenuPanel1Layout = new javax.swing.GroupLayout(heroMenuPanel1);
        heroMenuPanel1.setLayout(heroMenuPanel1Layout);
        heroMenuPanel1Layout.setHorizontalGroup(
            heroMenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroMenuPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(heroMenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(heroMenuPanel1Layout.createSequentialGroup()
                        .addComponent(sScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, heroMenuPanel1Layout.createSequentialGroup()
                        .addGroup(heroMenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sideBarMinSwitch1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sideBarMinButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sLabelHover2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(sPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(sPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(logoutButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))))
        );
        heroMenuPanel1Layout.setVerticalGroup(
            heroMenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heroMenuPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(sLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sLabelHover2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sideBarMinButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarMinSwitch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(logoutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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
                .addComponent(heroLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1546, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heroLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
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
    public FrameSystem.HeroGroup.Components.HeroLayer heroLayer_Offline;
    private javax.swing.JLayeredPane heroLayeredPane;
    public FrameSystem.HeroGroup.Components.HeroMenuPanel heroMenuPanel;
    public FrameSystem.HeroGroup.Components.HeroMenuPanel heroMenuPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    public FrameSystem.SComponents.SLabelHover loginButton;
    public FrameSystem.SComponents.SLabel loginError1;
    public FrameSystem.SComponents.SLabel loginError2;
    public FrameSystem.SComponents.SLabel loginError3;
    private FrameSystem.LoginGroup.Components.LoginPanel loginPanel1;
    private FrameSystem.LoginGroup.Components.LoginPanelContainer loginPanelContainer1;
    public FrameSystem.SComponents.SPasswordField loginPasswordField;
    private FrameSystem.LoginGroup.Components.LoginTextFieldContainer loginTextFieldContainer1;
    private FrameSystem.LoginGroup.Components.LoginTextFieldContainer loginTextFieldContainer2;
    public FrameSystem.SComponents.STextField loginUsernameField;
    public FrameSystem.SComponents.SLabelHover logoutButton;
    public FrameSystem.SComponents.SLabelHover logoutButton1;
    private FrameSystem.MainOfflineGroup.Components.MainOfflinePanel mainOfflinePanel1;
    private FrameSystem.MainOfflineGroup.Components.MainOfflinePanelContainer mainOfflinePanelContainer1;
    private FrameSystem.SComponents.SLabel sLabel1;
    private FrameSystem.SComponents.SLabel sLabel10;
    private FrameSystem.SComponents.SLabel sLabel11;
    private FrameSystem.SComponents.SLabel sLabel12;
    private FrameSystem.SComponents.SLabel sLabel14;
    private FrameSystem.SComponents.SLabel sLabel2;
    private FrameSystem.SComponents.SLabel sLabel20;
    public FrameSystem.SComponents.SLabel sLabel22;
    private FrameSystem.SComponents.SLabel sLabel25;
    private FrameSystem.SComponents.SLabel sLabel26;
    private FrameSystem.SComponents.SLabel sLabel27;
    private FrameSystem.SComponents.SLabel sLabel28;
    private FrameSystem.SComponents.SLabel sLabel29;
    private FrameSystem.SComponents.SLabel sLabel3;
    private FrameSystem.SComponents.SLabel sLabel4;
    private FrameSystem.SComponents.SLabel sLabel5;
    private FrameSystem.SComponents.SLabel sLabel6;
    private FrameSystem.SComponents.SLabel sLabel7;
    private FrameSystem.SComponents.SLabel sLabel8;
    private FrameSystem.SComponents.SLabel sLabel9;
    public FrameSystem.SComponents.SLabelHover sLabelHover1;
    public FrameSystem.SComponents.SLabelHover sLabelHover2;
    private FrameSystem.SComponents.SPanel sPanel1;
    private FrameSystem.SComponents.SPanel sPanel14;
    private FrameSystem.SComponents.SPanel sPanel2;
    private FrameSystem.SComponents.SPanel sPanel20;
    private FrameSystem.SComponents.SPanel sPanel21;
    private FrameSystem.SComponents.SPanel sPanel23;
    private FrameSystem.SComponents.SPanel sPanel24;
    public FrameSystem.SComponents.SPanel sPanel27;
    private FrameSystem.SComponents.SPanel sPanel3;
    public FrameSystem.SComponents.SScrollPane sScrollPane1;
    private FrameSystem.SComponents.SPanel sScrollPane1Container;
    private FrameSystem.SComponents.SPanel sScrollPane1Wrapper;
    public FrameSystem.SComponents.SScrollPane sScrollPane2;
    private FrameSystem.SComponents.SPanel sScrollPane2Container;
    private FrameSystem.SComponents.SPanel sScrollPane2Wrapper;
    private FrameSystem.SGenericComponents.STogglePassword sTogglePassword1;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton10;
    public FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton11;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton2;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton3;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton4;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton5;
    public FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton6;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton7;
    private FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton8;
    public FrameSystem.SideBarGroup.Components.SideBarButton sideBarButton9;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton10;
    public FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton11;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton2;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton3;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton4;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton5;
    public FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton6;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton7;
    private FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton8;
    public FrameSystem.SideBarGroup.Components.SideBarMinButton sideBarMinButton9;
    public FrameSystem.SideBarGroup.Components.SideBarMinSwitch sideBarMinSwitch1;
    public FrameSystem.SideBarGroup.Components.SideBarSwitch sideBarSwitch1;
    public FrameSystem.SComponents.SLabel userName;
    public FrameSystem.SComponents.SLabel userRole;
    // End of variables declaration//GEN-END:variables

}

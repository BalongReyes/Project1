
package FrameSystem.SideBarGroup.Managers;

import DatabaseSystem.AccountsData.AccountsDataTable;
import EventSystem.Listeners.MouseClickedAdaptor;
import MainSystem.Manager;
import java.awt.event.MouseListener;

public class ManagerSideBar extends Manager{

    public static void initDefault(){
        MouseListener m = (MouseClickedAdaptor) evt -> {
            frame.toggleFullscreen();
        };
        frame.sideBarSwitch1.addMouseListener(m);
        frame.sideBarMinSwitch1.addMouseListener(m);
        
        frame.sScrollPane1.applyInnerListeners();
        frame.sScrollPane2.applyInnerListeners();
        
        frame.sLabelHover1.addMouseListener((MouseClickedAdaptor) evt -> {
            frame.heroMenuPanel.setVisible(false);
            frame.heroMenuPanel1.setVisible(true);
        });
        
        frame.sLabelHover2.addMouseListener((MouseClickedAdaptor) evt -> {
            frame.heroMenuPanel.setVisible(true);
            frame.heroMenuPanel1.setVisible(false);
        });
    }
    
    public static void changeAccount(AccountsDataTable user){
        frame.userName.setText(user.getName());
        switch(user.getRole()){
            case 1 -> {
                frame.userRole.setText("Super Admin");
                setAdminControls(true);
            }
            case 2 -> {
                frame.userRole.setText("Admin");
                setAdminControls(true);
            }
            case 3 -> {
                frame.userRole.setText("User");
                setAdminControls(false);
            }
        }
    }
    
    private static void setAdminControls(boolean visible){
        frame.sideBarButton6.setVisible(visible);
        frame.sideBarMinButton6.setVisible(visible);
        
        frame.sLabel22.setVisible(visible);
        frame.sPanel27.setVisible(visible);
        
        frame.sideBarButton9.setVisible(visible);
        frame.sideBarMinButton9.setVisible(visible);
        frame.sideBarButton11.setVisible(visible);
        frame.sideBarMinButton11.setVisible(visible);
    }

}

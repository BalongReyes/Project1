
package FrameSystem.SideBarGroup.Managers;

import EventSystem.Listeners.MouseClickedAdaptor;
import MainSystem.Manager;
import java.awt.event.MouseListener;

public class ManagerSideBar extends Manager{

    public static void initDefault(){
        MouseListener m = (MouseClickedAdaptor) evt -> {
            frame.toggleFullscreen();
        };
    }
    

}

package MainSystem;

import FrameSystem.HeroGroup.Managers.ManagerHero;
import FrameSystem.LoginGroup.Managers.ManagerLogin;
import FrameSystem.SideBarGroup.Managers.ManagerSideBar;

public class Manager {

    protected static SFrame frame;

    public static void setDefault(SFrame frame) {
        Manager.frame = frame;

        ManagerHero.initDefault();
        ManagerLogin.initDefault();
        ManagerSideBar.initDefault();
    }

}

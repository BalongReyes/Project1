package MainSystem;

import FrameSystem.LoginGroup.Managers.ManagerLogin;

public class Manager {

    protected static SFrame frame;

    public static void setDefault(SFrame frame) {
        Manager.frame = frame;

        ManagerLogin.initDefault();
    }

}

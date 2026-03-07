package MainSystem;

import DatabaseSystem.Database;

public class Main {

    public static boolean debugDataHandlerRefresh = true;
    public static boolean coloredOutput = true;

    public static void main(String[] args) {
        Database.openConnection();

        SFrame frame = new SFrame();
        frame.setVisible(true);
        
        Manager.setDefault(frame);
        frame.initShowDefaultLayer();
        frame.setVisible(true);
    }

}

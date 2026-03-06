package MainSystem;

import DatabaseSystem.Database;

public class Main {

    public static boolean coloredOutput = true;

    public static void main(String[] args) {
        Database.openConnection();

        SFrame frame = new SFrame();
        frame.setVisible(true);
    }

}

package ConsoleSystem;

import MainSystem.Main;

public enum ConsoleColors {

    BLACK("\033[0;30m"), // BLACK
    RED("\033[0;31m"), // RED
    GREEN("\033[0;32m"), // GREEN
    YELLOW("\033[0;33m"), // YELLOW
    BLUE("\033[0;34m"), // BLUE
    PURPLE("\033[0;35m"), // PURPLE
    CYAN("\033[0;36m"), // CYAN
    WHITE("\033[0;37m"); // WHITE

    private final String string;

    ConsoleColors(String string) {
        this.string = string;
    }

    public String getString() {
        return Main.coloredOutput ? string : "";
    }

    public static String getResetString() {
        return Main.coloredOutput ? "\033[0m" : "";
    }
}

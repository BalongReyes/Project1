
package ConsoleSystem;

public class ConsoleNonStatic {

    public ConsoleNonStatic tab() {
        return Console.tab();
    }

    public ConsoleNonStatic gap() {
        return Console.gap();
    }

    public ConsoleNonStatic line() {
        return Console.line();
    }

    public ConsoleNonStatic line(ConsoleColors color) {
        return Console.line(color);
    }

    // System console output
    // -------------------------------------------------------------------------------------

    public <E> ConsoleNonStatic out(E[] arrayOutput) {
        return Console.out(arrayOutput);
    }

    public ConsoleNonStatic out(String output) {
        return Console.out(output);
    }

    public ConsoleNonStatic out(String output, boolean line) {
        return Console.out(output, line);
    }

    // System console output with color
    // --------------------------------------------------------------------------

    public <E> ConsoleNonStatic out(E[] arrayOutput, ConsoleColors color) {
        return Console.out(arrayOutput, color);
    }

    public ConsoleNonStatic out(String output, ConsoleColors color) {
        return Console.out(output, color);
    }

    public ConsoleNonStatic out(String output, ConsoleColors color, boolean line) {
        return Console.out(output, color, line);
    }

}

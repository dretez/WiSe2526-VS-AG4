package de.haw.vs.termin4.common;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final Logger instance = new Logger();

    private boolean colorMode;

    public Logger() {
        colorMode = false;
    }

    public static void setColorMode(boolean mode) {
        instance.colorMode = mode;
    }

    public static void println(String string) {
        System.out.println(string);
    }

    public static void print(String string) {
        System.out.print(string);
        System.out.flush();
    }

    public static void error(String string) {
        if (instance.colorMode)
            System.out.print(ANSI_RED);
        System.out.println(string);
        if (instance.colorMode)
            System.out.print(ANSI_RESET);
    }
}

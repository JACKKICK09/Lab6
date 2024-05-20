package NetWork;

public class Outputter {
    public static void println() {
        System.out.println();
    }


    public static void println(Object toOut) {
        System.out.println(toOut);
    }


    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }
}

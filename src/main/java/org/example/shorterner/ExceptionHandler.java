package org.example.shorterner;

/**
 * @author Denis Zolotarev
 */
public class ExceptionHandler extends Exception {
    public static void log(Exception e) {
        Helper.printMessage(e.getMessage());
    }
}

package org.example.shorterner;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Denis Zolotarev
 */
public class Helper {
    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(150, random).toString(32);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}

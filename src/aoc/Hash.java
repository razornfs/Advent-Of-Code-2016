package aoc;

import java.security.MessageDigest;

public class Hash {
    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String getMD5(String s) {
        byte[] idBytes = s.getBytes();
        byte[] encodedBytes = digest.digest(idBytes);
        return toHexString(encodedBytes);
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

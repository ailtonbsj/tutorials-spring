package io.github.ailtonbsj.relationships.mappers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public abstract class MapperUtils {

    static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error on password encryptation";
        }
    }

    static LocalDateTime dateTimeNowIfNull(LocalDateTime datetime) {
        return datetime == null ? LocalDateTime.now() : datetime;
    }

}

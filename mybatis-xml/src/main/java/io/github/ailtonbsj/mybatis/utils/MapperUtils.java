package io.github.ailtonbsj.mybatis.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public abstract class MapperUtils {

    public static String encryptPassword(String password) {
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

    public static LocalDateTime dateTimeNowIfNull(LocalDateTime datetime) {
        return datetime == null ? LocalDateTime.now() : datetime;
    }

    public static <T> T toEntity(Long id, Class<T> clazz) {
        if (id == null) return null;
        try {
            T object = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod("setId", Long.class);
            method.invoke(object, id);
            return object;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException
                | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            return null;
        }
    }

}
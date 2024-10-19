package io.github.ailtonbsj.multipledb.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class Utils {

    public static Sort directionPropsToOrders(String[] directions, String[] sortProps) {
        List<Sort.Order> orders = new ArrayList<>();
        if (directions.length == sortProps.length) {
            IntStream.range(0, directions.length).forEach(i -> {
                Sort.Direction direction = null;
                if (directions[i].equals("asc"))
                    direction = Sort.Direction.ASC;
                else if (directions[i].equals("desc"))
                    direction = Sort.Direction.DESC;
                orders.add(new Sort.Order(direction, sortProps[i]));
            });
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Directions and properties with different length");
        return Sort.by(orders);
    }

    
    public static String idToString(Object object) {
        StringJoiner joiner = new StringJoiner("_");
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value;
                if(field.getType() == LocalDateTime.class){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    value = ((LocalDateTime) field.get(object)).format(dtf);
                } else {
                    value = field.get(object);
                }
                joiner.add(value.toString());
            }
            return joiner.toString();
        } catch (IllegalAccessException e) {
            throw new IdentityConversionException("Cannot convert JOPO to String a token");
        }
    }

    public static <T> T stringToId(String token, Class<T> clazz) {
        try {
            String[] strings = token.split("_");
            Field[] fields = clazz.getDeclaredFields();
            T obj = clazz.getConstructor().newInstance();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if (field.getType() == Integer.class) {
                    field.set(obj, Integer.valueOf(strings[i]));
                } else if(field.getType() == LocalDate.class) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    field.set(obj, LocalDate.parse(strings[i], dtf));
                } else if(field.getType() == LocalDateTime.class) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    field.set(obj, LocalDateTime.parse(strings[i], dtf));
                } else {
                    field.set(obj, strings[i]);
                }
            }
            return obj;
        } catch (Exception e) {
            throw new IdentityConversionException("Cannot convert String token to POJO IdClass");
        }
    }


}

package io.github.ailtonbsj.relationships;

import java.util.ArrayList;
import java.util.List;
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

}

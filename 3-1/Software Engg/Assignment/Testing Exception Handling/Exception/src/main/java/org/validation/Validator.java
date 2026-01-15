package org.validation;

import org.validation.exception.*;
import org.validation.model.Order;

import java.util.Set;

public class Validator {

    private static final Set<String> VALID_DEPARTMENTS =
            Set.of("ICT", "CSE", "BGE", "ESRM", "BMB");

    public static void validate(Order order)
            throws AgeNotValidException, DepartmentNotValidException {

        if (order.getAge() < 18 || order.getAge() > 60) {
            throw new AgeNotValidException("Age must be between 18 and 60");
        }

        if (!VALID_DEPARTMENTS.contains(order.getDepartment().toUpperCase())) {
            throw new DepartmentNotValidException(
                    "Department must be one of " + VALID_DEPARTMENTS
            );
        }
    }
}

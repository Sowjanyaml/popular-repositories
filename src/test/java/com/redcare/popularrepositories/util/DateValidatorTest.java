package com.redcare.popularrepositories.util;

import com.redcare.popularrepositories.exception.DateInFutureException;
import com.redcare.popularrepositories.exception.InvalidDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DateValidatorTest {

    @Test
    void dateValidation_ValidDate() {

        String validDate = "2022-02-08";
        assertDoesNotThrow(() -> DateValidator.dateValidation(validDate));
    }

    @Test
    void dateValidation_InvalidDateFormat() {
        String invalidDate = "2022/02/08";

        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> DateValidator.dateValidation(invalidDate));
        assertEquals("Invalid date format: " + invalidDate + ". Please ensure the date is in the \"yyyy-mm-dd\" format.", exception.getMessage());
    }

    @Test
    void dateValidation_DateInFuture() {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String futureDate = tomorrow.toString();
        DateInFutureException exception = assertThrows(DateInFutureException.class, () -> DateValidator.dateValidation(futureDate));
        assertEquals("Error: The date " + futureDate + " is invalid. Please provide a date that is not in the future: " + futureDate + ".", exception.getMessage());
    }
}

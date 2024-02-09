package com.redcare.popularrepositories.util;

import com.redcare.popularrepositories.exception.DateInFutureException;
import com.redcare.popularrepositories.exception.InvalidDateException;
import com.redcare.popularrepositories.exception.InvalidLimitPerPage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    void limitValidation_ValidLimit() {
        int limit = 50;
        assertDoesNotThrow(() -> Validator.validateLimit(limit));
    }
    @Test
    void limitValidation_InvalidValidLimit() {
        int limit = 0;
        InvalidLimitPerPage invalidLimitPerPage = assertThrows(InvalidLimitPerPage.class, () -> Validator.validateLimit(limit));
        assertEquals("The value 0 is not a valid page limit. Please enter a valid limit per page.", invalidLimitPerPage.getMessage());
    }
    @Test
    void dateValidation_ValidDate() {
        String validDate = "2022-02-08";
        assertDoesNotThrow(() -> Validator.dateValidation(validDate));
    }

    @Test
    void dateValidation_InvalidDateFormat() {
        String invalidDate = "2022/02/08";

        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> Validator.dateValidation(invalidDate));
        assertEquals("Invalid date format: " + invalidDate + ". Please ensure the date is in the \"yyyy-mm-dd\" format.", exception.getMessage());
    }

    @Test
    void dateValidation_DateInFuture() {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String futureDate = tomorrow.toString();
        DateInFutureException exception = assertThrows(DateInFutureException.class, () -> Validator.dateValidation(futureDate));
        assertEquals("Error: The date " + futureDate + " is invalid. Please provide a date that is not in the future: " + futureDate + ".", exception.getMessage());
    }
}

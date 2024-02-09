package com.redcare.popularrepositories.util;

import com.redcare.popularrepositories.exception.DateInFutureException;
import com.redcare.popularrepositories.exception.InvalidDateException;
import com.redcare.popularrepositories.exception.InvalidLimitPerPage;
import org.apache.commons.validator.GenericValidator;

import java.time.LocalDate;

public class Validator {

    public static void validate(int limit, String fromDate) {
        validateLimit(limit);
        dateValidation(fromDate);
    }

    public static void validateLimit(int limit) {
        if (limit <= 0) {
            throw new InvalidLimitPerPage("The value " + limit + " is not a valid page limit. Please enter a valid limit per page.");
        }
    }

    public static void dateValidation(String fromDate) {

        if (!GenericValidator.isDate(fromDate, "yyyy-mm-dd", true)) {
            throw new InvalidDateException("Invalid date format: " + fromDate + ". Please ensure the date is in the \"yyyy-mm-dd\" format.");
        }
        LocalDate date = LocalDate.parse(fromDate);
        if (date.isAfter(LocalDate.now())) {
            throw new DateInFutureException("Error: The date " + fromDate + " is invalid. Please provide a date that is not in the future: " + fromDate + ".");
        }
    }
}

package com.redcare.popularrepositories.util;

import com.redcare.popularrepositories.exception.DateInFutureException;
import com.redcare.popularrepositories.exception.InvalidDateException;
import org.apache.commons.validator.GenericValidator;

import java.time.LocalDate;

public class DateValidator {
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

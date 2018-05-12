package fetcher.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class UserFileDataValidator implements FileDataValidator<String[]> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String[] data) {
        return hasRequiredColumnValues(data) && hasValidDateFormat(data);
    }

    private boolean hasRequiredColumnValues(String[] columns) {
        return columns.length >= 3;
    }

    private boolean hasValidDateFormat(String[] columns) {
        try {
            LocalDate.parse(columns[2], formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

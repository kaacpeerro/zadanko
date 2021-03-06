package fetcher.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Optional;

class UserFileDataValidator implements FileDataValidator<String[]> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String[] data) {
        return Optional.ofNullable(data)
                .filter(this::hasRequiredColumns)
                .filter(this::hasValidDateFormat)
                .isPresent();
    }

    private boolean hasRequiredColumns(String[] columns) {
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
